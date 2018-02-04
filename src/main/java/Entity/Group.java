package Entity;

import java.sql.Array;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import Dao.GroupDao;
import Dao.PermissionDao;

public class Group {
	
	int id;
	String name;
	
	public Group(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Group() {
		this.id = 0;
		this.name = "custom";
	}
//// TODO: 04.02.2018 grubu olmayan veya özel izinleri olan kullanıcılara yeni gruplar tanımla
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
		return "Group{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}