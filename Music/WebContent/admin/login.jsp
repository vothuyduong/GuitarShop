<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ include file="/templates/admin/inc/header.jsp" %>
<%@ include file="/templates/admin/inc/leftbar.jsp" %>
<div id="page-wrapper">
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <h2 style="margin-left: 15px;">Đăng nhập</h2>
            </div>
        </div>
        <!-- /. ROW  -->
        <%
	        if(request.getParameter("err") != null) {
	    		String err = request.getParameter("err");
	    		if(err.equals("1")) {
	    			out.print("<span style=\"color: red; margin-left: 20px;\">Tài khoản đăng nhập không đúng!</span>");
	    		}
	    		if(err.equals("2")) {
	    			out.print("<span style=\"color: red; margin-left: 20px;\">Mật khẩu không đúng!</span>");
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
                                <form role="form" method="post"id="form">
                                    <div class="form-group">
                                        <label for="username">Tên người dùng</label>
                                        <input type="text" id="username" value="<%if(request.getParameter("username")!= null) out.print(request.getParameter("username")); %>" name="username" class="form-control" />
                                    </div>
                                    
                                    <div class="form-group">
                                        <label for="password">Mật khẩu</label>
                                        <input type="password" id="password" value="" name="password" class="form-control" />
                                    </div>
                                    <button type="submit" name="submit" class="btn btn-success btn-md">Đăng nhập</button>
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
    <!-- /. PAGE INNER  -->
<!-- /. PAGE WRAPPER  -->
<%@ include file="/templates/admin/inc/footer.jsp" %>