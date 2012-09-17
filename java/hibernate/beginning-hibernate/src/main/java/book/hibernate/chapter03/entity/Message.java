package book.hibernate.chapter03.entity;

import lombok.Data;

@Data
public class Message {

	private String message;
	
	public Message() {}
	
	public Message(String message) {
		this.message = message;
	}
	
	
}
