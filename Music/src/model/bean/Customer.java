package model.bean;

import java.sql.Date;

public class Customer {
	private int MaKH;
	private String TenKH;
	private Date NgaySinh;
	private String DiaChi;
	private String Sdt;
	private String Email;
	private String TenDangNhap;
	private String MatKhau;
	private int GioiTinh;
	
	public Customer(String tenDangNhap, String matKhau) {
		super();
		TenDangNhap = tenDangNhap;
		MatKhau = matKhau;
	}

	public Customer(String tenKH, Date ngaySinh, String diaChi, String sdt, String email, String tenDangNhap,
			String matKhau, int gioiTinh) {
		super();
		TenKH = tenKH;
		NgaySinh = ngaySinh;
		DiaChi = diaChi;
		Sdt = sdt;
		Email = email;
		TenDangNhap = tenDangNhap;
		MatKhau = matKhau;
		GioiTinh = gioiTinh;
	}

	public Customer(int maKH, String tenKH, Date ngaySinh, String diaChi, String sdt, String email, String tenDangNhap,
			String matKhau, int gioiTinh) {
		super();
		MaKH = maKH;
		TenKH = tenKH;
		NgaySinh = ngaySinh;
		DiaChi = diaChi;
		Sdt = sdt;
		Email = email;
		TenDangNhap = tenDangNhap;
		MatKhau = matKhau;
		GioiTinh = gioiTinh;
	}

	public int getMaKH() {
		return MaKH;
	}

	public void setMaKH(int maKH) {
		MaKH = maKH;
	}

	public String getTenKH() {
		return TenKH;
	}

	public void setTenKH(String tenKH) {
		TenKH = tenKH;
	}

	public Date getNgaySinh() {
		return NgaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		NgaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return DiaChi;
	}

	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}

	public String getSdt() {
		return Sdt;
	}

	public void setSdt(String sdt) {
		Sdt = sdt;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getTenDangNhap() {
		return TenDangNhap;
	}

	public void setTenDangNhap(String tenDangNhap) {
		TenDangNhap = tenDangNhap;
	}

	public String getMatKhau() {
		return MatKhau;
	}

	public void setMatKhau(String matKhau) {
		MatKhau = matKhau;
	}

	public int getGioiTinh() {
		return GioiTinh;
	}

	public void setGioiTinh(int gioiTinh) {
		GioiTinh = gioiTinh;
	}

}
