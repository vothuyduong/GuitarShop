package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Cart;
import model.bean.Product;
import util.DBConnectionUtil;

public class CartDAO {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public CartDAO() {
		
	}
	
	public ArrayList<Cart> getItems(int id) {
		ArrayList<Cart> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from cart where MaKH = "+ id;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				ProductDAO proDAO = new ProductDAO();
				Cart item = new Cart(rs.getInt("MaKH"), rs.getInt("MaSP"), proDAO.getGiaById(rs.getInt("MaSP")), rs.getInt("SoLuongMua"));
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
	
	public boolean checkTonTai(int id_cus, int id_sp) {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from cart where MaKH = ? and MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id_cus);
			pst.setInt(2, id_sp);
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
	
	public int tangSP(int id_cus, int id_sp) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "update cart set SoLuongMua = SoLuongMua + 1 where MaKH = ? and MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id_cus);
			pst.setInt(2, id_sp);
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
	
	public int tangSPP(int id_cus, int id_sp, int soluong) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "update cart set SoLuongMua = SoLuongMua + ? where MaKH = ? and MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, soluong);
			pst.setInt(2, id_cus);
			pst.setInt(3, id_sp);
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
	
	public int updateSP(int id_cus, int id_sp, int soluong) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "update cart set SoLuongMua = ? where MaKH = ? and MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, soluong);
			pst.setInt(2, id_cus);
			pst.setInt(3, id_sp);
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
	
	public int insertItem(Cart item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "insert into cart(MaKH, MaSP, SoLuongMua) values (?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, item.getMaKH());
			pst.setInt(2, item.getMaSP());
			pst.setInt(3, item.getSoluong());
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
	
	public int tongTien(int cus_id) {
		ProductDAO proDAO = new ProductDAO();
		int tong = 0;
		ArrayList<Cart> listSP = getItems(cus_id);
		for(Cart item: listSP) {
			Product pro = proDAO.getItemById(item.getMaSP());
			int giam = (item.getGia()*pro.getKhuyenMai())/100;
			tong += (item.getGia()-giam) * item.getSoluong();
		}
		return tong;
	}
	
	public int demSP(int cus_id) {
		int dem = 0;
		ArrayList<Cart> listSP = getItems(cus_id);
		for(Cart item: listSP) {
			dem += item.getSoluong();
		}
		return dem;
	}
	
	public int delete(int cus_id, int sp_id) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "delete from cart where MaKH = ? and MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, cus_id);
			pst.setInt(2, sp_id);
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
	
	public int deleteTC(int cus_id) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "delete from cart where MaKH = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, cus_id);
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
