package model.bean;

public class Admin {
	private int MaAD;
	private String TenDangNhap;
	private String MatKhau;
	private String HoTen;
	
	public Admin(String tenDangNhap, String matKhau) {
		super();
		TenDangNhap = tenDangNhap;
		MatKhau = matKhau;
	}

	public Admin(int maAD, String tenDangNhap, String matKhau, String hoTen) {
		super();
		MaAD = maAD;
		TenDangNhap = tenDangNhap;
		MatKhau = matKhau;
		HoTen = hoTen;
	}

	public int getMaAD() {
		return MaAD;
	}

	public void setMaAD(int maAD) {
		MaAD = maAD;
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

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}
	
	
}
