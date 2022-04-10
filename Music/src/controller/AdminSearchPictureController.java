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

import model.bean.Picture;
import model.dao.PictureDAO;

public class AdminSearchPictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PictureDAO pictureDAO;

    public AdminSearchPictureController() {
        super();
        pictureDAO = new PictureDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
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
		
		String key = request.getParameter("key");
		if(key.equals("")) {
			RequestDispatcher rd = request.getRequestDispatcher("/admin/picture/search.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		ArrayList<Picture> listAnh = pictureDAO.searchItem(key);
		
		int soPT = listAnh.size();
		int size = 4;
		int soTrang = soPT / size;
		if(soPT % size != 0) {
			soTrang++;
		}
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<Picture> ss = pictureDAO.searchItemm(key, page, size);
		
		
		request.setAttribute("listAnh", ss);
		request.setAttribute("key", key);
		request.setAttribute("sotrang", soTrang);
		request.setAttribute("soPT", soPT);
		request.setAttribute("trangHT", page);
		request.setAttribute("size", size);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/picture/search.jsp?msg=1");
		rd.forward(request, response);
		return;
	}

}
