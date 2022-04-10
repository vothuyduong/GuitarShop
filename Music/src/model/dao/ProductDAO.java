package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.bean.Category;
import model.bean.Picture;
import model.bean.Product;
import util.DBConnectionUtil;

public class ProductDAO {
	private Connection con;
	private Statement st;
	private PreparedStatement pst;
	private ResultSet rs;
	
	public ProductDAO() {
		
	}
	
	public ArrayList<Product> getItems() {
		ArrayList<Product> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from product";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				CategoryDAO catDAO = new CategoryDAO();
				PictureDAO pictureDAO = new PictureDAO();
				ArrayList<Picture> listAnh = pictureDAO.getListByIdSP(rs.getInt("MaSP"));
				Product item = new Product(rs.getInt("MaSP"), rs.getString("TenSP"), rs.getInt("SoLuong"), rs.getString("ChiTiet"), rs.getInt("Gia"), rs.getInt("KhuyenMai"), catDAO.getItemById(rs.getInt("MaDM")), listAnh);
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
	
	public ArrayList<Product> getItemss(int page, int size) {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from product limit ?, ?";
		ArrayList<Product> items = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (page-1)*size);
			pst.setInt(2, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				CategoryDAO catDAO = new CategoryDAO();
				PictureDAO pictureDAO = new PictureDAO();
				ArrayList<Picture> listAnh = pictureDAO.getListByIdSP(rs.getInt("MaSP"));
				Product item = new Product(rs.getInt("MaSP"), rs.getString("TenSP"), rs.getInt("SoLuong"), rs.getString("ChiTiet"), rs.getInt("Gia"), rs.getInt("KhuyenMai"), catDAO.getItemById(rs.getInt("MaDM")), listAnh);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null && rs != null && pst != null) {
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
	
	public ArrayList<Product> getMoiNhat(int page, int size) {
		con = DBConnectionUtil.getConnection();
		String sql = "select * from product order by MaSP desc limit ?, ?";
		ArrayList<Product> items = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (page-1)*size);
			pst.setInt(2, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				CategoryDAO catDAO = new CategoryDAO();
				PictureDAO pictureDAO = new PictureDAO();
				ArrayList<Picture> listAnh = pictureDAO.getListByIdSP(rs.getInt("MaSP"));
				Product item = new Product(rs.getInt("MaSP"), rs.getString("TenSP"), rs.getInt("SoLuong"), rs.getString("ChiTiet"), rs.getInt("Gia"), rs.getInt("KhuyenMai"), catDAO.getItemById(rs.getInt("MaDM")), listAnh);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null && rs != null && pst != null) {
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
	
	public ArrayList<Product> getNames() {
		ArrayList<Product> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select MaSP, TenSP from product";
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql);
			while(rs.next()) {
				Product item = new Product(rs.getInt("MaSP"), rs.getString("TenSP"));
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null && rs != null && st != null) {
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
	
	public String getNameById(int id) {
		String name = "";
		con = DBConnectionUtil.getConnection();
		String sql = "select TenSP from product where MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				name = rs.getString("TenSP");
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
		return name;
	}
	
	public int getGiaById(int id) {
		int gia = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "select Gia from product where MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				gia = rs.getInt("Gia");
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
		return gia;
	}
	
	public Product getItemById(int id) {
		Product item = null;
		con = DBConnectionUtil.getConnection();
		String sql = "select * from product where MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				CategoryDAO catDAO = new CategoryDAO();
				PictureDAO pictureDAO = new PictureDAO();
				ArrayList<Picture> listAnh = pictureDAO.getListByIdSP(rs.getInt("MaSP"));
				item = new Product(rs.getInt("MaSP"), rs.getString("TenSP"), rs.getInt("SoLuong"), rs.getString("ChiTiet"), rs.getInt("Gia"), rs.getInt("KhuyenMai"), catDAO.getItemById(rs.getInt("MaDM")), listAnh);
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
	
	public ArrayList<Product> getItemsByIdDM(int id) {
		ArrayList<Product> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from product where MaDM = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				Product item = null;
				CategoryDAO catDAO = new CategoryDAO();
				PictureDAO pictureDAO = new PictureDAO();
				ArrayList<Picture> listAnh = pictureDAO.getListByIdSP(rs.getInt("MaSP"));
				item = new Product(rs.getInt("MaSP"), rs.getString("TenSP"), rs.getInt("SoLuong"), rs.getString("ChiTiet"), rs.getInt("Gia"), rs.getInt("KhuyenMai"), catDAO.getItemById(rs.getInt("MaDM")), listAnh);
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
	
	public ArrayList<Product> searchItem(String TenSP) {
		ArrayList<Product> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from product where TenSP like ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, '%' + TenSP + '%');
			rs = pst.executeQuery();
			while(rs.next()) {
				CategoryDAO catDAO = new CategoryDAO();
				PictureDAO pictureDAO = new PictureDAO();
				ArrayList<Picture> listAnh = pictureDAO.getListByIdSP(rs.getInt("MaSP"));
				Product item = new Product(rs.getInt("MaSP"), rs.getString("TenSP"), rs.getInt("SoLuong"), rs.getString("ChiTiet"), rs.getInt("Gia"), rs.getInt("KhuyenMai"), catDAO.getItemById(rs.getInt("MaDM")), listAnh);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null && rs != null && pst != null) {
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
	
	public ArrayList<Product> searchItemm(String TenSP, int page, int size) {
		ArrayList<Product> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from product where TenSP like ? limit ?, ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, '%' + TenSP + '%');
			pst.setInt(2, (page-1)*size);
			pst.setInt(3, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				CategoryDAO catDAO = new CategoryDAO();
				PictureDAO pictureDAO = new PictureDAO();
				ArrayList<Picture> listAnh = pictureDAO.getListByIdSP(rs.getInt("MaSP"));
				Product item = new Product(rs.getInt("MaSP"), rs.getString("TenSP"), rs.getInt("SoLuong"), rs.getString("ChiTiet"), rs.getInt("Gia"), rs.getInt("KhuyenMai"), catDAO.getItemById(rs.getInt("MaDM")), listAnh);
				items.add(item);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(con != null && rs != null && pst != null) {
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
	
	public int insertItem(Product item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "insert into product(TenSP, SoLuong, ChiTiet, Gia, KhuyenMai, MaDM) values(?, ?, ?, ?, ?,?)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getTenSP());
			pst.setInt(2, item.getSoLuong());
			pst.setString(3, item.getChiTiet());
			pst.setInt(4, item.getGia());
			pst.setInt(5, item.getKhuyenMai());
			pst.setInt(6, item.getMaDM().getId());
			kq = pst.executeUpdate();
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
	
	public int updateItem(Product item) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "update product set TenSP = ?, SoLuong = ?, ChiTiet = ?, Gia = ?, KhuyenMai = ?, MaDM = ? where MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, item.getTenSP());
			pst.setInt(2, item.getSoLuong());
			pst.setString(3, item.getChiTiet());
			pst.setInt(4, item.getGia());
			pst.setInt(5, item.getKhuyenMai());
			pst.setInt(6, item.getMaDM().getId());
			pst.setInt(7, item.getMaSP());
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
		String sql = "delete from product where MaSP = ?";
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
	
	public ArrayList<Product> getSP(int id) {
		ArrayList<Product> items = new ArrayList<>();
		CategoryDAO catDAO = new CategoryDAO();
		if(catDAO.checkCon(id) == false) {
			items = getItemsByIdDM(id);
		} else {
			ArrayList<Category> listCat = catDAO.getItems();
			for(Category item: listCat) {
				if(catDAO.checkCon(item.getId()) == false && catDAO.checkHoHang(id, item.getId())) {
					ArrayList<Product> tam = getItemsByIdDM(item.getId());
					items.addAll(tam);
					tam = null;
				}
			}
		}
		return items;
	}
	
	public ArrayList<Product> getSPBanChay(int page, int size) {
		ArrayList<Product> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select detailorder.MaSP, sum(SoLuongMua) as sl, product.* from detailorder inner join product on detailorder.MaSP = product.MaSP group by detailorder.MaSP order by sl desc limit ?, ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, (page-1)*size);
			pst.setInt(2, size);
			rs = pst.executeQuery();
			while(rs.next()) {
				CategoryDAO catDAO = new CategoryDAO();
				PictureDAO pictureDAO = new PictureDAO();
				ArrayList<Picture> listAnh = pictureDAO.getListByIdSP(rs.getInt("MaSP"));
				Product item = new Product(rs.getInt("MaSP"), rs.getString("TenSP"), rs.getInt("SoLuong"), rs.getString("ChiTiet"), rs.getInt("Gia"), rs.getInt("KhuyenMai"), catDAO.getItemById(rs.getInt("MaDM")), listAnh);
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
	
	public ArrayList<Product> listSPLienQuan(int id, int id_DM) {
		ArrayList<Product> items = new ArrayList<>();
		con = DBConnectionUtil.getConnection();
		String sql = "select * from product where MaDM = ? and MaSP != ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id_DM);
			pst.setInt(2, id);
			rs = pst.executeQuery();
			while(rs.next()) {
				CategoryDAO catDAO = new CategoryDAO();
				PictureDAO pictureDAO = new PictureDAO();
				ArrayList<Picture> listAnh = pictureDAO.getListByIdSP(rs.getInt("MaSP"));
				Product item = new Product(rs.getInt("MaSP"), rs.getString("TenSP"), rs.getInt("SoLuong"), rs.getString("ChiTiet"), rs.getInt("Gia"), rs.getInt("KhuyenMai"), catDAO.getItemById(rs.getInt("MaDM")), listAnh);
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
	
	public int getSoLuong(int id) {
		int soluong = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "select SoLuong from product where MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				soluong = rs.getInt("SoLuong");
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
		return soluong;
	}
	
	public int updateSL(int id_sp, int soluong) {
		int kq = 0;
		con = DBConnectionUtil.getConnection();
		String sql = "update product set SoLuong = SoLuong - ? where MaSP = ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setInt(1, soluong);
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
	
}
