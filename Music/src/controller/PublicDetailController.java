package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.Product;
import model.dao.CommentDAO;
import model.dao.ProductDAO;

public class PublicDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO proDAO;
	private CommentDAO cmtDAO;

    public PublicDetailController() {
        super();
        proDAO = new ProductDAO();
        cmtDAO = new CommentDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			
		}
		Product sp = proDAO.getItemById(id);
		ArrayList<Product> listLQ = proDAO.listSPLienQuan(id, sp.getMaDM().getId());
		ArrayList<Comment> listBL = cmtDAO.getItems(id);
		request.setAttribute("sp", sp);
		request.setAttribute("listLQ", listLQ);
		request.setAttribute("listBL", listBL);
		RequestDispatcher rd = request.getRequestDispatcher("/public/product-details.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
