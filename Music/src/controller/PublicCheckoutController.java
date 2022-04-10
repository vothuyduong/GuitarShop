package controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Cart;
import model.bean.Customer;
import model.bean.DetailOrders;
import model.bean.Orders;
import model.bean.Product;
import model.dao.CartDAO;
import model.dao.DetailOrdersDAO;
import model.dao.OrdersDAO;
import model.dao.ProductDAO;

public class PublicCheckoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CartDAO cartDAO;
	private OrdersDAO orDAO;
	private ProductDAO proDAO;
	private DetailOrdersDAO deDAO;

    public PublicCheckoutController() {
        super();
        cartDAO = new CartDAO();
        orDAO = new OrdersDAO();
        proDAO = new ProductDAO();
        deDAO = new DetailOrdersDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("cusLogin") != null) {
			Customer cus = (Customer)session.getAttribute("cusLogin");
			
			ArrayList<Cart> listSP = cartDAO.getItems(cus.getMaKH());
			int tongTien = cartDAO.tongTien(cus.getMaKH());
			
			//kiểm tra sp có nhỏ hơn hoặc bằng số lượng sp trong kho không
			for(Cart it: listSP) {
				int sl = proDAO.getSoLuong(it.getMaSP());
				if(sl < it.getSoluong()) {
					response.sendRedirect(request.getContextPath() + "/cart?err=1");
					return;
				}
			}
			
			//thêm vào đơn hàng
			Orders itemO = new Orders(cus, new Timestamp(new Date().getTime()), tongTien, 1);
			int kq = orDAO.insetItem(itemO);
			if(kq > 0) {
				//xóa các sp trong giỏ hàng
				cartDAO.deleteTC(cus.getMaKH());
				
				//lấy id đơn hàng 
				int id_donhang = orDAO.getIdMoi(cus.getMaKH());
				
				//thêm các sp vào bảng chi tiết đơn hàng 
				//cập nhập lại số lượng sp còn trong bảng sản phẩm
				for(Cart it: listSP) {
					Product pro = proDAO.getItemById(it.getMaSP());
					int giam = (it.getGia()*pro.getKhuyenMai())/100;
					DetailOrders detailItem = new DetailOrders(it.getMaSP(), it.getSoluong(), it.getSoluong()*(it.getGia()-giam), it.getGia()-giam, id_donhang);
					deDAO.insetItem(detailItem);
					proDAO.updateSL(it.getMaSP(), it.getSoluong());
				}
				
			}
			request.setAttribute("listSP", listSP);
			request.setAttribute("tong", tongTien);
			RequestDispatcher rd = request.getRequestDispatcher("/public/checkout.jsp");
			rd.forward(request, response);
			return;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
