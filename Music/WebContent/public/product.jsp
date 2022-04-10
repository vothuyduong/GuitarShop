<%@page import="util.StringUtil"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>

    <!-- Product Section Begin -->
    <section class="product spad">
        <div class="container">
            <div class="row">
                    <div class="row">
                    <%
                    	if(request.getAttribute("listSP") != null) {
                    		ArrayList<Product> listSP = (ArrayList<Product>)request.getAttribute("listSP");
                    		if(listSP.size() > 0) {
                    			for(Product item: listSP) {
                    %>
                        <div class="col-lg-4 col-md-6 col-sm-6">
                            <div class="product__item">
                                <div class="product__item__pic set-bg" data-setbg="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=item.getPicture().get(0).getTenHA()%>">
                                    <ul class="product__item__pic__hover">
                                        <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                        <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                        <li><a href="<%=request.getContextPath()%>/gio-hang/<%=StringUtil.makeSlug(item.getTenSP()) %>-<%=item.getMaSP()%>"><i class="fa fa-shopping-cart"></i></a></li>
                                    </ul>
                                </div>
                                <div class="product__item__text">
                                    <h6><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(item.getMaDM().getName()) %>/<%=StringUtil.makeSlug(item.getTenSP()) %>-<%=item.getMaSP()%>.html"><%=item.getTenSP() %></a></h6>
                                    <h5 style="color: red;">
                                    <%
                                    	int giam = (item.getGia()*item.getKhuyenMai())/100;
                                    	DecimalFormat formatter = new DecimalFormat("###,###,###");
                                    	out.println(formatter.format(item.getGia()-giam)+" VNĐ");
                                    %>
                                    </h5>
                                    <span style="margin-right: 7px;">Giảm: <%=item.getKhuyenMai() %>%</span><span  style="color: #C0C0C0; text-decoration: line-through;">Giá gốc:  <% out.println(formatter.format(item.getGia())+" VNĐ"); %></span>
                                </div>
                            </div>
                        </div>
                    <%} }}%>
                    <!--
                    <div class="product__pagination">
                        <a href="#">1</a>
                        <a href="#">2</a>
                        <a href="#">3</a>
                        <a href="#"><i class="fa fa-long-arrow-right"></i></a>
                    </div>
                    -->
                </div>
            </div>
        </div>
    </section>
    <!-- Product Section End -->

 <!-- Footer Section Begin -->
    <%@include file="/templates/public/inc/footer.jsp"%>