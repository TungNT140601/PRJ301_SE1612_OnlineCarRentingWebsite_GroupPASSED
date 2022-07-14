<%-- 
    Document   : cardetails
    Created on : Mar 13, 2022, 9:57:50 AM
   Author     : tuanbee
--%>

<%@page import="fu.ex.entities.Customers"%>
<%@page import="fu.ex.entities.Car_type"%>
<%@page import="fu.ex.entities.Car_brand"%>
<%@page import="fu.ex.daos.CarDAO"%>
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
        <title>Homepage</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <style>
        li{
            cursor: pointer;
        }
        body{
            background-color: white;
            margin-top:0px;
        }
        .card {
            margin-bottom: 30px;
        }
        .card {
            position: relative;
            display: flex;
            flex-direction: column;
            min-width: 0;
            word-wrap: break-word;
            background-color: #fff;
            background-clip: border-box;
            border: 0 solid transparent;
            border-radius: 0;
        }
        .card .card-subtitle {
            font-weight: 300;
            margin-bottom: 10px;
            color: #8898aa;
        }
        .table-product.table-striped tbody tr:nth-of-type(odd) {
            background-color: #f3f8fa!important
        }
        .table-product td{
            border-top: 0px solid #dee2e6 !important;
            color: #728299!important;
        }
        .img-responsive {
            max-width: 100%;
            width: 100%;
        }
        .mainHeaderWrapper {
            width: 100%;
            height: 450px;
            padding-top: 100%;
            background-image: url('https://scontent.fsgn2-1.fna.fbcdn.net/v/t1.15752-9/275078928_518455753236585_5041462400179996834_n.png?_nc_cat=111&ccb=1-5&_nc_sid=ae9488&_nc_ohc=3C9Lt1IQCuEAX-AKbAV&tn=WA0Lku0uHdaswYHq&_nc_ht=scontent.fsgn2-1.fna&oh=03_AVJWew2W0hakYmkWicMF4Fyzc65-Vd34VWtwjl0Kkj2WMA&oe=625258E2');
            background-size: 100% auto;
            background-repeat: no-repeat;
        }
    </style>
    <body>
        <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="HomePage">Công ty trách nhiệm hữu hạn một mình tôi</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="HomePage">Home</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">About</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a target="_blank" rel="noopener noreferrer" class="dropdown-item" href="https://www.facebook.com/tuanblep">Nguyen Anh Tuan</a></li>
                                <li><a target="_blank" rel="noopener noreferrer" class="dropdown-item" href="https://www.facebook.com/trinhhuu.truong.1">Trinh Huu Truong</a></li>
                                <li><a target="_blank" rel="noopener noreferrer" class="dropdown-item" href="https://www.facebook.com/nttung.1406">Nguyen Thanh Tung</a></li>
                                <li><a target="_blank" rel="noopener noreferrer" class="dropdown-item" href="https://www.facebook.com/profile.php?id=100056065892536">Hoang Khac Tri</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">All Products</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">Popular Items</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
                        &nbsp;&nbsp;&nbsp;&nbsp;<li class="nav-item"><a class="nav-link active" aria-current="page" href=""><% if (session.getAttribute("userdata") == null) {%>
                                <%} else if (session.getAttribute("userdata") != null) {%>
                                <footer style="">
                                    <% Customers cus = (Customers) request.getSession().getAttribute("userdata");%>
                                    Hello <%= cus.getFullname()%> 
                                </footer>
                                <%}%></a></li>
                    </ul>
                    <form action="MainController" method="POST" class="d-flex">
                        <button class="btn btn-outline-dark" type="submit">
                            <i class="bi-cart-fill me-1"></i>
                            Cart
                            <span class="badge bg-dark text-white ms-1 rounded-pill">0</span>
                        </button>&nbsp;&nbsp;&nbsp;&nbsp;
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
        <header class="mainHeaderWrapper py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">CÔNG TY CHO MƯỢN XE HÀNG ĐẦU VIỆT NAM</h1>
                    <p class="lead fw-normal text-white-50 mb-0">CHẤT LƯỢNG VƯỢT TRỘI, KHÔNG LÀM BẠN THẤT VỌNG (MẶC DÙ XE HƠI CŨ :) )</p>
                </div>
            </div>
        </header>
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <div class="row">
                    <div class="breadcrumb-item col-md-9">
                        <li class="breadcrumb-item"><a href="HomePage">Home</a></li>
                        <li class="breadcrumb-item"><a href="HomePage">Category</a></li>
                            <%  Car c = new Car();
                                if (request.getAttribute("gc").equals(request.getAttribute("tbs"))) {
                                    c = (Car) request.getAttribute("tbs");%>
                        <li class="breadcrumb-item"><a href="Category?tid=<%=c.getTypeID().getTypeID()%>&search=type"><%=c.getTypeID().getTypeType()%></a></li>
                            <%}
                                if (request.getAttribute("gc").equals(request.getAttribute("cbs"))) {
                                    c = (Car) request.getAttribute("cbs");%>
                        <li class="breadcrumb-item"><a href="Category?bid=<%=c.getBrandID().getBrandID()%>&search=brand"><%=c.getBrandID().getBrandName()%></a></li>
                            <%}
                                if (request.getAttribute("gc").equals(request.getAttribute("cn"))) {
                                    c = (Car) request.getAttribute("cn");%>
                        <li class="breadcrumb-item"><a href="HomePage"><%=c.getBrandID().getBrandName()%> - <%=c.getTypeID().getTypeType()%> </a></li>
                            <%}
                                if (request.getAttribute("gc").equals(request.getAttribute("lc"))) {
                                    c = (Car) request.getAttribute("lc");%>
                        <li class="breadcrumb-item"><a href="SearchCar?searchname=<%=session.getAttribute("gettext")%>">Searching for "<%=session.getAttribute("gettext")%>"</a></li>
                            <%}%>
                        <li class="breadcrumb-item"><a href=""><%=c.getCarName()%></a></li>
                    </div>
                    <form class="d-flex col-md-3" action="MainController" method="POST"> 
                        <input  class="form-control mr-2" type="search" name="searchname" placeholder="Search" aria-label="Search"> 
                        <button class="btn btn-outline-success" type="submit" name="action" value="Search">Search</button> </form>
                </div>
            </ol>
        </nav>
        <!-- Section-->          
        <section class="py-5 ">

            <div class="row">
                <div class="col-md-3">
                    <form action="MainController" method="POST">
                        <div class="container">
                            <div class="row my-5">
                                <div class="col-md-10">                        
                                    <div class="accordion" id="accordionExample">
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="headingOne">
                                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
                                                    Car's Brand
                                                </button>
                                            </h2>
                                            <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                                <div class="accordion-body">
                                                    <%
                                                        ArrayList<Car_brand> lstCB = new ArrayList<Car_brand>();
                                                        lstCB = (ArrayList<Car_brand>) request.getAttribute("lstCB");
                                                        for (Car_brand cb : lstCB) {%>
                                                    <div class="list-group">
                                                        <a href="Category?bid=<%=cb.getBrandID()%>&search=brand" class="list-group-item list-group-item-action " aria-current="true">
                                                            <%=cb.getBrandName()%>
                                                        </a>      
                                                    </div>
                                                    <%}%> 
                                                </div>
                                            </div>
                                        </div>
                                        <div class="accordion-item">
                                            <h2 class="accordion-header" id="headingTwo">
                                                <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                                    Car's Type
                                                </button>
                                            </h2>
                                            <div id="collapseTwo" class="accordion-collapse collapse " aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                                                <div class="accordion-body">
                                                    <%
                                                        ArrayList<Car_type> lstCT = new ArrayList<Car_type>();
                                                        lstCT = (ArrayList<Car_type>) request.getAttribute("lstCT");
                                                        for (Car_type ct : lstCT) {%>
                                                    <div class="list-group">
                                                        <a href="Category?tid=<%=ct.getTypeID()%>&search=type" class="list-group-item list-group-item-action " aria-current="true">
                                                            <%=ct.getTypeType()%>
                                                        </a>      
                                                    </div>
                                                    <%}%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="col-md-9">
                    <div class="container">
                        <div class="card">
                            <div class="card-body">
                                <%
                                    if (request.getAttribute("gc").equals(request.getAttribute("tbs"))) {
                                        c = (Car) request.getAttribute("tbs");
                                    }
                                    if (request.getAttribute("gc").equals(request.getAttribute("cbs"))) {
                                        c = (Car) request.getAttribute("cbs");
                                    }
                                    if (request.getAttribute("gc").equals(request.getAttribute("cn"))) {
                                        c = (Car) request.getAttribute("cn");
                                    }
                                    if (request.getAttribute("gc").equals(request.getAttribute("lc"))) {
                                        c = (Car) request.getAttribute("lc");
                                    }
                                %>
                                <h3 class="card-title"><%=c.getCarName()%></h3>
                                <h6 class="card-subtitle">globe type chair for rest</h6>
                                <div class="row">
                                    <div class="row">
                                        <div class="col-md-6">

                                            <div class="white-box text-center"><img src="<%=c.getImgURL()%>" class="img-responsive"></div>

                                        </div>
                                        <div class=" d-flex col-md-6 center justify-content-center">
                                            <div class=" col-lg-7 col-md-12 col-sm-7 " >
                                                <h4 class="box-title mt-5">Car description</h4>
                                                <p><%=c.getDescription()%></p>
                                                <h2 class="mt-5">
                                                    $ <%=c.getPrice()%>
                                                </h2>
                                                <% Customers user = (Customers) request.getSession().getAttribute("userdata"); %>
                                                <button class="btn btn-primary btn-rounded">
                                                    <% if (c.getQuantity() > 0) { %>
                                                    <a  style="color: #fff; text-decoration: none;" 
                                                        href="<% if (user != null) {%> CartServlet?action=buy&id=<%=c.getCarID()%> <% } else { %> LoginServlet <% }%>">
                                                        Rent Now
                                                    </a>
                                                    <% } else { %>
                                                    <a  style="color: #fff; text-decoration: none;" 
                                                        href="<% if (user != null) {%>#<% } else { %>LoginServlet<% }%>" onclick="alert('Out of stock')">
                                                        Rent Now
                                                    </a>
                                                    <% }%>
                                                </button>

                                                <h3 class="box-title mt-5">Key Highlights</h3>
                                                <ul class="list-unstyled">
                                                    <li><i class="fa fa-check text-success"></i>High Quality</li>
                                                    <li><i class="fa fa-check text-success"></i>Ngon</li>
                                                    <div class="d-flex justify-content-lg-start small text-warning mb-2">
                                                        <div class="bi-star-fill"></div>
                                                        <div class="bi-star-fill"></div>
                                                        <div class="bi-star-fill"></div>
                                                        <div class="bi-star-fill"></div>
                                                        <div class="bi-star-fill"></div>

                                                    </div>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-12 col-md-12 col-sm-12">
                                        <h3 class="box-title mt-5">General Info</h3>
                                        <div class="table-responsive">
                                            <table class="table table-striped table-product">
                                                <tbody>
                                                    <tr>
                                                        <td width="390">Car's Brand</td>
                                                        <td><%=c.getBrandID().getBrandName()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Car's Type</td>
                                                        <td><%=c.getTypeID().getTypeType()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Quantity</td>
                                                        <td><%=c.getQuantity()%></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Purchase Date</td>
                                                        <td><%=c.getPurchaseDate()%></td>
                                                    </tr> 
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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

