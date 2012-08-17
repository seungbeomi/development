package book.hibernate3.mapping01.component;

import java.io.Serializable;

import lombok.Data;

@Data
public class Name implements Serializable {

	private Employment employment;	// 컴포넌트에서 부모 객체 참조하기
	
	private String surname;
	private String name;
	private FamilyInfo familyInfo;	// 컴포넌트 중첩
	
	public Name() {}
	
	public Name(String surname, String name) {
		this.surname = surname;
		this.name = name;
	}
	
}
