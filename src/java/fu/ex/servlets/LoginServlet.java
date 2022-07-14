/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlets;

import fu.ex.daos.CustomerDAO;
import fu.ex.entities.Customers;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tung Nguyen
 */
public class LoginServlet extends HttpServlet {

    private String ERROR = "WEB-INF/view/error.jsp";
    private String ADMIN = "AdminPage";
    private String USER = "HomePage";
    private String INVALID = "WEB-INF/view/loginpage.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/view/loginpage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {

            HttpSession session = request.getSession();

            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            CustomerDAO cDao = new CustomerDAO();

            Customers acc = cDao.checkLogin(userName, password);
            if (acc != null) {
                if (acc.getRole().getRoleID().equals("2")) {
                    session.setAttribute("userdata", acc);
                    response.sendRedirect(ADMIN);
                }
                if (acc.getRole().getRoleID().equals("1")) {
                    session.setAttribute("userdata", acc);
                    response.sendRedirect(USER);
                }
            } else {
                request.setAttribute("errMessage", "Incorrect !");
                request.getRequestDispatcher(INVALID).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, e);
            request.getRequestDispatcher(ERROR).forward(request, response);
        }
    }
}
