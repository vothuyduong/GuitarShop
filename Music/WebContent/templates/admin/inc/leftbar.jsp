<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<nav class="navbar-default navbar-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav" id="main-menu">
            <li>
                <a id="index" href="<%=request.getContextPath()%>/admin"><i class="fa fa-dashboard fa-3x"></i> Trang chủ</a>
            </li>
            <li>
                <a id="category" href="<%=request.getContextPath()%>/admin/cats"><i class="fa fa-list fa-3x"></i> Quản lý danh mục</a>
            </li>
            <li>
                <a id="product" href="<%=request.getContextPath()%>/admin/products"><i class="fa fa-music fa-3x"></i> Quản lý sản phẩm</a>
            </li>
            <li>
                <a id="customer" href="<%=request.getContextPath()%>/admin/customers"><i class="fa fa-user fa-3x"></i> Quản lý khách hàng</a>
            </li>
             <li>
                <a id="picture" href="<%=request.getContextPath()%>/admin/pictures"><i class="fa fa-picture-o fa-3x"></i> Quản lý hình ảnh</a>
            </li>
            <li>
                <a id="orders" href="<%=request.getContextPath()%>/admin/orders"><i class="fa fa-film fa-3x"></i> Quản lý đơn hàng</a>
            </li>
            <li>
                <a id="evaluate" href=""><i class="fa fa-comment fa-3x"></i> Quản lý đánh giá</a>
            </li>
            <li>
                <a id="feedback" href=""><i class="fa fa-reply-all fa-3x"></i> Quản lý phản hồi</a>
            </li>
            <li>
                <a id="contact" href="<%=request.getContextPath()%>/admin/contacts"><i class="fa fa-envelope fa-3x"></i> Quản lý liên hệ</a>
            </li>
           <li>
                <a id="report" href="<%=request.getContextPath()%>/admin/reports"><i class="fa fa-bars fa-3x"></i> Thống kê</a>
            </li>
        </ul>
    </div>
</nav>
<!-- /. NAV SIDE  -->