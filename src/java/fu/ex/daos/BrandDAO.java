/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.entities.Car_brand;
import fu.ex.entities.Roles;
import fu.ex.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Tung Nguyen
 */
public class BrandDAO {
    

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    public BrandDAO() {

    }

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

    public Car_brand getBrandById(String id) throws Exception {
        Car_brand result = null;
        try {
            String sql = "Select brandID, brandName\n"
                    + "From Car_brand\n"
                    + "Where brandID=?\n";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String bid = rs.getString("brandID");
                String name = rs.getString("brandName");
                result = new Car_brand(bid, name);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<Car_brand> getAllBrands() throws NamingException, SQLException, Exception {

        String sql = "Select brandID, brandName\n"
                + "FROM Car_brand";

        ArrayList<Car_brand> lst = new ArrayList<>();

        try {
            con = DBUtils.makeConnection();

            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String brandID = rs.getString("brandID");
                    String brandName = rs.getString("brandName");
                    
                    Car_brand brand = new Car_brand(brandID, brandName);
                    lst.add(brand);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lst;
    }
}
