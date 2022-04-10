<%@page import="util.StringUtil"%>
<%@page import="model.dao.PhanHoiDAO"%>
<%@page import="model.bean.PhanHoi"%>
<%@page import="model.bean.Comment"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>

    <!-- Product Details Section Begin -->
    <%
    	Product itemSP = null;
    	if(request.getAttribute("sp") != null) {
    		itemSP = (Product)request.getAttribute("sp");
    	}
    %>
    
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                            <img class="product__details__pic__item--large"
                                src="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=itemSP.getPicture().get(0).getTenHA() %>"  width="500px" height="700px" alt="">
                        </div>
                        <div class="product__details__pic__slider owl-carousel">
                        <%
                        	if(itemSP.getPicture().size() > 1) {
                        		for(int i = 0; i <= (itemSP.getPicture().size()-1); i++) {
                        %>
                            <img data-imgbigurl="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=itemSP.getPicture().get(i).getTenHA() %>"
                                src="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=itemSP.getPicture().get(i).getTenHA() %>" alt="">
                        <%} }%>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__text">
                        <h3><%=itemSP.getTenSP() %></h3>
                        <div class="product__details__price">
                        <%
                        	int giam = (itemSP.getGia()*itemSP.getKhuyenMai())/100;
                            DecimalFormat formatter = new DecimalFormat("###,###,###");
                            out.println(formatter.format(itemSP.getGia()-giam)+" VNĐ");
                        %>
                        </div>
                        <div style="margin-bottom: 10px;">
                        	<span style="margin-right: 7px;">Giảm: <%=itemSP.getKhuyenMai() %>%</span><span  style="color: #C0C0C0; text-decoration: line-through;">Giá gốc:  <% out.println(formatter.format(itemSP.getGia())+" VNĐ"); %></span>
                        </div>
                        <p><%=itemSP.getChiTiet().substring(0, 100) %>...</p>
                        <form action="<%=request.getContextPath() %>/gio-hang" method="post">
	                        <div class="product__details__quantity">
	                            <div class="quantity">
	                                <div class="pro-qty">
	                                    <input type="text" value="1" name="soluong">
	                                </div>
	                                <input type="hidden" value="<%=itemSP.getMaSP() %>" name="MaSP">
	                            </div>
	                        </div>
	                        <input type="submit" class="primary-btn" value="Thêm vào giỏ hàng">
                        </form>
                        <ul>
                            <li><b>Nhà sản xuất: </b> <span>Tân Việt</span></li>
                            <li><b>Ship: </b> <span>Go<samp></samp></span></li>
                            <li><b>Cân nặng: </b> <span>0.5 kg</span></li>
                            <li><b>Chia sẻ:</b>
                                <div class="share">
                                    <a href="#"><i class="fa fa-facebook"></i></a>
                                    <a href="#"><i class="fa fa-twitter"></i></a>
                                    <a href="#"><i class="fa fa-instagram"></i></a>
                                    <a href="#"><i class="fa fa-pinterest"></i></a>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-lg-12">
                    <div class="product__details__tab">
                        <ul class="nav nav-tabs" role="tablist">
                            <li class="nav-item">
                                <a class="nav-link active" data-toggle="tab" href="#tabs-1" role="tab"
                                    aria-selected="true">Thông tin</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" data-toggle="tab" href="#tabs-3" role="tab"
                                    aria-selected="false">Nhận xét</a>
                            </li>
                        </ul>
                        <div class="tab-content">
                            <div class="tab-pane active" id="tabs-1" role="tabpanel">
                                <div class="product__details__tab__desc">
                                    <h6>Thông tin sản phẩm</h6>
                                    <p><%=itemSP.getChiTiet() %></p>
                                </div>
                            </div>
                             <%
	                        	HttpSession sess = request.getSession();
	                        	if(sess.getAttribute("cusLogin") != null) {
	                        		Customer cus = (Customer)sess.getAttribute("cusLogin");
                        	%>
                            <div class="tab-pane" id="tabs-3" role="tabpanel">
                                <div class="product__details__tab__desc" >
                                   <h6>Nhận xét về sản phẩm</h6>
                                <div class="col-8" style="border: 1px solid grey; border-radius: 5px; background-color: white;">
                            		<form action="javascript:void(0)" method="POST" id="danh-gia">
	                                	<input type="hidden" name="MaKH" id="MaKH" value="<%=cus.getMaKH()%>"> 
	                                	<input type="hidden" name="MaSP" id="MaSP" value="<%=itemSP.getMaSP()%>"> 
	                                	<div class="c-user-rate-form" style="display: flex; align-items: center; flex-direction: column;">
		                                    <textarea style="width: 100%; height: 50px; padding: 10px;" name="text" id="txtReview" rows="4" placeholder="Viết nhận xét của bạn..."></textarea>
		                                    <p class="f-err" style="display: none; color: red;">Mời bạn viết nhập xét...</p>
		                                    <button type="submit" style="margin: 10px;" class="btn btn-primary" onclick="getComment()">Gửi</button>
	                                	</div>
                            		</form>
                        		</div>
                        		<div class="giaidap" style="margin-top: 50px;">
                                	<%
                                		if(request.getAttribute("listBL") != null) {
                                			ArrayList<Comment> listBL = (ArrayList<Comment>)request.getAttribute("listBL");
                                			if(listBL.size() > 0) {
                                				for(Comment ite: listBL) {
                                	%>
                                				<div style="margin-bottom: 10px;">
                                					<span style="font-weight: bold;"><%=ite.getCus().getTenKH() %></span>
                                					<span style="color: #CDCDCD; margin-left: 20px; font-style: italic;"><%=ite.getNgay() %></span>
                                					<br />
                                					<span><%=ite.getNoidung() %></span><a href="javascript:void(0)" onclick="displayChat(<%=ite.getMaCM() %>, 2, <%=cus.getMaKH() %>)" style="text-decoration: none; margin-left: 15px; font-style: italic;">Phản hồi</a>
                                			    </div>
                                			    
                                			    <div class="phanhoi<%=ite.getMaCM()%>">
                                			    <%
                                			    	PhanHoiDAO phDAO = new PhanHoiDAO();
                                			    	ArrayList<PhanHoi> listPH = phDAO.getItems(ite.getMaCM());
                                			    	if(listPH.size() > 0) {
                                			    		for(PhanHoi itemPH: listPH) {
                                			    %>
                                			    <div style="margin-bottom: 10px; margin-left: 30px;">
                                					<span style="font-weight: bold;"><%=itemPH.getCus().getTenKH() %></span>
                                					<span style="color: #CDCDCD; margin-left: 20px; font-style: italic;"><%=itemPH.getNgay() %></span>
                                					<br />
                                					<span><%=itemPH.getNoiDung() %></span>
                                				</div>
                                				<%}} %>
                                			    </div>
                                			    
                                			    <div class="khung<%=ite.getMaCM()%>">
                                			    	
                                			    </div>
                                	<%	
                                				}
                                			} else {
                                	%>
                                		Chưa có bình luận nào. Bạn hãy là người bình luận đầu tiên.
                                	<%
                                			}
                                		}
                                	%>
                                </div>
                                </div>
								<div class="clearfix"></div>
							</div>
                                </div>
                            </div>
                          <%} else { %>
                          <span style="color: red; margin-left: 500px; margin-top: 50px;">Bạn cần đăng nhập để thực hiện chức năng bình luận.</span>
                          <%} %>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->

    <!-- Related Product Section Begin -->
    <section class="related-product">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="section-title related__product__title">
                        <h2>Sản phẩm tương tự</h2>
                    </div>
                </div>
            </div>
            <div class="row">
            <%
            	if(request.getAttribute("listLQ") != null) {
            		ArrayList<Product> listLQ = (ArrayList<Product>)request.getAttribute("listLQ");
            		if(listLQ.size() > 0) {
            			for(Product ite: listLQ) {
            %>
                <div class="col-lg-3 col-md-4 col-sm-6">
                    <div class="product__item">
                        <div class="product__item__pic set-bg" data-setbg="<%=request.getContextPath() %>/templates/admin/assets/img/product/<%=ite.getPicture().get(0).getTenHA() %>">
                            <ul class="product__item__pic__hover">
                                <li><a href="#"><i class="fa fa-heart"></i></a></li>
                                <li><a href="#"><i class="fa fa-retweet"></i></a></li>
                                <li><a href="<%=request.getContextPath()%>/gio-hang/<%=StringUtil.makeSlug(ite.getTenSP()) %>-<%=ite.getMaSP()%>"><i class="fa fa-shopping-cart"></i></a></li>
                            </ul>
                        </div>
                        <div class="product__item__text">
                            <h6><a href="<%=request.getContextPath()%>/<%=StringUtil.makeSlug(ite.getMaDM().getName()) %>/<%=StringUtil.makeSlug(ite.getTenSP()) %>-<%=ite.getMaSP()%>.html"><%=ite.getTenSP() %></a></h6>
                            <h5>
                            <%
	                             out.println(formatter.format(ite.getGia())+" VNĐ");
	                        %>
                            </h5>
                        </div>
                    </div>
                </div>
            <%}}} else { %>
            	Không có sản phẩm liên quan nào!
            <%} %>
            </div>
        </div>
    </section>
    <!-- Related Product Section End -->
