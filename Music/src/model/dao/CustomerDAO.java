package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Customer;
import util.DBConnectionUtil;

public class CustomerDAO {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public CustomerDAO() {
		
	}
	
	public ArrayList<Customer> getItems() {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from customer";
		ArrayList<Customer> items = new ArrayList<>();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Customer item = new Customer(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("Email"), rs.getString("TenDangNhap"), rs.getString("MatKhau"), rs.getInt("GioiTinh"));
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
	
	public ArrayList<Customer> getItemss(int page, int size) {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from customer limit ?, ?";
		ArrayList<Customer> items = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (page-1)*size);
			pst.setInt(2, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				Customer item = new Customer(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("Email"), rs.getString("TenDangNhap"), rs.getString("MatKhau"), rs.getInt("GioiTinh"));
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
	
	public ArrayList<Customer> searchItem(String key) {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from customer where TenKH like ?";
		ArrayList<Customer> items = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');
			rs = pst.executeQuery();
			while(rs.next()) {
				Customer item = new Customer(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("Email"), rs.getString("TenDangNhap"), rs.getString("MatKhau"), rs.getInt("GioiTinh"));
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
	
	public ArrayList<Customer> searchItemm(String key, int page, int size) {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from customer where TenKH like ? limit ?, ?";
		ArrayList<Customer> items = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');
			pst.setInt(2, (page - 1)*size);
			pst.setInt(3, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				Customer item = new Customer(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("Email"), rs.getString("TenDangNhap"), rs.getString("MatKhau"), rs.getInt("GioiTinh"));
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
	
	public Customer getItemById(int id) {
		Customer item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "select * from customer where MaKH = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Customer(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("Email"), rs.getString("TenDangNhap"), rs.getString("MatKhau"), rs.getInt("GioiTinh"));
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
		return item;
	}
	
	public Customer getItemByUserName(String username) {
		Customer item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "select * from customer where TenDangNhap = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Customer(rs.getInt("MaKH"), rs.getString("TenKH"), rs.getDate("NgaySinh"), rs.getString("DiaChi"), rs.getString("Sdt"), rs.getString("Email"), rs.getString("TenDangNhap"), rs.getString("MatKhau"), rs.getInt("GioiTinh"));
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
		return item;
	}
	
	public boolean hasItem(String username, String password) {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from customer where TenDangNhap = ? and MatKhau = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				return true;
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
		return false;
	}
	
	public int insertItem(Customer item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "insert into customer(TenKH, NgaySinh, DiaChi, Sdt, Email, TenDangNhap, MatKhau, GioiTinh) values(?, ?, ?, ?, ?, ?, ?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getTenKH());
			pst.setDate(2, item.getNgaySinh());
			pst.setString(3, item.getDiaChi());
			pst.setString(4, item.getSdt());
			pst.setString(5, item.getEmail());
			pst.setString(6, item.getTenDangNhap());
			pst.setString(7, item.getMatKhau());
			pst.setInt(8, item.getGioiTinh());
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
