package model.bean;

public class Cart {
	private int MaKH;
	private int MaSP;
	private int gia;
	private int soluong;
	
	public Cart(int maKH, int maSP, int gia, int soluong) {
		super();
		MaKH = maKH;
		MaSP = maSP;
		this.gia = gia;
		this.soluong = soluong;
	}

	public int getMaKH() {
		return MaKH;
	}

	public void setMaKH(int maKH) {
		MaKH = maKH;
	}

	public int getMaSP() {
		return MaSP;
	}

	public void setMaSP(int maSP) {
		MaSP = maSP;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}
	
	
}
