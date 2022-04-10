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

public class AdminIndexProductCotroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

    public AdminIndexProductCotroller() {
        super();
        productDAO = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		ArrayList<Product> listSP = productDAO.getItems();
		
		int size = 5;
		int soPT = listSP.size();
		int soTrang = soPT / size;
		if(soPT % size != 0) {
			soTrang++;
		}
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<Product> hh = productDAO.getItemss(page, size);
		
		request.setAttribute("listSP", hh);
		request.setAttribute("soPT", soPT);
		request.setAttribute("sotrang", soTrang);
		request.setAttribute("trangHT", page);
		request.setAttribute("size", size);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/product/index.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
