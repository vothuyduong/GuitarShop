<%@page import="java.text.SimpleDateFormat"%>
<%@page import="model.bean.Orders"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2 style="margin-left: 15px;">Quản lý đơn hàng</h2>
            </div>
        </div>
        <!-- /. ROW  -->
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
                                    <form method="post" action="<%=request.getContextPath()%>/admin/order/search">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="key" value="" class="form-control input-sm" placeholder="Nhập tên khách hàng" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên khách hàng</th>
                                        <th>Ngày đặt</th>
                                        <th>Tổng tiền</th>
                                        <th>Trạng thái</th>
                                        <th width="80px">Chi tiết</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	if(request.getAttribute("listO") != null) {
                                		ArrayList<Orders> listO = (ArrayList<Orders>)request.getAttribute("listO");
                                		if(listO.size() > 0) {
                                			for(Orders item: listO) {
                                %>
                                    <tr>
                                        <td><%=item.getMaDH() %></td>
                                        <td class="center"><%=item.getKhachHang().getTenKH() %></td>
                                        <td class="center">
                                        <%
                                        	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
                                        	String ngayTao = sdf.format(item.getNgayTao());
                                        	out.print(ngayTao);
                                        %>
                                        </td>
                                        <td class="center"><%=item.getTongTien() %></td>
                                        <td class="center"><%if(item.getTrangThai()==1) out.print("Đang vận chuyển"); else out.print("Đã giao"); %></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/order/detail?id=<%=item.getMaDH() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Xem</a>
                                        </td>
                                    </tr>
                                <%
                                			}} else {
                                %>
                                	<tr><td colspan="6">Chưa có đơn hàng nào!</td></tr>
                                <%
                                		}
                                %>
                                </tbody>
                            </table>
                            <div class="row">
                                <div class="col-sm-6">
                                <%
                                	int soTrang = (int)request.getAttribute("sotrang");
                                	int trangHT = (int)request.getAttribute("trangHT");
                                	int size = (int)request.getAttribute("size");
                                	int soPT = (int)request.getAttribute("soPT");
                                %>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị <%=size %> của <%=soPT %> đơn hàng</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        <%
                                        if(trangHT > 1) {
                                        %>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/orders?page=<%=trangHT-1%>">Trang trước</a></li>
                                            <%
                                        }
                                            	for(int i = 1; i <= soTrang; i++) {
                                            		if(i == trangHT) {
                                            %>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/orders?page=<%=i%>"><%=i %></a></li>
                                            <%} else { %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/orders?page=<%=i%>"><%=i %></a></li>
											<%
                                            	}}
                                            	if(trangHT < soTrang) {
											%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/orders?page=<%=trangHT+1%>">Trang tiếp</a></li>
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
    document.getElementById("orders").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>