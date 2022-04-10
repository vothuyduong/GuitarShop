package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.bean.Admin;
import util.DBConnectionUtil;

public class AdminDAO {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public AdminDAO() {
		
	}
	
	public Admin getItem(String username, String password) {
		Admin item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "select * from adminn where TenDangNhap = ? and MatKhau = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, username);
			pst.setString(2, password);
			rs = pst.executeQuery();
			if(rs.next()) {
				item = new Admin(rs.getString("TenDangNhap"), rs.getString("MatKhau"));
			}
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
		return item;
	}
}
