package book.hibernate3.mapping01.component;

import java.io.Serializable;

import lombok.Data;

@Data
public class Employment implements Serializable {

	private int id;
	private Name korName;
	private Name engName;
	
}
