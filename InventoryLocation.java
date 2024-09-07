package System;

public class InventoryLocation extends Inventory{
	private int floor;
	private String rack;
	
	public InventoryLocation(String barcode, String name, int quantity, int floor, String rack) {
		super(barcode, name, quantity);
		this.floor = floor;
		this.rack = rack;
	}
	
	

	
	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getRack() {
		return rack;
	}

	public void setRack(String rack) {
		this.rack = rack;
	}
	
	@Override
	public String toString() {
		return super.toString() + "|" + floor + "|" + rack; 
	}
	
	
	
	
}
