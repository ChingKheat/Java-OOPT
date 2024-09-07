package System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
	
//	public void writeToFile(String file, String content) {
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
//			writer.write(content);
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	public void deleteFromFile(String file, String contentDelete) {
        List<String> fileContents = new ArrayList<>();
        try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
	        String Line;
	        
            while ((Line = reader.readLine()) != null) {
                String[] parts = Line.split("\\|");
                
                boolean matchFound = false;
                
                // Check if any part of the array matches contentDelete
                for (String part : parts) {
                    if (part.equals(contentDelete)) {
                        matchFound = true;
                        break;
                    }
                }
                
                // If no match is found, add the line to fileContents
                if (!matchFound) {
                    fileContents.add(Line);
                }
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (String line : fileContents) {
                writer.write(line);
                writer.newLine();
            }
            writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public List<Inventory> readInventoryFile(String filename) {
	    List<Inventory> inventory = new ArrayList<>();
        try {
        	BufferedReader reader = new BufferedReader(new FileReader(filename)); 
        
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                String barcode = parts[0];
                String name = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                inventory.add(new Inventory(barcode, name, quantity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return inventory;
    }
	
	public List<ProductMaster> readPMasterFile(String filename){
	    List<ProductMaster> productMaster = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");
				
				String barcode = parts[0];
				String name = parts[1];
				
				productMaster.add(new ProductMaster(parts[0], parts[1]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return productMaster;
	}
	
	public List<InventoryLocation> readInvLocationFile(String filename){
	    List<InventoryLocation> invLocation = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");
				
				invLocation.add(new InventoryLocation(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), parts[4]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        return invLocation;
	}
	
	public List<ReceiveOrder> readROrderFile(String filename){
		List<ReceiveOrder> receiveOrder = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");
				
//				String orderId = parts[0];
//				String barcode = parts[1];
//				String name = parts[2];
//				int quantity = Integer.parseInt(parts[3]);
				
				
				receiveOrder.add(new ReceiveOrder(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return receiveOrder;
	}
	
	public List<DeliveryOrder> readDeliveryOrderFile(String filename){
		List<DeliveryOrder> deliveryOrder = new ArrayList<>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(filename));
			
			String line;
			while((line = reader.readLine()) != null) {
				String[] parts = line.split("\\|");
				
//				String orderId = parts[0];
//				String barcode = parts[1];
//				String name = parts[2];
//				int quantity = Integer.parseInt(parts[3]);
				
				
				deliveryOrder.add(new DeliveryOrder(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return deliveryOrder;
	}

	public void writeInventoryFile(String filename, List<Inventory> inventory) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Inventory inv : inventory) {
                writer.write(inv.getBarcode() + "|" + inv.getName() + "|" + inv.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void writeInvLocationFile(String filename, List<InventoryLocation> invLocation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (InventoryLocation invL : invLocation) {
                writer.write(invL.getBarcode() + "|" + invL.getName() + "|" + invL.getQuantity() + "|" + invL.getFloor() + "|" + invL.getRack());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void writeReceiveOrderFile(String filename, List<ReceiveOrder> receiveOrder) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (ReceiveOrder RO : receiveOrder) {
                writer.write(RO.getOrderId() + "|" + RO.getBarcode() + "|" + RO.getName() + "|" + RO.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void writeProductMasterFile(String filename, List<ProductMaster> productMaster) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (ProductMaster PM : productMaster) {
                writer.write(PM.getBarcode() + "|" +PM.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public void writeDeliveryOrderFile(String filename, List<DeliveryOrder> deliveryOrder) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (DeliveryOrder DO : deliveryOrder) {
                writer.write(DO.getOrderId() + "|" + DO.getBarcode() + "|" + DO.getName() + "|" + DO.getQuantity());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

