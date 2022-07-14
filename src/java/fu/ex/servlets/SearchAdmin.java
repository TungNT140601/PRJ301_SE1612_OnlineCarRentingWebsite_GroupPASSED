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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tung Nguyen
 */
public class SearchAdmin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "/WEB-INF/view/adminpage.jsp";
    private static final String ERROR = "/WEB-INF/view/error.jsp";
    private static final String WRONG_ACCESS = "HomePage";
    private static final String INVALID = "LoginServlet";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            HttpSession session = request.getSession(false);

            if (session.getAttribute("userdata") != null) {
                Customers acc = (Customers) request.getSession().getAttribute("userdata");
                if (acc.getRole().getRoleID().equals("2")) {

                    String stype = request.getParameter("stype");
                    if (stype.equals("None")) {
                        stype = "";
                    } else {
                        stype = stype.split("-")[0].trim();
                    }
                    String sbrand = request.getParameter("sbrand");
                    if (sbrand.equals("None")) {
                        sbrand = "";
                    } else {
                        sbrand = sbrand.split("-")[0].trim();
                    }
                    String year = request.getParameter("year");
                    if (year.equals("None")) {
                        year = "";
                    }
                    CustomerDAO cusdao = new CustomerDAO();
                    ArrayList<Customers> clst = cusdao.getAllCustomer();
                    request.setAttribute("chooseCus", clst);
                    String name = request.getParameter("scarname");
                    System.out.println(stype + "-" + sbrand + "-" + year + "-" + name);
                    CarDAO cdao = new CarDAO();
                    System.out.println(cdao.checkSql(stype, sbrand, year, name));
                    ArrayList<Car> listS = cdao.searchAdmin(stype, sbrand, year, name);
                    request.setAttribute("listS", listS);
                    TypeDAO typeDao = new TypeDAO();
                    ArrayList<Car_type> listTypes = null;
                    listTypes = typeDao.getAllTypes();
                    request.setAttribute("listTypes", listTypes);

                    BrandDAO brandDao = new BrandDAO();
                    ArrayList<Car_brand> listBrands = null;
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
        } catch (Exception e) {
            e.printStackTrace();
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
