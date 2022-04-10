package model.bean;

import java.sql.Timestamp;

public class Comment {
	private int MaCM;
	private int MaSP;
	private Customer cus;
	private Timestamp ngay;
	private String noidung;
	
	public Comment(int maSP, Customer cus, Timestamp ngay, String noidung) {
		super();
		MaSP = maSP;
		this.cus = cus;
		this.ngay = ngay;
		this.noidung = noidung;
	}
	public Comment(int maCM, int maSP, Customer cus, Timestamp ngay, String noidung) {
		super();
		MaCM = maCM;
		MaSP = maSP;
		this.cus = cus;
		this.ngay = ngay;
		this.noidung = noidung;
	}
	public int getMaCM() {
		return MaCM;
	}
	public void setMaCM(int maCM) {
		MaCM = maCM;
	}
	public int getMaSP() {
		return MaSP;
	}
	public void setMaSP(int maSP) {
		MaSP = maSP;
	}
	public Customer getCus() {
		return cus;
	}
	public void setCus(Customer cus) {
		this.cus = cus;
	}
	public Timestamp getNgay() {
		return ngay;
	}
	public void setNgay(Timestamp ngay) {
		this.ngay = ngay;
	}
	public String getNoidung() {
		return noidung;
	}
	public void setNoidung(String noidung) {
		this.noidung = noidung;
	}
	
	
}
