<%-- 
    Document   : adminpage
    Created on : Mar 8, 2022, 1:38:43 PM
    Author     : Tung Nguyen
--%>

<%@page import="fu.ex.entities.Customers"%>
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
        <title>Shop Management Page</title>
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
            .img-responsive {
                max-width: 100%;
                width: 100%;

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
        <%
            String errMsg = (String) request.getAttribute("errMsg");
            if(errMsg != null){%>
            <script>
                window.alert("<%=errMsg%>");
            </script> 
           <% } %>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="HomePage">Công ty trách nhiệm hữu hạn một mình tôi</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="AdminPage">Admin Page</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">About</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a target="_blank" rel="noopener noreferrer" class="dropdown-item" href="https://www.facebook.com/tuanblep">Nguyen Anh Tuan</a></li>
                                <li><a target="_blank" rel="noopener noreferrer" class="dropdown-item" href="https://www.facebook.com/trinhhuu.truong.1">Trinh Huu Truong</a></li>
                                <li><a target="_blank" rel="noopener noreferrer" class="dropdown-item" href="https://www.facebook.com/nttung.1406">Nguyen Thanh Tung</a></li>
                                <li><a target="_blank" rel="noopener noreferrer" class="dropdown-item" href="https://www.facebook.com/profile.php?id=100056065892536">Hoang Khac Tri</a></li>
                            </ul>
                        </li>
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                            <li class="nav-item"><a class="nav-link active" aria-current="page" href=""><% if (session.getAttribute("userdata") == null) {%>
                                    <%} else if (session.getAttribute("userdata") != null) {%>
                                    <footer style="">
                                        <% Customers cus = (Customers) request.getSession().getAttribute("userdata");%>
                                        Hello <%= cus.getFullname()%> 
                                    </footer>
                                    <%}%></a></li>
                        </ul>
                    </ul>

                    <form action="MainController" method="POST" class="d-flex">
                        <% if (session.getAttribute("userdata") == null) {%>
                        <button type="submit" name="action" value="Loginhomepage" class="btn btn-dark">Login</button>
                        <%} else if (session.getAttribute("userdata") != null) {%>
                        <button type="submit" name="action" value="Logout" class="btn btn-dark" onclick="return confirm('Do you want to log out ?')">Logout</button>

                        <%}%>
                    </form>
                </div>
            </div>
        </nav>
        <!-- Header-->
        <header class="bg-dark py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">Shop Management</h1>
                    <p class="lead fw-normal text-white-50 mb-0">Group PASSED</p>
                </div>
            </div>
        </header>
        <!-- Section-->
        <section class="py-5">
            <div class="container-xl">
                <div>
                    <p>View rent by: </p>
                    <div>
                        <%
                            ArrayList<Customers> clst = (ArrayList<Customers>) request.getAttribute("chooseCus");
                        %>

                        <table cellpadding="0" cellspacing="0" border="0"  width="25%">
                            <tbody>
                                <tr>
                            <form action="ViewCarRent" method="POST">
                                <td><p>Customer :</p></td>
                                <td>
                                    <select name="cus">
                                        <% for (Customers c : clst) {
                                                if (c.getRole().getRoleID().equalsIgnoreCase("1")) {%>
                                        <option value="<%=c.getCustomerID()%>"><%=c.getFullname()%></option>
                                        <% }
                                            }%>
                                    </select>
                                </td>
                                <td>
                                    <button type="submit">Go</button>
                                </td>
                            </form>  
                            </tr> 
                            <tr>
                            <form action="ViewCarRent" method="POST">
                                <td><p>Date :</p></td>
                                <td>
                                    <input type="date" name="date">
                                </td>
                                <td>
                                    <button type="submit">Go</button>
                                </td>
                            </form>  
                            </tr> 
                            </tbody>
                        </table>
                    </div>           
                </div>
                <form action="SearchAdmin" method="POST">
                    <table class="table table-striped table-hover" cellpadding="0" cellspacing="0" border="1" align="center" width="75%">
                        <thead>
                            <tr>
                                <th>Search</th>
                                <th>Type :</th>
                                <th> <select name="stype">
                                        <option>None</option>
                                        <%
                                            ArrayList<Car_type> ctype = (ArrayList<Car_type>) request.getAttribute("listTypes");
                                            for (Car_type ct : ctype) {
                                        %>
                                        <option><%=ct.getTypeID()%>-<%=ct.getTypeType()%></option>
                                        <% } %>
                                    </select></th>
                                <th>Brand : </th>
                                <th><select name="sbrand">
                                        <option>None</option>
                                        <%
                                            ArrayList<Car_brand> cbrand = (ArrayList<Car_brand>) request.getAttribute("listBrands");
                                            for (Car_brand cb : cbrand) {
                                        %>
                                        <option><%=cb.getBrandID()%>-<%=cb.getBrandName()%></option>
                                        <% } %>
                                    </select></th>
                                <th>Year Purchase  : </th>
                                <th><select name="year">
                                        <option>None</option>
                                        <%
                                            for (int i = 1990; i <= 2050; i++) {
                                        %>
                                        <option><%=i%></option>
                                        <% } %>
                                    </select></th>
                                <th>Name :</th>
                                <th><input type="text" name="scarname" value=""></th>
                                <th><button type="submit">Go</button></th>
                            </tr>
                        </thead>
                    </table>
                </form>
                <div class="table-responsive">
                    <% ArrayList<Car> listS = (ArrayList<Car>) request.getAttribute("listS");
                        if (listS != null) {
                            if (listS.size() != 0) {
                    %>
                    <div class="table-wrapper">
                        <div class="table-title">
                            <div class="row">
                                <div class="col-sm-6">
                                    <h2>Manage <b>Cars</b></h2>
                                </div>
                                <div class="col-sm-6">
                                    <a href="CreateFormServlet" class="btn btn-success"><i class="material-icons">&#xE147;</i> <span>Add New Car</span></a>					
                                </div>
                            </div>
                        </div>
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Car ID</th>
                                    <th>Type</th>
                                    <th>Name</th>
                                    <th>Brand</th>
                                    <th>Description</th>
                                    <th>Purchase Date</th>
                                    <th>Quantity</th>
                                    <th>Image</th>
                                    <th>Price</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <% for (Car c : listS) {%>
                                <tr>
                                    <td><%=c.getCarID()%></td>
                                    <td><%=c.getTypeID().getTypeType()%></td>
                                    <td><%=c.getCarName()%></td>
                                    <td><%=c.getBrandID().getBrandName()%></td>
                                    <td><%=c.getDescription()%></td>
                                    <td><%=c.getPurchaseDate()%></td>
                                    <td><%=c.getQuantity()%></td>
                                    <td><img class="card-img-top img-responsive" src="<%=c.getImgURL()%>" alt="..." /></td>
                                    <td><%=c.getPrice()%></td>
                                    <td>
                                        <a href="UpdateFormServlet?id=<%=c.getCarID()%>" class="edit"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="DeleteServlet?id=<%=c.getCarID()%>" class="delete"><i class="material-icons" data-toggle="tooltip" title="Delete">&#xE872;</i></a>
                                    </td>
                                </tr>
                                <% }%>
                            </tbody>
                        </table>
                    </div>
                    <% } else {%>
                    <font color="red">Not found 404!!!!</font> <% }
                    } else {%>
                    <font color="red">Not found 404!!!!</font> <% }%>
                </div>        
            </div>
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

