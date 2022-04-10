<%@page import="java.text.DecimalFormat"%>
<%@page import="model.bean.Product"%>
<%@page import="model.dao.ProductDAO"%>
<%@page import="model.bean.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
    <!-- Header Section End -->
    <!-- Shoping Cart Section Begin -->
    <%
    	if(request.getParameter("err") != null) {
    		String err = request.getParameter("err");
    		if(err.equals("1")) {
    			out.print("<span style=\"color: red; font-size: 20px; margin-left: 500px;\">Sản phẩm trong kho không đủ cho lần đặt hàng này.</span>");
    		}
    	}
    %>
    <%
    	HttpSession see = request.getSession();
    	if(see.getAttribute("cusLogin") != null) {
    %>
    <section class="shoping-cart spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__table">
                    	<div class="giohang">
                        <table>
                            <thead>
                                <tr>
                                    <th class="shoping__product">Sản phẩm</th>
                                    <th>Giá</th>
                                    <th>Số lượng</th>
                                    <th>Thành tiền</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                            
	                            <%
	                            	DecimalFormat formatter = new DecimalFormat("###,###,###");
	                            	int tong = 0;
	                            	if(request.getAttribute("listSP") != null) {
	                            		ArrayList<Cart> listSP = (ArrayList<Cart>)request.getAttribute("listSP");
	                            		if(listSP.size() > 0) {
	                            			for(Cart ite: listSP) {
	                            				ProductDAO proDAO = new ProductDAO();
	                            				Product pro = proDAO.getItemById(ite.getMaSP());
	                            				int giam = (ite.getGia()*pro.getKhuyenMai())/100;
	                            				int tam = (ite.getGia()-giam) * ite.getSoluong();
	                            				tong += tam;
	                            %>
	                                <tr>
	                                    <td class="shoping__cart__item">
	                                        <img src="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=pro.getPicture().get(0).getTenHA() %>" width="50px" alt="">
	                                        <h5><%=pro.getTenSP() %></h5>
	                                    </td>
	                                    <td class="shoping__cart__price">
	                                        <%
		                                        out.println(formatter.format(ite.getGia()-giam)+" VNĐ");
	                                        %>
	                                    </td>
	                                    <td class="shoping__cart__quantity">
	                                        <div class="quantity">
	                                        <form action="javascript:void(0)">
	                                            <div class="pro-qty">
	                                                <input type="text" value="<%=ite.getSoluong()%>" name="soluong" id="soluong<%=ite.getMaSP()%>">
	                                            </div>
	                                            <input type="submit" class="primary-btn" value="Cập nhập" onclick="editSP(<%=ite.getMaSP() %>)">
	                                             </form>
	                                        </div>
	                                    </td>
	                                    <td class="shoping__cart__total">
	                                        <%
		                                        out.println(formatter.format(tam)+" VNĐ");
	                                        %>
	                                    </td>
	                                    <td class="shoping__cart__item__close">
	                                        <a href="<%=request.getContextPath()%>/cart/del?id=<%=ite.getMaSP()%>"><span class="icon_close"></span></a>
	                                    </td>
	                                </tr>
	                            <%}} else { %>
	                            <tr><td colspan="5">Chưa có sản phẩm nào trong giỏ hàng.</td></tr>
	                            <%}} %>
	                            
                            </tbody>
                        </table>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <div class="shoping__cart__btns">
                        <a href="<%=request.getContextPath() %>/cat?id=1" class="primary-btn cart-btn">Tiếp tục mua</a>
                    </div>
                </div>
  
                <div class="col-lg-6">
                    <div class="shoping__checkout">
                        <h5>Tổng tiền</h5>
                        <span style="color: red; font-weight: bold; font-size: 20px;">
                        <%
	                        out.println(formatter.format(tong)+" VNĐ");
                        %>
                        </span>
                        <a href="<%=request.getContextPath() %>/checkout" class="primary-btn">Thanh toán</a>
                    </div>
                </div>    
            </div>
    
        </div>
    </section>
    <%
    	} else {
    %>
    	<span style="margin-left: 500px; color: red; font-size: 20px;">Bạn cần đăng nhập để thực hiện chức năng giỏ hàng.</span>
    <%} %>
    <!-- Shoping Cart Section End -->
<script type="text/javascript">
				function editSP(idsp) {
					var soluong = $("#soluong" + idsp).val();
					
					$.ajax({
						url: '<%=request.getContextPath() %>/cart/edit',
						type: 'POST',
						cache: false,
						data: {
								aidsp : idsp,
								asoluong: soluong,
								},
						success: function(data){
							$(".giohang").html(data);
						},
						error: function (){
						    alert("có lỗi trong quá trình xử lý");
						}
					});
					return false;
				}
</script>
<%@include file="/templates/public/inc/footer.jsp"%>