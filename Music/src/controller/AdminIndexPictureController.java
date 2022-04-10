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

public class AdminIndexPictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PictureDAO pictureDAO;

    public AdminIndexPictureController() {
        super();
        pictureDAO = new PictureDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		ArrayList<Picture> listAnh = pictureDAO.getItems();
		
		int soPT = listAnh.size();
		int size = 8;
		int soTrang = soPT / size;
		if(soPT % size != 0) {
			soTrang++;
		}
		
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		ArrayList<Picture> ss = pictureDAO.getItemss(page, size);
		
		request.setAttribute("listAnh", ss);
		request.setAttribute("soPT", soPT);
		request.setAttribute("size", size);
		request.setAttribute("sotrang", soTrang);
		request.setAttribute("trangHT", page);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/picture/index.jsp");
		rd.forward(request, response);
		return;
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
