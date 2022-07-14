<%-- 
    Document   : history.jsp
    Created on : Mar 14, 2022, 11:07:00 AM
    Author     : Admin
--%>

<%@page import="java.util.Date"%>
<%@page import="fu.ex.entities.ShoppingCart"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History</title>
    </head>
    <body>
        <table cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
            <thead>
                <tr>
                    <th>Rent ID</th>
                    <th>Car ID</th>
                    <th>Quantity</th>
                    <th>Pickup Date</th>
                    <th>Confirm Return Date</th>
                    <th>Image</th>
                    <th>Amount</th>
                    <th>Old Return Date</th>
                    <th>Option</th>

                </tr>
            </thead>
            <tbody>
                <%
                    ArrayList<ShoppingCart> lst = new ArrayList<ShoppingCart>();
                    lst = (ArrayList<ShoppingCart>) request.getAttribute("history");
                    String date = (String) request.getAttribute("return");

                    if (lst != null) {
                        for (int i = 0; i < lst.size(); i++) {%>
                <tr>
                    <td style="text-align: center"><%=lst.get(i).getRentID().getRentID()%></td>
                    <td style="text-align: center"><%=lst.get(i).getCar().getCarID()%></td>
                    <td style="text-align: center"><%=lst.get(i).getQuantity()%></td>
                    <td style="text-align: center"><%=lst.get(i).getPickupDate()%></td>
                    <td style="text-align: center"><%=lst.get(i).getReturnDate()%></td>
                    <td style="text-align: center"><img src="<%=lst.get(i).getCar().getImgURL()%>" width="200" height="150"></td>
                    <td style="text-align: center"><%=lst.get(i).getRentID().getAmount()%></td>
                    <td style="text-align: center"><% if (date != null) {
                            int index = (int) request.getAttribute("index");
                            if (index == i) {
                                out.print(date);
                            }
                        }%> </td>
                    <td style="text-align: center">
                        <button><a style="text-decoration: none;" onclick="return confirm('Are you sure return your rental car?')" href="ReturnCar?rid=<%=lst.get(i).getRentID().getRentID()%>&cid=<%=lst.get(i).getCar().getCarID()%>&index=<%=i%>">Return</a></button> 
                    </td>
                </tr>
                <%  }
                } else { %>
            <td colspan="5">You not rent anything</td>
            <% }%>
        </tbody>
    </table>
    <br>
    <div style="text-align: center"><a href="HomePage" style="text-decoration: none;">Home</a> | <a href="LogoutServlet"  style="text-decoration: none;">Logout</a></div>
</body>
</html>
