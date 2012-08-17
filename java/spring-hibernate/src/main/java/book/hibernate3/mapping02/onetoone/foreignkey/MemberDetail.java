package book.hibernate3.mapping02.onetoone.foreignkey;

import lombok.Data;

@Data
public class MemberDetail {

	private int id;
	private String zipcode;
	private String address;
	private Member member;
	
	public MemberDetail() {}
	
	public MemberDetail(String zipcode, String address) {
		this.zipcode = zipcode;
		this.address = address;
	}
	
}
