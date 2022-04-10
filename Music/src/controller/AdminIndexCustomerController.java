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

import model.bean.Customer;
import model.dao.CustomerDAO;

public class AdminIndexCustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustomerDAO customerDAO;

    public AdminIndexCustomerController() {
        super();
        customerDAO = new CustomerDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		ArrayList<Customer> list = customerDAO.getItems();
		
		int soPT = list.size();
		int size = 4;
		int soTrang = soPT / size;
		if(soPT % size != 0) {
			soTrang++;
		}
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<Customer> hh = customerDAO.getItemss(page, size);
		
		request.setAttribute("listKH", hh);
		request.setAttribute("sotrang", soTrang);
		request.setAttribute("size", size);
		request.setAttribute("soPT", soPT);
		request.setAttribute("trangHT", page);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/customer/index.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
