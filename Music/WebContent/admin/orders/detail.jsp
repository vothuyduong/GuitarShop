<%@page import="model.bean.DetailOrders"%>
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
                <h2 style="margin-left: 15px;">Chi tiết đơn hàng</h2>
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
                            
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>Tên sản phẩm</th>
                                        <th>Giá</th>
                                        <th>Số lượng mua</th>
                                        <th>Thành tiền</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <%
                                	if(request.getAttribute("listCT") != null) {
                                		ArrayList<DetailOrders> listCT = (ArrayList<DetailOrders>)request.getAttribute("listCT");
                                		if(listCT.size() > 0) {
                                			for(DetailOrders itemCT: listCT) {
                                %>
                                    <tr>
                                        <td class="center"><%=itemCT.getTenSP() %></td>
                                        <td class="center"><%=itemCT.getGia() %></td>
                                        <td class="center"><%=itemCT.getSoLuongMua() %></td>
                                        <td class="center"><%=itemCT.getThanhTien() %></td>
                                    </tr>
                                <%}} }%>
                                </tbody>
                            </table>
                            <div class="row">
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
    document.getElementById("orders").classList.add('active-menu');
</script>
<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>