package model.bean;

public class DetailOrders {
	private int maSP;
	private String tenSP;
	private int soLuongMua;
	private int thanhTien;
	private int gia;
	private int maDH;
	
	public DetailOrders(String tenSP, int soLuongMua) {
		super();
		this.tenSP = tenSP;
		this.soLuongMua = soLuongMua;
	}

	public DetailOrders(int maSP, int soLuongMua, int thanhTien, int gia, int maDH) {
		super();
		this.maSP = maSP;
		this.soLuongMua = soLuongMua;
		this.thanhTien = thanhTien;
		this.gia = gia;
		this.maDH = maDH;
	}

	public DetailOrders(String tenSP, int soLuongMua, int thanhTien, int gia) {
		super();
		this.tenSP = tenSP;
		this.soLuongMua = soLuongMua;
		this.thanhTien = thanhTien;
		this.gia = gia;
	}

	public DetailOrders(int maSP, int soLuongMua, int thanhTien, int gia) {
		super();
		this.maSP = maSP;
		this.soLuongMua = soLuongMua;
		this.thanhTien = thanhTien;
		this.gia = gia;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public int getSoLuongMua() {
		return soLuongMua;
	}

	public void setSoLuongMua(int soLuongMua) {
		this.soLuongMua = soLuongMua;
	}

	public int getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public int getMaSP() {
		return maSP;
	}

	public void setMaSP(int maSP) {
		this.maSP = maSP;
	}

	public int getMaDH() {
		return maDH;
	}

	public void setMaDH(int maDH) {
		this.maDH = maDH;
	}
	
	

}
