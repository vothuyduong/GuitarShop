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

import model.bean.Product;
import model.dao.ProductDAO;

public class AdminSearchProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

    public AdminSearchProductController() {
        super();
        productDAO = new ProductDAO();
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
			RequestDispatcher rd = request.getRequestDispatcher("/admin/product/search.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		ArrayList<Product> listSP = productDAO.searchItem(key);
		
		int soPT = listSP.size();
		int size = 4;
		int soTrang = soPT / size;
		if(soPT % size != 0) {
			soTrang++;
		}
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<Product> ll = productDAO.searchItemm(key, page, size);
		
		request.setAttribute("listSP", ll);
		request.setAttribute("soPT", soPT);
		request.setAttribute("sotrang", soTrang);
		request.setAttribute("trangHT", page);
		request.setAttribute("size", size);
		request.setAttribute("key", key);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/product/search.jsp?msg=1");
		rd.forward(request, response);
		return;
	}

}
