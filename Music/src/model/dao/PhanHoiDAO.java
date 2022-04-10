package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.PhanHoi;
import util.DBConnectionUtil;

public class PhanHoiDAO {
	private Connection con;
	private PreparedStatement pst;
	private Statement st;
	private ResultSet rs;
	
	public PhanHoiDAO() {
		
	}
	
	public ArrayList<PhanHoi> getItems(int idhoi) {
		ArrayList<PhanHoi> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from phanhoi where MaHoi = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, idhoi);
			rs = pst.executeQuery();
			while(rs.next()) {
				CustomerDAO cusDAO = new CustomerDAO();
				PhanHoi item = new PhanHoi(rs.getInt("MaPH"), rs.getInt("MaHoi"), cusDAO.getItemById(rs.getInt("MaKH")), rs.getTimestamp("Ngay"), rs.getString("NoiDung"));
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
	
	public int addItem(PhanHoi item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "insert into phanhoi(MaHoi, MaKH, Ngay, NoiDung) values(?, ?, ?, ?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, item.getMaCmt());
			pst.setInt(2, item.getCus().getMaKH());
			pst.setTimestamp(3, item.getNgay());
			pst.setString(4, item.getNoiDung());
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
