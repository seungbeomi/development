package book.hibernate3.mapping01.component;

import java.io.Serializable;

import lombok.Data;

@Data
public class FamilyInfo implements Serializable {

	private String origin;	// 컴포넌트 중첩
	
	public FamilyInfo() {}
	public FamilyInfo(String origin) {
		this.origin = origin;
	}
	
}
