package defaultP2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class ShoeStore {
	private String name;
	private ArrayList<Shoe> shoeInv;
	private findInfo expensiveShoe;
	private findInfo highShoe;
	private findInfo mostSales;
	private findInfo mostSizes;
	public static void main(String[] args) {
		String fileNa = "./src/defaultPack/ShoeInventory.csv";
		String line = "";
		String values[];
		ShoeStore myStore = new ShoeStore("AKG");
		Shoe myShoe;
		String type, brand;
		
		double price;
		boolean isGortex;
		double isWeight;
		int isTraction;
		int size, qty;
		// Load Shoe inventory...
		try {
			Scanner input = new Scanner(new File(fileNa));
			while (input.hasNext()) {
				line = input.nextLine();
				values = line.split(",");
				if (line.startsWith("#")) {
					continue;
				}
				type = values[0];
				brand = values[1];
				price = Double.parseDouble(values[3]);
				size = Integer.parseInt(values[2]);
				qty = Integer.parseInt(values[4]);
				if (type.equals("Walking")) {
					isGortex = Boolean.parseBoolean(values[5]);
					myShoe = myStore.findShoe(type, brand);
					if (myShoe == null) {
						myShoe = new Walking(brand, type, price, isGortex);
						myStore.addShoe(myShoe);
					}
				} else if (type.equals("Running")) {
					isWeight = Double.parseDouble(values[5]);
					myShoe = myStore.findShoe(type, brand);
					if (myShoe == null) {
						myShoe = new Running(brand, type, price, isWeight);
						myStore.addShoe(myShoe);
					}
				} else if (type.equals("Hiking")) {
					isTraction = Integer.parseInt(values[5]);
					myShoe = myStore.findShoe(type, brand);
					if (myShoe == null) {
						myShoe = new Hiking(brand, type, price, isTraction);
						myStore.addShoe(myShoe);
					}
				} else {
					myShoe = new Shoe(brand, type, price);
					myStore.addShoe(myShoe);
				}
				myShoe.setQuantity(size, qty);
			}
		} catch (IOException e) {
			System.out.println("Error Reading File: " + fileNa);
		}
		myStore.printInventory();
	}
	public ShoeStore(String name) {
		this.name = name;
		this.shoeInv = new ArrayList<Shoe>(10);
	}
	public void welcome() {
		System.out.printf("%83s\n", "    |_|,   ,|_|,");
		System.out.printf("%83s\n", "    |===|   |===|");
		System.out.printf("%83s\n", "    |   |   |   |");
		System.out.printf("%83s\n", "    /  &|   |&  \\");
		System.out.printf("%87s\n", ".-'`  , )* *( ,  `'-.");
		System.out.printf("%85s\n", "`\"\"\"\"\"`   `\"\"\"\"\"`");
		System.out.println();
		System.out.printf("%93s\n", "=================================");
		System.out.printf("%86s\n", "     " + this.name + "'s Shoe Store!");
		System.out.printf("%93s\n", "=================================");
	}
	public void addShoe(Shoe shoe) {
		shoeInv.add(shoe);
	}
	public Shoe findShoe(String type, String brand) {
		Shoe shoe;
		for (int i = 0; i < shoeInv.size(); i++) {
			shoe = shoeInv.get(i);
			if (shoe.getType().equals(type) && shoe.getBrand().equals(brand)) {
				return shoe;
			}
		}
		return null;
	}
	public void mostExpensiveShoe() {
		Shoe myShoe;
		double high = 0.0;
		double tempPrice = 0.0;
		String tempBrand = "";
		String tempType = "";
		for (int i = 0; i < shoeInv.size(); i++) {
			myShoe = shoeInv.get(i);
			tempPrice = myShoe.getBasePrice();
			if (high < tempPrice) {
				high = tempPrice + 0.0;
				tempBrand = myShoe.getBrand();
				tempType = myShoe.getType();
			}
		}
		expensiveShoe = new findInfo(tempBrand, tempType, high);
	}
	public void highestStockedShoe() {
		Shoe myShoe;
		double high = 0;
		int temp = 0;
		int totalQuant = 0;
		String tempBrand = "";
		String tempType = "";
		for (int i = 0; i < shoeInv.size(); i++) {
			myShoe = shoeInv.get(i);
			for (int size = 7; size < 12; size++) {
				temp = myShoe.getQuantity(size);
				totalQuant += temp;
			}
			if (high < totalQuant) {
				high = totalQuant + 0.0;
				tempBrand = myShoe.getBrand();
				tempType = myShoe.getType();
			}
			totalQuant = 0;
		}
		highShoe = new findInfo(tempBrand, tempType, high);
	}
	public void mostSizes() {
		Shoe myShoe;
		int temp = 0;
		int totalSizes = 0;
		double high = 0;
		String tempBrand = "";
		String tempType = "";
		for (int i = 0; i < shoeInv.size(); i++) {
			myShoe = shoeInv.get(i);
			for (int size = 7; size <= 12; size++) {
				temp = myShoe.getQuantity(size);
				if (temp != 0) {
					totalSizes++;
				}
			}
			if (high < totalSizes) {
				high = totalSizes + 0.0;
				tempBrand = myShoe.getBrand();
				tempType = myShoe.getType();
			}
			totalSizes = 0;
		}
		mostSizes = new findInfo(tempBrand, tempType, high);
	}
	public void mostOnSale() {
		Shoe myShoe;
		boolean temp = false;
		int numOnSale = 0;
		double high = 0;
		String tempBrand = "";
		String tempType = "";
		for (int i = 0; i < shoeInv.size(); i++) {
			myShoe = shoeInv.get(i);
			for (int size = 7; size < 12; size++) {
				temp = myShoe.isOnSale(size);
				if (temp) {
					numOnSale++;
				}
				if (high < numOnSale) {
					high = numOnSale;
					tempBrand = myShoe.getBrand();
					tempType = myShoe.getType();
				}
				temp = false;
			}
			numOnSale = 0;
		}
		mostSales = new findInfo(tempBrand, tempType, high);
	}
	
	
	public void printInventory() {
		Shoe myShoe;
		int totalQuantity = 0;
		welcome();
		mostExpensiveShoe();
		highestStockedShoe();
		mostSizes();
		mostOnSale();
		System.out.printf("%-1s %2s %-3s, %-11s $ %4.2f %-1s\n", "Most Expensive ", "Shoe:", expensiveShoe.getType(),
				expensiveShoe.getBrand(), expensiveShoe.getNums(), "(price)");
		System.out.printf("%-1s %7s %-3s, %-1s # %6.2f %-1s\n", "Most Stocked ", "Shoe:", highShoe.getType(),
				highShoe.getBrand(), highShoe.getNums(), "(stock)");
		System.out.printf("%-1s %10s %-3s, %-11s # %6.2f %-1s\n", "Most Shoe ", "Sizes:", mostSizes.getType(),
				mostSizes.getBrand(), mostSizes.getNums(), "(sizes)");
		System.out.printf("%-1s %7s %-3s, %-1s # %6.2f %-1s\n", "Most On Sale ", "Shoe:", mostSales.getType(),
				mostSales.getBrand(), mostSales.getNums(), "(sizes on sale)");
		System.out.println();
		
		System.out.printf("%-15s %-7s %-18s %1s %12s %12s %13s %12s %12s %18s %18s\n", "Type", "Brand", "Prices (size):",
				"7", "8", "9", "10", "11", "12", "Quantity", "Speciality");
		for (int i = 0; i < shoeInv.size(); i++) {
			myShoe = shoeInv.get(i);
			System.out.printf("%-15s %-20s", myShoe.getType(), myShoe.getBrand());
			for (int size = 7; size <= 12; size++) {
				if (myShoe.getQuantity(size) == 0) {
					System.out.printf("%13s", "");
				} else if (myShoe.getPrice(size) > 100) {
					System.out.printf("%5s%3.2f %1s", "$", myShoe.getPrice(size), ((myShoe.isOnSale(size)) ? "*" : ""));
				} else {
					System.out.printf("%5s %5.2f %1s", "$", myShoe.getPrice(size),
							((myShoe.isOnSale(size)) ? "*" : ""));
				}
			totalQuantity += myShoe.getQuantity(size);
				
			}
			
			System.out.printf("%12s", totalQuantity);
			if (myShoe.getType().equals("Walking")) {
				System.out.printf("%-4s %13s", "", myShoe.getSpecialty());
				System.out.println();
			} else if (myShoe.getType().equals("Hiking")) {
				System.out.printf("%11s %-1s %4s", "", "Traction of", myShoe.getSpecialty());
				System.out.println();
			} else {
				System.out.printf("%11s %-1s %6s", "", "Weight of", myShoe.getSpecialty());
				System.out.println();
			}
			
			totalQuantity = 0;
		}
		System.out.println();
		System.out.println("Shoes with * are on sale!");
	}
}
