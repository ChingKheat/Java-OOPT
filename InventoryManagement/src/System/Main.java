package System;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); // create scanner
		List<ProductMaster> productMaster = new ArrayList<>();
        List<ReceiveOrder> receiveOrder = new ArrayList<>();
        List<Inventory> inventory = new ArrayList<>();
        FileManager file = new FileManager();
        
        //read productmaster file
        try (BufferedReader reader = new BufferedReader(new FileReader("ProductMaster.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] PMparts = line.split("\\|");
                productMaster.add(new ProductMaster(PMparts[0], PMparts[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        //read ReceiveOrder file
        try (BufferedReader reader = new BufferedReader(new FileReader("ReceiveOrder.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] ROparts = line.split("\\|");
                receiveOrder.add(new ReceiveOrder(ROparts[0], ROparts[1], ROparts[2], Integer.parseInt(ROparts[3])));
                                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

		
		System.out.println("TKWC Computer Accessories");
		System.out.println("Menu");
		System.out.println("1. Product Master\n"
						 + "2. Receive\n"
						 + "3. Putaway Task\n"
						 + "4. Inventory\n"
						 + "5. Storage Location\n"
						 + "6. Stock Movement\n"
						 + "7. Packing Task");
		
		System.out.print("\nEnter your choice: ");
		int choice = input.nextInt();
		
		switch(choice) {
		case 1:
			for (ProductMaster PM : productMaster) {
				System.out.println(PM);
			}

			break;
		case 2:
			System.out.println("Receive");
			break;
		case 3:
			
            System.out.println("\nReceive Order:\n");
            for (ReceiveOrder task : receiveOrder) {
                System.out.println(task);
            }
            
            System.out.print("Enter the ID of the OrderId you want: ");
            input.nextLine();
            String orderId = input.nextLine();
            
            //use the input loop in receiveOrder file / array to check is correct or not
			boolean found = false;
			for(ReceiveOrder i : receiveOrder) {
				if(i.getOrderId().equals(orderId)){
					found = true;
					System.out.println("Order found: " + orderId);
					System.out.println("\n" + i); //print all the details of in textfile
				
		            // Find the product in the productMaster list
		            ProductMaster product = null;
		            for (ProductMaster p : productMaster) {
		                if (p.getBarcode().equals(i.getBarcode())) {
		                    product = p;
		                    break;
		                }
		            }
		            
		            
		            inventory.add(new Inventory(product.getBarcode(),
		            							product.getName(),
		            							i.getQuantity(),
		            							"1", "1")); //put input for the location //and the floor location is fixed
		         
//		            try {
//						BufferedWriter writer = new BufferedWriter(new FileWriter("Inventory.txt", true));
//						for(Inventory inv : inventory) {
//							writer.write(inv.toString());
//							writer.close();
//						}
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
		            
		            //same as up comment write to file
		            for(Inventory inv : inventory) {
			            file.writeToFile("Inventory.txt", inv.toString());
		            }

		            file.deleteFromFile("ReceiveOrder.txt", orderId);
		            
		            
					break;
					
					
				}
			}
		    if (!found) {
		        System.out.println("Order ID not found.");
		    }
		    
		    //if found move the quantity to inventory location
		    //inside the loop
			
          
			break;
		case 4:
			//read inventory for all amount
			try {
				BufferedReader reader = new BufferedReader(new FileReader("Inventory.txt"));	
				String line;

				while((line = reader.readLine())!=null) {
					String[] parts = line.split("\\|");
					String barcode = parts[0];
					String name = parts[1];
					int quantity = Integer.parseInt(parts[2]);
					String floor = parts[3];
					String rack = parts[4];
					
					inventory.add(new Inventory(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4]));
					
					System.out.println(barcode + "|" + name + "|" + quantity);
				}

				System.out.println("\nDo you want to check product location? ");
				System.out.println("1-Yes");
				System.out.println("2-Back");
				int productLocation = input.nextInt();
				
				switch (productLocation) {
				case 1:
					System.out.println("Enter barcode: ");
					input.nextLine();
					String barcode = input.nextLine();
					
					for (Inventory inv : inventory) {
						if(inv.getBarcode().equals(barcode)) {
							System.out.println(inv.getBarcode()  + "|" + 
											   inv.getName()     + "|" + 
											   inv.getQuantity() + "|" + 
											   inv.getFloor()    + "|" + 
											   inv.getRack());
						}
					}
					break;
					
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5:
			System.out.println("Storage Location");
			break;
		case 6:
			System.out.println("Stock Movement");
			break;
		case 7:
			System.out.println("Packing Task");
			break;
			
	}
	}
}
