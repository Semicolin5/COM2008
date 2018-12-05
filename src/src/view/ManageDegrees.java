package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import src.controller.Controller;
import src.objects.*;

/**
 * ManageDegrees.java
 * Only accessible for Administrators (privilege level 4)
 * Extension of form, creates a functional GUI form which allows
 * user to view & delete existing degrees, as well as viewing lead/non-lead departments related to a degree.
 */
public class ManageDegrees extends Form {
    private JPanel panel1;
    private JList degreeList;
    private JButton relatedDepartmentsButton;
    private JButton deleteDegreeButton;
    private JButton createDegreeButton;
    private JButton backButton;
    private JTable associateTable;
    private JScrollPane scroll1;
    private DefaultListModel<String> degreeModel;
    private DefaultTableModel associateModel;

    /**
     * Constructor sets the columns of the empty JTable, and loads degree codes into the degreeList JList.
     * Also adds ActionListeners to the different buttons on the form.
     *
     * @param frame - JFrame with properties defined in the GUIFrame class.
     */
    public ManageDegrees(GUIFrame frame) {
        super(frame);
        setJPanel(panel1);
        frame.setTitle("Manage Degrees");

        degreeModel = new DefaultListModel<>();
        associateModel = new DefaultTableModel();

        //Add columns to JTable
        associateModel.addColumn("Department Code");
        associateModel.addColumn("Department Name");
        associateModel.addColumn("Lead Status");

        //Add all existing degrees to the degreeList.
        for (Degree degree : Controller.getDegrees()) {
            degreeModel.addElement(degree.getDegreeCode());
        }

        degreeList.setLayoutOrientation(JList.VERTICAL);
        degreeList.setVisibleRowCount(10);
        degreeList.setModel(degreeModel);
        associateTable.setModel(associateModel);

        createDegreeButton.addActionListener(new CreateDegreeHandler());
        deleteDegreeButton.addActionListener(new DeleteDegreesHandler());
        relatedDepartmentsButton.addActionListener(new AssociatedHandler());
        backButton.addActionListener(new BackHandler());
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
        panel1.setLayout(new GridLayoutManager(6, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        degreeList = new JList();
        scrollPane1.setViewportView(degreeList);
        scroll1 = new JScrollPane();
        panel1.add(scroll1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        associateTable = new JTable();
        scroll1.setViewportView(associateTable);
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        relatedDepartmentsButton = new JButton();
        relatedDepartmentsButton.setText("Load Associated Departments");
        panel1.add(relatedDepartmentsButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteDegreeButton = new JButton();
        deleteDegreeButton.setText("Delete Degree");
        panel1.add(deleteDegreeButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createDegreeButton = new JButton();
        createDegreeButton.setText("Create Degree");
        panel1.add(createDegreeButton, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("Back");
        panel1.add(backButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /**
     * ActionListener class which takes the user back to the CreateDegree form.
     */
    public class CreateDegreeHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new CreateDegree(getFrame()).getJPanel());
        }
    }

    /**
     * DeleteDegreesHandler calls the removeDegree methods from the controller to work out if the Admin is allowed
     * to delete the degree. In order for a degree to be allowed to be deleted:
     * 1) no students should be taking it
     * 2) no modules should be associated with the degree
     */
    private class DeleteDegreesHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            for (Object code : degreeList.getSelectedValuesList()) {
                if (!Controller.removeDegree((String) code)) {
                    JOptionPane.showMessageDialog(getFrame(), "The current degree is not allowed to be deleted until " +
                            "there are no students or modules affiliated with the degree");
                }
            }
            changeJPanel(new ManageDegrees(getFrame()).getJPanel());
        }
    }

    /**
     * AssociateHandler calls methods from the controller which check the user's privilege level
     * and assuming it is suitable, will load the list of departments which the selected degree
     * is related to.
     */
    private class AssociatedHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            //Reset JTable
            associateModel.setRowCount(0);

            //Check its not null
            if (degreeList.getSelectedValue() != null) {
                String degreeCode = degreeList.getSelectedValue().toString();
                for (Degree degree : Controller.getDegrees()) {
                    if (degreeCode.equals(degree.getDegreeCode())) {
                        Department lead = degree.getLeadDepartment();
                        associateModel.addRow(new Object[]{lead.getCode(), lead.getName(), "Lead"});
                        if (degree.getNonLeadDepartments() != null) {
                            for (Department d : degree.getNonLeadDepartments()) {
                                associateModel.addRow(new Object[]{d.getCode(), d.getName(), "Non-Lead"});
                            }
                        }
                    }
                }
            } else {
                JOptionPane.showMessageDialog(getFrame(), "Please select a degree to display.");
            }
        }
    }

    /**
     * ActionListener class which takes the user back to the Welcome form.
     */
    private class BackHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new Welcome(getFrame()).getJPanel());
        }
    }
}

