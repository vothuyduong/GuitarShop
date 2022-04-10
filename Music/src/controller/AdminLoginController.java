package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Admin;
import model.bean.Customer;
import model.dao.AdminDAO;

public class AdminLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AdminDAO adDAO;
 
    public AdminLoginController() {
        super();
        adDAO = new AdminDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") != null) {
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Admin admin = adDAO.getItem(username, password);
		if(admin != null) {
			HttpSession session = request.getSession();
			session.setAttribute("adminLogin", admin);
			response.sendRedirect(request.getContextPath() + "/admin");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/login.jsp?err=1");
			rd.forward(request, response);
		}
		
	}

}
