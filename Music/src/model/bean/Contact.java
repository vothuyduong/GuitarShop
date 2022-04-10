package model.bean;

public class Contact {
	private int MaLH;
	private String HoTen;
	private String email;
	private String NoiDung;
	
	public Contact(String hoTen, String email, String noiDung) {
		super();
		HoTen = hoTen;
		this.email = email;
		NoiDung = noiDung;
	}

	public Contact(int maLH, String hoTen, String email, String noiDung) {
		super();
		MaLH = maLH;
		HoTen = hoTen;
		this.email = email;
		NoiDung = noiDung;
	}

	public int getMaLH() {
		return MaLH;
	}

	public void setMaLH(int maLH) {
		MaLH = maLH;
	}

	public String getHoTen() {
		return HoTen;
	}

	public void setHoTen(String hoTen) {
		HoTen = hoTen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNoiDung() {
		return NoiDung;
	}

	public void setNoiDung(String noiDung) {
		NoiDung = noiDung;
	}
	
	
}
