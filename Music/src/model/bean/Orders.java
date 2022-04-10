package model.bean;

import java.sql.Timestamp;

public class Orders {
	private int maDH;
	private Customer khachHang;
	private Timestamp ngayTao;
	private int tongTien;
	private int trangThai;
	
	public Orders(Customer khachHang, Timestamp ngayTao, int tongTien, int trangThai) {
		super();
		this.khachHang = khachHang;
		this.ngayTao = ngayTao;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
	}

	public Orders(int maDH, Customer khachHang, Timestamp ngayTao, int tongTien, int trangThai) {
		super();
		this.maDH = maDH;
		this.khachHang = khachHang;
		this.ngayTao = ngayTao;
		this.tongTien = tongTien;
		this.trangThai = trangThai;
	}

	public int getMaDH() {
		return maDH;
	}
	
	public void setMaDH(int maDH) {
		this.maDH = maDH;
	}
	
	public Customer getKhachHang() {
		return khachHang;
	}
	
	public void setKhachHang(Customer khachHang) {
		this.khachHang = khachHang;
	}
	
	public Timestamp getNgayTao() {
		return ngayTao;
	}
	
	public void setNgayTao(Timestamp ngayTao) {
		this.ngayTao = ngayTao;
	}
	
	public int getTongTien() {
		return tongTien;
	}
	
	public void setTongTien(int tongTien) {
		this.tongTien = tongTien;
	}
	
	public int getTrangThai() {
		return trangThai;
	}
	
	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}
	
	
}
