package model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.bean.Category;
import util.DBConnectionUtil;
import util.StringUtil;

public class CategoryDAO {
	private Connection con;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public ArrayList<Category> getItems() {
		ArrayList<Category> items = new ArrayList<>();
		String sql = "select * from category";
		con = DBConnectionUtil.getConnection();
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Category item = new Category(rs.getInt("MaDM"), rs.getString("TenDM"), rs.getInt("ParentId"), rs.getInt("DaXoa"));
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
	
	public ArrayList<Category> getItemss(int page, int size) {
		ArrayList<Category> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from category limit ?, ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (page - 1)*size);
			pst.setInt(2, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				Category item = new Category(rs.getInt("MaDM"), rs.getString("TenDM"), rs.getInt("ParentId"), rs.getInt("DaXoa"));
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
	
	public String getNameDM(int id) {
		String ten = "";
		String sql = "select TenDM from category where MaDM = ?";
		con = DBConnectionUtil.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				ten = rs.getString("TenDM");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pst != null && con != null) {
				try {
					pst.close();
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ten;
	}
	
	public static String lap(int lan) {
		String kq = "";
		if(lan > 1) {
			for(int i = 1; i <= lan; i++) {
				kq = kq + '-';
			}
		}
		return kq;
	}
	
	public void displayDM(ArrayList<Category> list, int id_parent, int lan, int id_chon, javax.servlet.jsp.JspWriter out) {
		for(Category item: list) {	
			if(item.getId_parent() == id_parent) {
				try {
					if(item.getId() == id_chon) {
						out.println("<option value=\"" + item.getId() + "\" selected>"  + lap(lan) + item.getName() +  "</option>");
					} else {
						out.println("<option value=\"" + item.getId() + "\">"  + lap(lan) + item.getName() +  "</option>");
					}
					int tam = item.getId();
					displayDM(list, tam, (lan + 7), id_chon, out);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void displayDMPL(ArrayList<Category> list, int id_parent, javax.servlet.jsp.JspWriter out, HttpServletRequest request) {
		try {
			out.print("<ul>");
			for(Category item : list) {
				if(item.getId_parent() == id_parent) {
					out.print("<li><a href=\"" + request.getContextPath() + "/" + StringUtil.makeSlug(item.getName()) + "-" + item.getId() +"\">" + item.getName() + "</a></li>");
					int tam = item.getId();
					displayDMPL(list, tam, out, request);
				}
			}
			out.print("</ul>");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public boolean hasItem(String name) {
		String sql = "select * from category where TenDM = ?";
		con = DBConnectionUtil.getConnection();
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
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
	
	public int insertItem(Category item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "insert into category(TenDM, ParentId) values(?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setInt(2, item.getId_parent());
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
	
	public Category getItemById(int id) {
		Category item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "select * from category where MaDM = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Category(rs.getInt("MaDM"), rs.getString("TenDM"), rs.getInt("ParentId"), rs.getInt("DaXoa"));
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
	
	public int editItem(Category item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "update category set TenDM = ?, ParentId = ? where MaDM = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getName());
			pst.setInt(2, item.getId_parent());
			pst.setInt(3, item.getId());
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
	
	public int updateHienThi(int ht, int id) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "update category set DaXoa = ? where MaDM = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, ht);
			pst.setInt(2, id);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null && pst != null) {
				try {
					con.close();
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return kq;
	}
	
	public ArrayList<Category> searchItem(String key) {
		ArrayList<Category> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from category where TenDM like ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');
			rs = pst.executeQuery();
			while(rs.next()) {
				Category item = new Category(rs.getInt("MaDM"), rs.getString("TenDM"), rs.getInt("ParentId"), rs.getInt("DaXoa"));
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
	
	public ArrayList<Category> searchItemm(String key, int page, int size) {
		ArrayList<Category> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from category where TenDM like ? limit ?, ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, '%' + key + '%');
			pst.setInt(2, (page - 1)*size);
			pst.setInt(3, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				Category item = new Category(rs.getInt("MaDM"), rs.getString("TenDM"), rs.getInt("ParentId"), rs.getInt("DaXoa"));
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
	
	public boolean checkCon(int id) {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from category where ParentId = ?";
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
			if(rs != null && pst != null && con != null) {
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
	
	public boolean checkSP(int id) {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from product where MaDM = ?";
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
			if(rs != null && pst != null && con != null) {
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
	
	public int deleteItem(int id) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "delete from category where MaDM = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			kq = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pst != null && con != null) {
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
	
	public int getIdParent(int id) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "select ParentId from category where MaDM = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				kq = rs.getInt("ParentId");
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
		return kq;
	}
	
	public boolean checkHoHang(int id, int parent_id) {
		int cha = getIdParent(parent_id);
		if(cha == 0) {
			return false;
		}
		if(cha == id) {
			return true;
		}
		return checkHoHang(id, cha);
	}
	
	public int layCon(int id, ArrayList<Category> list) {
		int id_con = id;
		for(Category item: list) {
			if(item.getId_parent() == id) {
				return layCon(item.getId(), list);
			}
		}
		return id_con;
	}
}
