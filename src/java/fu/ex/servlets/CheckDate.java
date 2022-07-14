/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlets;

import fu.ex.entities.ShoppingCart;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class CheckDate extends HttpServlet {

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

        try {
            String pickupDate = request.getParameter("pickupDate");
            String returnDate = request.getParameter("returnDate");
            long diffDays = 0;
            int flag = 0;
            HttpSession session = request.getSession(false);
            ArrayList<ShoppingCart> cart = (ArrayList<ShoppingCart>) session.getAttribute("cart");
            String id = request.getParameter("id");
            String valid = "";
            
            if (!pickupDate.equalsIgnoreCase("") && !returnDate.equalsIgnoreCase("")) {
                SimpleDateFormat d = new SimpleDateFormat("dd-MM-yyyy");
                LocalDate d1 = LocalDate.parse(pickupDate, DateTimeFormatter.ISO_LOCAL_DATE);
                LocalDate d2 = LocalDate.parse(returnDate, DateTimeFormatter.ISO_LOCAL_DATE);
                Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
                diffDays = diff.toDays();
                
                if (pickupDate == null || returnDate == null || diffDays < 0) {
                    valid = "Fail to enter date!";
                }
                if ((pickupDate != null && returnDate != null) && (diffDays > 0)) {
                    valid = "Success";
                    for(ShoppingCart sc : cart){
                        if(sc.getCar().getCarID().equalsIgnoreCase(id)){
                            sc.setPickupDate(pickupDate);
                            sc.setReturnDate(returnDate);
                        }
                    }
                }
            } else {
                flag = 1;
                valid = "Cannot be empty pickupDate and returnDate";
            }

            request.setAttribute("check", valid);
            request.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(request, response);
        } catch (Exception e) {
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
