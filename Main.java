package System;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in); // create scanner
		List<ProductMaster> productMaster = new ArrayList<>();
        List<ReceiveOrder> receiveOrder = new ArrayList<>();
        List<Inventory> inventory = new ArrayList<>();
        List<InventoryLocation> invLocation = new ArrayList<>(); 
        List<DeliveryOrder> deliveryOrder = new ArrayList<>(); 
        
        //Get all data from file to Array
        FileManager file = new FileManager();
        
        //read Inventory.txt
        inventory = file.readInventoryFile("Inventory.txt");
        
        //Read ProductMaster.txt   
        productMaster = file.readPMasterFile("ProductMaster.txt");
        
        //read ReceiveOrder.txt
        receiveOrder = file.readROrderFile("ReceiveOrder.txt");
        
        //read InventoryLocation.txt
        invLocation = file.readInvLocationFile("InventoryLocation.txt");
        
        //read DeliveryOrder.txt
        deliveryOrder = file.readDeliveryOrderFile("DeliveryOrder.txt");
		for(InventoryLocation inv : invLocation) {
			if(inv.getQuantity() > 500) {
		        System.out.println("Overflow system");
				System.out.println(inv.getName());
				
				int newQuantity = inv.getQuantity()-500;
				
				inv.setQuantity(500);
				System.out.print("Enter floor: ");
				int newFloor = input.nextInt();
				System.out.print("Enter rack: ");
				input.nextLine();
				String newRack= input.nextLine();	
				
				invLocation.add(new InventoryLocation(inv.getBarcode(), inv.getName(), newQuantity, newFloor, newRack));
				file.writeInvLocationFile("InventoryLocation.txt", invLocation);
				break;
			}
		}
        
        int choice;
        
        do {
        
			System.out.println("TKWC Computer Accessories");
			System.out.println("Menu");
			System.out.println("1. Product Master\n"
							 + "2. Purchase Order\n"
							 + "3. Receive Order\n"
							 + "4. Inventory\n"
							 + "5. Packing\n"
							 + "6. Exit\n"
							 + "7. Delivery Order");
			
			System.out.print("\nEnter your choice: ");
			choice = input.nextInt();

        	switch(choice) {

			case 1:
				for (ProductMaster PM : productMaster) {
					System.out.println(PM);
				}
				
				
				System.out.println("\nAdd new product");
				System.out.println("1. Yes\n2. No");
				System.out.print("Please enter an integer: ");
				int integer = input.nextInt();
				input.nextLine(); // clear buffer
				
				if(integer == 1) {

					
				   boolean exists;
				   String name;
				   String barcode;

			        do {
			            System.out.print("Enter product name: ");
			            name = input.nextLine().toUpperCase();

			            System.out.print("Enter product barcode: ");
			            barcode = input.nextLine().toUpperCase();

			            exists = false;

			            for (ProductMaster PM : productMaster) {
			                if (name.equals(PM.getName()) || barcode.equals(PM.getBarcode())) {
			                    exists = true;
			                    System.out.println(name + " | " + barcode);
			                    System.out.println("Name or Barcode already exists. Please try again.");
			                    break; // Exit the loop early if a duplicate is found
			                }
			            }

//			            // Only prompt for input again if a duplicate was found
//			            if (exists) {
//			                System.out.println("Please enter a new product.");
//			            }
			            
			        } while (exists);

					System.out.println("Successful");
					
					productMaster.add(new ProductMaster(barcode, name));
					
					System.out.println("New product added: " + name);
					
					file.writeInvLocationFile("Inventory.txt", invLocation);
					
				}
				break;
			case 2:
				System.out.println("Purchase Order\n");
				
				int i = 1;
				for(ProductMaster PM : productMaster) {

					System.out.println(i + ". " +PM.getName());
					i++;
				}
				
				System.out.print("What item Do you want to order: ");
				input.nextLine();
				String order = input.nextLine().toUpperCase();
				
				boolean barcodeFound = false;
				for(ProductMaster PM: productMaster) {
					if(order.equals(PM.getName())){
						System.out.println(PM.getName());
						barcodeFound = true;
						
						System.out.print("OrderId: ");
						String orderId = input.nextLine();
						
						System.out.print("Quantity: ");
						int quantity = input.nextInt();
						
						receiveOrder.add(new ReceiveOrder(orderId , PM.getBarcode(), PM.getName(), quantity));
						
						file.writeReceiveOrderFile("receiveOrder.txt", receiveOrder);
						
						System.out.println("Order confirmed");
					}
				}
				if(!barcodeFound) {
					System.out.println(order + " Item not found");
				}
				
				input.nextLine(); //clear buffer
				System.out.println("Press any key to continue...");
				input.nextLine(); //show the message
				
				break;
			case 3:
		        System.out.println("\nReceive Order:\n");
		        for (ReceiveOrder task : receiveOrder) {
		            System.out.println(task);
		        }
		        
		        System.out.print("\nEnter the ID of the OrderId you want: ");
		        input.nextLine();
		        String orderId = input.nextLine();
		
		        //Validate is the input's OrderId in Receive Order
				boolean found = false;
				for(ReceiveOrder RO : receiveOrder) {
					
					if(RO.getOrderId().equals(orderId)){
						found = true;
						
						System.out.println("Order found: " + orderId);
						System.out.println("\n" + RO);
						   
		
		                // Step 2: Check if the barcode is already in the inventory
		                boolean barcodeExists = false;
		                for (Inventory inv : inventory) {
		                    
		                    if(RO.getBarcode().equals(inv.getBarcode())) {
		                    	inv.setQuantity(inv.getQuantity() + RO.getQuantity());
		                    	barcodeExists = true;
		                    	break;
		                    }
		                }
		                if(!barcodeExists) {
		                    inventory.add(new Inventory(RO.getBarcode(), RO.getName(), RO.getQuantity()));
		                }
		
		                
						System.out.print("Enter floor: ");
						int floor = input.nextInt();
						System.out.print("Enter rack: ");
						input.nextLine();
						String rack = input.nextLine();	
						
						invLocation.add(new InventoryLocation(RO.getBarcode(), RO.getName(), RO.getQuantity(), floor, rack));
						
		
		                file.writeInventoryFile("Inventory.txt", inventory);
						file.writeInvLocationFile("InventoryLocation.txt", invLocation);
		                
			            file.deleteFromFile("ReceiveOrder.txt", orderId);
//						System.out.println("Sucessful");
						
						// inventory check 500 quantity
						for(InventoryLocation inv : invLocation) {
							if(inv.getQuantity() > 500) {
								System.out.println(inv.getName());
								
								int newQuantity = inv.getQuantity()-500;
								
								inv.setQuantity(500);
								System.out.print("Enter floor: ");
								int newFloor = input.nextInt();
								System.out.print("Enter rack: ");
								input.nextLine();
								String newRack= input.nextLine();	
								
								invLocation.add(new InventoryLocation(inv.getBarcode(), inv.getName(), newQuantity, newFloor, newRack));
								file.writeInvLocationFile("InventoryLocation.txt", invLocation);
								break;
							}
						}
						
						break;
						
						
					}
				}
			    if (!found) {
			        System.out.println("Order ID not found.");
			    }
			    
				break;
			case 4:
				for(Inventory inv : inventory) {
					System.out.println(inv);
				}
				System.out.println("\n");
				System.out.println("Do want want to check location? ");
				System.out.println("1. Yes\n2. No");
				System.out.print("Please enter an integer: ");
				int check = input.nextInt();
				input.nextLine(); // clear buffer
				
				if(check == 1) {
					for(InventoryLocation location : invLocation) {
						System.out.println(location);
						
					}
					
					System.out.println("Do want find to item location? ");
					System.out.println("1. Yes\n2. No");
					System.out.print("Please enter an integer: ");
					int item = input.nextInt();
					input.nextLine();

					if(item == 1) {
						for(ProductMaster PM : productMaster) {
							System.out.println(PM.getName());
						}
						
						System.out.println("Enter name: ");
						String name = input.nextLine().toUpperCase();
						
						for(InventoryLocation loc : invLocation) {
							if(name.equals(loc.getName())) {
								System.out.println(loc);
							}else {
								System.out.println("error");
							}
						}
							


					}
				}
				
				System.out.println("Press any key to continues...");
				input.nextLine();
			
				break;
			case 5:
		        // Create Delivery Order from another company
				for(DeliveryOrder DO :deliveryOrder) {
					System.out.println(DO);
				}
				input.nextLine();
				System.out.println("Enter ID");
				String Id = input.nextLine();
				
				boolean IdFound = false;
				for(DeliveryOrder DO: deliveryOrder) {
					if(Id.equals(DO.getOrderId())){
						System.out.println(DO.getName());
						IdFound = true;
						
						System.out.println(DO.getQuantity());
					}
				}
				if(!IdFound) {
					System.out.println(Id + " not found");
				}
				
				System.out.println("exit...");
				input.nextLine();
		        // Read Purchase Order
//		        try (BufferedReader reader = new BufferedReader(new FileReader("DeliveryOrder.txt"))) {
//		            String line;
//		            while ((line = reader.readLine()) != null) {
//		                String[] parts = line.split("\\|");
//		                String id = parts[0];
//		                String name = parts[1];
//		                int quantity = Integer.parseInt(parts[2]);
//		
//		                salesOrder.add(new Inventory(id, name, quantity));
//		            }
//		
//		            // Display all Sales Orders
//		            for (Inventory inv : salesOrder) {
//		                System.out.println(inv);
//		            }
//		
//		            // Select ID
//		            System.out.print("Enter ID: ");
//		            input.next();
//		            String selectedId = input.nextLine();
//		
//		            // Check inventory quantity (validate)
//		            boolean found1 = false;
//		            for (Inventory inv : salesOrder) {
//		                if (inv.getBarcode().equals(selectedId)) {
//		                    found1 = true;
//		                    System.out.print("Enter quantity to purchase: ");
//		                    int quantityToPurchase = Integer.parseInt(input.nextLine());
//		
//		                    if (quantityToPurchase <= inv.getQuantity()) {
//		                        inv.setQuantity(inv.getQuantity() - quantityToPurchase);
//		                        System.out.println("Purchase successful! Remaining quantity: " + inv.getQuantity());
//		                    } else {
//		                        System.out.println("Not enough stock available.");
//		                    }
//		                    break;
//		                }
//		            }
//		            if (!found1) {
//		                System.out.println("ID not found.");
//		            }
//		
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }
		        break;
			case 6:

					System.out.println("Delivery Order");
					input.nextLine();
					System.out.println("Enter Order ID: ");
					String id = input.nextLine();
					System.out.println("Enter Quantity: ");
					int quantity = input.nextInt();
					
					deliveryOrder.add(new DeliveryOrder("PC", "COMPUTER" , id , quantity)); 
					
					file.writeDeliveryOrderFile("DeliveryOrder.txt", deliveryOrder);
				break;
				
			case 7:

				System.out.println("Exiting the program...");
				break;
				 
			 default: 
				System.out.println("Invalid number! Please enter a number between 1 and 6.");
				System.out.print("\nEnter your choice: ");
				choice = input.nextInt();
				
        	}
        }while(choice != 7);


	}
}
