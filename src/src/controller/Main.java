package src.controller;

import src.db_handler.*;
import src.gui.*;
import src.objects.*;

/**
 * Main.java
 *
 * This is the main class of the controller,
 * Main method runs the System.
 * */
public class Main {    

    //TODO implement password && username checking when evaluating priv
    private static String DEMOUSERNAME = "HWhite";
    private static int priv = 4;

    // stores the privilege for the user.
    private static DatabaseHandler db;

    public static void main(String[] args) {
        new GUIFrame(new User("Login", "Pass", 4));
        db = new DatabaseHandler();
    }

    public static void saveDegree(String degreeCode, String degreeName){
        src.controller.Controller.saveDegree(degreeCode, degreeName);
    }

    public static void saveDepartment(String departmentCode, String departmentName){
        src.controller.Controller.saveDepartment(departmentCode, departmentName);
    }

    /**
     * Accessor method for the DatabaseHandler object db
     * @return DatabaseHandler object
     * */
    public static DatabaseHandler getDB() {
        return db;
    }


}






