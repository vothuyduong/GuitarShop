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

import model.bean.Orders;
import model.dao.OrdersDAO;

public class AdminIndexOrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdersDAO ordersDAO;

    public AdminIndexOrdersController() {
        super();
        ordersDAO = new OrdersDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		ArrayList<Orders> list = ordersDAO.getItems();
		
		int size = 4;
		int soPT = list.size();
		int soTrang = soPT / size;
		if(soPT % size != 0) {
			soTrang++;
		}
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<Orders> hh = ordersDAO.getItemss(page, size);
		
		request.setAttribute("listO", hh);
		request.setAttribute("sotrang", soTrang);
		request.setAttribute("trangHT", page);
		request.setAttribute("size", size);
		request.setAttribute("soPT", soPT);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/orders/index.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
