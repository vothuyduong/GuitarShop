package model.bean;

public class Category {
	private int id;
	private String name;
	private int id_parent;
	private int hienthi;
	
	public Category(String name, int id_parent) {
		super();
		this.name = name;
		this.id_parent = id_parent;
	}

	public Category(int id, String name, int id_parent) {
		super();
		this.id = id;
		this.name = name;
		this.id_parent = id_parent;
	}

	public Category(int id, String name, int id_parent, int hienthi) {
		super();
		this.id = id;
		this.name = name;
		this.id_parent = id_parent;
		this.hienthi = hienthi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId_parent() {
		return id_parent;
	}

	public void setId_parent(int id_parent) {
		this.id_parent = id_parent;
	}

	public int getHienthi() {
		return hienthi;
	}

	public void setHienthi(int hienthi) {
		this.hienthi = hienthi;
	}

}
