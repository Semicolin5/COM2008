package src.view;

import src.controller.Controller;
import src.objects.Department;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManageDepartments extends Form {
    private JPanel panel1;
    private JButton createDepButton;
    private JList departmentList;
    private JButton deleteDepButton;
    private JButton backButton;
    private DefaultListModel<String> departmentsModel;

    /**
     * Constructor sets the columns of the empty JTable, and loads department codes into the department JList.
     * Also adds ActionListeners to the different buttons on the form.
     * @param frame - JFrame with properties defined in the GUIFrame class.
     */
    public ManageDepartments(GUIFrame frame) {
        super(frame);
        setJPanel(panel1);
        departmentsModel = new DefaultListModel<>();
        frame.setTitle("Manage Departments");

        for (Department department : Controller.getDepartments()) {
            departmentsModel.addElement(department.getCode());
        }

        departmentList.setModel(departmentsModel);
        departmentList.setVisibleRowCount(10);

        backButton.addActionListener(new BackHandler());
        createDepButton.addActionListener(new DepartmentHandler());
        deleteDepButton.addActionListener(new RemoveDepartmentHandler());
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
        panel1.setLayout(new GridBagLayout());
        final JScrollPane scrollPane1 = new JScrollPane();
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        panel1.add(scrollPane1, gbc);
        departmentList = new JList();
        scrollPane1.setViewportView(departmentList);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer2, gbc);
        deleteDepButton = new JButton();
        deleteDepButton.setText("Delete Selected Departments");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(deleteDepButton, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer3, gbc);
        createDepButton = new JButton();
        createDepButton.setText("Create Department");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(createDepButton, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("List of Departments");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel1.add(label1, gbc);
        backButton = new JButton();
        backButton.setText("Back");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(backButton, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel1.add(spacer4, gbc);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    /**
     * ActionListener class which takes the user  to the CreateDepartment form.
     */
    public class DepartmentHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new CreateDepartment(getFrame()).getJPanel());
        }
    }

    /**
     * RemoveDepartmentHandler calls the removeDegree methods from the controller to work out if the Admin is allowed
     * to delete the department. In order for a degree to be allowed to be deleted:
     * 1) no degrees should be affiliated with the department.
     */
    private class RemoveDepartmentHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            for (Object code : departmentList.getSelectedValuesList()) {
                if (!Controller.removeDepartment((String) code)) {
                    JOptionPane.showMessageDialog(getFrame(), "The current department is not allowed to be deleted until " +
                            "there are no degrees affiliated with the department");
                }
            }
            changeJPanel(new ManageDepartments(getFrame()).getJPanel());
        }
    }

    /**
     * ActionListener class which takes the user back to the Welcome form.
     */
    private class BackHandler implements ActionListener {
        public void actionPerformed(ActionEvent actionEvent) {
            changeJPanel(new src.view.Welcome(getFrame()).getJPanel());
        }
    }
}
