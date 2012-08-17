package book.hibernate3.mapping02.onetoone.foreignkey;

import lombok.Data;

@Data
public class Member {

	private int id;
	private String username;
	private MemberDetail memberDetail;
	
	public Member() {}
	
	public Member(String username) {
		this.username = username;
	}
	
}
