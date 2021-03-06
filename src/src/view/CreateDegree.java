package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import src.objects.Department;
import src.controller.*;

/**
 * CreateDegree.java
 * Only accessible for Administrators (privilege level 4)
 * Extension of form, creates a functional GUI form which allows
 * user to create a new degree as an entry in the degree table.
 */
public class CreateDegree extends Form {
    private JPanel panel1;
    private JTextField degreeCode;
    private JTextField degreeName;
    private JButton createDegree;
    private JComboBox mastersCombo;
    private JComboBox yearIndustryCombo;
    private JComboBox departmentCombo;
    private JComboBox leadCombo;
    private JButton linkDepButton;
    private JButton cancelButton;
    private JTable departmentTable;
    private DefaultTableModel departmentsModel;
    private ArrayList<String[]> departmentLinker = new ArrayList<>();
    private String errorMessage = "";

    /**
     * Set default JFrame sizes & add Event Listener
     *
     * @param frame - JFrame with properties set in the GUIFrame class.
     */
    public CreateDegree(GUIFrame frame) {
        super(frame);
        setJPanel(panel1);
        frame.setTitle("Create Degree Screen");

        departmentsModel = new DefaultTableModel();
        departmentsModel.addColumn("Department Code");
        departmentsModel.addColumn("Lead Status");
        departmentTable.setModel(departmentsModel);

        //loops through degrees in database and adds all of their codes to the JComboBox.
        for (Department department : Controller.getDepartments()) {
            departmentCombo.addItem(department.getCode());
        }

        linkDepButton.addActionListener(new LinkHandler());
        createDegree.addActionListener(new CreateDegreeHandler());
        cancelButton.addActionListener(new cancelHandler());
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(11, 6, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Degree Code");
        panel1.add(label1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Degree Name");
        panel1.add(label2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        degreeCode = new JTextField();
        panel1.add(degreeCode, new GridConstraints(2, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        degreeName = new JTextField();
        panel1.add(degreeName, new GridConstraints(3, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Bachelors or Masters?");
        panel1.add(label3, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        mastersCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Bachelors");
        defaultComboBoxModel1.addElement("Masters");
        mastersCombo.setModel(defaultComboBoxModel1);
        panel1.add(mastersCombo, new GridConstraints(4, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Includes Placement Year?");
        panel1.add(label4, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createDegree = new JButton();
        createDegree.setText("Create Degree");
        panel1.add(createDegree, new GridConstraints(9, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(10, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        yearIndustryCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Exclues Year In Industry");
        defaultComboBoxModel2.addElement("Includes Year In Industry");
        yearIndustryCombo.setModel(defaultComboBoxModel2);
        panel1.add(yearIndustryCombo, new GridConstraints(5, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(6, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Add Related Department");
        panel1.add(label5, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        departmentCombo = new JComboBox();
        panel1.add(departmentCombo, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        leadCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel3 = new DefaultComboBoxModel();
        defaultComboBoxModel3.addElement("Lead");
        defaultComboBoxModel3.addElement("Not Lead");
        leadCombo.setModel(defaultComboBoxModel3);
        panel1.add(leadCombo, new GridConstraints(8, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        linkDepButton = new JButton();
        linkDepButton.setText("Link Department");
        panel1.add(linkDepButton, new GridConstraints(8, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Department Code");
        panel1.add(label6, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Lead Status");
        panel1.add(label7, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel1.add(cancelButton, new GridConstraints(9, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel1.add(spacer5, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("List of Related Departments");
        panel1.add(label8, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(1, 4, 6, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, 1, null, null, null, 0, false));
        departmentTable = new JTable();
        scrollPane1.setViewportView(departmentTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /**
     * This ActionListener adds the selected JCombo values to the JTable - if the checks are passed.
     */

    /**
     * This ActionListener will add related departments to the linker table, but only after running checks.
     * Only one lead department can be assigned to each degree, and each department can be assigned only once to a given degree.
     */
    public class LinkHandler implements ActionListener {
        private boolean hasLead = false;
        private ArrayList<String> storedDeps = new ArrayList<>();

        public void actionPerformed(ActionEvent e) {
            String depCode = departmentCombo.getSelectedItem().toString();
            String leadStatus = leadCombo.getSelectedItem().toString();
            String[] depLead = {depCode, leadStatus};

            if (leadStatus.equals("Lead") && hasLead) {
                errorMessage = "A lead department already exists for this module.";
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            } else if (storedDeps.contains(depCode)) {
                errorMessage = "This department is already linked to this module.";
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            } else if (leadStatus.equals("Lead") && !hasLead) {
                hasLead = true;
                departmentLinker.add(depLead);
                storedDeps.add(depCode);
                departmentsModel.addRow(new Object[]{departmentCombo.getSelectedItem().toString(), leadCombo.getSelectedItem().toString()});
                errorMessage = "";
            } else {
                departmentLinker.add(depLead);
                storedDeps.add(depCode);
                departmentsModel.addRow(new Object[]{departmentCombo.getSelectedItem().toString(), leadCombo.getSelectedItem().toString()});
                errorMessage = "";
            }
        }
    }

    /**
     * This function calls checks, then providing they are passed
     * it will add a row to the degree table, and then loop through the JList,
     * creating a row in the degree_department table for every entry in the JList.
     */
    public class CreateDegreeHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String degCode = degreeCode.getText();
            String degName = degreeName.getText();
            boolean masters = mastersCombo.getSelectedItem().toString().equals("Masters");
            boolean yearInd = yearIndustryCombo.getSelectedItem().toString().equals("Includes Year In Industry");
            if (yearInd) {
            	degName = degName + " - With a year in industry.";
            }
            
            //Check the inputs
            errorMessage = Controller.checkInputDegree(degCode, degName, masters, Main.getPriv());

            if (errorMessage.equals("Accepted")) {
                //Now we can store everything
                Controller.saveDegree(degCode, degName, masters, yearInd);
                //Now lets save the stored department links
                for (int i = 0; i < departmentLinker.size(); i++) {
                    Controller.saveDepartmentAssociation(degCode, departmentLinker.get(i)[0], departmentLinker.get(i)[1].equals("Lead"));
                }
                changeJPanel(new ManageDegrees(getFrame()).getJPanel());
            } else {
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            }
        }
    }

    /**
     * ActionListener which takes the user back to the ManageDegrees form.
     */
    private class cancelHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new ManageDegrees(getFrame()).getJPanel());
        }
    }
}
