
import fu.ex.daos.CarDAO;
import fu.ex.daos.RentDAO;
import fu.ex.daos.ShoppingCartDAO;
import fu.ex.entities.Car;
import fu.ex.entities.ShoppingCart;
import fu.ex.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import javax.naming.NamingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tung Nguyen
 */
public class test {

    private static Scanner sc = new Scanner(System.in);

    private static Connection con;
    private static PreparedStatement pstm;
    private static ResultSet rs;

    private static void closeConnection() throws Exception {
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

    public static void main(String[] args) throws NamingException, SQLException {
        try {
            CarDAO cdao = new CarDAO();
            if(cdao.delete("12")){
                System.out.println("Sucess");
            }else{
                System.out.println("Fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
