package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import src.controller.Controller;
import src.objects.Grade;
import src.objects.Module;
import src.objects.Student;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java .util.List;

public class UpdateGrades extends Form {
    private JPanel panel1;
    private JList studentList;
    private JList moduleList;
    private DefaultListModel<String> studentModel;
    private DefaultListModel<String> moduleModel;
    private JTextField initialGrade;
    private JTextField resitGrade;
    private JTextField repeatGrade;
    private JButton updateButton;
    private JButton backButton;
    private final JLabel label2 = new JLabel();

    private List<Grade> modList;
    private List<Grade> selectedGrades;
    private int loginID;
    private String latestLevel;
    private String latestPeriod;
    private String selectedModuleCode;
    private boolean repeatedLevel;


    public UpdateGrades(GUIFrame frame) {
        super(frame);
        frame.setTitle("Update Grades Screen");

        backButton.addActionListener(new BackButtonHandler());

        setJPanel(panel1);

        studentModel = new DefaultListModel<>();
        for (Student student : Controller.getStudents()) {
            studentModel.addElement(student.getLogin());
        }
        studentList.setModel(studentModel);

        moduleModel = new DefaultListModel<>();
        moduleList.setModel(moduleModel);
        studentList.getSelectionModel().addListSelectionListener(new studentListHandler());

        moduleList.getSelectionModel().addListSelectionListener(new ModuleListHandler());

        updateButton.addActionListener(new UpdateButtonHandler());
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
        panel1.setLayout(new GridLayoutManager(5, 4, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(1, 0, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        studentList = new JList();
        studentList.setSelectionMode(0);
        scrollPane1.setViewportView(studentList);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, new GridConstraints(1, 1, 3, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        moduleList = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        moduleList.setModel(defaultListModel1);
        moduleList.setSelectionMode(0);
        scrollPane2.setViewportView(moduleList);
        final JLabel label1 = new JLabel();
        label1.setText("Students");
        panel1.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Modules");
        panel1.add(label3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Repeat %");
        panel1.add(label4, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(68, 15), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Resit %");
        panel1.add(label5, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(68, 15), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Initial %");
        panel1.add(label6, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(68, 15), null, 0, false));
        resitGrade = new JTextField();
        panel1.add(resitGrade, new GridConstraints(2, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        repeatGrade = new JTextField();
        panel1.add(repeatGrade, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        initialGrade = new JTextField();
        panel1.add(initialGrade, new GridConstraints(1, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        updateButton = new JButton();
        updateButton.setText("Update");
        panel1.add(updateButton, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Grades");
        panel1.add(label7, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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


    private class studentListHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            //Init our variables
            loginID = Integer.parseInt(studentList.getSelectedValue().toString());
            latestLevel = Controller.getLatestPeriodOfStudy(loginID).getLevelOfStudy();
            latestPeriod = Controller.getLatestPeriodOfStudy(loginID).getLabel();

            modList = Controller.getStudentsGradeAtPeriod(loginID, latestPeriod);

            //If one module is a repeat, all of them will be  TODO test this bad boy when the databse scheme is full
            if (modList.size() > 0) {
                repeatedLevel = modList.get(0).getRepeated();
            }


            moduleModel.removeAllElements();
            for (Grade grade : modList) {
                moduleModel.addElement(grade.getModuleCode());
            }

            //Now lets assign the modules to the list
            clearGrades();
            moduleList.setModel(moduleModel);

            //Change the name to show if the period is repeated or not
            if (repeatedLevel) {
                label2.setText("Modules at level " + latestLevel + " for period of study: " + latestPeriod + " (repeated year).");
            } else {
                label2.setText("Modules at level " + latestLevel + " for period of study: " + latestPeriod + ".");
            }

        }
    }

    /**
     * ModuleListHandler contains handler for displaying the initial, resit and repeat scores for a
     * module that a student took. Displays null grades as blank
     */
    private class ModuleListHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent listSelectionEvent) {
            //Get our module code
            selectedModuleCode = moduleList.getSelectedValue().toString();

            //Boilerplate, just so selectedMOduleGrade is not null, should never be called without being changed prior
            Grade selectedModuleGrade = modList.get(0);

            for (Grade grade : modList) {
                if (grade.getModuleCode().equals(selectedModuleCode)) {
                    selectedModuleGrade = grade;
                }
            }


            //Assign Grades
            clearGrades();
            initialGrade.setText(String.valueOf(selectedModuleGrade.getInitialPercent()));
            resitGrade.setText(String.valueOf(selectedModuleGrade.getResitPercent()));


        }
    }

    /**
     * UpdateButtonHandler deals with when the Teacher wants to edit a students Grade.
     * TODO Edward seems strange that Teacher would be able to edit the initial and resit grade results for a module in a previous period?!!
     */
    private class UpdateButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {

            //Make sure we have something selected
            if (studentList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(getFrame(), "Please select a student.");
            } else if (moduleList.getSelectedValue() == null) {
                JOptionPane.showMessageDialog(getFrame(), "Please select a module.");
            } else {
                loginID = Integer.parseInt(studentList.getSelectedValue().toString());
                String modCode = moduleList.getSelectedValue().toString();

                String initialGradeText = initialGrade.getText();
                String resitGradeText = resitGrade.getText();
                //String repeatGradeText = repeatGrade.getText();

                //Call a controller method to check these inputs
                int a = 1;
                if (a == 1) {
                    //Take the values of our grades as floats
                    Float resitGradeFloat = Float.valueOf(initialGradeText);
                    Float initialGradeFloat = Float.valueOf(resitGradeText);
                    //Float repeatGradeFloat = Float.valueOf(repeatGradeText);


                    //Update the grades

                    //Controller.updateGrades(loginID, module, posLabel, repeatGradeFloat, (float) -1);
                    //Controller.updateGrades(loginID, module, posLabel, repeatGradeFloat, (float) -1);


                } else {
                    JOptionPane.showMessageDialog(getFrame(), "Please input correct numbers");
                }
            }


            Grade firstInList = selectedGrades.get(0); // the
            int loginID = Integer.parseInt(firstInList.getLoginID());
            String module = firstInList.getModuleCode();
            String posLabel = String.valueOf(firstInList.getLabel());

            Float resitGradeFloat = Float.valueOf(resitGrade.getText());
            Float initialGradeFloat = Float.valueOf(initialGrade.getText());
            Float repeatGradeFloat = Float.valueOf(repeatGrade.getText());

            // in case of null grades
            if (resitGrade.getText() == "")
                resitGradeFloat = (float) -1;
            if (initialGrade.getText() == "")
                initialGradeFloat = (float) -1;

            if (repeatGrade.getText() == "")
                repeatGradeFloat = (float) -1;

            if (firstInList.getRepeated() && (repeatGrade.getText() != "")) {
                Controller.updateGrades(loginID, module, posLabel, repeatGradeFloat, (float) -1);
            } else {
                Controller.updateGrades(loginID, module, posLabel, repeatGradeFloat, (float) -1);
            }
        }
    }

    private class BackButtonHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new Welcome(getFrame()).getJPanel());
        }
    }

    private void clearGrades() {
        initialGrade.setText("");
        resitGrade.setText("");
        repeatGrade.setText("");
    }

}
