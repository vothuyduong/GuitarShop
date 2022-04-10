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
                <h2 style="margin-left: 15px;">Thêm sản phẩm</h2>
            </div>
        </div>
        <!-- /. ROW  -->
         <%
        	if(request.getParameter("err") != null) {
        		String err = request.getParameter("err");
        		if(err.equals("1")) {
        			out.print("<span style=\"color: red; margin-left: 20px;\">Hãy chọn danh mục con của danh mục bạn vừa chọn!</span>");
        		}
        		if(err.equals("2")) {
        			out.print("<span style=\"color: red; margin-left: 20px;\">Thêm sản phẩm thất bại!</span>");
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
                                <form role="form" method="post" action="" id="form">
                                    <div class="form-group">
                                        <label for="name">Tên sản phẩm</label>
                                        <input type="text" id="name" value="<%if(request.getParameter("name")!= null) out.print(request.getParameter("name")); %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="soluong">Số lượng</label>
                                        <input type="text" id="soluong" value="<%if(request.getParameter("soluong") != null) out.print(request.getParameter("soluong")); %>" name="soluong" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="gia">Giá</label>
                                        <input type="text" id="gia" value="<%if(request.getParameter("gia") != null) out.print(request.getParameter("gia")); %>" name="gia" class="form-control" />
                                    </div>
                                     <div class="form-group">
                                        <label for="khuyenmai">Khuyến mãi</label>
                                        <input type="text" id="khuyenmai" value="<%if(request.getParameter("khuyenmai") != null) out.print(request.getParameter("khuyenmai")); %>" name="khuyenmai" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục sản phẩm</label>
                                        <select id="category" name="category" class="form-control">
                                        	<option value="0">Chọn</option>
	                                        <%
	                                        	CategoryDAO catDAO = new CategoryDAO();
                                        		ArrayList<Category> list = catDAO.getItems();
                                        		int chon = 0;
                                        		if(request.getParameter("category") != null) {
                                        			chon = Integer.parseInt(request.getParameter("category"));
                                        		}
                                        		catDAO.displayDM(list, 0, 0, chon, out);
	                                        %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea class="form-control" id="detail" rows="5" name="detail"><%if(request.getParameter("detail") != null) out.print(request.getParameter("detail")); %></textarea>
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
    document.getElementById("product").classList.add('active-menu');
</script>
<script type="text/javascript">
		
		$(document).ready(function() {
			$('#form').validate({
				
				rules: {
					name: {
						required: true,
					},
					soluong: {
						required: true,
					},
					gia: {
						required: true,
					},
					khuyenmai: {
						required: true,
					},
					detail: {
						required: true,
					},
				},
				
				messages: {
					name: {
						required: 'Vui lòng nhập tên sản phẩm!',
					},
					soluong: {
						required: 'Vui lòng nhập số lượng sản phẩm!',
					},
					gia: {
						required: 'Vui lòng nhập giá sản phẩm!',
					},
					khuyenmai: {
						required: 'Vui lòng nhập khuyến mãi sản phẩm!',
					},
					detail: {
						required: 'Vui lòng nhập chi tiết sản phẩm!',
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