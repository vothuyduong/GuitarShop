package controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Picture;
import model.dao.PictureDAO;

public class AdminDeletePictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PictureDAO pictureDAO;

    public AdminDeletePictureController() {
        super();
        pictureDAO = new PictureDAO();
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
		} catch (Exception e) {
			
		}
		
		Picture item = pictureDAO.getItem(id);
		String ten = item.getTenHA();
		
		if(pictureDAO.deleteItem(id) > 0) {
			//tạo thư mục chứa ảnh
			final String dirPartName = request.getServletContext().getRealPath("/templates/admin/assets/img/product");
			File dirFile = new File(dirPartName);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			
			final String duongDanAnhCu = dirPartName + File.separator + ten;
			File cu = new File(duongDanAnhCu);
			if(cu.exists()) {
				cu.delete();
			}
			response.sendRedirect(request.getContextPath() + "/admin/pictures?msg=3");
			return;
		} else {
			response.sendRedirect(request.getContextPath() + "/admin/pictures?err=1");
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
