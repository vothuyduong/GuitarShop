<%@page import="model.bean.Product"%>
<%@page import="model.dao.ProductDAO"%>
<%@page import="model.bean.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>

<%
	HttpSession s = request.getSession();
	if(s.getAttribute("cusLogin") != null) {
		Customer item = (Customer)s.getAttribute("cusLogin");
%>
    <!-- Checkout Section Begin -->
    <section class="checkout spad">
        <div class="container">
           <div class="checkout__form">
                <h4>Chi tiết hóa đơn</h4>
                <form action="#">
                    <div class="row">
                        <div class="col-lg-8 col-md-6">
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Họ và tên<span></span></p>
                                        <input type="text" value="<%=item.getTenKH()%>">
                                    </div>
                                </div>
                            </div>

                            <div class="checkout__input">
                                <p>Địa chỉ<span></span></p>
                                <input type="text" value="<%=item.getDiaChi() %>" class="checkout__input__add">
                            </div>
                            <div class="row">
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Số điện thoại<span></span></p>
                                        <input type="text" value="<%=item.getSdt()%>">
                                    </div>
                                </div>
                                <div class="col-lg-6">
                                    <div class="checkout__input">
                                        <p>Email<span></span></p>
                                        <input type="text" value="<%=item.getEmail()%>">
                                    </div>
                                </div>
                            </div>
                        </div>
            
                        <div class="col-lg-4 col-md-6">
                            <div class="checkout__order">
                                <h4>Đơn hàng của bạn</h4>
                                <div class="checkout__order__products">Sản phẩm <span>Thành tiền</span></div>
                                <ul>
                                <%
                                	ArrayList<Cart> listt = (ArrayList<Cart>)request.getAttribute("listSP");
                                	if(list.size() > 0) {
                                		for(Cart it: listt) {
                                			ProductDAO proDAO = new ProductDAO();
                                			Product pro = proDAO.getItemById(it.getMaSP());
                                			int giam = (it.getGia() * pro.getKhuyenMai())/100;
                                %>
                                    <li><%=proDAO.getNameById(it.getMaSP()) %> <span><%=(it.getGia()-giam)*it.getSoluong() %></span></li>
                                <%} }%>
                                </ul>
                                <div class="checkout__order__total">Tổng cộng<span><%if(request.getAttribute("tong") != null) out.print(request.getAttribute("tong")); %></span></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </section>
    <!-- Checkout Section End -->
<%} %>
    <!-- Footer Section Begin -->
    <%@include file="/templates/public/inc/footer.jsp"%>