<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="model.bean.Product"%>
<%@page import="model.dao.ProductDAO"%>
<%@page import="model.bean.Customer"%>
<%@page import="model.dao.CustomerDAO"%>
<%@page import="model.bean.Orders"%>
<%@page import="model.dao.OrdersDAO"%>
<%@page import="model.bean.Contact"%>
<%@page import="model.dao.ContactDAO"%>
<%@page import="model.bean.Picture"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.PictureDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2>TRANG QUẢN TRỊ VIÊN</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <hr />
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-list"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath()%>/admin/cats" title="">Quản lý danh mục</a></p>
                        <%
                        	CategoryDAO catDAO = new CategoryDAO();
                        	ArrayList<Category> cat = catDAO.getItems();
                        %>
                        <p class="text-muted">Có <%=cat.size() %> danh mục</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-music"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath()%>/admin/products" title="">Quản lý sản phẩm</a></p>
                        <%
                        	ProductDAO proDAO = new ProductDAO();
                        	ArrayList<Product> sp = proDAO.getItems();
                        %>
                        <p class="text-muted">Có <%=sp.size() %> sản phẩm</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-user"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath()%>/admin/customers" title="">Quản lý khách hàng</a></p>
                        <%
                        	CustomerDAO cusDAO = new CustomerDAO();
                        	ArrayList<Customer> cus = cusDAO.getItems();
                        %>
                        <p class="text-muted">Có <%=cus.size() %> khách hàng</p>
                    </div>
                </div>
            </div>
        </div>
		
		<div class="row">
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-film"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath()%>/admin/orders" title="">Quản lý đơn hàng</a></p>
                        <%
                        	OrdersDAO orDAO = new OrdersDAO();
                        	ArrayList<Orders> or = orDAO.getItems();
                        %>
                        <p class="text-muted">Có <%=or.size() %> đơn hàng</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-comment"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="" title="">Quản lý đánh giá</a></p>
                        <p class="text-muted">Có 0 đánh giá</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-reply-all"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="" title="">Quản lý phản hồi</a></p>
                        <p class="text-muted">Có 0 phản hồi</p>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="row">
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-green set-icon">
                    <i class="fa fa-envelope"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath()%>/admin/contacts" title="">Quản lý liên hệ</a></p>
                        <%
                        	ContactDAO conDAO = new ContactDAO();
                        	ArrayList<Contact> lienhe = conDAO.list();
                        %>
                        <p class="text-muted">Có <%=lienhe.size() %> liên hệ</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-blue set-icon">
                    <i class="fa fa-picture-o"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath()%>/admin/pictures" title="">Quản lý hình ảnh</a></p>
                        <%
                        	PictureDAO picDAO = new PictureDAO();
                        	ArrayList<Picture> hinhanh = picDAO.getItems();
                        %>
                        <p class="text-muted">Có <%=hinhanh.size() %> hình ảnh</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-4 col-xs-4">
                <div class="panel panel-back noti-box">
                    <span class="icon-box bg-color-brown set-icon">
                    <i class="fa fa-bars"></i>
                </span>
                    <div class="text-box">
                        <p class="main-text"><a href="<%=request.getContextPath()%>/admin/reports" title="">Thống kê</a></p>
                        <p class="text-muted"></p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("index").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>