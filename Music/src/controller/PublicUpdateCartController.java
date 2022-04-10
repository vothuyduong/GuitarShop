package controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Cart;
import model.bean.Customer;
import model.bean.Product;
import model.dao.CartDAO;
import model.dao.ProductDAO;

public class PublicUpdateCartController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartDAO cartDAO;

    public PublicUpdateCartController() {
        super();
        cartDAO = new CartDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		System.out.println("hello");
		HttpSession se = request.getSession();
		if(se.getAttribute("cusLogin") != null) {
			Customer item = (Customer)se.getAttribute("cusLogin");
			int id_sp = 0;
			try {
				id_sp = Integer.parseInt(request.getParameter("aidsp"));
			} catch (Exception e) {
				
			}
			
			int soluong = 0;
			try {
				soluong = Integer.parseInt(request.getParameter("asoluong"));
			} catch (Exception e) {
				
			}
			System.out.println(soluong);
			System.out.println(id_sp);
			cartDAO.updateSP(item.getMaKH(), id_sp, soluong);
			
			DecimalFormat formatter = new DecimalFormat("###,###,###");
        	int tong = 0;
			ArrayList<Cart> listSP = cartDAO.getItems(item.getMaKH());
			if(listSP.size() > 0) {
				response.getWriter().print(
						 "<table>" +
                         "<thead>" +
                             "<tr>" +
                                 "<th class=\"shoping__product\">Sản phẩm</th>" +
                                 "<th>Giá</th>" +
                                 "<th>Số lượng</th>" +
                                 "<th>Thành tiền</th>" +
                                 "<th></th>" +
                             "</tr>" +
                         "</thead>" +
                         "<tbody>"
						);
    			for(Cart ite: listSP) {
    				ProductDAO proDAO = new ProductDAO();
    				Product pro = proDAO.getItemById(ite.getMaSP());
    				int giam = (ite.getGia()*pro.getKhuyenMai())/100;
    				int tam = (ite.getGia()-giam) * ite.getSoluong();
    				tong += tam;
    				response.getWriter().print(
    						"<tr>" +
                            "<td class=\"shoping__cart__item\">" +
                                "<img src=\"" + request.getContextPath() + "/templates/admin/assets/img/product/" + pro.getPicture().get(0).getTenHA() +  "\" width=\"50px\" alt=\"\">" +
                                "<h5>" + pro.getTenSP() + "</h5>" +
                            "</td>" +
                            "<td class=\"shoping__cart__price\">" +
                               formatter.format(ite.getGia()-giam) + " VNĐ" +
                            "</td>" +
                            "<td class=\"shoping__cart__quantity\">" +
                            "<form action=\"javascript:void(0)\">" +
                                "<div class=\"quantity\">" +
                                    "<div class=\"pro-qty\">" +
                                        "<input type=\"text\" value=\"" + ite.getSoluong() + "\" name=\"soluong\" id=\"soluong" + ite.getMaSP() + "\">" +
                                    "</div>" +
                                    "<input type=\"submit\" class=\"primary-btn\" value=\"Cập nhập\" onclick=\"editSP(" + ite.getMaSP()+")\">" +
                                    "</form>" +
                                "</div>" + 
                            "</td>" +
                            "<td class=\"shoping__cart__total\">" +
                               formatter.format(tam)+ " VNĐ" +
                            "</td>" +
                            "<td class=\"shoping__cart__item__close\">" +
                                "<a href=\"" + request.getContextPath() + "/cart/del?id=" + ite.getMaSP() + "\"><span class=\"icon_close\"></span></a>" +
                            "</td>" +
                        "</tr>"
    						);
    			}
    			response.getWriter().print(
    					 "</tbody>" +
    	                 "</table>"
    					);
    			}
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher("/public/cart.jsp");
		rd.forward(request, response);
		return;
	}

}
