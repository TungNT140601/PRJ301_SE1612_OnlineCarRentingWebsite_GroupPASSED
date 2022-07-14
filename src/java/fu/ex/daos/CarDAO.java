/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.daos;

import fu.ex.entities.Car;
import fu.ex.entities.Car_brand;
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
public class CarDAO {

    private Connection con = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;

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

    private ArrayList<Car> cars;

    public Car find(String id) throws Exception {
        ArrayList<Car> c = getAllCar();

        for (Car car : c) {
            if (car.getCarID().equalsIgnoreCase(id)) {
                return car;
            }
        }
        return null;
    }

    public ArrayList<Car> getAllCar() throws NamingException, SQLException {

        String sql = "SELECT carID, typeID, carName, brandID, description, purchaseDate, quantity, imgURL, price\n"
                + "FROM Car";

        ArrayList<Car> lst = new ArrayList<>();

        try {
            con = DBUtils.makeConnection();

            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();

                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String typeID = rs.getString("typeID");
                    String carName = rs.getString("carName");
                    String brandID = rs.getString("brandID");
                    String description = rs.getString("description");
                    String purchaseDate = rs.getString("purchaseDate");
                    int quantity = rs.getInt("quantity");
                    String imgURL = rs.getString("imgURL");
                    float price = rs.getFloat("price");

                    TypeDAO tDao = new TypeDAO();
                    BrandDAO bDao = new BrandDAO();

                    Car c = new Car(carID, tDao.getTypeById(typeID), carName, bDao.getBrandById(brandID), description, purchaseDate, quantity, imgURL, price);

                    lst.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lst;
    }

    public Car getCarById(String id) throws Exception {
        Car result = null;
        try {
            String sql = "SELECT carID, typeID, carName, brandID, description, purchaseDate, quantity, imgURL, price\n"
                    + "FROM Car\n"
                    + "Where carID=?\n";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            rs = pstm.executeQuery();
            if (rs.next()) {
                String typeID = rs.getString("typeID");
                String carName = rs.getString("carName");
                String brandID = rs.getString("brandID");
                String description = rs.getString("description");
                String purchaseDate = rs.getString("purchaseDate");
                int quantity = rs.getInt("quantity");
                String imgURL = rs.getString("imgURL");
                float price = rs.getFloat("price");
                TypeDAO tDao = new TypeDAO();
                BrandDAO bDao = new BrandDAO();

                result = new Car(id, tDao.getTypeById(typeID), carName, bDao.getBrandById(brandID), description, purchaseDate, quantity, imgURL, price);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean insert(Car car) throws Exception {
        boolean check = false;
        try {
            String sql = "Insert Into Car(carID, typeID, carName, brandID, description, purchaseDate, quantity, imgURL, price)\n"
                    + "Values(?,?,?,?,?,?,?,?,?)";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, car.getCarID());
            pstm.setString(2, car.getTypeID().getTypeID());
            pstm.setString(3, car.getCarName());
            pstm.setString(4, car.getBrandID().getBrandID());
            pstm.setString(5, car.getDescription());
            pstm.setString(6, car.getPurchaseDate());
            pstm.setInt(7, car.getQuantity());
            pstm.setString(8, car.getImgURL());
            pstm.setFloat(9, car.getPrice());
            check = pstm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean delete(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "Delete From Car\n"
                    + "Where carID=?";
            DBUtils db = new DBUtils();
            con = db.makeConnection();
            pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            check = pstm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(Car car) throws Exception {
        String sql = "UPDATE Car SET"
                + " typeID=?"
                + ", carName=?"
                + ", brandID=?"
                + ", description=?"
                + ", purchaseDate=?"
                + ", quantity=?"
                + ", imgURL=?"
                + ", price=?"
                + " WHERE carID=?";
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);

                pstm.setString(1, car.getTypeID().getTypeID());
                pstm.setString(2, car.getCarName());
                pstm.setString(3, car.getBrandID().getBrandID());
                pstm.setString(4, car.getDescription());
                pstm.setString(5, car.getPurchaseDate());
                pstm.setInt(6, car.getQuantity());
                pstm.setString(7, car.getImgURL());
                pstm.setFloat(8, car.getPrice());
                pstm.setString(9, car.getCarID());
                pstm.executeUpdate();
                return true;
            }
        } finally {
            closeConnection();
        }
        return false;
    }

    public Car getCarByName(String cid) throws Exception {
        String sql = "Select carID, typeID, carName, brandID, description, purchaseDate, quantity, imgURL, price "
                + "from car "
                + "where carName=?";

        Car c = null;
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, cid);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String typeID = rs.getString("typeID");
                    String carName = rs.getString("carName");
                    String brandID = rs.getString("brandID");
                    String description = rs.getString("description");
                    String purchaseDate = rs.getString("purchaseDate");
                    String quantity = rs.getString("quantity");
                    String imgURL = rs.getString("imgURL");
                    String price = rs.getString("price");
                    TypeDAO tdao = new TypeDAO();
                    BrandDAO bdao = new BrandDAO();
                    Car_brand cb = bdao.getBrandById(brandID);
                    Car_type ct = tdao.getTypeById(typeID);

                    c = new Car(carID, ct, carName, cb, description, purchaseDate, Integer.parseInt(quantity), imgURL, Float.parseFloat(price));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return c;
    }

    public ArrayList<Car> getListCarByName(String nid) throws Exception {
        String sql = "Select carID, typeID, carName, brandID, description, purchaseDate, quantity, imgURL, price "
                + "from car "
                + "where carName like ?";

        ArrayList<Car> lst = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, "%" + nid + "%");
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String typeID = rs.getString("typeID");
                    String carName = rs.getString("carName");
                    String brandID = rs.getString("brandID");
                    String description = rs.getString("description");
                    String purchaseDate = rs.getString("purchaseDate");
                    String quantity = rs.getString("quantity");
                    String imgURL = rs.getString("imgURL");
                    String price = rs.getString("price");
                    TypeDAO tdao = new TypeDAO();
                    BrandDAO bdao = new BrandDAO();
                    Car_brand cb = bdao.getBrandById(brandID);
                    Car_type ct = tdao.getTypeById(typeID);

                    Car c = new Car(carID, ct, carName, cb, description, purchaseDate, Integer.parseInt(quantity), imgURL, Float.parseFloat(price));

                    lst.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Car> getCarByBrandID(String bid) throws Exception {
        String sql = "Select carID, typeID, carName, brandID, description, purchaseDate, quantity, imgURL, price "
                + "from car "
                + "where brandID=?";

        ArrayList<Car> lst = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, bid);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String typeID = rs.getString("typeID");
                    String carName = rs.getString("carName");
                    String brandID = rs.getString("brandID");
                    String description = rs.getString("description");
                    String purchaseDate = rs.getString("purchaseDate");
                    String quantity = rs.getString("quantity");
                    String imgURL = rs.getString("imgURL");
                    String price = rs.getString("price");
                    TypeDAO tdao = new TypeDAO();
                    BrandDAO bdao = new BrandDAO();
                    Car_brand cb = bdao.getBrandById(brandID);
                    Car_type ct = tdao.getTypeById(typeID);

                    Car c = new Car(carID, ct, carName, cb, description, purchaseDate, Integer.parseInt(quantity), imgURL, Float.parseFloat(price));

                    lst.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Car> getCarByTypeID(String tid) throws Exception {
        String sql = "Select carID, typeID, carName, brandID, description, purchaseDate, quantity, imgURL, price "
                + "from car "
                + "where typeID=?";

        ArrayList<Car> lst = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                pstm.setString(1, tid);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String typeID = rs.getString("typeID");
                    String carName = rs.getString("carName");
                    String brandID = rs.getString("brandID");
                    String description = rs.getString("description");
                    String purchaseDate = rs.getString("purchaseDate");
                    String quantity = rs.getString("quantity");
                    String imgURL = rs.getString("imgURL");
                    String price = rs.getString("price");
                    TypeDAO tdao = new TypeDAO();
                    BrandDAO bdao = new BrandDAO();
                    Car_brand cb = bdao.getBrandById(brandID);
                    Car_type ct = tdao.getTypeById(typeID);

                    Car c = new Car(carID, ct, carName, cb, description, purchaseDate, Integer.parseInt(quantity), imgURL, Float.parseFloat(price));

                    lst.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lst;
    }

    public ArrayList<Car> searchAdmin(String stype, String sbrand, String year, String name) {
        String sql = "SELECT [carID]\n"
                + "      ,[typeID]\n"
                + "      ,[carName]\n"
                + "      ,[brandID]\n"
                + "      ,[description]\n"
                + "      ,[purchaseDate]\n"
                + "      ,[quantity]\n"
                + "      ,[imgURL]\n"
                + "      ,[price]\n"
                + "  FROM [OnlineCarRentingWebsite1].[dbo].[Car]\n"
                + "WHERE ";
        if (!stype.equals("")) {
            sql = sql + "typeID LIKE N'" + stype + "' ";
            if (!sbrand.equals("")) {
                sql = sql + "AND brandID LIKE N'" + sbrand + "' ";
                if (!year.equals("")) {
                    sql = sql + "AND purchaseDate LIKE N'%" + year + "%' ";
                    if (!name.equals("")) {
                        sql = sql + "AND carName LIKE N'%" + name + "%' ";
                    }
                } else if (!name.equals("")) {
                    sql = sql + "AND carName LIKE N'%" + name + "%' ";
                }
            } else if (!year.equals("")) {
                sql = sql + "AND purchaseDate LIKE N'%" + year + "%' ";
                if (!name.equals("")) {
                    sql = sql + "AND carName LIKE N'%" + name + "%' ";
                }
            } else if (!name.equals("")) {
                sql = sql + "AND carName LIKE N'%" + name + "%' ";
            }
        } else if (!sbrand.equals("")) {
            sql = sql + "brandID LIKE N'" + sbrand + "' ";
            if (!year.equals("")) {
                sql = sql + "AND purchaseDate LIKE N'%" + year + "%' ";
                if (!name.equals("")) {
                    sql = sql + "AND carName LIKE N'%" + name + "%' ";
                }
            } else if (!name.equals("")) {
                sql = sql + "carName LIKE N'%" + name + "%' ";
            }
        } else if (!year.equals("")) {
            sql = sql + "purchaseDate LIKE N'%" + year + "%' ";
            if (!name.equals("")) {
                sql = sql + "AND carName LIKE N'%" + name + "%' ";
            }
        } else if (!name.equals("")) {
            sql = sql + "carName LIKE N'%" + name + "%' ";
        }
        ArrayList<Car> lst = new ArrayList<>();
        try {
            con = DBUtils.makeConnection();
            if (con != null) {
                pstm = con.prepareStatement(sql);
                rs = pstm.executeQuery();
                while (rs.next()) {
                    String carID = rs.getString("carID");
                    String typeID = rs.getString("typeID");
                    String carName = rs.getString("carName");
                    String brandID = rs.getString("brandID");
                    String description = rs.getString("description");
                    String purchaseDate = rs.getString("purchaseDate");
                    String quantity = rs.getString("quantity");
                    String imgURL = rs.getString("imgURL");
                    String price = rs.getString("price");
                    TypeDAO tdao = new TypeDAO();
                    BrandDAO bdao = new BrandDAO();
                    Car_brand cb = bdao.getBrandById(brandID);
                    Car_type ct = tdao.getTypeById(typeID);

                    Car c = new Car(carID, ct, carName, cb, description, purchaseDate, Integer.parseInt(quantity), imgURL, Float.parseFloat(price));

                    lst.add(c);
                }
            }
            closeConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lst;
    }
    public String checkSql(String stype, String sbrand, String year, String name){
        String sql = "SELECT [carID]\n"
                + "      ,[typeID]\n"
                + "      ,[carName]\n"
                + "      ,[brandID]\n"
                + "      ,[description]\n"
                + "      ,[purchaseDate]\n"
                + "      ,[quantity]\n"
                + "      ,[imgURL]\n"
                + "      ,[price]\n"
                + "  FROM [OnlineCarRentingWebsite1].[dbo].[Car]\n"
                + "WHERE ";
        if (!stype.equals("")) {
            sql = sql + "typeID LIKE N'" + stype + "' ";
            if (!sbrand.equals("")) {
                sql = sql + "AND brandID LIKE N'" + sbrand + "' ";
                if (!year.equals("")) {
                    sql = sql + "AND purchaseDate LIKE N'%" + year + "%' ";
                    if (!name.equals("")) {
                        sql = sql + "AND carName LIKE N'%" + name + "%' ";
                    }
                } else if (!name.equals("")) {
                    sql = sql + "AND carName LIKE N'%" + name + "%' ";
                }
            } else if (!year.equals("")) {
                sql = sql + "AND purchaseDate LIKE N'%" + year + "%' ";
                if (!name.equals("")) {
                    sql = sql + "AND carName LIKE N'%" + name + "%' ";
                }
            } else if (!name.equals("")) {
                sql = sql + "AND carName LIKE N'%" + name + "%' ";
            }
        } else if (!sbrand.equals("")) {
            sql = sql + "brandID LIKE N'" + sbrand + "' ";
            if (!year.equals("")) {
                sql = sql + "AND purchaseDate LIKE N'%" + year + "%' ";
                if (!name.equals("")) {
                    sql = sql + "AND carName LIKE N'%" + name + "%' ";
                }
            } else if (!name.equals("")) {
                sql = sql + "carName LIKE N'%" + name + "%' ";
            }
        } else if (!year.equals("")) {
            sql = sql + "purchaseDate LIKE N'%" + year + "%' ";
            if (!name.equals("")) {
                sql = sql + "AND carName LIKE N'%" + name + "%' ";
            }
        } else if (!name.equals("")) {
            sql = sql + "carName LIKE N'%" + name + "%' ";
        }
        return sql;
    }
}
