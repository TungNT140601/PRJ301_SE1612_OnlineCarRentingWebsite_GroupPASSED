<%-- 
    Document   : addcar
    Created on : Mar 9, 2022, 9:21:06 AM
    Author     : Tung Nguyen
--%>

<%@page import="fu.ex.entities.Customers"%>
<%@page import="fu.ex.entities.CarError"%>
<%@page import="fu.ex.entities.Car_brand"%>
<%@page import="fu.ex.entities.Car_type"%>
<%@page import="fu.ex.entities.Car"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Add Car</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <style>
            body {
                color: #566787;
                background: #f5f5f5;
                font-family: 'Varela Round', sans-serif;
                font-size: 13px;
            }
            .table-responsive {
                margin: 30px 0;
            }
            .table-wrapper {
                background: #fff;
                padding: 20px 25px;
                border-radius: 3px;
                min-width: 1000px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {        
                padding-bottom: 15px;
                background: #435d7d;
                color: #fff;
                padding: 16px 30px;
                min-width: 100%;
                margin: -20px -25px 10px;
                border-radius: 3px 3px 0 0;
            }
            .table-title h2 {
                margin: 5px 0 0;
                font-size: 24px;
            }
            .table-title .btn-group {
                float: right;
            }
            .table-title .btn {
                color: #fff;
                float: right;
                font-size: 13px;
                border: none;
                min-width: 50px;
                border-radius: 2px;
                border: none;
                outline: none !important;
                margin-left: 10px;
            }
            .table-title .btn i {
                float: left;
                font-size: 21px;
                margin-right: 5px;
            }
            .table-title .btn span {
                float: left;
                margin-top: 2px;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
                padding: 12px 15px;
                vertical-align: middle;
            }
            table.table tr th:first-child {
                width: 60px;
            }
            table.table tr th:last-child {
                width: 100px;
            }
            table.table-striped tbody tr:nth-of-type(odd) {
                background-color: #fcfcfc;
            }
            table.table-striped.table-hover tbody tr:hover {
                background: #f5f5f5;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }	
            table.table td:last-child i {
                opacity: 0.9;
                font-size: 22px;
                margin: 0 5px;
            }
            table.table td a {
                font-weight: bold;
                color: #566787;
                display: inline-block;
                text-decoration: none;
                outline: none !important;
            }
            table.table td a:hover {
                color: #2196F3;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #F44336;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table .avatar {
                border-radius: 50%;
                vertical-align: middle;
                margin-right: 10px;
            }
            .pagination {
                float: right;
                margin: 0 0 5px;
            }
            .pagination li a {
                border: none;
                font-size: 13px;
                min-width: 30px;
                min-height: 30px;
                color: #999;
                margin: 0 2px;
                line-height: 30px;
                border-radius: 2px !important;
                text-align: center;
                padding: 0 6px;
            }
            .pagination li a:hover {
                color: #666;
            }	
            .pagination li.active a, .pagination li.active a.page-link {
                background: #03A9F4;
            }
            .pagination li.active a:hover {        
                background: #0397d6;
            }
            .pagination li.disabled i {
                color: #ccc;
            }
            .pagination li i {
                font-size: 16px;
                padding-top: 6px
            }
            .hint-text {
                float: left;
                margin-top: 10px;
                font-size: 13px;
            }    
            /* Custom checkbox */
            .custom-checkbox {
                position: relative;
            }
            .custom-checkbox input[type="checkbox"] {    
                opacity: 0;
                position: absolute;
                margin: 5px 0 0 3px;
                z-index: 9;
            }
            .custom-checkbox label:before{
                width: 18px;
                height: 18px;
            }
            .custom-checkbox label:before {
                content: '';
                margin-right: 10px;
                display: inline-block;
                vertical-align: text-top;
                background: white;
                border: 1px solid #bbb;
                border-radius: 2px;
                box-sizing: border-box;
                z-index: 2;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                content: '';
                position: absolute;
                left: 6px;
                top: 3px;
                width: 6px;
                height: 11px;
                border: solid #000;
                border-width: 0 3px 3px 0;
                transform: inherit;
                z-index: 3;
                transform: rotateZ(45deg);
            }
            .custom-checkbox input[type="checkbox"]:checked + label:before {
                border-color: #03A9F4;
                background: #03A9F4;
            }
            .custom-checkbox input[type="checkbox"]:checked + label:after {
                border-color: #fff;
            }
            .custom-checkbox input[type="checkbox"]:disabled + label:before {
                color: #b8b8b8;
                cursor: auto;
                box-shadow: none;
                background: #ddd;
            }
            /* Modal styles */
            .modal .modal-dialog {
                max-width: 400px;
            }
            .modal .modal-header, .modal .modal-body, .modal .modal-footer {
                padding: 20px 30px;
            }
            .modal .modal-content {
                border-radius: 3px;
                font-size: 14px;
            }
            .modal .modal-footer {
                background: #ecf0f1;
                border-radius: 0 0 3px 3px;
            }
            .modal .modal-title {
                display: inline-block;
            }
            .modal .form-control {
                border-radius: 2px;
                box-shadow: none;
                border-color: #dddddd;
            }
            .modal textarea.form-control {
                resize: vertical;
            }
            .modal .btn {
                border-radius: 2px;
                min-width: 100px;
            }	
            .modal form label {
                font-weight: normal;
            }	
        </style>
        <script>
            $(document).ready(function () {
                // Activate tooltip
                $('[data-toggle="tooltip"]').tooltip();

                // Select/Deselect checkboxes
                var checkbox = $('table tbody input[type="checkbox"]');
                $("#selectAll").click(function () {
                    if (this.checked) {
                        checkbox.each(function () {
                            this.checked = true;
                        });
                    } else {
                        checkbox.each(function () {
                            this.checked = false;
                        });
                    }
                });
                checkbox.click(function () {
                    if (!this.checked) {
                        $("#selectAll").prop("checked", false);
                    }
                });
            });
        </script>
    </head>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="AdminPage">Công ty trách nhiệm hữu hạn một mình tôi</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="AdminPage">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
                    </ul>
                    <form class="d-flex">
                        <%
                            Customers user = (Customers) request.getSession().getAttribute("userdata");
                        %>
                        <button type="button" class="btn btn-dark">
                            <%
                                if (user != null) { %>
                            <a href="LogoutServlet">Logout</a>
                            <%  } else {  %>
                            <a href="LoginServlet">Login</a>
                            <% } %>
                        </button>
                    </form>
                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Shop in style</h1>
                    <p class="lead fw-normal text-white-50 mb-0">With this shop hompeage template</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
            <!-- Edit Modal HTML -->
            <div>
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="CreateServlet" method="POST">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add New Car</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">
                                <%
                                    CarError createError = (CarError) request.getAttribute("createError");
                                    Car addCar = (Car) request.getAttribute("addCar");
                                %>
                                <table>
                                    <tr class="form-group">
                                                                <td>Car ID :</td><td><input type="text" name="carID" value="<% if (addCar != null) {
                                                out.print(addCar.getCarID());
                                            }%>" 
                                                                class="form-control" required></td><td><font color="red">
                                            <% if (createError != null) {
                                                    out.print(createError.getIdError());
                                                }
                                            %>
                                            </font></td>
                                    </tr>
                                    <tr class="form-group">
                                        <td>Type :</td><td><select name="car_type">
                                                <%
                                                    ArrayList<Car_type> lstType = new ArrayList<Car_type>();

                                                    lstType = (ArrayList< Car_type>) request.getAttribute("listTypes");
                                                    if (lstType != null) {
                                                        for (Car_type dt : lstType) {%>
                                                <option><%=dt.getTypeID()%>-<%=dt.getTypeType()%></option>
                                                <%  }
                                                    }%>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr class="form-group">
                                                                  <td>Car name :</td><td><input type="text" name="carName" value="<% if (addCar != null) {
                                                out.print(addCar.getCarName());
                                            }%>" 
                                                                  class="form-control" required></td><td><font color="red">
                                            <% if (createError != null) {
                                                    out.print(createError.getNameError());
                                                }
                                            %>
                                            </font></td>
                                    </tr>
                                    <tr class="form-group">
                                        <td>Brand :</td><td><select name="car_brand">
                                                <%
                                                    ArrayList<Car_brand> lstBrand = new ArrayList<Car_brand>();

                                                    lstBrand = (ArrayList< Car_brand>) request.getAttribute("listBrands");
                                                    if (lstType != null) {
                                                        for (Car_brand dt : lstBrand) {%>
                                                <option><%=dt.getBrandID()%>-<%=dt.getBrandName()%></option>
                                                <%  }
                                                    }%>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr class="form-group">
                                        <td>Car description :</td>
                                        <td>
                                            <textarea name="description"><% if (addCar != null) {
                                                out.print(addCar.getDescription());
                                            }%></textarea>
                                        </td>
                                        <td>
                                            <font color="red">
                                            <% if (createError != null) {
                                                    out.print(createError.getDescriptionError());
                                                }
                                            %>
                                            </font>
                                        </td>
                                    </tr>
                                    <tr class="form-group">
                                                                       <td>Purchase Date :</td><td><input type="date" name="purchaseDate" value="<% if (addCar != null) {
                                                out.print(addCar.getPurchaseDate());
                                            }%>" 
                                                                       class="form-control" required></td><td><font color="red">
                                            <% if (createError != null) {
                                                    out.print(createError.getPurchaseError());
                                                }
                                            %>
                                            </font></td>
                                    </tr>
                                    <tr class="form-group">
                                                                  <td>Quantity :</td><td><input type="number" name="quantity" min="0" max="50" value="<% if (addCar != null) {
                                                out.print(addCar.getQuantity());
                                            }%>" 
                                                                  class="form-control" required></td><td><font color="red">
                                            <% if (createError != null) {
                                                    out.print(createError.getQuantityError());
                                                }
                                            %>
                                            </font></td>
                                    </tr>
                                    <tr class="form-group">
                                                                   <td>Image URL :</td><td><input type="text" name="image" value="<% if (addCar != null) {
                                                out.print(addCar.getImgURL());
                                            }%>" 
                                                                   class="form-control" required></td><td><font color="red">
                                            <% if (createError != null) {
                                                    out.print(createError.getImgURLError());
                                                }
                                            %>
                                            </font></td>
                                    </tr>
                                    <tr class="form-group">
                                                               <td>Price :</td><td><input type="text" name="price" value="<% if (addCar != null) {
                                                out.print(addCar.getPrice());
                                            }%>" 
                                                               class="form-control" required></td><td><font color="red">
                                            <% if (createError != null) {
                                                    out.print(createError.getPriceError());
                                                }
                                            %>
                                            </font></td>
                                    </tr>

                                </table>					
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2021</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>


