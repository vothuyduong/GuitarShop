<%@page import="model.bean.Customer"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2 style="margin-left: 15px;">Tìm kiếm khách hàng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        	if(request.getParameter("msg") != null) {
        		String msg = request.getParameter("msg");
        		if(msg.equals("1")) {
        			out.print("<span style=\"color: blue; margin-left: 20px;\">Tìm khách hàng thành công!</span>");
        		}
        	}
	        if(request.getParameter("err") != null) {
	    		String err = request.getParameter("err");
	    		if(err.equals("1")) {
	    			out.print("<span style=\"color: red; margin-left: 20px;\">Tên khách hàng cần tìm rỗng!</span>");
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
                                
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/customer/search">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" value="<%if(request.getAttribute("key") != null) out.print(request.getAttribute("key")); %>" name="key" class="form-control input-sm" placeholder="Nhập tên khách hàng" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên khách hàng</th>
                                        <th>Ngày sinh</th>
                                        <th>Địa chỉ</th>
                                        <th>Số điện thoại</th>
                                        <th>Email</th>
                                        <th>Tên đăng nhập</th> 
                                        <th>Mật khẩu</th> 
                                        <th>Giới tính</th>
                                        <th width="80px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	if(request.getAttribute("listKH") != null) {
                                		ArrayList<Customer> listKH = (ArrayList<Customer>)request.getAttribute("listKH");
                                		if(listKH.size() > 0) {
                                			for(Customer item: listKH) {
                                %>
                                    <tr>
                                        <td><%=item.getMaKH() %></td>
                                        <td class="center"><%=item.getTenKH() %></td>
                                        <td class="center"><%=item.getNgaySinh() %></td>
                                        <td class="center"><%=item.getDiaChi() %></td>
                                        <td class="center"><%=item.getSdt() %></td>
                                        <td class="center"><%=item.getEmail() %></td>
                                        <td class="center"><%=item.getTenDangNhap() %></td>
                                        <td class="center"><%=item.getMatKhau() %></td>
                                        <td class="center"><%if(item.getGioiTinh() == 0) out.print("Nữ"); else out.print("Nam"); %></td>
                                        <td class="center">
                                            <a href="" title="" class="btn btn-danger"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                <%
                                			}} else {
                                %>
                                	<tr><td colspan="10">Chưa có khách hàng nào!</td></tr>
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
                                	String key = (String)request.getAttribute("key");
                                %>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến <%=size %> của <%=soPT %> khách hàng</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        	<%
                                        		if(trangHT > 1) {
                                        	%>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/customers?page=<%=trangHT-1 %>&key=<%=key%>">Trang trước</a></li>
                                            <%
                                        		}
                                            	for(int i = 1; i <= soTrang; i++) {
                                            		if(i == trangHT) {
                                            %>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/customers?page=<%=i %>&key=<%=key%>"><%=i %></a></li>
                                            <%} else { %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/customers?page=<%=i %>&key=<%=key%>"><%=i %></a></li>
											<%}}
                                            	if(trangHT < soTrang) {
                                            	%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/customers?page=<%=trangHT+1 %>&key=<%=key%>">Trang tiếp</a></li>
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
    document.getElementById("customer").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>