package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Comment;
import model.bean.PhanHoi;
import model.dao.CommentDAO;
import model.dao.CustomerDAO;
import model.dao.PhanHoiDAO;

public class PublicCommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentDAO cmtDAO;
	private CustomerDAO cusDAO;

    public PublicCommentController() {
        super();
        cmtDAO = new CommentDAO();
        cusDAO = new CustomerDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		int stt = 0;
		try {
			stt = Integer.parseInt(request.getParameter("astt"));
		} catch (Exception e) {
			
		}
		
		if(stt == 2) {
			int idcmt = 0;
			try {
				idcmt = Integer.parseInt(request.getParameter("aidcmt"));
			} catch (Exception e) {
				
			}
			
			int idcus = 0;
			try {
				idcus = Integer.parseInt(request.getParameter("aidcus"));
			} catch (Exception e) {
				
			}
			
			response.getWriter().print(
					"<form action=\"javascript:void(0)\">" +
		    		"<input type=\"hidden\" name=\"MaKH\" id=\"MaKH\" value=\"" + idcus + "\">" + 
		    		"<input type=\"hidden\" name=\"MaCMT\" id=\"MaCMT\" value=\"" + idcmt + "\">" +
		    		"<textarea style=\"width: 100%; height: 50px; padding: 10px;\" name=\"text\" id=\"txtPH\" rows=\"4\" placeholder=\"Viết phản hồi của bạn...\"></textarea>" +
		    		"<button type=\"submit\" style=\"margin: 10px;\" onclick=\"getPhanhoi("+ idcus + ", "+ idcmt +")\" class=\"btn btn-primary\">Gửi</button>" +
		    		"</form>"
					);
			return;
		}
		
		int idsp = 0;
		try {
			idsp = Integer.parseInt(request.getParameter("aidsp"));
		} catch (Exception e) {
			
		}
		
		int idcus = 0;
		try {
			idcus = Integer.parseInt(request.getParameter("aidcus"));
		} catch (Exception e) {
			
		}
		
		String noidung = request.getParameter("anoidung");
		
		Comment item = new Comment(idsp, cusDAO.getItemById(idcus), new Timestamp(new Date().getTime()), noidung);
		int kq = cmtDAO.insertItem(item);
		if(kq > 0) {
			ArrayList<Comment> listBL = cmtDAO.getItems(idsp);
			if(listBL.size() > 0) {
				for(Comment it: listBL) {
					response.getWriter().print("<div style=\"margin-bottom: 10px;\"><span style=\"font-weight: bold;\">" + it.getCus().getTenKH() + "</span><span style=\"color: #CDCDCD; margin-left: 20px; font-style: italic;\">"+ it.getNgay() +"</span><br/><span>"+ it.getNoidung() +"</span><a href=\"javascript:void(0)\" onclick=\"displayChat(" + it.getMaCM() + ", 2," + idcus + ")\" style=\"text-decoration: none; margin-left: 15px; font-style: italic;\">Phản hồi</a></div>" +
							"<div class=\"phanhoi" + it.getMaCM() + "\">"
							);
					PhanHoiDAO phDAO = new PhanHoiDAO();
			    	ArrayList<PhanHoi> listPH = phDAO.getItems(it.getMaCM());
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
			    	response.getWriter().print(
			    			"</div>" +
            			    "<div class=\"khung" + it.getMaCM() + "\">" +
            			    "</div>"
			    			);
				}
			}
		}
	}

}
