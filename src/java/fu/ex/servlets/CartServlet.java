/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlets;

import fu.ex.daos.CarDAO;
import fu.ex.daos.RentDAO;
import fu.ex.entities.Customers;
import fu.ex.entities.Rent;
import fu.ex.entities.ShoppingCart;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class CartServlet extends HttpServlet {

    public CartServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession(false);

        if (session.getAttribute("userdata") != null) {
            if (action == null) {
                displayCart(request, response); // hiển thị giỏ hàng
            } else {
                if (action.equalsIgnoreCase("buy")) {
                    buyItem(request, response); // thêm sản phẩm vào giỏ hàng
                } else if (action.equalsIgnoreCase("remove")) {
                    removeItem(request, response); // Xóa sản phầm trong giỏ hàng
                } else if (action.equalsIgnoreCase("empty")) {
                    emptyCart(request, response); //xáo giỏ hàng
                }
            }
        } else {
            request.setAttribute("erormessage", "Incorrect!");
            request.getRequestDispatcher("LoginServlet").forward(request, response);
        }
    }

    protected void displayCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/cart.jsp").forward(request, response);
    }

    protected void removeItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        ArrayList<ShoppingCart> cart = (ArrayList<ShoppingCart>) session.getAttribute("cart");
        int index = isExisting(request.getParameter("id"), cart);
        if (cart.get(index).getQuantity() > 1) {
            int quantity = cart.get(index).getQuantity() - 1;
            cart.get(index).setQuantity(quantity);
            session.setAttribute("cart", cart);
            response.sendRedirect("CartServlet");
        } else if (cart.get(index).getQuantity() == 1) {
            cart.remove(index);
            session.setAttribute("cart", cart);
            response.sendRedirect("CartServlet");
        }

    }

    protected void emptyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("cart");

        response.sendRedirect("CartServlet");
    }

    protected void buyItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDAO carDao = new CarDAO();
        HttpSession session = request.getSession();
        try {        
            RentDAO rdao = new RentDAO();
            int i = 0;
            if (rdao.getAllRent() == null) {
                i = 0;
            } else {
                i = rdao.getAllRent().size();
            }
            Rent rent = new Rent("" + i, 0, (Customers) session.getAttribute("userdata"));
            session.setAttribute("rent", rent);
            if (session.getAttribute("cart") == null) {
                List<ShoppingCart> cart = new ArrayList<ShoppingCart>();
                cart.add(new ShoppingCart(carDao.find(request.getParameter("id")), 1, rent, "", ""));
                session.setAttribute("cart", cart);
            } else {
                ArrayList<ShoppingCart> cart = (ArrayList<ShoppingCart>) session.getAttribute("cart");
                int index = isExisting(request.getParameter("id"), cart);
                if (index == -1) {
                    cart.add(new ShoppingCart(carDao.find(request.getParameter("id")), 1, rent, "", ""));
                } else {
                    int quantity = cart.get(index).getQuantity() + 1;
                    cart.get(index).setQuantity(quantity);
                }
                session.setAttribute("cart", cart);
            }
            response.sendRedirect("CartServlet");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int isExisting(String id, ArrayList<ShoppingCart> cart) {
        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getCar().getCarID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
