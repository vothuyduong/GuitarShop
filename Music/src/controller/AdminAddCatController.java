package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.dao.CategoryDAO;

public class AdminAddCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;
       
    public AdminAddCatController() {
        super();
        catDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String name = request.getParameter("name");
		if(name.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		if(catDAO.hasItem(name)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/add.jsp?err=2");
			rd.forward(request, response);
			return;
		}
		
		int id = Integer.parseInt(request.getParameter("category"));
		
		Category item = new Category(name, id);
		int kq = catDAO.insertItem(item);
		if(kq > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=1");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/add.jsp?err=3");
			rd.forward(request, response);
			return;
		}
		
	}

}
