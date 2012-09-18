package book.hibernate.chapter03.entity;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

@Data
public class Category {

	private long id;
	private String title;
	private Set<Advert> adverts = new HashSet<Advert>();
	
	public Category() {}
	public Category(String title) {
		this.title = title;
	}
}
