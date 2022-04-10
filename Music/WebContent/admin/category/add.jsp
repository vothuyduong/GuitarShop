<%@page import="model.bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2 style="margin-left: 15px;">Thêm danh mục</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
        	if(request.getParameter("err") != null) {
        		String err = request.getParameter("err");
        		if(err.equals("1")) {
        			out.print("<span style=\"color: red; margin-left: 20px;\">Tên danh mục rỗng. Vui lòng nhập lại!</span>");
        		}
        		if(err.equals("2")) {
        			out.print("<span style=\"color: red; margin-left: 20px;\">Tên danh mục đã tồn tại. Vui lòng nhập lại!</span>");
        		}
        		if(err.equals("3")) {
        			out.print("<span style=\"color: red; margin-left: 20px;\">Thêm danh mục thất bại!</span>");
        		}
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
                                <form role="form" method="post" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên danh mục</label>
                                        <input type="text" id="name" value="" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục cha</label>
                                        <select id="category" name="category" class="form-control">
	                                        <option value="0">Chọn</option>
											<%
												CategoryDAO catDAO = new CategoryDAO();
                                        		ArrayList<Category> list = catDAO.getItems();
                                        		catDAO.displayDM(list, 0, 0, 0, out);
											%>
                                        </select>
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Thêm</button>
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
    document.getElementById("category").classList.add('active-menu');
</script>
<script type="text/javascript">
		
		$(document).ready(function() {
			$('#form').validate({
				
				rules: {
					name: {
						required: true,
					},
				},
				
				messages: {
					name: {
						required: 'Vui lòng nhập tên danh mục!',
					},
				}
			});
		});
</script>
	
<style>
	.error {
		color: red;
	}
</style>
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>