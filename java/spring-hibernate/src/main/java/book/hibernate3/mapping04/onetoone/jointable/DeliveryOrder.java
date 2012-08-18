package book.hibernate3.mapping04.onetoone.jointable;

import lombok.Data;

@Data
public class DeliveryOrder {

	private int deliveryId;
	private int orderinfoId;
	
	// 관계
	private Delivery delivery;
	private Orderinfo orderinfo;
	
}
