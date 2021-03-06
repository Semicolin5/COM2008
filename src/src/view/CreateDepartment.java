package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import src.controller.Controller;
import src.controller.Main;

/**
 * CreateDepartment.java
 * Only accessible for Administrators (privilege level 4)
 * Extension of form, creates a functional GUI form which allows
 * user to create a new department as an entry in the department table.
 */
public class CreateDepartment extends Form {
    private JPanel panel1;
    private JTextField departmentCode;
    private JTextField departmentName;
    private JButton createDepartmentButton;
    private JButton cancelButton;
    private String errorMessage = "";

    /**
     * Set default JFrame sizes & add Event Listener
     *
     * @param frame - JFrame with properties set in the GUIFrame class.
     */
    public CreateDepartment(GUIFrame frame) {
        super(frame);
        setJPanel(panel1);
        frame.setTitle("Create Department");
        createDepartmentButton.addActionListener(new CreateDepartmentHandler());
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
        panel1.setLayout(new GridLayoutManager(5, 5, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Deparment Code");
        panel1.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Department Name");
        panel1.add(label2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        departmentCode = new JTextField();
        panel1.add(departmentCode, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        departmentName = new JTextField();
        panel1.add(departmentName, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        createDepartmentButton = new JButton();
        createDepartmentButton.setText("Create Department");
        panel1.add(createDepartmentButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel1.add(cancelButton, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /**
     * Action listener which calls a controller function, ultimately adding a new row
     * in the department table.
     * Also loads the ManageDepartments form, so user can see in the JList
     * that the data has actually been saved.
     */
    public class CreateDepartmentHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String depCode = departmentCode.getText();
            String depName = departmentName.getText();

            //Do error checking
            errorMessage = Controller.checkInputDepartment(depCode, depName, Main.getPriv());
            if (errorMessage.equals("Accepted")) {
                Controller.saveDepartment(departmentCode.getText(), departmentName.getText());
                changeJPanel(new ManageDepartments(getFrame()).getJPanel());
            } else {
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            }
        }
    }

    /**
     * ActionListener class which takes the user back to the ManageDepartments form.
     */
    private class cancelHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new ManageDepartments(getFrame()).getJPanel());
        }
    }
}
