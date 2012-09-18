package book.hibernate.chapter03.entity;

import lombok.Data;

@Data
public class Advert {

	private long id;
	private String title;
	private String message;
	private User user;
	
	public Advert() {}
	public Advert(String title, String message, User user) {
		this.title = title;
		this.message = message;
		this.user = user;
	}
	
}
