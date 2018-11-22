package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import src.objects.Degree;
import src.controller.Controller;

/**
 * CreateModdule.java
 * Only accessible for Administrators (privilege level 4)
 * Extension of form, creates a functional GUI form which allows
 * user to create a new Module as an entry in the module table, and also
 * to add any degrees/levels which the module is approved for and its core status,
 * which are added as rows in the core table.
 */
public class CreateModule extends Form {
    private JPanel myPanel;
    private JTextField moduleCode;
    private JTextField moduleName;
    private JTextField moduleCredits;
    private JComboBox degreeCombo;
    private JComboBox levelCombo;
    private JComboBox coreCombo;
    private JButton linkButton;
    private JList moduleList;
    private JButton createModuleButton;
    private JComboBox semesterCombo;
    private JButton cancelButton;
    private DefaultListModel<String> departmentsModel;

    /**
     * Set default JFrame sizes & add Event Listeners & Item Listener.
     * Also adds values to the different JComboBoxes.
     *
     * @param frame - JFrame with properties set in the GUIFrame class.
     */
    public CreateModule(GUIFrame frame) {
        super(frame);

        setBackButton(cancelButton);
        setBackButtonPanel(new ManageModules(getFrame()).getJPanel());

        setJPanel(myPanel);
        frame.setTitle("Create Module Screen");

        degreeCombo.addItem("");
        departmentsModel = new DefaultListModel<>();
        moduleList.setModel(departmentsModel);
        moduleList.setVisibleRowCount(10);
        //loops through degrees in database and adds all of their codes to the JComboBox.
        for (Degree degree : Controller.getDegrees()) {
            degreeCombo.addItem(degree.getDegreeCode());
        }

        /*
         * Item Listener which updates the levelsCombo depending on which value
         * is selected in  the degreeCombo JComboBox.
         */
        degreeCombo.addItemListener(e -> {
            levelCombo.removeAllItems();
            for (Degree degree : Controller.getDegrees()) {
                if (degree.getDegreeCode().equals(e.getItem()) && degree.getDegreeType()) {
                    for (int i = 1; i < 5; i++)
                        levelCombo.addItem(i);
                } else if (degree.getDegreeCode().equals(e.getItem())) {
                    for (int i = 1; i < 4; i++)
                        levelCombo.addItem(i);
                }
            }
        });
        //TODO: Can we call the item listener in the constructor, so that the...
        //TODO: Continued: ...second comboBox automatically loads based on the value of the first?
        linkButton.addActionListener(new LinkHandler());
        createModuleButton.addActionListener(new CreateModuleHandler());
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
        myPanel = new JPanel();
        myPanel.setLayout(new GridLayoutManager(13, 10, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Module Code");
        myPanel.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Module Name");
        myPanel.add(label2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Module Credits");
        myPanel.add(label3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        moduleCode = new JTextField();
        moduleCode.setText("");
        myPanel.add(moduleCode, new GridConstraints(1, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        moduleName = new JTextField();
        myPanel.add(moduleName, new GridConstraints(2, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        moduleCredits = new JTextField();
        myPanel.add(moduleCredits, new GridConstraints(3, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("List of Related Degrees");
        myPanel.add(label4, new GridConstraints(8, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        myPanel.add(spacer1, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        myPanel.add(spacer2, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Module Details");
        myPanel.add(label5, new GridConstraints(0, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        myPanel.add(spacer3, new GridConstraints(11, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        myPanel.add(spacer4, new GridConstraints(10, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        myPanel.add(scrollPane1, new GridConstraints(9, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        moduleList = new JList();
        scrollPane1.setViewportView(moduleList);
        final JLabel label6 = new JLabel();
        label6.setText("Degree Code");
        myPanel.add(label6, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Degree Level");
        myPanel.add(label7, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        myPanel.add(spacer5, new GridConstraints(7, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Core Status");
        myPanel.add(label8, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        degreeCombo = new JComboBox();
        myPanel.add(degreeCombo, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        levelCombo = new JComboBox();
        myPanel.add(levelCombo, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        coreCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Core");
        defaultComboBoxModel1.addElement("Not Core");
        coreCombo.setModel(defaultComboBoxModel1);
        myPanel.add(coreCombo, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        linkButton = new JButton();
        linkButton.setText("Link to Degree Level");
        myPanel.add(linkButton, new GridConstraints(6, 5, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Add Related Degree");
        myPanel.add(label9, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createModuleButton = new JButton();
        createModuleButton.setText("Create Module");
        myPanel.add(createModuleButton, new GridConstraints(11, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Semester");
        myPanel.add(label10, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        semesterCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
        defaultComboBoxModel2.addElement("Autumn");
        defaultComboBoxModel2.addElement("Spring");
        defaultComboBoxModel2.addElement("Summer");
        defaultComboBoxModel2.addElement("Year-long");
        semesterCombo.setModel(defaultComboBoxModel2);
        myPanel.add(semesterCombo, new GridConstraints(4, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        myPanel.add(cancelButton, new GridConstraints(11, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return myPanel;
    }

    /**
     * LinkHandler checks whether the data currently in the three JComboBoxes is appropriate
     * Before adding the info to the JList.
     */
    public class LinkHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: Run length/form/duplicate checks here.
            //TODO: Check that the module code is not already present in the JList.
            //TODO: Check that the degree & module choice aren't forming a duplicate primary key.
            //TODO: Colin, do we need length/form checks here? If we have correct checks on the degree data we are saving.
            //TODO: 't all data in here already be correct? Other than the potential for the first JComboBox being blank.
            //TODO: idea is that we know that data is correct before it is added to the JList.
            String details = degreeCombo.getSelectedItem().toString() + " " +
                    levelCombo.getSelectedItem().toString() + " " + coreCombo.getSelectedItem().toString();
            departmentsModel.addElement(details);
        }
    }

    /**
     * Action Handler which calls functions which add a row to the module table.
     * Also adds a row to the core table for each degree level which this module is approved for.
     */
    public class CreateModuleHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            //TODO: Check that text entered into the first three textboxes meets format/length/duplication checks before runnimg this.
            //We should already know that data in the JList is in the correct format here, as we checked it before adding to the JList.
            ListModel model = moduleList.getModel();
            //Need to put in checks here
            //Controller.saveModule(moduleCode.getText(), moduleName.getText(), Integer.parseInt(moduleCredits.getText()), semesterCombo.getSelectedIndex());
            for (int i = 0; i < model.getSize(); i++) {
                Object o = model.getElementAt(i);
                String arr[] = o.toString().split(" ");
                String degreeCode = arr[0];
                int level = Integer.parseInt(arr[1]);
                if (arr[2].equals("Core")) {
                    Controller.saveModuleAssociation(moduleCode.getText(), degreeCode, level, true);
                } else {
                    Controller.saveModuleAssociation(moduleCode.getText(), degreeCode, level, false);
                }
            }
            changeJPanel(new ManageModules(getFrame()).getJPanel());
        }
    }
}


