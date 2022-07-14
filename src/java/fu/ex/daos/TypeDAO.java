/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.entities.Car_type;
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
public class TypeDAO {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    public TypeDAO() {

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

    public Car_type getTypeById(String id) throws Exception {
        Car_type result = null;
        try {
            String sql = "Select typeID, typeType\n"
                    + "From Car_type\n"
                    + "Where typeID=?\n";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String tid = rs.getString("typeID");
                String name = rs.getString("typeType");
                result = new Car_type(tid, name);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public ArrayList<Car_type> getAllTypes() throws NamingException, SQLException, Exception {

        String sql = "Select typeID, typeType\n"
                + "FROM Car_type";

        ArrayList<Car_type> lst = new ArrayList<>();

        try {
            con = DBUtils.makeConnection();

            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String typeID = rs.getString("typeID");
                    String typeType = rs.getString("typeType");
                    
                    Car_type type = new Car_type(typeID, typeType);
                    lst.add(type);
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
