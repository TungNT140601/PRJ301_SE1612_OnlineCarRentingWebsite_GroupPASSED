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
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Tung Nguyen
 */
public class UpdateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String SUCCESS = "AdminPage";
    private static final String ERROR = "/WEB-INF/view/error.jsp";
    private static final String INVALID = "UpdateFormServlet";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = ERROR;
        try {
            String carID = request.getParameter("carID").toUpperCase();
            String car_type = request.getParameter("car_type");
            String carName = request.getParameter("carName");
            String car_brand = request.getParameter("car_brand");
            String description = request.getParameter("description");
            String purchaseDate = request.getParameter("purchaseDate");
            String quantity = request.getParameter("quantity");
            String image = request.getParameter("image");
            String price = request.getParameter("price");
            int quantityI = 0;
            float priceF = 0;
            boolean valid = true;
            System.out.println(image);
            CarError errorObj = new CarError();
//carName-------------------------------------------------------------------------------------------
            if (carName.trim().isEmpty()) {
                errorObj.setNameError("Car name is not supposed to be empty");
                valid = false;
            }
//description-------------------------------------------------------------------------------------------
            if (description.trim().isEmpty()) {
                errorObj.setDescriptionError("Description is not supposed to be empty");
                valid = false;
            }
//Year of production-------------------------------------------------------------------------------------------
            if (quantity.trim().isEmpty()) {
                errorObj.setPurchaseError("Quantity is not supposed to be empty");
                valid = false;
            } else {
                try {
                    quantityI = Integer.parseInt(quantity);
                } catch (Exception e) {
                    if (e != null) {
                        valid = false;
                    }
                }
            }
//carName-------------------------------------------------------------------------------------------
            if (image.trim().isEmpty()) {
                errorObj.setImgURLError("Car imageURL is not supposed to be empty");
                valid = false;
            }
//Year of production-------------------------------------------------------------------------------------------
            if (price.trim().isEmpty()) {
                errorObj.setPriceError("Price is not supposed to be empty");
                valid = false;
            } else {
                try {
                    priceF = Float.parseFloat(price);
                    if(priceF <= 0){
                        errorObj.setPriceError("Price must be greater than 0");
                        valid = false;
                        
                    }
                } catch (Exception e) {
                    if (e != null) {
                        errorObj.setPriceError("Price must be numbered");
                        valid = false;
                    }
                }
            }
//-------------------------------------------------------------------------------------------             
            TypeDAO tDao = new TypeDAO();
            BrandDAO bDao = new BrandDAO();
            CarDAO dao = new CarDAO();
            Car car = new Car(carID, tDao.getTypeById(car_type.split("-")[0].trim()), carName, bDao.getBrandById(car_brand.split("-")[0].trim()), description, purchaseDate, quantityI, image, priceF);
            
            if (valid) {
                if (dao.update(car)) {
                    response.sendRedirect(SUCCESS);
                } else {
                    request.setAttribute("errMessage", "Update failed");
                }
            } else {
                url = INVALID;
                request.setAttribute("updateCar", car);
                request.setAttribute("updateError", errorObj);
                request.getRequestDispatcher(url).forward(request, response);
            }
        }catch(Exception e){
            log("ERROR at UpdateServlet: " + e.getMessage());
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
