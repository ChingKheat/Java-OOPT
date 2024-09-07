package System;

public class DeliveryOrder extends ProductMaster{
	private String orderId;
	private int quantity;
	
	
	public DeliveryOrder(String barcode, String name, String orderId, int quantity) {
		super(barcode, name);
		this.orderId = orderId;
		this.quantity = quantity;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return  orderId   + "|" +
			   super.toString() + 
			   quantity   ;
	}
	
	
	

}
