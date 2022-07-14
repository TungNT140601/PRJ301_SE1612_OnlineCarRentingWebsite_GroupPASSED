/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.entities.ShoppingCart;
import fu.ex.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class ShoppingCartDAO {

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

    public boolean insert(ShoppingCart cart) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into Rent_Car(carID, quantity, rentID, pickupDate, returnDate)\n"
                    + "Values(?,?,?,?,?)";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, cart.getCar().getCarID());
            pstm.setInt(2, cart.getQuantity());
            pstm.setString(3, cart.getRentID().getRentID());
            pstm.setString(4, cart.getPickupDate());
            pstm.setString(5, cart.getReturnDate());

            check = pstm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public long countDate(String date1, String date2) {
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
        Date data1 = null;
        Date data2 = null;
        long diffDays = 0;
        try {
            data1 = d.parse(date1);
            data2 = d.parse(date2);
            LocalDate d1 = LocalDate.parse(date1, DateTimeFormatter.ISO_LOCAL_DATE);
            LocalDate d2 = LocalDate.parse(date2, DateTimeFormatter.ISO_LOCAL_DATE);
            Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
            diffDays = diff.toDays();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return diffDays;
    }

    public ArrayList<ShoppingCart> getHistory(String cusId) {
        ArrayList<ShoppingCart> result = new ArrayList<>();
        try {
            String sql = "SELECT rc.carID,rc.quantity,rc.rentID,rc.pickupDate,rc.returnDate\n"
                    + "FROM Rent r INNER JOIN Rent_Car rc on r.rentID = rc.rentID\n"
                    + "WHERE r.customerID LIKE ?";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, cusId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String carID = rs.getString("carID");
                int quantity = rs.getInt("quantity");
                String rentID = rs.getString("rentID");
                String pickupDate = rs.getString("pickupDate");
                String returnDate = rs.getString("returnDate");

                CarDAO cdao = new CarDAO();
                RentDAO rdao = new RentDAO();

                ShoppingCart sc = new ShoppingCart(cdao.getCarById(carID), quantity, rdao.getRentById(rentID), pickupDate, returnDate);
                result.add(sc);
            }
        } catch (Exception e) {
        }

        return result;
    }

    public ArrayList<ShoppingCart> getRentHistory() {
        ArrayList<ShoppingCart> result = new ArrayList<>();
        try {
            String sql = "SELECT carID,quantity,rentID,pickupDate,returnDate \n"
                    + "FROM Rent_Car";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String carID = rs.getString("carID");
                int quantity = rs.getInt("quantity");
                String rentID = rs.getString("rentID");
                String pickupDate = rs.getString("pickupDate");
                String returnDate = rs.getString("returnDate");

                CarDAO cdao = new CarDAO();
                RentDAO rdao = new RentDAO();

                ShoppingCart sc = new ShoppingCart(cdao.getCarById(carID), quantity, rdao.getRentById(rentID), pickupDate, returnDate);
                result.add(sc);
            }
        } catch (Exception e) {
        }
        return result;
    }

    public ArrayList<ShoppingCart> getRentByCarId(String carId) {
        ArrayList<ShoppingCart> result = new ArrayList<>();
        try {
            String sql = "SELECT rc.carID,rc.quantity,rc.rentID,rc.pickupDate,rc.returnDate\n"
                    + "FROM Rent_Car rc INNER JOIN Car c ON rc.carID = c.carID\n"
                    + "WHERE c.carID LIKE ?";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, carId);
            rs = pstm.executeQuery();
            while (rs.next()) {
                String carID = rs.getString("carID");
                int quantity = rs.getInt("quantity");
                String rentID = rs.getString("rentID");
                String pickupDate = rs.getString("pickupDate");
                String returnDate = rs.getString("returnDate");

                CarDAO cdao = new CarDAO();
                RentDAO rdao = new RentDAO();

                ShoppingCart sc = new ShoppingCart(cdao.getCarById(carID), quantity, rdao.getRentById(rentID), pickupDate, returnDate);
                result.add(sc);
            }
        } catch (Exception e) {
        }

        return result;
    }

    public boolean update(ShoppingCart cart) throws Exception {
        String sql = "UPDATE Rent_Car SET"
                + " carID=?"
                + ", quantity=?"
                + ", rentID=?"
                + ", pickupDate=?"
                + ", returnDate=?"
                + " WHERE rentID=?";
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);

                pstm.setString(1, cart.getCar().getCarID());
                pstm.setInt(2, cart.getQuantity());
                pstm.setString(3, cart.getRentID().getRentID());
                pstm.setString(4, cart.getPickupDate());
                pstm.setString(5, cart.getReturnDate());
                pstm.setString(6, cart.getRentID().getRentID());
                pstm.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public ShoppingCart getRentById(String rid, String cid) throws Exception {
        ShoppingCart result = null;
        try {
            String sql = "Select carID, quantity, rentID, pickupDate, returnDate From Rent_Car Where rentID=? AND carID=?";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, rid);
            pstm.setString(2, cid);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String carID = rs.getString("carID");
                int quantity = rs.getInt("quantity");
                String rentID = rs.getString("rentID");
                String pickupDate = rs.getString("pickupDate");
                String returnDate = rs.getString("returnDate");
                CarDAO cdao = new CarDAO();
                RentDAO rdao = new RentDAO();
                result = new ShoppingCart(cdao.getCarById(carID), quantity, rdao.getRentById(rentID), pickupDate, returnDate);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
