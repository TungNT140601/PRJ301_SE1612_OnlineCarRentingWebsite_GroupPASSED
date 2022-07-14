/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.entities.Roles;
import fu.ex.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Tung Nguyen
 */
public class RoleDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    public RoleDAO() {

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

    public Roles getRoleById(String id) throws Exception {
        Roles result = null;
        try {
            String sql = "Select roleID, roleName\n"
                    + "From Roles\n"
                    + "Where roleID=?\n";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String rid = rs.getString("roleID");
                String name = rs.getString("roleName");
                result = new Roles(rid, name);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
