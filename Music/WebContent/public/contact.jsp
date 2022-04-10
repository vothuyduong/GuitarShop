<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/templates/public/inc/header.jsp" %>
    <!-- Contact Section Begin -->
    <section class="contact spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_phone"></span>
                        <h4>Số điện thoại</h4>
                        <p>09053508</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_pin_alt"></span>
                        <h4>Địa chỉ</h4>
                        <p>15 Phạm Như Xương</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_clock_alt"></span>
                        <h4>Thời gian mở cửa</h4>
                        <p>10h sáng đến 9h tối</p>
                    </div>
                </div>
                <div class="col-lg-3 col-md-3 col-sm-6 text-center">
                    <div class="contact__widget">
                        <span class="icon_mail_alt"></span>
                        <h4>Email</h4>
                        <p>hello@gmail.com</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Contact Section End -->

    <!-- Map Begin -->
    <div class="map">
        <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d49116.39176087041!2d-86.41867791216099!3d39.69977417971648!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x886ca48c841038a1%3A0x70cfba96bf847f0!2sPlainfield%2C%20IN%2C%20USA!5e0!3m2!1sen!2sbd!4v1586106673811!5m2!1sen!2sbd"
            height="500" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
        <div class="map-inside">
            <i class="icon_pin"></i>
            <div class="inside-widget">
                <h4>Việt Nam</h4>
                <ul>
                    <li>Số điện thoại: 09053508</li>
                    <li>Địa chỉ: 15 Phạm Như Xương</li>
                </ul>
            </div>
        </div>
    </div>
    <!-- Map End -->

    <!-- Contact Form Begin -->
    <div class="contact-form spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="contact__form__title">
                        <h2>Để lại lời nhắn</h2>
                    </div>
                </div>
            </div>
            <div>
            <%
            	if(request.getParameter("msg") != null) {
            		String msg = request.getParameter("msg");
            		if(msg.equals("1")) {
            			out.print("<span style=\"margin-botom: 10px; color: yellow;\">Gửi liên hệ thành công!</span>");
            		}
            	}
	            if(request.getParameter("err") != null) {
	        		String err = request.getParameter("err");
	        		if(err.equals("1")) {
	        			out.print("<span style=\"margin-botom: 10px; color: red;\">Gửi liên hệ thất bại!</span>");
	        		}
	        	}
            %>
            </div>
            <form action="" method="post">
                <div class="row">
                    <div class="col-lg-6 col-md-6">
                        <input type="text" placeholder="Tên của bạn" name="HoTen" value="">
                    </div>
                    <div class="col-lg-6 col-md-6">
                        <input type="text" placeholder="Địa chỉ email" name="email" value="">
                    </div>
                    <div class="col-lg-12 text-center">
                        <textarea placeholder="Nội dung" name="Noidung"></textarea>
                        <button type="submit" class="site-btn">Gửi</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!-- Contact Form End -->

 <!-- Footer Section Begin -->
    <%@include file="/templates/public/inc/footer.jsp"%>