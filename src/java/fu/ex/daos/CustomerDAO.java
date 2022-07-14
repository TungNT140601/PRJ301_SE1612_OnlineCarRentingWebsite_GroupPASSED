/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.entities.Customers;
import fu.ex.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Tung Nguyen
 */
public class CustomerDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    private void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (pstm != null) {
            pstm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public CustomerDAO() {

    }

    public Customers getCustomerById(String id) throws Exception {
        Customers result = null;
        try {
            String sql = "Select customerID, roleID, password, fullName, phone, place, country, email\n"
                    + "From Customers\n"
                    + "Where customerID=?\n";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                RoleDAO rDao = new RoleDAO();
                String roleID = rs.getString("roleID");
                String password = rs.getString("password");
                String fullName = rs.getString("fullName");
                String phone = rs.getString("phone");
                String place = rs.getString("place");
                String country = rs.getString("country");
                String email = rs.getString("email");

                result = new Customers(id, rDao.getRoleById(roleID), password, fullName, phone, place, country, email);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArrayList<Customers> getAllCustomer() throws Exception {
        ArrayList<Customers> result = new ArrayList<>();
        try {
            String sql = "Select customerID, roleID, password, fullName, phone, place, country, email\n"
                    + "From Customers";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String cusID = rs.getString("customerID");
                    RoleDAO rDao = new RoleDAO();
                    String roleID = rs.getString("roleID");
                    String password = rs.getString("password");
                    String fullName = rs.getString("fullName");
                    String phone = rs.getString("phone");
                    String place = rs.getString("place");
                    String country = rs.getString("country");
                    String email = rs.getString("email");

                    result.add(new Customers(cusID, rDao.getRoleById(roleID), password, fullName, phone, place, country, email));
                }
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public Customers checkLogin(String username, String password) throws Exception {
        Customers user = null;
        try {
            String sql = "Select customerID, roleID, password, fullName, phone, place, country, email\n"
                    + "From Customers\n"
                    + "Where customerID=? And password=?";
            con = DBUtils.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, username);
            pstm.setString(2, password);
            rs = pstm.executeQuery();

            if (rs.next()) {
                RoleDAO rDao = new RoleDAO();
                String roleID = rs.getString("roleID");
                String fullName = rs.getString("fullName");
                String phone = rs.getString("phone");
                String place = rs.getString("place");
                String country = rs.getString("country");
                String email = rs.getString("email");

                user = new Customers(username, rDao.getRoleById(roleID), password, fullName, phone, place, country, email);

            }
        } finally {
            closeConnection();
        }
        return user;
    }

    public boolean insert(Customers acc) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into Customers(customerID, roleID, password, fullName, phone, place, country, email)\n"
                    + "Values(?,?,?,?,?,?,?,?)";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, acc.getCustomerID());
            pstm.setString(2, acc.getRole().getRoleID());
            pstm.setString(3, acc.getPassword());
            pstm.setString(4, acc.getFullname());
            pstm.setString(5, acc.getPhone());
            pstm.setString(6, acc.getPlace());
            pstm.setString(7, acc.getCountry());
            pstm.setString(8, acc.getEmail());
            check = pstm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }
}
