package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Customer;
import model.dao.CustomerDAO;

public class PublicLoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO cusDAO;

    public PublicLoginController() {
        super();
        cusDAO = new CustomerDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/public/login.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(cusDAO.hasItem(username, password)) {
			HttpSession session = request.getSession();
			Customer item = cusDAO.getItemByUserName(username);
			session.setAttribute("cusLogin", item);
			response.sendRedirect(request.getContextPath());
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/public/login.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		
	}

}
