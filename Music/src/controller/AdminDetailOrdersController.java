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

import model.bean.DetailOrders;
import model.dao.DetailOrdersDAO;

public class AdminDetailOrdersController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DetailOrdersDAO detailDAO;

    public AdminDetailOrdersController() {
        super();
        detailDAO = new DetailOrdersDAO();
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
		} catch (Exception E) {
			
		}
		
		ArrayList<DetailOrders> listCT = detailDAO.getItem(id);
		request.setAttribute("listCT", listCT);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/orders/detail.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
