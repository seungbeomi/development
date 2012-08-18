package book.hibernate3.mapping04.onetoone.jointable;

import lombok.Data;

@Data
public class Orderinfo {

	private int id;
	private int count;
	private String zipcode;
	private String address;
	
	// 관계
	private DeliveryOrder order;
	
	public Orderinfo() {}
	
	public Orderinfo(String zipcode, String address) {
		this.zipcode = zipcode;
		this.address = address;
	}
	
}
