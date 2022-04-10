<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/login.css" rel="stylesheet" type="text/css"/>
        <script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery-3.2.1.js"></script>
		<script src="<%=request.getContextPath() %>/templates/admin/assets/js/jquery.validate.min.js"></script>
    </head>
    <body>
            <div class="login-form">
                <form action="" method="post" id="form">
                    <h1>Đăng nhập</h1>
     				<%
     					if(request.getParameter("err") != null) {
     						String err = (String)request.getParameter("err");
     						if(err.equals("1")) {
     							out.print("<span style=\"color: white; margin-bottom: 5px;\">Tên đăng nhập hoặc mật khẩu chưa chính xác!</span>");
     						}
     					}
	     				if(request.getParameter("msg") != null) {
	 						String msg = (String)request.getParameter("msg");
	 						if(msg.equals("1")) {
	 							out.print("<span style=\"color: white; margin-bottom: 5px;\">Đăng kí thành công. Mời bạn đăng nhập!</span>");
	 						}
	 					}
     				%>
    
                    <div class="input-box">
                        <i></i>
                        <input type="text" placeholder="Nhập tên đăng nhập" name="username">
                    </div>
                    <div class="input-box">
                        <i></i>
                        <input type="password" placeholder="Nhập mật khẩu" name="password">
                    </div>
                    <div class="btn-box">
                        <button type="submit">
                            Đăng nhập
                        </button>
                         <div style="font-size:13px; margin-right:45px; margin-top: 15px;">Bạn chưa có tài khoản? Hãy <a href="<%=request.getContextPath()%>/dang-ki">Đăng kí</a> ngay!</div>
                    </div>
                </form>
            </div>
<style>
	*{
	    padding: 0px;
	    margin: 0px;
	    font-family: sans-serif;
	    box-sizing: border-box;
	}
	
	header{
	    background-color: #cccccc;
	    min-height: 70px;
	    padding: 15px;
	}

	footer{
	    background-color: #cccccc;
	    min-height: 70px;
	    padding: 15px;
	}
	
	.container{
	    width: 100%;
	    max-width: 1200px;
	    margin-left: auto;
	    margin-right: auto;
	}
	
	.login-form{
	    width: 100%;
	    max-width: 400px;
	    margin: 100px auto;
	    background-color: #FFA500;
	    padding: 15px;
	    border: 2px dotted #cccccc;
	    border-radius: 10px;
	}
	
	h1{
	    color: #D9534F;
	    font-size: 20px;
	    margin-bottom: 30px;
	}
	
	.input-box{
	    margin-bottom: 10px;
	}
	
	.input-box input{
	    padding: 7.5px 15px;
	    width: 100%;
	    border: 1px solid #cccccc;
	    outline: none;
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
					username: {
						required: true,
					},
					password: {
						required: true,
					},
				},
				
				messages: {
					username: {
						required: 'Vui lòng nhập tên đăng nhập!',
					},
					password: {
						required: 'Vui lòng nhập mật khẩu!',
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