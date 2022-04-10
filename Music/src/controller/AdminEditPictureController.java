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
public class AdminEditPictureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PictureDAO pictureDAO;
	private ProductDAO productDAO;

    public AdminEditPictureController() {
        super();
        pictureDAO = new PictureDAO();
        productDAO = new ProductDAO();
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
		request.setAttribute("hinhanh", item);
		ArrayList<Product> listSP = productDAO.getNames();
		request.setAttribute("listSP", listSP);
		RequestDispatcher rd = request.getRequestDispatcher("/admin/picture/edit.jsp");
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
		request.setCharacterEncoding("UTF-8");
		
		int id = 0;
		try {
			id = Integer.parseInt(request.getParameter("id"));
		} catch (Exception e) {
			
		}
		
		//lấy phần tử cũ
		Picture itemCu = pictureDAO.getItem(id);
		String nameCu = itemCu.getTenHA();
		
		int id_sp = 0;
		try {
			id_sp = Integer.parseInt(request.getParameter("category"));
		} catch(Exception e) {
			
		}
		
		//cập nhập lại mã sản phẩm
		itemCu.setMaSP(id_sp);
		
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
		
		//doi ten file
		String picture = FileUtil.rename(fileName);
		if(!fileName.isEmpty()) {
			itemCu.setTenHA(picture);
		}
				
		//duong dan toi file anh tren sever
		final String filePathName = dirPartName + File.separator + picture;
		
		int kq = pictureDAO.editItem(itemCu);
		if(kq > 0) {
			if(!fileName.isEmpty()) {
				//xóa ảnh cũ
				final String duongDanAnhCu = dirPartName + File.separator + nameCu;
				File cu = new File(duongDanAnhCu);
				if(cu.exists()) {
					cu.delete();
				}
				//tải ảnh mới
				filePart.write(filePathName);
			}
			response.sendRedirect(request.getContextPath() + "/admin/pictures?msg=2");
			return;
		} else {
			request.setAttribute("hinhanh", itemCu);
			ArrayList<Product> listSP = productDAO.getNames();
			request.setAttribute("listSP", listSP);
			RequestDispatcher rd = request.getRequestDispatcher("/admin/picture/edit.jsp?err=1");
			rd.forward(request, response);
			return;
		}
		
		
	}

}
