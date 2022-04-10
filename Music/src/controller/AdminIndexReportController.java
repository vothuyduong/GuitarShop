package controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.DetailOrders;
import model.dao.DetailOrdersDAO;
import model.dao.OrdersDAO;

public class AdminIndexReportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrdersDAO orDAO;
	private DetailOrdersDAO deDAO;

    public AdminIndexReportController() {
        super();
        orDAO = new OrdersDAO();
        deDAO = new DetailOrdersDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String ngay = sdf.format(new Date());
		
		int tong = orDAO.doanhThu("2021/01/01", ngay);
		
		
		ArrayList<DetailOrders> list = deDAO.baoCao("2021/01/01", ngay);
		
		request.setAttribute("tong", tong);
		request.setAttribute("dt", list);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/report/index.jsp");
		rd.forward(request, response);
		return;
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
		
		String ngaybd = request.getParameter("ngaybd");
		String ngaykt = request.getParameter("ngaykt");
		
		int tong = orDAO.doanhThu(ngaybd, ngaykt);
		request.setAttribute("tong", tong);
		ArrayList<DetailOrders> list = deDAO.baoCao(ngaybd, ngaykt);
		request.setAttribute("dt", list);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/report/index.jsp");
		rd.forward(request, response);
		return;
	}

}
