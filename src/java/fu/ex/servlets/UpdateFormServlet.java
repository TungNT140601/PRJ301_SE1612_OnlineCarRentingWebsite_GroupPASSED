/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlets;

import fu.ex.daos.BrandDAO;
import fu.ex.daos.CarDAO;
import fu.ex.daos.TypeDAO;
import fu.ex.entities.Car;
import fu.ex.entities.CarError;
import fu.ex.entities.Car_brand;
import fu.ex.entities.Car_type;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tung Nguyen
 */
public class UpdateFormServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private final static String SUCCESS = "/WEB-INF/view/updatecar.jsp";
    private final static String ERROR = "/WEB-INF/view/error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            TypeDAO typeDao = new TypeDAO();
            ArrayList<Car_type> listTypes = new ArrayList<Car_type>();
            listTypes = typeDao.getAllTypes();
            request.setAttribute("listTypes", listTypes);

            BrandDAO brandDao = new BrandDAO();
            ArrayList<Car_brand> listBrands = new ArrayList<Car_brand>();
            listBrands = brandDao.getAllBrands();
            request.setAttribute("listBrands", listBrands);

            String id = request.getParameter("id");
            if (!id.equals("")) {
                System.out.println("ID : " + id);
                CarDAO dao = new CarDAO();
                Car c = dao.getCarById(id);
                request.setAttribute("updateCar", c);
            }else{
                System.out.println("No ID");
            }
            url = SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(UpdateFormServlet.class.getName()).log(Level.SEVERE, null, e);
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
