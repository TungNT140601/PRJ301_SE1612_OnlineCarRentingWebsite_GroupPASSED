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
import fu.ex.entities.Car_brand;
import fu.ex.entities.Car_type;
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
 * @author tuanbee
 */
public class CarDetails extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String URL = "Error.jsp";
        String brand = "brand";
        String type = "type";
        String name = "name";
        String searchname = "searchname";
        try {
            String check = request.getParameter("search");
            if (brand.equals(check)) {
                String search = request.getParameter("bid");
                CarDAO dao = new CarDAO();
                TypeDAO tdao = new TypeDAO();
                BrandDAO bdao = new BrandDAO();
                Car listcb = dao.getCarByName(search);
                ArrayList<Car_brand> lstCB = new ArrayList<>();
                ArrayList<Car_type> lstCT = new ArrayList<>();
                lstCB = bdao.getAllBrands();
                lstCT = tdao.getAllTypes();
                request.setAttribute("cbs", listcb);
                request.setAttribute("lstCT", lstCT);
                request.setAttribute("lstCB", lstCB);
                request.setAttribute("gc", listcb);
                URL = "/WEB-INF/view/cardetails.jsp";
            }
            if (type.equals(check)) {
                String search = request.getParameter("tid");

                CarDAO dao = new CarDAO();
                TypeDAO tdao = new TypeDAO();
                BrandDAO bdao = new BrandDAO();
                Car listct = dao.getCarByName(search);
                ArrayList<Car_brand> lstCB = new ArrayList<>();
                ArrayList<Car_type> lstCT = new ArrayList<>();
                lstCB = bdao.getAllBrands();
                lstCT = tdao.getAllTypes();
                request.setAttribute("tbs", listct);
                request.setAttribute("lstCT", lstCT);
                request.setAttribute("lstCB", lstCB);
                request.setAttribute("gc", listct);
                URL = "/WEB-INF/view/cardetails.jsp";
            }
            if (name.equals(check)) {
                String search = request.getParameter("nid");
                CarDAO dao = new CarDAO();
                TypeDAO tdao = new TypeDAO();
                BrandDAO bdao = new BrandDAO();
                Car listcb = dao.getCarByName(search);
                ArrayList<Car_brand> lstCB = new ArrayList<>();
                ArrayList<Car_type> lstCT = new ArrayList<>();
                lstCB = bdao.getAllBrands();
                lstCT = tdao.getAllTypes();
                request.setAttribute("cn", listcb);
                request.setAttribute("lstCT", lstCT);
                request.setAttribute("lstCB", lstCB);
                request.setAttribute("gc", listcb);
                URL = "/WEB-INF/view/cardetails.jsp";
            }
            if (searchname.equals(check)) {
                String search = request.getParameter("sid");
                CarDAO dao = new CarDAO();
                TypeDAO tdao = new TypeDAO();
                BrandDAO bdao = new BrandDAO();
                Car listcb = dao.getCarByName(search);
                ArrayList<Car_brand> lstCB = new ArrayList<>();
                ArrayList<Car_type> lstCT = new ArrayList<>();
                lstCB = bdao.getAllBrands();
                lstCT = tdao.getAllTypes();
                request.setAttribute("lc", listcb);
                request.setAttribute("lstCT", lstCT);
                request.setAttribute("lstCB", lstCB);
                request.setAttribute("gc", listcb);
                URL = "/WEB-INF/view/cardetails.jsp";
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            request.getRequestDispatcher(URL).forward(request, response);

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
