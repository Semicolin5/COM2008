package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createDepartment {
    private JPanel panel1;
    private JTextField departmentCode;
    private JTextField departmentName;
    private JButton createDepartment;

    private createDepartment() {
        createDepartment.addActionListener(new ActionListener() {
            private src.db_handler.DatabaseHandler db;

            @Override
            public void actionPerformed(ActionEvent e) {
                db = new src.db_handler.DatabaseHandler();
                src.db_handler.AdditionQueries additionQ = new src.db_handler.AdditionQueries(db);
                additionQ.addDepartment(departmentCode.getText(), departmentName.getText());
                JOptionPane.showMessageDialog(null, "Department " +  departmentCode.getText() + " Created");
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("createDepartment");
        frame.setContentPane(new createDepartment().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setSize(550, 140);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
