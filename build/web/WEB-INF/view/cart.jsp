<%-- 
    Document   : cart
    Created on : Mar 6, 2022, 6:33:38 PM
    Author     : Admin
--%>

<%@page import="fu.ex.daos.ShoppingCartDAO"%>
<%@page import="fu.ex.entities.Customers"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fu.ex.entities.ShoppingCart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>        
        <title>Cart</title>
        <style type="text/css">
            header{
                height: 120px;
                align-items: center;
            }
            section{

            }
            .img{
                text-align: center;
                border: 1px solid green;
                padding: 5px;
                margin: 5px;
                height: 250px;
                width: 200px;
                float: left;
            }
            footer{
                clear: both;
                height: 120px;
                align-items: center;

            }
        </style>
    </head>
    <body>
        <header>
            <img alt="" src="" >
        </header>
        <% String errMsg = (String) request.getParameter("errMsg");
            if (errMsg != null) {%>
        <font color="red"><%=errMsg%></font>
        <% } %>
        <section>
            <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">

                <tr>
                    <th>Car ID</th>
                    <th>Car Name</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Confirm</th>
                    <th>Image</th>
                    <th>Sub Total</th>
                    <th>Option</th>

                </tr>
                <%
                    ArrayList<ShoppingCart> ldt = new ArrayList<>();
                    ldt = (ArrayList<ShoppingCart>) session.getAttribute("cart");
                    String check = (String) request.getAttribute("check");
                    float total = 0;
                    if (ldt != null) {
                        for (ShoppingCart dt : ldt) {
                            if (!dt.getPickupDate().equals("") && !dt.getReturnDate().equals("")) {
                                ShoppingCartDAO sdao = new ShoppingCartDAO();
                                long diffDate = sdao.countDate(dt.getPickupDate(), dt.getReturnDate());
                                total = total + ((dt.getCar().getPrice() * diffDate) * dt.getQuantity());
                            }%>

                <tr>
                    <td style="text-align: center"><%=dt.getCar().getCarID()%></td>
                    <td style="text-align: center"><%=dt.getCar().getCarName()%></td>                        
                    <td style="text-align: center"><%=dt.getCar().getPrice()%></td>
                    <td style="text-align: center"><%=dt.getQuantity()%></td>

                    <td style="display: flex; justify-content: space-around">
                        <form action="CheckDate?id=<%=dt.getCar().getCarID()%>" method="POST" >                                    
                            <input type="date" name="pickupDate" value="<%=dt.getPickupDate()%>">
                            <input type="date" name="returnDate" value="<%=dt.getReturnDate()%>">
                            <input type="submit" value="Confirm" >
                            <font color="green"> <% if (check != null) {
                                    out.print(check);
                                }%> </font>
                        </form>  
                    </td>   
                    <td style="text-align: center"><img src="<%=dt.getCar().getImgURL()%>" width="150" height="100"></td>
                    <td style="text-align: center">
                        <% if (!dt.getPickupDate().equals("") && !dt.getReturnDate().equals("")) {
                                ShoppingCartDAO sdao = new ShoppingCartDAO();
                                long diffDate = sdao.countDate(dt.getPickupDate(), dt.getReturnDate());
                                out.print((dt.getCar().getPrice() * diffDate) * dt.getQuantity());
                            }%> 
                    </td>
                    <td align="center">
                        <a  style="text-decoration: none;" href="CartServlet?action=remove&id=<%=dt.getCar().getCarID()%>"
                            onclick="return confirm('Are you sure?)"> Remove</a>
                    </td>
                </tr>
                <% if (ldt.size() == 0) { %>
                <tr>
                    <th colspan="7" style="text-align: center" ><br> Your cart is empty!</th>
                </tr>
                <% }%>
                <%
                    }
                } else {
                %>
                <tr>
                    <th colspan="7" style="text-align: center" ><br> Your cart is empty!</th>
                </tr>
                <%
                    }
                %>

                <tr>    
                    <td colspan="6" style="text-align: center" >Total</td>
                    <td style="text-align: center"><%=total%></td>
                    <% if (total != 0) { %>
                    <td style="text-align: center"><a href="BuyToSaveServlet"  style="text-decoration: none;">Rent</a></td>
                    <% }%>
                </tr>
            </table>
        </section>
        <div style="text-align: center; margin-top: 20px">
            <%  Customers cus = (Customers) session.getAttribute("userdata");
                out.println("Hello " + cus.getFullname() + " - ");
            %>
            <a href="HomePage" style="text-decoration: none">HomePage</a> | <a href="CartServlet?action=empty"  style="text-decoration: none;"> Empty Cart</a> | <a href="History" style="text-decoration: none">History</a>
        </div>

    </body>
</html>
