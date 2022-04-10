package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Customer;
import model.dao.CustomerDAO;

public class PublicRegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO cusDAO;

    public PublicRegisterController() {
        super();
        cusDAO = new CustomerDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/register.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String hoTen = request.getParameter("HoTen");
		String ngaySinh = request.getParameter("NgaySinh");
		System.out.println(ngaySinh);
		
		int gioiTinh = Integer.parseInt(request.getParameter("GioiTinh"));
		if(gioiTinh != 1 && gioiTinh != 0) {
			RequestDispatcher rd = request.getRequestDispatcher("/public/register.jsp?err=3");
			rd.forward(request, response);
			return;
		}
		String diaChi = request.getParameter("DiaChi");
		String email = request.getParameter("email");
		String sdt = request.getParameter("SDT");
		String username = request.getParameter("username");
		
		if(cusDAO.getItemByUserName(username) != null) {
			RequestDispatcher rd = request.getRequestDispatcher("/public/register.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		String password = request.getParameter("password");
		String repassword = request.getParameter("rs-password");
		if(!repassword.equals(password)) {
			RequestDispatcher rd = request.getRequestDispatcher("/public/register.jsp?err=2");
			rd.forward(request, response);
			return;
		}
		
		Customer item = new Customer(hoTen, null, diaChi, sdt, email, username, password, gioiTinh);
		int kq = cusDAO.insertItem(item);
		if(kq > 0) {
			response.sendRedirect(request.getContextPath() + "/login?msg=1");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/public/register.jsp?err=4");
			rd.forward(request, response);
			return;
		}
	}

}
