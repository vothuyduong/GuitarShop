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

import model.bean.Category;
import model.dao.CategoryDAO;

public class AdminIndexCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;

    public AdminIndexCatController() {
        super();
        catDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		ArrayList<Category> list = catDAO.getItems();
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
		
		ArrayList<Category> hi = catDAO.getItemss(page, size);
		
		request.setAttribute("listCat", hi);
		request.setAttribute("sotrang", soTrang);
		request.setAttribute("size", size);
		request.setAttribute("trangHT", page);
		request.setAttribute("soPT", soPT);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/index.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int ht = Integer.parseInt(request.getParameter("aht"));
		int id = Integer.parseInt(request.getParameter("aid"));
		System.out.println(ht);
		System.out.println(id);
		
		int tam = -1;
		String htt = "";
		if(ht == 1) {
			tam = 0;
			htt = "Không";
		} else if(ht == 0){
			tam = 1;
			htt = "Có";
		}
		
		int kq = catDAO.updateHienThi(tam, id);
		if(kq > 0) {
			response.getWriter().print("<a href=\"javascript:void(0)\" onclick=\"getDisplay("+ tam + ", "+ id +")\">"+ htt + "</a>");
		}

	}

}
