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

public class AdminSearchCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;

    public AdminSearchCatController() {
        super();
        catDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
		
		String key = request.getParameter("key");
		if(key.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/search.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		ArrayList<Category> listSearch = catDAO.searchItem(key);
		
		int soPT = listSearch.size();
		int size = 4;
		int soTrang = soPT / size;
		if(soPT % size != 0) {
			soTrang++;
		}
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<Category> hi = catDAO.searchItemm(key, page, size);
		
		request.setAttribute("listCat", hi);
		request.setAttribute("key", key);
		request.setAttribute("soPT", soPT);
		request.setAttribute("size", size);
		request.setAttribute("sotrang", soTrang);
		request.setAttribute("trangHT", page);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/search.jsp?msg=1");
		rd.forward(request, response);
		return;
	}

}
