package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import model.bean.Picture;
import model.bean.Product;
import model.dao.PictureDAO;
import model.dao.ProductDAO;
import util.FileUtil;

@MultipartConfig
public class AdminAddPictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;
	private PictureDAO pictureDAO;
	
    public AdminAddPictureController() {
        super();
        productDAO = new ProductDAO();
        pictureDAO = new PictureDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		ArrayList<Product> listSP = productDAO.getNames();
		request.setAttribute("listSP", listSP);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/picture/add.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("adminLogin") == null) {
			response.sendRedirect(request.getContextPath() + "/admin/login");
			return;
		}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		request.setCharacterEncoding("UTF-8");
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("category"));
		} catch(Exception e) {
			
		}
		
		//lấy đường dẫn ảnh của máy tính
		Part filePart = request.getPart("picture");
		
		//tạo thư mục chứa ảnh
		final String dirPartName = request.getServletContext().getRealPath("/templates/admin/assets/img/product");
		File dirFile = new File(dirPartName);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		
		//lay ten file
		String fileName = FileUtil.getName(filePart);
		if(fileName.isEmpty()) {
			ArrayList<Product> listSP = productDAO.getNames();
			request.setAttribute("listSP", listSP);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/picture/add.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		//doi ten file
		String picture = FileUtil.rename(fileName);
		
		//duong dan toi file anh tren sever
		final String filePathName = dirPartName + File.separator + picture;
		
		Picture item = new Picture(picture, id);
		int kq = pictureDAO.insertItem(item);
		if(kq > 0) {
			//tai anh len
			if(!fileName.isEmpty()) {
				filePart.write(filePathName);
			}
			response.sendRedirect(request.getContextPath() + "/admin/pictures?msg=1");
			return;
		} else {
			ArrayList<Product> listSP = productDAO.getNames();
			request.setAttribute("listSP", listSP);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/picture/add.jsp?err=2");
			rd.forward(request, response);
			return;
		}
	}

}
