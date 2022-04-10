<%@page import="model.dao.ProductDAO"%>
<%@page import="model.bean.Picture"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2 style="margin-left: 15px;">Quản lý hình ảnh</h2>
            </div>
        </div>
        <!-- /. ROW  -->
         <%
        	if(request.getParameter("msg") != null) {
        		String msg = request.getParameter("msg");
        		if(msg.equals("1")) {
        			out.print("<span style=\"color: blue; margin-left: 20px;\">Thêm hình ảnh thành công!</span>");
        		}
        		if(msg.equals("2")) {
        			out.print("<span style=\"color: blue; margin-left: 20px;\">Sửa hình ảnh thành công!</span>");
        		}
        		if(msg.equals("3")) {
        			out.print("<span style=\"color: blue; margin-left: 20px;\">Xóa hình ảnh thành công!</span>");
        		}
        	}
         
	         if(request.getParameter("err") != null) {
	     		String err = request.getParameter("err");
	     		if(err.equals("1")) {
	     			out.print("<span style=\"color: red; margin-left: 20px;\">Xóa hình ảnh thất bại!</span>");
	     		}
	     	}
         %>
        <hr />
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="table-responsive">
                            <div class="row">
                                <div class="col-sm-6">
                                    <a href="<%=request.getContextPath() %>/admin/picture/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/picture/search">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="key" value="" class="form-control input-sm" placeholder="Nhập tên sản phẩm cần tìm" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên sản phẩm</th>
                                        <th>Hình ảnh</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    ProductDAO productDAO = new ProductDAO();
                                    if(request.getAttribute("listAnh") != null) {
                                    	ArrayList<Picture> listAnh = (ArrayList<Picture>)request.getAttribute("listAnh");
                                    	if(listAnh.size() > 0) {
                                    		for(Picture item: listAnh) {
                                    %>
                                    <tr>
                                        <td><%=item.getMaHA() %></td>
                                        <td class="center"><%=productDAO.getNameById(item.getMaSP()) %></td>
                                        <td class="center">
                                        <%
                                        	if(!item.getTenHA().equals("")) {
                                        %>
											<center><img width="200px" height="200px" src="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=item.getTenHA() %>" alt="<%=item.getTenHA()%>"/></center>
										<%
                                        	} else {
										%>
											Chưa cập nhập hình ảnh.
										<%} %>
                                        </td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/picture/edit?id=<%=item.getMaHA() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/picture/del?id=<%=item.getMaHA() %>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                    <%}} else { %>
                                    <tr><td colspan="4">Chưa có hình ảnh nào!</td></tr>
                                    <%} %>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                <%
                                	int soTrang = (int)request.getAttribute("sotrang");
                                	int size = (int)request.getAttribute("size");
                                	int soPT = (int)request.getAttribute("soPT");
                                	int trangHT = (int)request.getAttribute("trangHT");
                                %>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến <%=size %> của <%=soPT %> hình ảnh</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        <%
                                        	if(trangHT > 1) {
                                        %>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/pictures?page=<%=trangHT-1%>">Trang trước</a></li>
                                            <%
                                        	}
                                            	for(int i = 1; i <= soTrang; i++) {
                                            		if(i == trangHT) {
                                            %>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/pictures?page=<%=i%>"><%=i %></a></li>
                                            <%} else { %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/pictures?page=<%=i%>"><%=i %></a></li>
											<%} }
                                            	if(trangHT < soTrang) {
											%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/pictures?page=<%=trangHT+1%>">Trang tiếp</a></li>
                                            <%} %>
                                        </ul>
                                    </div>
                                </div>
                            </div>
                        </div>
<%} %>
                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("picture").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>