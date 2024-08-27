package System;

public class Inventory extends ProductMaster{
	private String floor;
	private String rack;
	private int quantity;
	
	public Inventory(String barcode, String name, int quantity, String floor, String rack) {
		super(barcode, name);
		this.floor = floor;
		this.rack = rack;
		this.quantity = quantity;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return super.toString() + quantity + "|" 
								+ floor    + "|"  
								+ rack     + "\n";
	}
	
	
}


