package Entity;

public class Permission {

	int id;
	String name;
	
	
	public Permission(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Permission() {
		super();
		this.id = 0;
		this.name = "";
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


	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + "]";
	}

}
