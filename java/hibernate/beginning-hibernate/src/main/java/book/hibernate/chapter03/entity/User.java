package book.hibernate.chapter03.entity;

import lombok.Data;

@Data
public class User {

	private long id;
	private String name;
	private String password;
	
	public User() {}
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
}
