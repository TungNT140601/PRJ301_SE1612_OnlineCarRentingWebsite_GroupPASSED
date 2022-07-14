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
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tung Nguyen
 */
public class CreateServlet extends HttpServlet {

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
            String carID = request.getParameter("carID");
            String car_type = request.getParameter("car_type");
            String carName = request.getParameter("carName");
            String car_brand = request.getParameter("car_brand");
            String description = request.getParameter("description");
            String purchaseDate = request.getParameter("purchaseDate");
            String quantity = request.getParameter("quantity");
            String image = request.getParameter("image");
            String price = request.getParameter("price");

            CarDAO cDao = new CarDAO();
            float priceF = 0;
            int quantityI = 0;
            boolean valid = true;

            CarError errorObj = new CarError();
//ID-------------------------------------------------------------------------------------------            
            if (carID.trim().isEmpty()) {
                errorObj.setIdError("Car ID is not supposed to be empty");
                valid = false;
            }

            if (cDao.getCarById(carID) != null) {
                errorObj.setIdError("Car ID is existed");
                valid = false;
            }
//name-------------------------------------------------------------------------------------------            
            if (carName.trim().isEmpty()) {
                errorObj.setNameError("Car name is not supposed to be empty");
                valid = false;
            }
//description-------------------------------------------------------------------------------------------            
            if (description.trim().isEmpty()) {
                errorObj.setDescriptionError("Car description is not supposed to be empty");
                valid = false;
            }
//quantity-------------------------------------------------------------------------------------------            
            if (quantity.trim().isEmpty()) {
                errorObj.setQuantityError("Car quantity is not supposed to be empty");
                valid = false;
            } else {
                try {
                    quantityI = Integer.parseInt(quantity);

                } catch (Exception e) {
                    e.printStackTrace();
                    valid = false;
                    errorObj.setQuantityError("Car quantity must be numbered");
                }
            }
//url-------------------------------------------------------------------------------------------            
            if (image.trim().isEmpty()) {
                errorObj.setImgURLError("Car image URL is not supposed to be empty");
                valid = false;
            }
//price-------------------------------------------------------------------------------------------            
            if (price.trim().isEmpty()) {
                errorObj.setPriceError("Car price is not supposed to be empty");
                valid = false;
            } else {
                try {
                    priceF = Float.parseFloat(price);

                } catch (Exception e) {
                    e.printStackTrace();
                    valid = false;
                    errorObj.setQuantityError("Car price must be numbered");
                }
            }
//---------------------------------------------------------------------------------------------------------------
            Car_type type = new Car_type(car_type.split("-")[0].trim(), car_type.split("-")[1].trim());
            Car_brand brand = new Car_brand(car_brand.split("-")[0].trim(), car_brand.split("-")[1].trim());

            Car c = new Car(carID, type, carName, brand, description, purchaseDate, quantityI, image, priceF);
            if (valid) {
                if (cDao.insert(c)) {
                    response.sendRedirect("AdminPage");
                } else {
                    request.setAttribute("errMsg", "Insert failed");
                }
            } else {

                TypeDAO typeDao = new TypeDAO();
                ArrayList<Car_type> listTypes = new ArrayList<Car_type>();
                listTypes = typeDao.getAllTypes();
                request.setAttribute("listTypes", listTypes);

                BrandDAO brandDao = new BrandDAO();
                ArrayList<Car_brand> listBrands = new ArrayList<Car_brand>();
                listBrands = brandDao.getAllBrands();
                request.setAttribute("listBrands", listBrands);
                
                request.setAttribute("addCar", c);
                request.setAttribute("createError", errorObj);
                request.getRequestDispatcher("CreateFormServlet").forward(request, response);
                
            }
        } catch (Exception e) {
            log("ERROR at CreateServlet: " + e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
            System.out.println("Error at try catch");
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
