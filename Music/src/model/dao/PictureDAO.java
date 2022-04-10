package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Picture;
import util.DBConnectionUtil;

public class PictureDAO {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public PictureDAO() {
		super();
	}

	public ArrayList<Picture> getItems() {
		ArrayList<Picture> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from picture order by MaSP";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Picture item = new Picture(rs.getInt("MaHA"), rs.getString("TenHA"), rs.getInt("MaSP"));
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
	
	public ArrayList<Picture> getItemss(int page, int size) {
		con = DBConnectionUtil.getConnection();
		ArrayList<Picture> items = new ArrayList<>();
		String sql = "select * from picture limit ?, ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1,  (page - 1)*size);
			pst.setInt(2, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				Picture item = new Picture(rs.getInt("MaHA"), rs.getString("TenHA"), rs.getInt("MaSP"));
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
	
	public ArrayList<Picture> searchItem(String TenSP) {
		ArrayList<Picture> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select p.* from picture as p inner join product as pr on p.MaSP = pr.MaSP where pr.TenSP like ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, '%' + TenSP + '%');
			rs = pst.executeQuery();
			while(rs.next()) {
				Picture item = new Picture(rs.getInt("MaHA"), rs.getString("TenHA"), rs.getInt("MaSP"));
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
	
	public ArrayList<Picture> searchItemm(String TenSP, int page, int size) {
		ArrayList<Picture> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select p.* from picture as p inner join product as pr on p.MaSP = pr.MaSP where pr.TenSP like ? limit ?, ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, '%' + TenSP + '%');
			pst.setInt(2, (page-1)*size);
			pst.setInt(3, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				Picture item = new Picture(rs.getInt("MaHA"), rs.getString("TenHA"), rs.getInt("MaSP"));
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
	
	public int insertItem(Picture item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "insert into picture(TenHA, MaSP) values(?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getTenHA());
			pst.setInt(2, item.getMaSP());
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
	
	public Picture getItem(int id) {
		Picture item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "select * from picture where MaHA = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Picture(rs.getInt("MaHA"), rs.getString("TenHA"), rs.getInt("MaSP"));
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
	
	public Picture getItemm(int id) {
		Picture item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "select picture.* from picture inner join product on picture.MaSP = product.MaSP where product.MaDM = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Picture(rs.getInt("MaHA"), rs.getString("TenHA"), rs.getInt("MaSP"));
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
	
	public int editItem(Picture item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "update picture set TenHA = ?, MaSP = ? where MaHA = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getTenHA());
			pst.setInt(2, item.getMaSP());
			pst.setInt(3, item.getMaHA());
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
	
	public int deleteItem(int id) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "delete from picture where MaHA = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
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
	
	public ArrayList<Picture> getListByIdSP(int id) {
		ArrayList<Picture> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from picture where MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Picture item = new Picture(rs.getInt("MaHA"), rs.getString("TenHA"), rs.getInt("MaSP"));
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
	
	public boolean ktDM(int id) {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from picture where MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
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
}
