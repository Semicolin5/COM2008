package src.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import src.objects.Degree;
import src.controller.Controller;
import src.controller.Main;
import src.model.RegexTests;

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
    private ArrayList<String[]> degreeLinker = new ArrayList<String[]>();
    private String errorMessage = "";
    

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
                if (degree.getDegreeCode().equals(e.getItem()) && degree.hasPlacementYear()) {
                    if (degree.isMasters()) {
                        for (int i = 1; i < 4; i++)
                            levelCombo.addItem(i);
                        levelCombo.addItem("P");
                        levelCombo.addItem("4");
                    } else {
                        for (int i = 1; i < 3; i++)
                            levelCombo.addItem(i);
                        levelCombo.addItem("Placement Year");
                        levelCombo.addItem("3");
                    }
                } else if (degree.getDegreeCode().equals(e.getItem()) && degree.isMasters()) {
                    for (int i = 1; i < 5; i++)
                        levelCombo.addItem(i);
                } else if (degree.getDegreeCode().equals(e.getItem())) {
                    for (int i = 1; i < 4; i++)
                        levelCombo.addItem(i);
                }
            }
        });
        //TODO: Modify the above code so that it takes into account yeas in industry.
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
    	private ArrayList<String> storedDegs = new ArrayList<String>();
    	
    	@Override
        public void actionPerformed(ActionEvent e) {
        	String deg = degreeCombo.getSelectedItem().toString();
        	String level;
        	//Got to make sure levelCombo isn't null
        	if (levelCombo.getSelectedItem() == null) {
        		level = "";
        	}
        	else {
        		level = levelCombo.getSelectedItem().toString();
        	}
        	String core = coreCombo.getSelectedItem().toString();
            String[] degLevelCore = {deg, level, core};
            
            //Now we can do our other checks
            if (deg.equals("")) {
            	errorMessage = "Please select a degree to link to.";
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            }
            else if (levelCombo.getSelectedItem() == null) {
            	errorMessage = "Please select a level of study.";
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            }
            else if (storedDegs.contains(deg)) {
                errorMessage = "This degree is already linked to this module.";
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            } 
            else {
                degreeLinker.add(degLevelCore);
                storedDegs.add(deg);
                departmentsModel.addElement(degreeCombo.getSelectedItem().toString() + " " + levelCombo.getSelectedItem().toString() + " " + coreCombo.getSelectedItem().toString());
                errorMessage = "";
            }
        }
    }

    /**
     * Action Handler which calls functions which add a row to the module table.
     * Also adds a row to the core table for each degree level which this module is approved for.
     */
    public class CreateModuleHandler implements ActionListener {
        private ArrayList<String> storedDegs = new ArrayList<String>();
    	
    	@Override
        public void actionPerformed(ActionEvent e) {
            String modCode = moduleCode.getText().toString();
            String modName = moduleName.getText().toString();
            String modCredits = moduleCredits.getText().toString();
            String semesterString = semesterCombo.getSelectedItem().toString();
            int semester;
            //Case statement for semester
        	switch (semesterString) {
            case "Autumn":
                semester = 0;
                break;
            case "Spring":
            	semester = 1;
                break;
            case "Summer":
            	semester = 2;
            default:
            	semester = 3;
                break;
        	}
        	
            //Lets call our big boy checking function           
            errorMessage = Controller.checkInputModule(modCode, modName, modCredits, Main.getPriv());
            if (errorMessage.equals("Accepted")) {
            	Controller.saveModule(modCode, modName, Integer.parseInt(modCredits), semester);
            	
            	//Save the module department linker
                for (int i = 0; i < degreeLinker.size(); i++) {
                    Controller.saveModuleAssociation(modCode, degreeLinker.get(i)[0], degreeLinker.get(i)[1], degreeLinker.get(i)[2].equals("Core"));
                }
            	changeJPanel(new ManageModules(getFrame()).getJPanel());
            }
            else {
                JOptionPane.showMessageDialog(getFrame(), errorMessage);
            }           
        }
    }
}