<%-- 
    Document   : categorysearching
    Created on : Mar 13, 2022, 9:59:22 AM
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
    </head>
    <style>
        li{
            cursor: pointer;
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
                        <li class="breadcrumb-item "><a href="HomePage">Category</a></li>
                            <%  ArrayList<Car> ldt = new ArrayList<Car>();
                                if (request.getAttribute("gc").equals(request.getAttribute("tbs"))) {
                                    ldt = (ArrayList<Car>) request.getAttribute("tbs");
                                    for (int i = 0; i < 1; i++) {%>
                        <li class="breadcrumb-item"><a href=""><%=ldt.get(i).getTypeID().getTypeType()%></a></li>

                        <%}
                            }
                            if (request.getAttribute("gc").equals(request.getAttribute("cbs"))) {
                                ldt = (ArrayList<Car>) request.getAttribute("cbs");
                                for (int i = 0; i < 1; i++) {%>
                        <li class="breadcrumb-item"><a href=""><%=ldt.get(i).getBrandID().getBrandName()%></a></li>

                        <%}
                            }
                            if (request.getAttribute("gc").equals(request.getAttribute("lc"))) {
                                ldt = (ArrayList<Car>) request.getAttribute("lc");
                                for (int i = 0; i < 1; i++) {%>
                        <li class="breadcrumb-item"><a href="">Searching for "<%=request.getParameter("searchname")%>"</a></li>

                        <%}
                            }%>
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
                                            <div id="collapseOne" class="accordion-collapse collapse 

                                                 <% ArrayList<Car> s = new ArrayList<Car>();
                                                     if (request.getAttribute("gc").equals(request.getAttribute("cbs"))) {%>
                                                 show
                                                 <%}

                                                     if (request.getAttribute("gc").equals(request.getAttribute("tbs"))) {%>

                                                 <%}
                                                 %>
                                                 " aria-labelledby="headingOne" data-bs-parent="#accordionExample">
                                                <div class="accordion-body">
                                                    <%  ArrayList<Car_brand> lstCB = new ArrayList<Car_brand>();
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
                                            <div id="collapseTwo" class="accordion-collapse collapse 
                                                 <% if (request.getAttribute("gc").equals(request.getAttribute("tbs"))) {%>
                                                 show
                                                 <%}

                                                     if (request.getAttribute("gc").equals(request.getAttribute("cbs"))) {%>

                                                 <%}%> 
                                                 " aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
                                                <div class="accordion-body">
                                                    <%
                                                        ArrayList<Car_type> lstCT = new ArrayList<Car_type>();
                                                        lstCT = (ArrayList<Car_type>) request.getAttribute("lstCT");

                                                        for (Car_type ct : lstCT) {%>
                                                    <div class="list-group">
                                                        <a href="Category?tid=<%=ct.getTypeID()%>&search=type" class="list-group-item list-group-item-action" aria-current="true">
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
                    <div class="container px-4 px-lg-5 mt-5"> 
                        <div class="row gx-1 gx-lg-3 row-cols-1 row-cols-md-1 row-cols-xl-3 justify-content-lg-around">

                            <%  ArrayList<Car> ld = new ArrayList<Car>();

                                if (request.getAttribute("gc").equals(request.getAttribute("tbs"))) {
                                    ld = (ArrayList<Car>) request.getAttribute("tbs");
                                    for (Car c : ld) {%>
                            <div class="col mb-5">
                                <div class="card h6">
                                    <!-- Product image-->
                                    <img class="card-img-top" src="<%=c.getImgURL()%>" alt="..." />
                                    <!-- Product details-->
                                    <div class="card-body p-4" style="text-align: center">
                                        <h4 class="justify-content-center h6"><a href="CarDetails?tid=<%=c.getCarName()%>&search=type">
                                                <%=c.getCarName()%> - <%=c.getPurchaseDate()%>
                                            </a>
                                        </h4>
                                        <br><br>
                                        <div class="text-center">
                                            Brand : <%=c.getBrandID().getBrandName()%> <br>
                                            Type : <%=c.getTypeID().getTypeType()%><br>
                                            $ <%=c.getPrice()%> <br>
                                            Quantity : <%=c.getQuantity()%> 
                                        </div>
                                        Product reviews
                                        <div class="d-flex justify-content-center small text-warning mb-2">
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>

                                        </div>
                                    </div>
                                    <!-- Product actions-->
                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Rent</a></div>
                                    </div>
                                </div>
                            </div>
                            <%}
                                }

                                if (request.getAttribute("gc").equals(request.getAttribute("cbs"))) {
                                    ld = (ArrayList<Car>) request.getAttribute("cbs");
                                    for (Car c : ld) {%>
                            <div class="col mb-5">
                                <div class="card h6">
                                    <!-- Product image-->
                                    <img class="card-img-top" src="<%=c.getImgURL()%>" alt="..." />
                                    <!-- Product details-->
                                    <div class="card-body p-4" style="text-align: center">
                                        <h4 class="justify-content-center h6"><a href="CarDetails?bid=<%=c.getCarName()%>&search=brand">
                                                <%=c.getCarName()%> - <%=c.getPurchaseDate()%>
                                            </a>
                                        </h4>
                                        <br><br>
                                        <div class="text-center">
                                            Brand : <%=c.getBrandID().getBrandName()%> <br>
                                            Type : <%=c.getTypeID().getTypeType()%><br>
                                            $ <%=c.getPrice()%> <br>
                                            Quantity : <%=c.getQuantity()%> 
                                        </div>
                                        Product reviews
                                        <div class="d-flex justify-content-center small text-warning mb-2">
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>

                                        </div>
                                    </div>
                                    <!-- Product actions-->
                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Rent</a></div>
                                    </div>
                                </div>
                            </div>
                            <%}
                                }
                                if (request.getAttribute("gc").equals(request.getAttribute("lc"))) {
                                    ld = (ArrayList<Car>) request.getAttribute("lc");
                                    for (Car c : ld) {%>
                            <div class="col mb-5">
                                <div class="card h6">
                                    <!-- Product image-->
                                    <img class="card-img-top" src="<%=c.getImgURL()%>" alt="..." />
                                    <!-- Product details-->
                                    <div class="card-body p-4" style="text-align: center">
                                        <h4 class="justify-content-center h6"><a href="CarDetails?sid=<%=c.getCarName()%>&search=searchname">
                                                <%=c.getCarName()%> - <%=c.getPurchaseDate()%>
                                            </a>
                                        </h4>
                                        <br><br>
                                        <div class="text-center">
                                            Brand : <%=c.getBrandID().getBrandName()%> <br>
                                            Type : <%=c.getTypeID().getTypeType()%><br>
                                            $ <%=c.getPrice()%> <br>
                                            Quantity : <%=c.getQuantity()%> 
                                        </div>
                                        Product reviews
                                        <div class="d-flex justify-content-center small text-warning mb-2">
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>
                                            <div class="bi-star-fill"></div>

                                        </div>
                                    </div>
                                    <!-- Product actions-->
                                    <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                        <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="#">Rent</a></div>
                                    </div>
                                </div>
                            </div>
                            <%}
                                }%>

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

