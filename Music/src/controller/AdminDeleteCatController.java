package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.CategoryDAO;

public class AdminDeleteCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;

    public AdminDeleteCatController() {
        super();
        catDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=1");
			return;
		}
		
		if(catDAO.checkCon(id)) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=2");
			return;
		}
		
		if(catDAO.checkSP(id)) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=3");
			return;
		}
		
		int kq = catDAO.deleteItem(id);
		if(kq > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=4");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
