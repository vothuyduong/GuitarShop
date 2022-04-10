<%@page import="model.bean.Product"%>
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
        			out.print("<span style=\"color: red; margin-left: 20px;\">Sửa sản phẩm thất bại!</span>");
        		}
        	}
         	
         	String name = "";
         	String chitiet = "";
         	int gia = 0;
         	int khuyenmai = 0;
         	int soluong = 0;
         	int chon = 0;
         	if(request.getAttribute("itemSP") != null) {
         		Product item = (Product)request.getAttribute("itemSP");
         		name = item.getTenSP();
         		chitiet = item.getChiTiet();
         		gia = item.getGia();
         		khuyenmai = item.getKhuyenMai();
         		soluong = item.getSoLuong();
         		chon = item.getMaDM().getId();
         	}
         	if(request.getParameter("name") != null) {
         		name = request.getParameter("name");
         	}
         	if(request.getParameter("soluong") != null) {
         		soluong = Integer.parseInt(request.getParameter("soluong"));
         	}
         	if(request.getParameter("gia") != null) {
         		gia = Integer.parseInt(request.getParameter("gia"));
         	}
         	if(request.getParameter("khuyenmai") != null) {
         		khuyenmai = Integer.parseInt(request.getParameter("khuyenmai"));
         	}
         	if(request.getParameter("detail") != null) {
         		chitiet = request.getParameter("detail");
         	}
         	if(request.getParameter("category") != null) {
         		chon = Integer.parseInt(request.getParameter("category"));
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
                                <form role="form" method="post" action="">
                                    <div class="form-group">
                                        <label for="name">Tên sản phẩm</label>
                                        <input type="text" id="name" value="<%=name %>" name="name" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="soluong">Số lượng</label>
                                        <input type="text" id="soluong" value="<%=soluong %>" name="soluong" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="gia">Giá</label>
                                        <input type="text" id="gia" value="<%=gia %>" name="gia" class="form-control" />
                                    </div>
                                     <div class="form-group">
                                        <label for="khuyenmai">Khuyến mãi</label>
                                        <input type="text" id="khuyenmai" value="<%=khuyenmai %>" name="khuyenmai" class="form-control" />
                                    </div>
                                    <div class="form-group">
                                        <label for="category">Danh mục sản phẩm</label>
                                        <select id="category" name="category" class="form-control">
                                        	<option value="0">Chọn</option>
                                        	<%
	                                        	CategoryDAO catDAO = new CategoryDAO();
                                        		ArrayList<Category> list = catDAO.getItems();
                                        		catDAO.displayDM(list, 0, 0, chon, out);
	                                        %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label for="detail">Chi tiết</label>
                                        <textarea class="form-control" id="detail" rows="5" name="detail"><%=chitiet %></textarea>
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