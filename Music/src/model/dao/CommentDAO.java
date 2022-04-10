package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Comment;
import util.DBConnectionUtil;

public class CommentDAO {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public CommentDAO() {
		
	}
	
	public ArrayList<Comment> getItems(int MaSP) {
		con = DBConnectionUtil.getConnection();
		ArrayList<Comment> items = new ArrayList<>();
		String sql = "select * from hoi where MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, MaSP);
			rs = pst.executeQuery();
			while(rs.next()) {
				CustomerDAO cusDAO = new CustomerDAO();
				Comment item = new Comment(rs.getInt("MaHoi"), rs.getInt("MaSP"), cusDAO.getItemById(rs.getInt("MaKH")), rs.getTimestamp("Ngay"), rs.getString("NoiDung"));
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
	
	public int insertItem(Comment item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "insert into hoi(MaKH, MaSP, Ngay, NoiDung) values(?, ?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, item.getCus().getMaKH());
			pst.setInt(2, item.getMaSP());
			pst.setTimestamp(3, item.getNgay());
			pst.setString(4, item.getNoidung());
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
