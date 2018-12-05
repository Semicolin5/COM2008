package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import src.controller.*;
import src.model.RegexTests;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * CreateUser.java
 * Only accessible for Administrators (privilege level 4)
 * Extension of form, creates a functional GUI form which allows
 * admins to create a new user as an entry in the user table, and also
 * to add any degrees/levels which the module is approved for and its core status,
 * which are added as rows in the core table.
 */
public class CreateUser extends Form {
    private JPanel panel1;
    private JTextField loginID;
    private JComboBox userType;
    private JPasswordField confirmPass;
    private JButton createAccountButton;
    private JPasswordField initPass;
    private JButton cancelButton;
    private String errorMessage = "";


    /**
     * Constructor sets the frame and draws up the GUI.
     * Also creates an actionListener on the button.
     */
    public CreateUser(GUIFrame frame) {
        super(frame);
        frame.setTitle("Create Users Screen");

        setJPanel(panel1);
        createAccountButton.addActionListener(new CreateAccountHandler());
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
        panel1.setLayout(new GridLayoutManager(7, 5, new Insets(0, 0, 0, 0), -1, -1));
        loginID = new JTextField();
        panel1.add(loginID, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        userType = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("Teacher");
        defaultComboBoxModel1.addElement("Registrar");
        defaultComboBoxModel1.addElement("Administrator");
        userType.setModel(defaultComboBoxModel1);
        panel1.add(userType, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("User Type");
        panel1.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Login ID");
        panel1.add(label2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(6, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Password");
        panel1.add(label3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Confirm Password");
        panel1.add(label4, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        createAccountButton = new JButton();
        createAccountButton.setText("Create Account");
        panel1.add(createAccountButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        initPass = new JPasswordField();
        panel1.add(initPass, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        confirmPass = new JPasswordField();
        panel1.add(confirmPass, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel1.add(cancelButton, new GridConstraints(5, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /**
     * When button is clicked, create a user account based on the entered date.
     */
    public class CreateAccountHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String logIDString = loginID.getText();
            String userPrivString = userType.getSelectedItem().toString();
            int logID;
            String password;
            String confirmPassword;
            int userPriv;

            //Run some checks on the form
            if (!RegexTests.checkLoginID(logIDString)) {
                errorMessage = "Login ID should be a four digit number.";
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            } else if (userPrivString.equals("")) {
                errorMessage = "Please select a user privilege.";
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            } else {
                logID = Integer.parseInt(logIDString);
                password = new String(initPass.getPassword());
                confirmPassword = new String(confirmPass.getPassword());
                switch (userPrivString) {
                    case "Administrator":
                        userPriv = 4;
                        break;
                    case "Registrar":
                        userPriv = 3;
                        break;
                    default:
                        userPriv = 2;
                        break;
                }

                //Run our controller checks (uniqueness password matching etc.
                errorMessage = Controller.checkInputUser(logID, password, confirmPassword, Main.getPriv());
                if (errorMessage.equals("Accepted")) {
                    Controller.saveUser(logID, password, userPriv);
                    changeJPanel(new ManageUsers(getFrame()).getJPanel());
                } else {
                    JOptionPane.showMessageDialog(getFrame(), errorMessage);
                }
                //Allow password to be deleted by the garbage collector.
                password = "";
                confirmPassword = "";
            }
        }
    }

    /**
     * ActionListener class which takes the user back to the ManageUsers form.
     */
    private class cancelHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new ManageUsers(getFrame()).getJPanel());
        }
    }
}