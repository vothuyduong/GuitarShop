package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.DetailOrders;
import util.DBConnectionUtil;

public class DetailOrdersDAO {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public DetailOrdersDAO() {
		
	}
	
	public ArrayList<DetailOrders> getItem(int id) {
		ArrayList<DetailOrders> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from detailorder where MaHD = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				ProductDAO productDAO = new ProductDAO();
				DetailOrders item = new DetailOrders(productDAO.getNameById(rs.getInt("MaSP")), rs.getInt("SoLuongMua"), rs.getInt("ThanhTien"), productDAO.getGiaById(rs.getInt("MaSP")));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null && pst != null && rs != null) {
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return items;
	}
	
	public ArrayList<DetailOrders> baoCao(String ngaybd, String ngaykt) {
		ArrayList<DetailOrders> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select MaSP, sum(SoLuongMua) as SoLuong from detailorder WHERE MaHD in (SELECT MaHD from orders WHERE NgayTao BETWEEN ? and ?) GROUP by MaSP";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, ngaybd);
			pst.setString(2, ngaykt);
			rs = pst.executeQuery();
			while(rs.next()) {
				ProductDAO productDAO = new ProductDAO();
				DetailOrders item = new DetailOrders(productDAO.getNameById(rs.getInt("MaSP")), rs.getInt("SoLuong"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null && pst != null && rs != null) {
				try {
					rs.close();
					pst.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return items;
	}
	
	public int insetItem(DetailOrders item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "insert into detailorder(MaHD, MaSP, SoLuongMua, ThanhTien) values(?, ?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, item.getMaDH());
			pst.setInt(2, item.getMaSP());
			pst.setInt(3, item.getSoLuongMua());
			pst.setInt(4, item.getThanhTien());
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null && pst != null) {
				try {
					pst.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return kq;
	}
}
