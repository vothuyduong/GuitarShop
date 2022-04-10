package model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Contact;
import util.DBConnectionUtil;

public class ContactDAO {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ContactDAO() {
		
	}
	
	public int addItem(Contact item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "insert into contact(Ten, Email, NoiDung) values(?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getHoTen());
			pst.setString(2, item.getEmail());
			pst.setString(3, item.getNoiDung());
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
	
	public ArrayList<Contact> list() {
		ArrayList<Contact> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from contact";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Contact item = new Contact(rs.getInt("MaLH"), rs.getString("Ten"), rs.getString("Email"), rs.getString("NoiDung"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs != null && con != null && st != null) {
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
	
	public int deleteItem(int id) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "delete from contact where MaLH = ?";
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
}
