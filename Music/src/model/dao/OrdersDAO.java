package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Orders;
import util.DBConnectionUtil;

public class OrdersDAO {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public OrdersDAO() {
		
	}
	
	public ArrayList<Orders> getItems() {
		ArrayList<Orders> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from orders";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				CustomerDAO cusDAO = new CustomerDAO();
				Orders item = new Orders(rs.getInt("MaHD"), cusDAO.getItemById(rs.getInt("MaKH")), rs.getTimestamp("NgayTao"), rs.getInt("TongTien"), rs.getInt("TrangThai"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null && st != null && rs != null) {
				try {
					rs.close();
					st.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return items;
	}
	
	public ArrayList<Orders> getItemss(int page, int size) {
		ArrayList<Orders> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from orders limit ?, ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (page-1)*size);
			pst.setInt(2, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				CustomerDAO cusDAO = new CustomerDAO();
				Orders item = new Orders(rs.getInt("MaHD"), cusDAO.getItemById(rs.getInt("MaKH")), rs.getTimestamp("NgayTao"), rs.getInt("TongTien"), rs.getInt("TrangThai"));
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
	
	public ArrayList<Orders> searchItem(String key) {
		ArrayList<Orders> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select orders.* from orders inner join customer on orders.MaKH = customer.MaKH where TenKH like ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');
			rs = pst.executeQuery();
			while(rs.next()) {
				CustomerDAO cusDAO = new CustomerDAO();
				Orders item = new Orders(rs.getInt("MaHD"), cusDAO.getItemById(rs.getInt("MaKH")), rs.getTimestamp("NgayTao"), rs.getInt("TongTien"), rs.getInt("TrangThai"));
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
	
	public ArrayList<Orders> searchItemm(String key, int page, int size) {
		ArrayList<Orders> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select orders.* from orders inner join customer on orders.MaKH = customer.MaKH where TenKH like ? limit ?, ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');
			pst.setInt(2, (page-1)*size);
			pst.setInt(3, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				CustomerDAO cusDAO = new CustomerDAO();
				Orders item = new Orders(rs.getInt("MaHD"), cusDAO.getItemById(rs.getInt("MaKH")), rs.getTimestamp("NgayTao"), rs.getInt("TongTien"), rs.getInt("TrangThai"));
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
	
	public int doanhThu(String ngaybt, String ngaykt) {
		int tong = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "select sum(TongTien) as tong from orders where date(NgayTao) between ? and ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, ngaybt);
			pst.setString(2, ngaykt);
			rs = pst.executeQuery();
			if(rs.next()) {
				tong = rs.getInt("tong");
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
		return tong;
	}
	
	public int insetItem(Orders item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "insert into orders(MaKH, NgayTao, TrangThai, TongTien) values (?, ?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, item.getKhachHang().getMaKH());
			pst.setTimestamp(2, item.getNgayTao());
			pst.setInt(3, item.getTrangThai());
			pst.setInt(4, item.getTongTien());
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
	
	public int getIdMoi(int cus_id) {
		int id = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "select MaHD from orders where MaKH = ? order by MaHD desc";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, cus_id);
			rs = pst.executeQuery();
			if(rs.next()) {
				id = rs.getInt("MaHD");
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
		return id;
	}

}
