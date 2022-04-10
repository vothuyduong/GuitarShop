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

public class AdminAddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;
	private ProductDAO productDAO;

    public AdminAddProductController() {
        super();
        catDAO = new CategoryDAO();
        productDAO = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/admin/product/add.jsp");
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
		
		String name = request.getParameter("name");
		int soluong = Integer.parseInt(request.getParameter("soluong"));
		int gia = Integer.parseInt(request.getParameter("gia"));
		int khuyenmai = Integer.parseInt(request.getParameter("khuyenmai"));
		int danhMuc = Integer.parseInt(request.getParameter("category"));
		if(catDAO.checkCon(danhMuc)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/product/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		String chitiet = request.getParameter("detail");
		
		Product item = new Product(name, soluong, chitiet, gia, khuyenmai, catDAO.getItemById(danhMuc));
		int kq = productDAO.insertItem(item);
		if(kq > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/products?msg=1");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/product/add.jsp?err=2");
			rd.forward(request, response);
			return;
		}
	}

}
