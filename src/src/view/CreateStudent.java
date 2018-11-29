package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import src.controller.*;
import src.objects.Degree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateStudent extends Form {
    private JTextField studentNo;
    private JTextField studentForename;
    private JTextField studentSurname;
    private JComboBox titleCombo;
    private JComboBox degreeCombo;
    private JTextField studentTutor;
    private JPasswordField initPassword;
    private JPasswordField confirmPassword;
    private JButton addStudentButton;
    private JPanel panel1;
    private JButton cancelButton;
    private JComboBox degreeLevelCombo;
    private JSpinner posStart;
    private JSpinner posEnd;
    private String errorMessage;

    public CreateStudent(GUIFrame frame) {
        super(frame);
        setJPanel(panel1);

        //Add degree codes to combo box
        degreeCombo.addItem("");
        for (Degree degree : Controller.getDegrees()) {
            degreeCombo.addItem(degree.getDegreeCode());
        }

        degreeCombo.addItemListener(e -> {
            degreeLevelCombo.removeAllItems();
            for (Degree degree : Controller.getDegrees()) {
                if (degree.getDegreeCode().equals(e.getItem()) && degree.hasPlacementYear()) {
                    if (degree.isMasters()) {
                        for (int i = 1; i < 4; i++) {
                            degreeLevelCombo.addItem(i);
                        }
                        degreeLevelCombo.addItem("Placement Year");
                        degreeLevelCombo.addItem("4");
                    } else {
                        for (int i = 1; i < 3; i++) {
                            degreeLevelCombo.addItem(i);
                        }
                        degreeLevelCombo.addItem("Placement Year");
                        degreeLevelCombo.addItem("3");
                    }
                } else if (degree.getDegreeCode().equals(e.getItem()) && degree.isMasters()) {
                    for (int i = 1; i < 5; i++)
                        degreeLevelCombo.addItem(i);
                } else if (degree.getDegreeCode().equals(e.getItem())) {
                    for (int i = 1; i < 4; i++)
                        degreeLevelCombo.addItem(i);
                }
            }
        });

        posStart.setModel(new SpinnerDateModel(Calendar.getInstance().getTime(),
                null, null, Calendar.DAY_OF_WEEK));
        posStart.setEditor(new JSpinner.DateEditor(posStart, "yyyy-MM-dd"));

        posEnd.setModel(new SpinnerDateModel(Calendar.getInstance().getTime(),
                null, null, Calendar.DAY_OF_WEEK));
        posEnd.setEditor(new JSpinner.DateEditor(posEnd, "yyyy-MM-dd"));

        frame.setTitle("Create Student");
        addStudentButton.addActionListener(new AddStudentHandler());
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
        panel1.setLayout(new GridLayoutManager(15, 5, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Student No.");
        panel1.add(label1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        studentNo = new JTextField();
        panel1.add(studentNo, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        studentSurname = new JTextField();
        panel1.add(studentSurname, new GridConstraints(6, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        studentForename = new JTextField();
        panel1.add(studentForename, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        titleCombo = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("");
        defaultComboBoxModel1.addElement("Mr");
        defaultComboBoxModel1.addElement("Ms");
        titleCombo.setModel(defaultComboBoxModel1);
        panel1.add(titleCombo, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Surname");
        panel1.add(label2, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Forename");
        panel1.add(label3, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Title");
        panel1.add(label4, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Personal Tutor");
        panel1.add(label5, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        studentTutor = new JTextField();
        panel1.add(studentTutor, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Degree Code");
        panel1.add(label6, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        degreeCombo = new JComboBox();
        panel1.add(degreeCombo, new GridConstraints(8, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Initial Password");
        panel1.add(label7, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        initPassword = new JPasswordField();
        initPassword.setText("");
        initPassword.setToolTipText("Psswords must be between 8-16 characters long. \nThey must contain at least: 1 Capital letter, \n1 Lower case letter, 1 Number, 1 Special Character");
        panel1.add(initPassword, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Confirm Password");
        panel1.add(label8, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        confirmPassword = new JPasswordField();
        confirmPassword.setToolTipText("Psswords must be between 8-16 characters long. \nThey must contain at least: 1 Capital letter, \n1 Lower case letter, 1 Number, 1 Special Character");
        panel1.add(confirmPassword, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        addStudentButton = new JButton();
        addStudentButton.setText("Add Student");
        panel1.add(addStudentButton, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Degree Level");
        panel1.add(label9, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Period of Study Start Date");
        panel1.add(label10, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setText("Period of Study End Date");
        panel1.add(label11, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setText("Add a Student");
        panel1.add(label12, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel1.add(cancelButton, new GridConstraints(14, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        degreeLevelCombo = new JComboBox();
        panel1.add(degreeLevelCombo, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        posStart = new JSpinner();
        panel1.add(posStart, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        posEnd = new JSpinner();
        panel1.add(posEnd, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    private class AddStudentHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //Pull the values from the tables
            String studNo = studentNo.getText();
            String title = titleCombo.getSelectedItem().toString();
            String forename = studentForename.getText();
            String surname = studentSurname.getText();
            String pTutor = studentTutor.getText();
            String degCode = "";
            String degLevel = "";
            String startDate = dateFormat.format(posStart.getValue());
            String endDate = dateFormat.format(posEnd.getValue());
            String password = new String(initPassword.getPassword());
            String passwordConfirm = new String(confirmPassword.getPassword());
            //Check combo boxes are not null
            if (degreeCombo.getSelectedItem() == null) {
                errorMessage = "Please select degree.";
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            } else if (degreeLevelCombo.getSelectedItem() == null) {
                errorMessage = "Please select a degree level.";
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            } else {
                degCode = degreeCombo.getSelectedItem().toString();
                degLevel = degreeLevelCombo.getSelectedItem().toString();
                //Run our controller testing function
                errorMessage = Controller.checkInputStudent(studNo, forename, surname, pTutor, password, passwordConfirm, Main.getPriv());
                if (errorMessage.equals("Accepted")) {
                    //Save everything! - email is auto generated and degree level always starts at A
                    //Also not hashing passwords.  this needs a fix asap
                    Controller.saveStudent(Integer.parseInt(studNo), password, title, forename, surname, pTutor, Controller.generateEmail(forename, surname), degCode, degLevel, "A", startDate, endDate);
                    changeJPanel(new src.view.ManageStudents(getFrame()).getJPanel());
                } else {
                    JOptionPane.showMessageDialog(getFrame(), errorMessage);
                }
            }
            //De-reference so the garbage collector can take them
            password = "";
            passwordConfirm = "";
        }
    }

    /**
     * ActionListener class which takes the user back to the ManageStudents form.
     */
    private class cancelHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new src.view.ManageStudents(getFrame()).getJPanel());
        }
    }
}
