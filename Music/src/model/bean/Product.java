package model.bean;

import java.util.ArrayList;

public class Product {
	private int maSP;
	private String tenSP;
	private int soLuong;
	private String chiTiet;
	private int gia;
	private int khuyenMai;
	private Category maDM;
	private ArrayList<Picture> picture;
	
	public Product(int maSP, String tenSP) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
	}

	public Product(String tenSP, int soLuong, String chiTiet, int gia, int khuyenMai, Category maDM) {
		super();
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.chiTiet = chiTiet;
		this.gia = gia;
		this.khuyenMai = khuyenMai;
		this.maDM = maDM;
	}

	public Product(int maSP, String tenSP, int soLuong, String chiTiet, int gia, int khuyenMai, Category maDM) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.chiTiet = chiTiet;
		this.gia = gia;
		this.khuyenMai = khuyenMai;
		this.maDM = maDM;
	}

	public Product(int maSP, String tenSP, int soLuong, String chiTiet, int gia, int khuyenMai, Category maDM,
			ArrayList<Picture> picture) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuong = soLuong;
		this.chiTiet = chiTiet;
		this.gia = gia;
		this.khuyenMai = khuyenMai;
		this.maDM = maDM;
		this.picture = picture;
	}

	public int getMaSP() {
		return maSP;
	}

	public void setMaSP(int maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public String getChiTiet() {
		return chiTiet;
	}

	public void setChiTiet(String chiTiet) {
		this.chiTiet = chiTiet;
	}

	public int getGia() {
		return gia;
	}

	public void setGia(int gia) {
		this.gia = gia;
	}

	public int getKhuyenMai() {
		return khuyenMai;
	}

	public void setKhuyenMai(int khuyenMai) {
		this.khuyenMai = khuyenMai;
	}

	public Category getMaDM() {
		return maDM;
	}

	public void setMaDM(Category maDM) {
		this.maDM = maDM;
	}

	public ArrayList<Picture> getPicture() {
		return picture;
	}

	public void setPicture(ArrayList<Picture> picture) {
		this.picture = picture;
	}
	
}
