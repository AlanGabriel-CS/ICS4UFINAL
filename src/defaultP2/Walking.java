package defaultP2;

public class Walking extends Shoe {
	boolean isGortex;
	public Walking(String brand, String type, double price) {
		super(brand, type, price);
	}
	public Walking(String brand, String type, double price, boolean isGortex) {
		this(brand, type, price);
		this.isGortex = isGortex;
	}
	public boolean isOnSale(int size) {
		if (size >= 7 && size <= 12) {
			int qty = getQuantity(size);
			if (qty > 2 && !getBrand().equals("Puma")) {
				return true;
			}
		}
		return false;
	}
	public String toString() {
		return "Walking - Brand: " + this.getBrand() + " Price: " + this.getBasePrice() + " Is Gortex: "
				+ ((isGortex ? "Yes" : "No")) + " " + super.displayQuantity();
	}
	public boolean getGortex() {
		return isGortex;
	}
	public String getSpecialty() {
		return (isGortex ? "Gortex" : "");
	}
}


