package book.hibernate3.mapping04.onetoone.jointable;

import lombok.Data;

@Data
public class Delivery {
	
	private int id;
	
	// 관계
	private DeliveryOrder deliveryOrder;
	private Orderinfo orderinfo;

}
