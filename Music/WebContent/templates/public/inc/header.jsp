<%@page import="java.text.DecimalFormat"%>
<%@page import="model.dao.CartDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Customer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zxx">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="Ogani Template">
    <meta name="keywords" content="Ogani, unica, creative, html">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Guitar Shop</title>

    <!-- Google Font -->
    <link href="https://fonts.googleapis.com/css2?family=Cairo:wght@200;300;400;600;900&display=swap" rel="stylesheet">

    <!-- Css Styles -->
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/bootstrap.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/elegant-icons.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/nice-select.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/jquery-ui.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/owl.carousel.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/slicknav.min.css" type="text/css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/templates/public/css/style.css" type="text/css">
    <link rel='stylesheet prefetch' href='https://netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.css'>
    <script type="text/javascript" src="<%=request.getContextPath() %>/templates/public/js/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.validate.min.js"></script>
</head>

<body>
    <!-- Header Section Begin -->
    <header class="header">
        <div class="header__top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__left">
                            <ul>
                                <li><i class="fa fa-envelope"></i> xinchao@gmail.com</li>
                                <li>Miễn phí ship nếu mua trên 10 lần</li>
                            </ul>
                        </div>
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <div class="header__top__right">
                            <div class="header__top__right__social">
                                <a href="#"><i class="fa fa-facebook"></i></a>
                                <a href="#"><i class="fa fa-twitter"></i></a>
                                <a href="#"><i class="fa fa-linkedin"></i></a>
                                <a href="#"><i class="fa fa-pinterest-p"></i></a>
                            </div>
                            <div class="header__top__right__language">
                                <img src="<%=request.getContextPath() %>/templates/public/img/laco.png" alt="" width="50px">
                                <div>Việt Nam</div>
                                <span class="arrow_carrot-down"></span>
                                <ul>
                                    <li><a href="#">Việt Nam</a></li>
                                    <li><a href="#">English</a></li>
                                </ul>
                            </div>
                            <div class="header__top__right__auth">
                            <%
                            	HttpSession se = request.getSession();
                            	if(se.getAttribute("cusLogin") != null) {
                            		Customer cus = (Customer)se.getAttribute("cusLogin");
                            %>
                                <a href="<%=request.getContextPath()%>/dang-xuat"><i class="fa fa-user"><span style="margin-left: 5px;"><%=cus.getTenDangNhap() %></span></i> Đăng xuất</a>
                            <%} else {%>
                            	<a href="<%=request.getContextPath()%>/dang-nhap"><i class="fa fa-user"></i> Đăng nhập</a>
                            <%} %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="header__logo">
                        <a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath() %>/templates/public/img/hi.png" alt=""></a>
                        <span style="color: black; font-size: 20px; font-weight: bold; margin-left: 5px;">Guitar Shop</span>
                    </div>
                </div>
                <div class="col-lg-6">
                    <nav class="header__menu">
                        <ul>
                        <%
                        	String url_ht = request.getRequestURL().toString();
                        	String url_trangchu = "http://localhost:8085/Music/public/index.jsp";
                        	String url_thongtinshop = "http://localhost:8085/Music/public/shop.jsp";
                        	String url_lienhe = "http://localhost:8085/Music/public/contact.jsp";
                        	String url_giohang = "http://localhost:8085/Music/public/cart.jsp";
                        	if(url_ht.equals(url_trangchu)) {
                        %>
                            <li class="active"><a href="<%=request.getContextPath()%>">Trang chủ</a></li>
                        <%
                        	} else {
                        %>
                        	<li><a href="<%=request.getContextPath()%>">Trang chủ</a></li>
                        <%
                        	}
                        	if(url_ht.equals(url_thongtinshop) || url_ht.equals(url_giohang)) {
                        %>
                            <li class="active"><a href="#">Trang</a>
                        <%} else {%>
                        	<li><a href="#">Trang</a>
                        <%} %>
                                <ul class="header__menu__dropdown">
                               		 <li><a href="<%=request.getContextPath() %>/public/shop.jsp">Thông tin về shop</a></li>
                                    <li><a href="<%=request.getContextPath()%>/cart">Giỏ hàng</a></li>
                                    <li><a href="<%=request.getContextPath()%>/checkout">Thanh toán</a></li>
                                </ul>
                            </li>
                            <li><a href="#">Tin tức</a></li>
                         <%
                         	if(url_ht.equals(url_lienhe)) {
                         %>
                            <li class="active"><a href="<%=request.getContextPath()%>/lien-he">Liên hệ</a></li>
                        <%
                         	} else {
                        %>
                        	<li><a href="<%=request.getContextPath()%>/lien-he">Liên hệ</a></li>
                        <%} %>
                        </ul>
                    </nav>
                </div>
                <div class="col-lg-3">
                    <%
                    	CartDAO cartDAO = new CartDAO();
                    	if(se.getAttribute("cusLogin") != null) {
                    		Customer cus = (Customer)se.getAttribute("cusLogin");
                    %>
                    <div class="header__cart">
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/cart"><i class="fa fa-shopping-bag"></i> <span><%=cartDAO.demSP(cus.getMaKH()) %></span></a></li>
                        </ul>
                        <div class="header__cart__price">Tổng: 
                        <span>
                        	<%
                        		DecimalFormat formatte = new DecimalFormat("###,###,###");
                        		out.println(formatte.format(cartDAO.tongTien(cus.getMaKH()))+" VNĐ");
                        	%>
                        </span>
                        </div>
                     </div>
                    <%} else { %>
                     <div class="header__cart">
                        <ul>
                            <li><a href="<%=request.getContextPath()%>/gio-hang"><i class="fa fa-shopping-bag"></i> <span></span></a></li>
                        </ul>
                        <div class="header__cart__price">Tổng: <span>0 VNĐ</span></div>
                     </div>
                    <%} %>
                </div>
            </div>
            <div class="humberger__open">
                <i class="fa fa-bars"></i>
            </div>
        </div>
    </header>
    
    <!-- Hero Section Begin -->
    <section class="hero">
        <div class="container">
            <div class="row">
                <div class="col-lg-3">
                    <div class="hero__categories">
                        <div class="hero__categories__all">
                            <i class="fa fa-bars"></i>
                            <span>Danh mục sản phẩm</span>
                        </div>
                        <div style="border: 1px solid #ebebeb;">
                        <%
                            	CategoryDAO catDAO = new CategoryDAO();
                            	ArrayList<Category> list = catDAO.getItems();
                            	catDAO.displayDMPL(list, 0, out, request);
                        %>
                        </div>
                    </div>
                </div>
                <div class="col-lg-9">
                    <div class="hero__search">
                        <div class="hero__search__form">
                            <form action="<%=request.getContextPath()%>/search" method="post">
                                <input type="text" placeholder="Nhập tên sản phẩm bạn muốn tìm..." name="key" value="">
                                <button type="submit" class="site-btn">Tìm kiếm</button>
                            </form>
                        </div>
                        <div class="hero__search__phone">
                            <div class="hero__search__phone__icon">
                                <i class="fa fa-phone"></i>
                            </div>
                            <div class="hero__search__phone__text">
                                <h5>+09053508</h5>
                            </div>
                        </div>
                    </div>
                    <div class="hero__item set-bg" data-setbg="<%=request.getContextPath() %>/templates/public/img/quangcao.jpg">
                        <div class="hero__text">
                            <span>Guitar Shop</span>
                            <h2>Chất lượng <br />100%</h2>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Hero Section End -->
    <style>
    	.header__logo img{
    		width: 50px;
    		height: 50px;
    		border: 1px #d4d4d4 solid;
		    border-radius:50%;
		    -moz-border-radius:50%;
		    -webkit-border-radius:50%;
    	}
    </style>
