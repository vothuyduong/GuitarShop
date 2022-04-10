package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Product;
import model.dao.ProductDAO;

public class PublicCategoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO proDAO;

    public PublicCategoryController() {
        super();
        proDAO = new ProductDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			
		}
		
		ArrayList<Product> listSP = proDAO.getSP(id);
		request.setAttribute("listSP", listSP);
		RequestDispatcher rd = request.getRequestDispatcher("/public/product.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
