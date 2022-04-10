package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.PictureDAO;
import model.dao.ProductDAO;

public class AdminDeleteProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private PictureDAO pictureDAO;

    public AdminDeleteProductController() {
        super();
        productDAO = new ProductDAO();
        pictureDAO = new PictureDAO();
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
			
		}
		
		if(pictureDAO.ktDM(id)) {
			response.sendRedirect(request.getContextPath() + "/admin/products?err=1");
			return;
		}
		
		int kq = productDAO.deleteItem(id);
		if(kq > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/products?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/products?err=2");
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
