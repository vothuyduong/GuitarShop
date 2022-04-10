<%@page import="model.bean.DetailOrders"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2 style="margin-left: 15px;">Thống kê</h2>
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
                            <div class="row" style="margin-bottom: 20px;">
                               <div class="col-sm-6">
                               <form method="post" action="<%=request.getContextPath()%>/admin/reports">
                                    <label for="ngaybd">Ngày bắt đầu:</label>
									<input type="date" id="ngaybd" name="ngaybd" style="margin-right: 50px;">
									<label for="ngaykt">Ngày kết thúc:</label>
									<input type="date" id="ngaykt" name="ngaykt">
									<input type="submit" name="search" value="Tìm" class="btn btn-warning btn-sm" style="float:right; margin-top:5px; margin-left:15px;" />
								</form>
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>Tên sản phẩm</th>
                                        <th>Số lượng đã bán</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                    if(request.getAttribute("dt") != null) {
                                    	ArrayList<DetailOrders> list = (ArrayList<DetailOrders>)request.getAttribute("dt");
                                    	if(list.size() > 0) {
                                    		for(DetailOrders x: list) {
                                    %>
                                    <tr>
                                        <td><%=x.getTenSP() %></td>
                                        <td><%=x.getSoLuongMua() %></td>
                                     </tr>
                                    <%}}} %>
                                </tbody>
                            </table>
                            <div class="row">
                            	<div class="col-sm-6">
                                   <strong style="color: red;">Tổng doanh thu: <%if(request.getAttribute("tong") != null) out.print(request.getAttribute("tong")); %> VNĐ</strong>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
</div>
<script>
    document.getElementById("report").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>