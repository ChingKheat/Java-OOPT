package System;

public class ReceiveOrder extends ProductMaster{
	private String orderId;
	private int quantity;
	

	public ReceiveOrder(String orderId, String barcode, String name, int quantity) {
		super(barcode, name);
		this.orderId = orderId;
		this.quantity = quantity;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String taskId) {
		this.orderId = taskId;
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
