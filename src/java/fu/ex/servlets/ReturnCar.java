/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlets;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import fu.ex.daos.CarDAO;
import fu.ex.daos.ShoppingCartDAO;
import fu.ex.entities.Car;
import fu.ex.entities.Customers;
import fu.ex.entities.ShoppingCart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class ReturnCar extends HttpServlet {

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
        try{
            String cid = request.getParameter("cid");
            String rid = request.getParameter("rid");
            int index = -1;
            try{
                index = Integer.parseInt(request.getParameter("index"));
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("Error");
            }
            

//            String index = request.getParameter("index");
            
            System.out.println("full \""+cid + "\"");
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");           
            Date date = new Date();
            ShoppingCartDAO scdao = new ShoppingCartDAO();
            ShoppingCart cart = scdao.getRentById(rid, cid);
            String date1 = cart.getReturnDate();
            
            cart.setReturnDate(d.format(date));  
            scdao.update(cart);
            
            CarDAO cdao = new CarDAO();
            Car c = cdao.getCarById(cid);
            System.out.println("Aasddafsfdfsdafsadfas");
            System.out.println("Before :"+c.getQuantity());
            c.setQuantity(c.getQuantity() + cart.getQuantity());
            System.out.println("After :"+c.getQuantity());
            cdao.update(c);
//            request.setAttribute("today", d.format(date));
            request.setAttribute("return", date1);
//            if(index != null){
                request.setAttribute("index", index);
//            }
            request.getRequestDispatcher("History").forward(request, response);
        }catch(Exception e){
            e.printStackTrace();
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
