<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-3.2.1.js"></script>
		<script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.validate.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="register-form">
                <form action="" method="post" id="form">
                    <h1>Đăng ký</h1>
                    <%
     					if(request.getParameter("err") != null) {
     						String err = (String)request.getParameter("err");
     						if(err.equals("1")) {
     							out.print("<span style=\"color: white; margin-bottom: 5px;\">Tên đăng nhập đã tồn tại!</span>");
     						}
     						if(err.equals("2")) {
     							out.print("<span style=\"color: white; margin-bottom: 5px;\">Mật khẩu 2 lần nhập không khớp!</span>");
     						}
     						if(err.equals("3")) {
     							out.print("<span style=\"color: white; margin-bottom: 5px;\">Hãy nhập 0 hoặc 1 ở ô giới tính!</span>");
     						}
     						if(err.equals("4")) {
     							out.print("<span style=\"color: white; margin-bottom: 5px;\">Đăng ký thất bại!</span>");
     						}
     					}
     				%>
    
                    <div class="input-box" style="margin-top: 5px;">
                    	<label for="HoTen">Họ và tên:</label><br>
                        <input type="text" placeholder="Nhập họ và tên" name="HoTen" value="<%if(request.getParameter("HoTen") != null) out.print(request.getParameter("HoTen"));%>">
                    </div>
                    <div class="input-box">
                    	<label for="NgaySinh">Ngày sinh:</label><br>
                        <input type="date"  name="NgaySinh">
                    </div>
                    <div class="input-box">
                    	<label for="GioiTinh">Giới tính: (1 - Nam/ 0 - Nữ)</label><br>
                        <input type="text" placeholder="Nhập 1 hoặc 0" name="GioiTinh" value="<%if(request.getParameter("GioiTinh") != null) out.print(request.getParameter("GioiTinh"));%>">
                    </div>
                    <div class="input-box">
                    	<label for="DiaChi">Địa chỉ:</label><br>
                        <input type="text" placeholder="Nhập địa chỉ" name="DiaChi" value="<%if(request.getParameter("DiaChi") != null) out.print(request.getParameter("DiaChi"));%>">
                    </div>
                    <div class="input-box">
                    	<label for="SDT">Số điện thoại:</label><br>
                        <input type="text" placeholder="Nhập số điện thoại" name="SDT" value="<%if(request.getParameter("SDT") != null) out.print(request.getParameter("SDT"));%>">
                    </div>
                   <div class="input-box">
                    	<label for="username">Email:</label><br>
                        <input type="email" placeholder="Nhập email" name="email" value="<%if(request.getParameter("email") != null) out.print(request.getParameter("email"));%>">
                    </div>
                    <div class="input-box">
                    	<label for="username">Tên đăng nhập:</label><br>
                        <input type="text" placeholder="Nhập tên đăng nhập" name="username" value="<%if(request.getParameter("username") != null) out.print(request.getParameter("username"));%>">
                    </div>
                    <div class="input-box">
                    	<label for="password">Mật khẩu:</label><br>
                        <input type="password" placeholder="Nhập mật khẩu" name="password">
                    </div>
                     <div class="input-box">
                    	<label for="rs-password">Nhập lại mật khẩu:</label><br>
                        <input type="password" placeholder="Nhập lại mật khẩu" name="rs-password">
                    </div>
                    <div class="btn-box">
                        <button type="submit">
                            Đăng ký
                        </button>
                    </div>
                </form>
            </div>
        </div>
<style>
	*{
	    padding: 0px;
	    margin: 0px;
	    font-family: sans-serif;
	    box-sizing: border-box;
	}
	
	.container{
	    width: 100%;
	    max-width: 1200px;
	    margin-left: auto;
	    margin-right: auto;
	}
	
	.col-6{
	    float: left;
	    width: 50%;
	}
	
	.margin_b{
	    margin-bottom: 7.5px;
	}
	
	.clear{
	    clear: both;
	}
	
	header{
	    background-color: #cccccc;
	    min-height: 70px;
	    padding: 15px;
	}
	
	main{
	    background-color: #dddddd;
	    min-height: 300px;
	    padding: 7.5px 15px;
	}
	
	footer{
	    background-color: #cccccc;
	    min-height: 70px;
	    padding: 15px;
	}
	
	h1{
	    color: #D9534F;
	    font-size: 20px;
	    margin-bottom: 30px;
	}

	.register-form{
	    width: 100%;
	    max-width: 400px;
	    margin: 100px auto;
	     background-color: #FFA500;
	    padding: 15px;
	    border: 2px dotted #cccccc;
	    border-radius: 10px;
	}

	.input-box{
	    margin-bottom: 10px;
	}
	
	.input-box input[type='text'],
	.input-box input[type='email'],
	.input-box input[type='password'],
	.input-box input[type='date']{
	    padding: 7.5px 15px;
	    width: 100%;
	    border: 1px solid #cccccc;
	    outline: none;
	    font-size: 16px;
	    display: inline-block;
	    height: 40px;
	    color: #666666;
	    margin-top: 5px;
	}
	
	.input-box select{
	    padding: 7.5px 15px;
	    width: 100%;
	    border: 1px solid #cccccc;
	    outline: none;
	    font-size: 16px;
	    display: inline-block;
	    height: 40px;
	    color: #666666;
	}
	
	.input-box option{
	    font-size: 16px;
	}
	
	.input-box input[type='checkbox']{
	    height: 1.5em;
	    width: 1.5em;
	    vertical-align: middle;
	    line-height: 2em;
	}
	
	.input-box textarea{
	    padding: 7.5px 15px;
	    width: 100%;
	    border: 1px solid #cccccc;
	    outline: none;
	    font-size: 16px;
	    min-height: 120px;
	    color: #666666;
	}
	
	.btn-box{
	    text-align: right;
	    margin-top: 30px;
	}
	
	.btn-box button{
	    padding: 7.5px 15px;
	    border-radius: 2px;
	    background-color: #D9534F;
	    color: #ffffff;
	    border: none;
	    outline: none;
	}
	</style>

<script type="text/javascript">
		
		$(document).ready(function() {
			$('#form').validate({
				
				rules: {
					HoTen: {
						required: true,
					},
					NgaySinh: {
						required: true,
					},
					GioiTinh: {
						required: true,
					},
					DiaChi: {
						required: true,
					},
					SDT: {
						required: true,
					},
					email: {
						required: true,
					},
					username: {
						required: true,
					},
					password: {
						required: true,
					},
				},
				
				messages: {
					HoTen: {
						required: 'Vui lòng nhập họ tên!',
					},
					NgaySinh: {
						required: 'Vui lòng nhập ngày sinh!',
					},
					GioiTinh: {
						required: 'Vui lòng nhập giới tính!',
					},
					DiaChi: {
						required: 'Vui lòng nhập địa chỉ!',
					},
					SDT: {
						required: 'Vui lòng nhập số điện thoại!',
					},
					email: {
						required: 'Vui lòng nhập email!',
					},
					username: {
						required: 'Vui lòng nhập tên đăng nhập!',
					},
					password: {
						required: 'Vui lòng nhập tên mật khẩu!',
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
    </body>
</html>
