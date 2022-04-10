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

import model.bean.Cart;
import model.bean.Customer;
import model.dao.CartDAO;

public class PublicDeleteCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartDAO cartDAO;

    public PublicDeleteCartController() {
        super();
        cartDAO = new CartDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession();
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			
		}
		
		Customer item = null;
		if(se.getAttribute("cusLogin") != null) {
			item = (Customer)se.getAttribute("cusLogin");
		}
		
		cartDAO.delete(item.getMaKH(), id);
		ArrayList<Cart> listSP = cartDAO.getItems(item.getMaKH());
		request.setAttribute("listSP", listSP);
		RequestDispatcher rd = request.getRequestDispatcher("/public/cart.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
