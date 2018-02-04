package Entity;

public class User {
	private int id;
	private String name;
	private String username;
	private String password;
	private int type;
	
	
	public User() {
		id = 0;
		name = "";
		username = "";
		password = "";
		type = 0;
	}

//// TODO: 04.02.2018 kullanıcılara grup oluşturma şartı ekle
	// // TODO: 04.02.2018 userdao ve login controlleri düzenle
	public User(String name, String username, String password, int type) {
		super();
		this.name = name;
		this.username = username;
		this.password = password;
		this.type = type;
	}


	public User(int id, String name, String username, String password, int type) {
		super();
		this.id = id;
		this.name = name;
		this.username = username;
		this.password = password;
		this.type = type;
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


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}
	
}