<script type="text/javascript">
				function getComment() {
					var idcus = $("#MaKH").val();
					var idsp = $("#MaSP").val();
					var noidung = $("#txtReview").val();
					
					$.ajax({
						url: '<%=request.getContextPath() %>/comment',
						type: 'POST',
						cache: false,
						data: {
								aidcus: idcus,
								aidsp : idsp,
								anoidung: noidung,
								},
						success: function(data){
							$(".giaidap").html(data);
						},
						error: function (){
						    alert("có lỗi trong quá trình xử lý");
						}
					});
					return false;
				}
				
				function displayChat(id, stt, idcus) {
					
					$.ajax({
						url: '<%=request.getContextPath() %>/comment',
						type: 'POST',
						cache: false,
						data: {
								aidcmt : id,
								astt : stt,
								aidcus: idcus,
								},
						success: function(data){
							$(".khung" + id).html(data);
						},
						error: function (){
						    alert("có lỗi trong quá trình xử lý");
						}
					});
					return false;
				}
				
				function getPhanhoi(idcus, idcmt) {
					var noidung = $("#txtPH").val();
					
					$.ajax({
						url: '<%=request.getContextPath() %>/phanhoi',
						type: 'POST',
						cache: false,
						data: {
								aidcmt : idcmt,
								aidcus: idcus,
								anoidung: noidung,
								},
						success: function(data){
							$(".phanhoi" + idcmt).html(data);
						},
						error: function (){
						    alert("có lỗi trong quá trình xử lý");
						}
					});
					return false;
				}
</script>

 <!-- Footer Section Begin -->
    <%@include file="/templates/public/inc/footer.jsp"%>