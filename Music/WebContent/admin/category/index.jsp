<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2 style="margin-left: 15px;">Quản lý danh mục</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        	if(request.getParameter("msg") != null) {
        		String msg = request.getParameter("msg");
        		if(msg.equals("1")) {
        			out.print("<span style=\"color: blue; margin-left: 20px;\">Thêm danh mục thành công!</span>");
        		}
        		if(msg.equals("2")) {
        			out.print("<span style=\"color: blue; margin-left: 20px;\">Sửa danh mục thành công!</span>");
        		}
        		if(msg.equals("3")) {
        			out.print("<span style=\"color: blue; margin-left: 20px;\">Xóa danh mục thành công!</span>");
        		}
        	}
	        if(request.getParameter("err") != null) {
	    		String err = request.getParameter("err");
	    		if(err.equals("1")) {
	    			out.print("<span style=\"color: red; margin-left: 20px;\">ID danh mục không hợp lệ!</span>");
	    		}
	    		if(err.equals("2")) {
	    			out.print("<span style=\"color: red; margin-left: 20px;\">Hãy xóa danh mục con trước khi xóa danh mục này!</span>");
	    		}
	    		if(err.equals("3")) {
	    			out.print("<span style=\"color: red; margin-left: 20px;\">Không thể xóa danh mục này vì còn sản phẩm thuộc danh mục!</span>");
	    		}
	    		if(err.equals("4")) {
	    			out.print("<span style=\"color: red; margin-left: 20px;\">Xóa danh mục thất bại!</span>");
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
                                    <a href="<%=request.getContextPath() %>/admin/cat/add" class="btn btn-success btn-md">Thêm</a>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <form method="post" action="<%=request.getContextPath()%>/admin/cat/search">
                                        <input type="submit" name="search" value="Tìm kiếm" class="btn btn-warning btn-sm" style="float:right" />
                                        <input type="search" name="key" value="" class="form-control input-sm" placeholder="Nhập tên danh mục" style="float:right; width: 300px;" />
                                        <div style="clear:both"></div>
                                    </form><br />
                                </div>
                            </div>

                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Tên danh mục</th>
                                        <th>Danh mục cha</th>
                                        <th>Hiển thị</th>
                                        <th width="160px">Chức năng</th>
                                    </tr>
                                </thead>
                                <tbody>
                                 <%
                                 	CategoryDAO catDAO = new CategoryDAO();
                                 	if(request.getAttribute("listCat") != null) {
                                 		ArrayList<Category> list = (ArrayList<Category>)request.getAttribute("listCat");
                                 		if(list.size() > 0) {
                                 			for(Category item: list) {
                                 %>
                                    <tr>
                                        <td><%=item.getId() %></td>
                                        <td class="center"><%=item.getName() %></td>
                                        <td class="center"><%=catDAO.getNameDM(item.getId_parent()) %></td>
                                        <td class="center"><div class="hienthi-<%=item.getId()%>"><a href="javascript:void(0)" onclick="getDisplay(<%=item.getHienthi()%>, <%=item.getId()%>)"><%if(item.getHienthi() == 1) out.print("Có"); else out.print("Không"); %></a></div></td>
                                        <td class="center">
                                            <a href="<%=request.getContextPath() %>/admin/cat/edit?id=<%=item.getId() %>" title="" class="btn btn-primary"><i class="fa fa-edit "></i> Sửa</a>
                                            <a href="<%=request.getContextPath() %>/admin/cat/del?id=<%=item.getId() %>" title="" class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xóa không?')"><i class="fa fa-pencil"></i> Xóa</a>
                                        </td>
                                    </tr>
                                <%}} else { %>
                                	<tr><td colspan="5"></td>Chưa có danh mục nào!</tr>
                                 <%} %>
                                </tbody>
                            </table>
                            
                            <script type="text/javascript">
								function getDisplay(ht, id) {
									$.ajax({
										url: '<%=request.getContextPath() %>/admin/cats',
										type: 'POST',
										cache: false,
										data: {
											aht: ht,
											aid: id,
										},
									success: function(data){
										$(".hienthi-"+id).html(data);
									},
									error: function (){
						    			alert("có lỗi trong quá trình xử lý");
									}
									});
									return false;
								}		
							</script>
                            
                            <div class="row">
                                <div class="col-sm-6">
                                <%
                                	int soTrang = (int)request.getAttribute("sotrang");
                                	int size = (int)request.getAttribute("size");
                                	int soPT = (int)request.getAttribute("soPT");
                                	int trangHT = (int)request.getAttribute("trangHT");
                                %>
                                    <div class="dataTables_info" id="dataTables-example_info" style="margin-top:27px">Hiển thị từ 1 đến <%=size %> của <%=soPT %> danh mục</div>
                                </div>
                                <div class="col-sm-6" style="text-align: right;">
                                    <div class="dataTables_paginate paging_simple_numbers" id="dataTables-example_paginate">
                                        <ul class="pagination">
                                        	<%
                                        		if(trangHT > 1) {
                                        	%>
                                            <li class="paginate_button previous" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_previous"><a href="<%=request.getContextPath()%>/admin/cats?page=<%=trangHT-1%>">Trang trước</a></li>
                                            <%
                                        		}
                                            	for(int i = 1; i <= soTrang; i++) {
                                            		if(trangHT == i) {
                                            %>
                                            <li class="paginate_button active" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/cats?page=<%=i%>"><%=i %></a></li>
                                            <%} else { %>
											<li class="paginate_button" aria-controls="dataTables-example" tabindex="0"><a href="<%=request.getContextPath()%>/admin/cats?page=<%=i%>"><%=i %></a></li>
											<%}} 
                                            	if(trangHT < soTrang) {
											%>
                                            <li class="paginate_button next" aria-controls="dataTables-example" tabindex="0" id="dataTables-example_next"><a href="<%=request.getContextPath()%>/admin/cats?page=<%=trangHT+1%>">Trang tiếp</a></li>
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
    document.getElementById("category").classList.add('active-menu');
</script>

<!-- /. PAGE INNER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>