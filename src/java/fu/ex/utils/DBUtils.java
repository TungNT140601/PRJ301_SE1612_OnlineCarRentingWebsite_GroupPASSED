/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tuanbee
 */
public class DBUtils {

    public static Connection makeConnection() {
        try {
            //1.Load JDBC driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //2.Create connection
            String sqlConnectionString = "jdbc:sqlserver://localhost:1433;databaseName=OnlineCarRentingWebsite1";
            Connection con = DriverManager.getConnection(sqlConnectionString, "sa", "14062001");
            return con;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
