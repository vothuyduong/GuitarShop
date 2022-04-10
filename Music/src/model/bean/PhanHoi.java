package model.bean;

import java.sql.Timestamp;

public class PhanHoi {
	private int MaPH;
	private int MaCmt;
	private Customer cus;
	private Timestamp ngay;
	private String noiDung;
	
	public PhanHoi(int maCmt, Customer cus, Timestamp ngay, String noiDung) {
		super();
		MaCmt = maCmt;
		this.cus = cus;
		this.ngay = ngay;
		this.noiDung = noiDung;
	}

	public PhanHoi(int maPH, int maCmt, Customer cus, Timestamp ngay, String noiDung) {
		super();
		MaPH = maPH;
		MaCmt = maCmt;
		this.cus = cus;
		this.ngay = ngay;
		this.noiDung = noiDung;
	}

	public int getMaPH() {
		return MaPH;
	}

	public void setMaPH(int maPH) {
		MaPH = maPH;
	}

	public int getMaCmt() {
		return MaCmt;
	}

	public void setMaCmt(int maCmt) {
		MaCmt = maCmt;
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

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	
	
}
