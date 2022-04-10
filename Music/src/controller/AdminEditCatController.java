package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Category;
import model.dao.CategoryDAO;

public class AdminEditCatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryDAO catDAO;
       
    public AdminEditCatController() {
        super();
        catDAO = new CategoryDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		
		//lấy id danh mục cần sửa
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=1");
			return;
		}
		
		Category itemCat = catDAO.getItemById(id);
		request.setAttribute("itemCat", itemCat);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/category/edit.jsp");
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
		
		//lấy id danh mục cần sửa 
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?err=1");
			return;
		}
		
		String name = request.getParameter("name");
		int id_parent = Integer.parseInt(request.getParameter("category"));
		
		//nếu tên rỗng
		if(name.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		//nếu danh mục cha chọn trùng với danh mục hiện tại thì báo lỗi
		if(id == id_parent) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/edit.jsp?err=2");
			rd.forward(request, response);
			return;
		}
		
		//nếu danh mục cha tạm thời đang là con của danh mục hiện tại thì báo lỗi
		if(catDAO.checkHoHang(id, id_parent)) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/edit.jsp?err=4");
			rd.forward(request, response);
			return;
		}
		
		//sửa csdl
		Category item = new Category(id, name, id_parent);
		int kq = catDAO.editItem(item);
		if(kq > 0) {
			response.sendRedirect(request.getContextPath() + "/admin/cats?msg=2");
			return;
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/category/edit.jsp?err=3");
			rd.forward(request, response);
			return;
		}
	}

}
