/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.entities.Customers;
import fu.ex.entities.Rent;
import fu.ex.entities.Roles;
import fu.ex.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class RentDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    private void closeConnection() throws Exception {
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

    public ArrayList<Rent> getAllRent() throws Exception {
        String sql = "Select rentID, amount, customerID From Rent";
        ArrayList<Rent> rl = new ArrayList<>();
        try {
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String rentID = rs.getString("rentID");
                float amount = rs.getFloat("amount");
                String customerID = rs.getString("customerID");
                CustomerDAO dao = new CustomerDAO();
                Customers cus = dao.getCustomerById(customerID);
                Rent r = new Rent(rentID, amount, cus);
                rl.add(r);
            }
        } finally {
            closeConnection();
        }
        return rl;
    }

    public boolean insert(Rent rent) throws Exception {
        boolean check = false;
        try {
            String sql
                    = "Insert Into Rent(rentID, amount, customerID)\n"
                    + "Values(?,?,?)";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, rent.getRentID());
            pstm.setFloat(2, rent.getAmount());
            pstm.setString(3, rent.getCustomerID().getCustomerID());

            check = pstm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public Rent getRentById(String id) throws Exception {
        Rent result = null;
        try {
            String sql = "Select rentID, amount, customerID From Rent Where rentID=?";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String rentID = rs.getString("rentID");
                float amount = rs.getFloat("amount");
                String customerID = rs.getString("customerID");
                CustomerDAO dao = new CustomerDAO();
                Customers cus = dao.getCustomerById(customerID);
                result = new Rent(rentID, amount, cus);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
