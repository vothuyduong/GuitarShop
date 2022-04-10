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

public class PublicCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartDAO cartDAO;

    public PublicCartController() {
        super();
        cartDAO = new CartDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession se = request.getSession();
		
		if(se.getAttribute("cusLogin") != null) {
			Customer item = (Customer)se.getAttribute("cusLogin");
			int id = 0;
			try {
				id = Integer.parseInt(request.getParameter("id"));
			} catch (Exception e) {
				
			}
		
			if(cartDAO.checkTonTai(item.getMaKH(), id) == false) {
				Cart h = new Cart(item.getMaKH(), id, 0,  1);
				cartDAO.insertItem(h);
			} else {
				cartDAO.tangSP(item.getMaKH(), id);
			}
			
			ArrayList<Cart> listSP = cartDAO.getItems(item.getMaKH());
			request.setAttribute("listSP", listSP);
			RequestDispatcher rd = request.getRequestDispatcher("/public/cart.jsp");
			rd.forward(request, response);
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/public/cart.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		HttpSession se = request.getSession();
		if(se.getAttribute("cusLogin") != null) {
			Customer item = (Customer)se.getAttribute("cusLogin");
			int id_sp = 0;
			try {
				id_sp = Integer.parseInt(request.getParameter("MaSP"));
			} catch (Exception e) {
				
			}
			
			int soluong = 0;
			try {
				soluong = Integer.parseInt(request.getParameter("soluong"));
			} catch (Exception e) {
				
			}
			
			if(cartDAO.checkTonTai(item.getMaKH(), id_sp) == false) {
				Cart h = new Cart(item.getMaKH(), id_sp, 0,  soluong);
				cartDAO.insertItem(h);
			} else {
				cartDAO.tangSPP(item.getMaKH(), id_sp, soluong);
			}
			
			ArrayList<Cart> listSP = cartDAO.getItems(item.getMaKH());
			request.setAttribute("listSP", listSP);
			RequestDispatcher rd = request.getRequestDispatcher("/public/cart.jsp");
			rd.forward(request, response);
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/public/cart.jsp");
		rd.forward(request, response);
		return;
	}

}
