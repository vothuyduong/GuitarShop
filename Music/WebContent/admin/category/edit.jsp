<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.CategoryDAO"%>
<%@page import="model.bean.Category"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2 style="margin-left: 15px;">Sửa danh mục</h2>
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
    			out.print("<span style=\"color: red; margin-left: 20px;\">Danh mục cha trùng với danh mục hiện tại. Không hợp lệ!</span>");
    		}
    		if(err.equals("3")) {
    			out.print("<span style=\"color: red; margin-left: 20px;\">Sửa danh mục thất bại!</span>");
    		}
    		if(err.equals("4")) {
    			out.print("<span style=\"color: red; margin-left: 20px;\">Danh mục cha là con của danh mục cần sửa. Không hợp lệ!</span>");
    		}
    	}
        	Category itemCat = null;
        
        String name = "";
        int id_parent = 0;
        int id = 0;
    
       	if(request.getAttribute("itemCat") != null) {
       		itemCat = (Category)request.getAttribute("itemCat");
       		name = itemCat.getName();
       		id_parent = itemCat.getId_parent();
       		id = itemCat.getId();
       	}
       	if(request.getParameter("name") != null) {
       		name = request.getParameter("name");
       	}
     	
       	if(request.getParameter("category") != null) {
       		id_parent = Integer.parseInt(request.getParameter("category"));
       	}
       	if(request.getParameter("id") != null) {
       		id = Integer.parseInt(request.getParameter("id"));
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
                                <form role="form" method="post">
                                    <div class="form-group">
                                        <label for="name">Tên danh mục</label>
                                        <input type="text" id="name" value="<%=name %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục cha</label>
                                        <select id="category" name="category" class="form-control">
	                                        <option value="0">Chọn</option>
											<%
											CategoryDAO catDAO = new CategoryDAO();
                                        	ArrayList<Category> list = catDAO.getItems();
                                        	catDAO.displayDM(list, 0, 0, id_parent,out);
											%>
                                        </select>
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