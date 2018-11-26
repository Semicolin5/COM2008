package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import src.controller.Controller;
import src.controller.Main;
import src.objects.Grade;
import src.objects.PeriodOfStudy;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ViewRecords.java
 * Only accessible to Students and Teachers
 */
public class ViewRecord extends Form {

    private JButton backButton;
    private JButton loadRecordButton;
    private JTable displayOutcome;
    private JList periodList;
    private JPanel panel1;
    private DefaultTableModel outcomeModel;
    private DefaultListModel<String> periodListModel;
    private int username; // int for the current user record being viewed

    /**
     * Constructor sets up an empty JTable, and sets up a JList containing the periods of study, and levels for the
     * student.
     *
     * @param frame - JFrame with properties defined in the GUIFrame class.
     */
    public ViewRecord(GUIFrame frame) {
        super(frame);
        if (Main.getPriv() == 2) { // running for a teacher
            //TODO
            // Teacher should be able to change username so that they can view records for any student
        } else if (Main.getPriv() == 1) { // running for a student
            username = Main.getLoginID();
            System.out.println("loading record for: " + username);
        }
        System.out.println("in here");
        // setup the backbutton
        setBackButton(backButton);
        setBackButtonPanel(new Welcome(getFrame()).getJPanel());
        setJPanel(panel1);
        frame.setTitle("View Record");

        // setup the JList and JTable
        periodListModel = new DefaultListModel<>();
        outcomeModel = new DefaultTableModel();
        // setting up the columns in the table
        outcomeModel.addColumn("Module");
        outcomeModel.addColumn("Initial Percent Achieved");
        outcomeModel.addColumn("Resit Percent Achieved");
        // filling JList
        for (PeriodOfStudy p : Controller.getPeriodsOfStudyForStudent(username)) {
            periodListModel.addElement(p.getLabel()); // selects students periods of study
        }
        periodList.setLayoutOrientation(JList.VERTICAL);
        periodList.setModel(periodListModel);
        periodList.setVisibleRowCount(10);
        displayOutcome.setModel(outcomeModel);
        loadRecordButton.addActionListener(new LoadRecordHandler());
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
        panel1.setLayout(new GridLayoutManager(4, 2, new Insets(0, 0, 0, 0), -1, -1));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        periodList = new JList();
        final DefaultListModel defaultListModel1 = new DefaultListModel();
        periodList.setModel(defaultListModel1);
        scrollPane1.setViewportView(periodList);
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        displayOutcome = new JTable();
        scrollPane2.setViewportView(displayOutcome);
        backButton = new JButton();
        backButton.setText("Back");
        panel1.add(backButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        loadRecordButton = new JButton();
        loadRecordButton.setText("Load Record");
        panel1.add(loadRecordButton, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }


    /**
     * LoadRecordHandler loads a students progress in a given period of study onto a JTable
     */
    private class LoadRecordHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            outcomeModel.setRowCount(0); // resets the table
            String periodOfStudyLabel = periodList.getSelectedValue().toString(); // finds the period of study label
            for (Grade g : Controller.getStudentsGradeAtPeriod(username, periodOfStudyLabel)) {
                System.out.println(g.toString());
            }
            /*associateModel.setRowCount(0);

            String degreeCode = degreeList.getSelectedValue().toString();
            for (Degree degree : Controller.getDegrees()) {
                if (degreeCode.equals(degree.getDegreeCode())) {
                    Department lead = degree.getLeadDepartment();
                    //System.out.println(lead.getCode() + " " + lead.getName() + " Lead");
                    associateModel.addRow(new Object[]{lead.getCode(), lead.getName(), "Lead"});
                    if (degree.getNonLeadDepartments() != null) {
                        for (Department d : degree.getNonLeadDepartments())
                            associateModel.addRow(new Object[]{d.getCode(), d.getName(), "Non-Lead"});
                    }
                }
            }*/
        }
    }

}