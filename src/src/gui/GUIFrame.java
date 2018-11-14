package src.gui;

import src.objects.User;

import javax.swing.*;

public class GUIFrame extends JFrame {

    private JPanel currentPanel;

    public GUIFrame() {
        currentPanel = new Login(this).getJPanel();
        setContentPane(currentPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,300);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void changeJPanel(JPanel panel) {
        System.out.println("changeJPanel");
        currentPanel.removeAll();
        getContentPane().setVisible(false);
        getContentPane().remove(currentPanel);
        currentPanel = panel;
        getContentPane().add(currentPanel);
        getContentPane().setVisible(true);
    }

}
