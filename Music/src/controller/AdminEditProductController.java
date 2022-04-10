package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Product;
import model.dao.CategoryDAO;
import model.dao.ProductDAO;

public class AdminEditProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private CategoryDAO catDAO;

    public AdminEditProductController() {
        super();
        productDAO = new ProductDAO();
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
			
		}
		
		Product itemSP = productDAO.getItemById(id); 
		request.setAttribute("itemSP", itemSP);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/product/edit.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			
		}
		
		String name = request.getParameter("name");
		int soluong = Integer.parseInt(request.getParameter("soluong"));
		int gia = Integer.parseInt(request.getParameter("gia"));
		int khuyenmai = Integer.parseInt(request.getParameter("khuyenmai"));
		int danhMuc = Integer.parseInt(request.getParameter("category"));
		if(catDAO.checkCon(danhMuc)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/product/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		String chitiet = request.getParameter("detail");
		
		Product item = new Product(id, name, soluong, chitiet, gia, khuyenmai, catDAO.getItemById(danhMuc));
		int kq = productDAO.updateItem(item);
		if(kq > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/products?msg=2");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/product/edit.jsp?err=2");
			rd.forward(request, response);
			return;
		}
	}

}
