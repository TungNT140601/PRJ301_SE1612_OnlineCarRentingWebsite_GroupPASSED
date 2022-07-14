/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fu.ex.servlets;

import fu.ex.daos.CustomerDAO;
import fu.ex.daos.RoleDAO;

import fu.ex.entities.Customers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tuanbee
 */
public class UserRegister extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/registerpage.jsp").forward(request, response);

    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String user = request.getParameter("customerID");
            String pass = request.getParameter("password");
            String repass = request.getParameter("confirm");
            String role = "1";
            String name = request.getParameter("fullName");
            String phone = request.getParameter("mobile");
            String place = request.getParameter("place");
            String country = request.getParameter("country");
            String email = request.getParameter("email");
            if (!pass.equals(repass)) {
                request.setAttribute("errMessage", "Wrong confirm password!");
                request.getRequestDispatcher("/WEB-INF/view/registerpage.jsp").forward(request, response);
            }
            CustomerDAO dao = new CustomerDAO();
            Customers a = dao.getCustomerById(user);
            if (a == null) {
                RoleDAO rdao = new RoleDAO();
                dao.insert(new Customers(user, rdao.getRoleById(role), pass, name, phone, place, country, email));
                request.setAttribute("Message", "Create acount successfully!");
                request.getRequestDispatcher("LoginServlet").forward(request, response);
            } else {
                request.setAttribute("errMessage", "User is exist!");
                request.getRequestDispatcher("/WEB-INF/view/registerpage.jsp").forward(request, response);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
