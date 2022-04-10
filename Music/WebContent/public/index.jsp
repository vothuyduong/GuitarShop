<%@page import="util.StringUtil"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.bean.Product"%>
<%@page import="model.dao.ProductDAO"%>
<%@page import="model.dao.PictureDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
    <!-- Header Section End -->

    <!-- Categories Section Begin -->
    <section class="categories">
        <div class="container">
	        <div class="row">
	                <div class="col-lg-12">
	                    <div class="section-title from-blog__title">
	                        <h2>Các loại sản phẩm</h2>
	                    </div>
	                </div>
	        </div>
            <div class="row">
                <div class="categories__slider owl-carousel">
                <%
                	CategoryDAO caDAO = new CategoryDAO();
                	PictureDAO picDAO = new PictureDAO();
                	ArrayList<Category> li = (ArrayList<Category>)caDAO.getItems();
                	for(Category it: li) {
                		int id = caDAO.layCon(it.getId(), li);
                		String tenAnh = picDAO.getItemm(id).getTenHA();
                %>
                    <div class="col-lg-3">
                        <div class="categories__item set-bg" data-setbg="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=tenAnh%>">
                            <h5><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(it.getName()) %>-<%=it.getId()%>"><%=it.getName() %></a></h5>
                        </div>
                    </div>
                <%} %>
                </div>
            </div>
        </div>
    </section>

    <!-- Latest Product Section Begin -->
    <section class="latest-product spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Sản phẩm mới</h4>
                        <div class="latest-product__slider owl-carousel">
                            <div class="latest-prdouct__slider__item">
                            <%
                            	ProductDAO proDAO = new ProductDAO();
                            	ArrayList<Product> li1 = proDAO.getMoiNhat(1, 3);
                            	for(Product itemm: li1) {
                            %>
                                <a href="<%=request.getContextPath() %>/<%=StringUtil.makeSlug(itemm.getMaDM().getName()) %>/<%=StringUtil.makeSlug(itemm.getTenSP()) %>-<%=itemm.getMaSP() %>.html" class="latest-product__item">
                                    <div class="latest-product__item__pic">
                                        <img src="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=itemm.getPicture().get(0).getTenHA()%>" alt="">
                                    </div>
                                    <div class="latest-product__item__text">
                                        <h6><%=itemm.getTenSP() %></h6>
                                        <span>
                                        <%
                                        DecimalFormat formatter = new DecimalFormat("###,###,###");
                                        out.println(formatter.format(itemm.getGia())+" VNĐ");
                                        %>
                                        </span>
                                    </div>
                                </a>
                            <%} %>
                            </div>
                            <div class="latest-prdouct__slider__item">
                             <%
                            	ArrayList<Product> li2 = proDAO.getMoiNhat(2, 3);
                            	for(Product itemm: li2) {
                            %>
                                <a href="<%=request.getContextPath() %>/<%=StringUtil.makeSlug(itemm.getMaDM().getName()) %>/<%=StringUtil.makeSlug(itemm.getTenSP()) %>-<%=itemm.getMaSP() %>" class="latest-product__item">
                                    <div class="latest-product__item__pic">
                                        <img src="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=itemm.getPicture().get(0).getTenHA()%>" alt="">
                                    </div>
                                    <div class="latest-product__item__text">
                                        <h6><%=itemm.getTenSP() %></h6>
                                        <span>
                                       	<%
	                                        DecimalFormat formatter = new DecimalFormat("###,###,###");
	                                        out.println(formatter.format(itemm.getGia())+" VNĐ");	
                                        %>
                                        </span>
                                    </div>
                                </a>
                            <%} %>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Sản phẩm bán chạy</h4>
                        <div class="latest-product__slider owl-carousel">
                            <div class="latest-prdouct__slider__item">
                            <%
                            	ArrayList<Product> listBC = proDAO.getSPBanChay(1, 3);
                            	for(Product itemm: listBC) {
                            %>
                                <a href="<%=request.getContextPath() %>/<%=StringUtil.makeSlug(itemm.getMaDM().getName()) %>/<%=StringUtil.makeSlug(itemm.getTenSP()) %>-<%=itemm.getMaSP() %>.html" class="latest-product__item">
                                    <div class="latest-product__item__pic">
                                        <img src="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=itemm.getPicture().get(0).getTenHA()%>" alt="">
                                    </div>
                                    <div class="latest-product__item__text">
                                        <h6><%=itemm.getTenSP() %></h6>
                                        <span>
                                         <%
	                                        DecimalFormat formatter = new DecimalFormat("###,###,###");
	                                        out.println(formatter.format(itemm.getGia())+" VNĐ");
	                                    %>
                                        </span>
                                    </div>
                                </a>
                            <%} %>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-6">
                    <div class="latest-product__text">
                        <h4>Phụ kiện</h4>
                           <div class="latest-product__slider owl-carousel">
                            <div class="latest-prdouct__slider__item">
                            <%
                            	ArrayList<Product> listPK = proDAO.getSP(6);
                            	int d = 0;
                            	for(Product itemm: listPK) {
                            		d++;
                            		if(d > 3) break;
                            %>
                                <a href="<%=request.getContextPath() %>/<%=StringUtil.makeSlug(itemm.getMaDM().getName()) %>/<%=StringUtil.makeSlug(itemm.getTenSP()) %>-<%=itemm.getMaSP() %>.html" class="latest-product__item">
                                    <div class="latest-product__item__pic">
                                        <img src="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=itemm.getPicture().get(0).getTenHA()%>" alt="">
                                    </div>
                                    <div class="latest-product__item__text">
                                        <h6><%=itemm.getTenSP() %></h6>
                                        <span>
                                         <%
	                                        DecimalFormat formatter = new DecimalFormat("###,###,###");
	                                        out.println(formatter.format(itemm.getGia())+" VNĐ");
                                        %>
                                        </span>
                                    </div>
                                </a>
                            <%} %>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Latest Product Section End -->

    <!-- Blog Section Begin -->
    <section class="from-blog spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title from-blog__title">
                        <h2>Blog hay về đàn</h2>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="<%=request.getContextPath() %>/templates/admin/assets/img/blog1.jpg" width="360px" height="240px" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i> 3/3/2022</li>
                                <li><i class="fa fa-comment-o"></i> 5</li>
                            </ul>
                            <h5><a href="#">Tập hát trên nền nhạc guitar</a></h5>
                            <p>Nam ca sĩ Devid đang tập hát cho buổi biểu diễn sắp tới, vào dịp 30/4.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="<%=request.getContextPath() %>/templates/admin/assets/img/blog2.jpg" width="360px" height="240px" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i> 3/3/2022 </li>
                                <li><i class="fa fa-comment-o"></i> 10</li>
                            </ul>
                            <h5><a href="#">Cách chơi đàn Organ</a></h5>
                            <p>Ông Ba sẽ chỉ chúng ta cách chơi đàn Organ với 30 năm kinh nghiệm của mình.</p>
                        </div>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-6">
                    <div class="blog__item">
                        <div class="blog__item__pic">
                            <img src="<%=request.getContextPath() %>/templates/admin/assets/img/blog3.jpg" width="360px" height="240px" alt="">
                        </div>
                        <div class="blog__item__text">
                            <ul>
                                <li><i class="fa fa-calendar-o"></i> 3/3/2022</li>
                                <li><i class="fa fa-comment-o"></i> 9</li>
                            </ul>
                            <h5><a href="#">Thanh Bùi ra bài hát mới</a></h5>
                            <p>Trong bài hát, chàng ca sĩ đã thể hiện khả năng đánh đàn piano của mình.</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Blog Section End -->

    <!-- Footer Section Begin -->
    <%@include file="/templates/public/inc/footer.jsp"%>