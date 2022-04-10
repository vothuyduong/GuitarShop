package model.bean;

public class Picture {
	private int maHA;
	private String tenHA;
	private int maSP;
	
	public Picture(String tenHA, int maSP) {
		super();
		this.tenHA = tenHA;
		this.maSP = maSP;
	}

	public Picture(int maHA, String tenHA, int maSP) {
		super();
		this.maHA = maHA;
		this.tenHA = tenHA;
		this.maSP = maSP;
	}

	public int getMaHA() {
		return maHA;
	}
	
	public void setMaHA(int maHA) {
		this.maHA = maHA;
	}
	
	public String getTenHA() {
		return tenHA;
	}
	
	public void setTenHA(String tenHA) {
		this.tenHA = tenHA;
	}
	
	public int getMaSP() {
		return maSP;
	}
	
	public void setMaSP(int maSP) {
		this.maSP = maSP;
	}
	
	
}
