<%@page import="model.bean.Picture"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2 style="margin-left: 15px;">Sửa hình ảnh</h2>
            </div>
        </div>
        <!-- /. ROW  -->
         <%
        	if(request.getParameter("err") != null) {
        		String err = request.getParameter("err");
        		if(err.equals("1")) {
        			out.print("<span style=\"color: red; margin-left: 20px;\">Sửa hình ảnh thất bại!</span>");
        		}
        	}
         	Picture itemAnh = null;
         	if(request.getAttribute("hinhanh") != null) {
         		itemAnh = (Picture)request.getAttribute("hinhanh");
         	}
         %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Form Elements -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-md-12">
                                <form role="form" method="post" enctype="multipart/form-data" id="form">
                                    <div class="form-group">
                                        <label for="category">Sản phẩm</label>
                                        <select id="category" name="category" class="form-control">
                                        <%
                                        if(request.getAttribute("listSP") != null) {
                                        	ArrayList<Product> listSP = (ArrayList<Product>)request.getAttribute("listSP");
                                        	for(Product item: listSP) {
                                        %>
	                                        <option value="<%=item.getMaSP()%>" <%if(item.getMaSP() == itemAnh.getMaSP()) out.print("selected"); %>><%=item.getTenSP() %></option>
										<%}} %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="picture">Hình ảnh</label>
                                        <input type="file" name="picture" />
                                        <img width="200px" height="200px" src="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=itemAnh.getTenHA() %>" alt="<%=itemAnh.getTenHA()%>"/>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Sửa</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End Form Elements -->
            </div>
        </div>
        <!-- /. ROW  -->
    </div>
    <!-- /. PAGE INNER  -->
</div>
<script>
    document.getElementById("picture").classList.add('active-menu');
</script>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>