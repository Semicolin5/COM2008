package src.model.db_handler;
import java.sql.*;

import src.objects.Degree;
import src.objects.Department;
import src.objects.User;
import java.util.*;


/**
 * RetrieveQueries.java
 * extension of Queries, used to encapsulate the retrieval methods of SQL.
 * */
public class RetrieveQueries extends Queries {

    // constructor is passed a DatabaseHandler object from which it obtains the Connection object
    public RetrieveQueries (DatabaseHandler db) {
        super(db);
    }

    /**
     * retrieves the department table as a list of Department objects
     * @return list of Department objects.
     * */
    public List<Department> retrieveDepartmentTable() {
        List<Department> table = new ArrayList<Department>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        if (!super.isTableEmpty("department") && super.getPriv() == 4) {
            try {
                pstmt = conn.prepareStatement("SELECT * FROM department");
                res = pstmt.executeQuery();
                while (res.next()) {
                    table.add(new Department(res.getString(1), res.getString(2)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeResources(pstmt, res);
            }


        }
        return table;
    }


    /**
     * retrieve the degree table, linked with the departments that teach that course
     * */
    public List<Degree> retrieveDegrees() {
        List<Degree> degreeTable = new ArrayList<Degree>();
        PreparedStatement pstmt = null;
        ResultSet res = null;
        if(super.getPriv() == 4) {
            try {
                pstmt = conn.prepareStatement("SELECT * FROM degree");
                res = pstmt.executeQuery();
                while (res.next()) {
                    degreeTable.add(new Degree(res.getString(1), res.getString(2)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                closeResources(pstmt, res);
            }
        }
        return degreeTable;
    }

    /**
     * retrieve the degree table, linked with the departments that teach that course
     * */
   public List<User> retrieveUsersTable() {
       List<User> userTable = new ArrayList<>();
       PreparedStatement pstmt = null;
       ResultSet res = null;
       if (super.getPriv() == 4) {
           try {
               pstmt = conn.prepareStatement("SELECT * FROM users");
               res = pstmt.executeQuery();
               while (res.next()) {
                   userTable.add(new User(res.getString(1), res.getString(2),
                           res.getInt(3), res.getString(4)));
               }
           } catch (SQLException e) {
               e.printStackTrace();
           } finally {
               closeResources(pstmt, res);
           }
       }
       return userTable;
   }

}