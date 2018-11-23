
package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.controller.Controller;
import src.objects.Degree;

public class ManageDegrees extends Form {
    private JPanel panel1;
    private JList degreeList;
    private JList associatedList;
    private JButton loadAssociatedDepartmentsButton;
    private JButton deleteSelectedDegreesButton;
    private JButton createDegreeButton;
    private JButton backButton;
    private DefaultListModel<String> degreeModel;

    public ManageDegrees(GUIFrame frame) {
        super(frame);

        //Setup back button
        setBackButton(backButton);
        setBackButtonPanel(new Welcome(getFrame()).getJPanel());

        setJPanel(panel1);
        degreeModel = new DefaultListModel<>();
        frame.setTitle("Manage Degrees");

        //loops through users in database and adds all of their loginIDs to the JList.
        System.out.println("Sbout to search through Degree");
        for (Degree degree : Controller.getDegrees()) {
            System.out.println(degree.getDegreeCode());
            degreeModel.addElement(degree.getDegreeCode());
        }
        degreeList.setLayoutOrientation(JList.VERTICAL);
        degreeList.setModel(degreeModel);
        degreeList.setVisibleRowCount(10);
        createDegreeButton.addActionListener(new CreateDegreeHandler());
        deleteSelectedDegreesButton.addActionListener(new DeleteDegreesHandler());
        loadAssociatedDepartmentsButton.addActionListener(new AssociatedHandler());
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
        final JScrollPane scrollPane2 = new JScrollPane();
        panel1.add(scrollPane2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        associatedList = new JList();
        scrollPane2.setViewportView(associatedList);
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        loadAssociatedDepartmentsButton = new JButton();
        loadAssociatedDepartmentsButton.setText("Load Associated Departments");
        panel1.add(loadAssociatedDepartmentsButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        deleteSelectedDegreesButton = new JButton();
        deleteSelectedDegreesButton.setText("Delete Degree");
        panel1.add(deleteSelectedDegreesButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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

    public class CreateDegreeHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new CreateDegree(getFrame()).getJPanel());
        }
    }

    private class DeleteDegreesHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            for (Object code : degreeList.getSelectedValuesList()) {
                Controller.removeDegree((String) code);
            }
            changeJPanel(new ManageDegrees(getFrame()).getJPanel());
        }
    }

    private class AssociatedHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            for (Object code : degreeList.getSelectedValuesList()) {
                Controller.removeDegree((String) code);
            }
            changeJPanel(new ManageDegrees(getFrame()).getJPanel());
        }
    }

}
