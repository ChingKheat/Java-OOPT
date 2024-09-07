package System;

public class Inventory extends ProductMaster{
//	product
//	location
	private int quantity;
	
	public Inventory(String barcode, String name, int quantity) {
		super(barcode, name);
//		this.floor = floor;
//		this.rack = rack;
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return super.toString() + quantity ;
	}
	
	
}


