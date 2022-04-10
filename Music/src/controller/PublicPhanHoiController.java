package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Customer;
import model.bean.PhanHoi;
import model.dao.CustomerDAO;
import model.dao.PhanHoiDAO;

public class PublicPhanHoiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PhanHoiDAO phDAO;

    public PublicPhanHoiController() {
        super();
        phDAO = new PhanHoiDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String noidung = request.getParameter("anoidung");
		int idcus = 0;
		try {
			idcus = Integer.parseInt(request.getParameter("aidcus"));
		} catch (Exception e) {
			
		}
		
		int idcmt = 0;
		try {
			idcmt = Integer.parseInt(request.getParameter("aidcmt"));
		} catch(Exception e) {
			
		}
		
		CustomerDAO cusDAO = new CustomerDAO();
		Customer cus = cusDAO.getItemById(idcus);
		PhanHoi item = new PhanHoi(idcmt, cus, new Timestamp(new Date().getTime()), noidung);
		int kq = phDAO.addItem(item);
		if(kq > 0) {
			ArrayList<PhanHoi> listPH = phDAO.getItems(idcmt);
	    	if(listPH.size() > 0) {
	    		for(PhanHoi itemPH: listPH) {
	    			response.getWriter().print(
	    					 "<div style=\"margin-bottom: 10px; margin-left: 30px;\">" +
	    					"<span style=\"font-weight: bold;\">" + itemPH.getCus().getTenKH() + "</span>" +
        					"<span style=\"color: #CDCDCD; margin-left: 20px; font-style: italic;\">" + itemPH.getNgay() + "</span>" +
        					"<br />" +
        					"<span>" + itemPH.getNoiDung() + "</span>" +
        					"</div>"
	    					);
	    		}
	    	}
		}
	}

}
