/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlets;

import fu.ex.daos.BrandDAO;
import fu.ex.daos.CarDAO;
import fu.ex.daos.CustomerDAO;
import fu.ex.daos.TypeDAO;
import fu.ex.entities.Car;
import fu.ex.entities.Car_brand;
import fu.ex.entities.Car_type;
import fu.ex.entities.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tung Nguyen
 */
public class AdminPage extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private String SUCCESS = "/WEB-INF/view/adminpage.jsp";
    private String ERROR = "/WEB-INF/view/error.jsp";
    private String WRONG_ACCESS = "HomePage";
    private String INVALID = "LoginServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {

            HttpSession session = request.getSession(false);
            if (session.getAttribute("userdata") != null) {
                Customers acc = (Customers) request.getSession().getAttribute("userdata");
                if (acc.getRole().getRoleID().equals("2")) {

                    CustomerDAO cusdao = new CustomerDAO();
                    ArrayList<Customers> clst = cusdao.getAllCustomer();
                    request.setAttribute("chooseCus", clst);

                    CarDAO cDao = new CarDAO();
                    ArrayList<Car> listCars = new ArrayList<>();
                    listCars = cDao.getAllCar();
                    request.setAttribute("listS", listCars);

                    TypeDAO typeDao = new TypeDAO();
                    ArrayList<Car_type> listTypes = new ArrayList<Car_type>();
                    listTypes = typeDao.getAllTypes();
                    request.setAttribute("listTypes", listTypes);

                    BrandDAO brandDao = new BrandDAO();
                    ArrayList<Car_brand> listBrands = new ArrayList<Car_brand>();
                    listBrands = brandDao.getAllBrands();

                    request.setAttribute("listBrands", listBrands);
                    url = SUCCESS;

                } else {
                    request.setAttribute("alertmessage", "You are not permit to access this page!");
                    url = WRONG_ACCESS;
                }
            } else {
                request.setAttribute("errormessage", "You are not login!");
                url = INVALID;
            }

        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception ex) {
            Logger.getLogger(AdminPage.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
