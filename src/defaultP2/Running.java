package defaultP2;

public class Running extends Shoe {
	private double weight;
	public Running(String brand, String type, double price) {
		super(brand, type, price);
	}
	public Running(String brand, String type, double price, double weight) {
		this(brand, type, price);
		this.weight = weight;
	}
	public boolean isOnSale(int size) {
		if (size >= 7 && size <= 12) {
			int qty = getQuantity(size);
			if (qty > 3 && !getBrand().equals("Saucony") && !getBrand().equals("Asics")) {
				return true;
			}
		}
		return false;
	}
	public String toString() {
		return "Running - Brand: " + this.getBrand() + " Price: " + this.getPrice(0) + " Weight: " + weight + " "
				+ super.displayQuantity();
	}
	public double getPrice(int size) {
		if (isOnSale(size)) {
			return getBasePrice() - (getBasePrice() * 0.3);
		} else {
			return getBasePrice();
		}
	}
	public String getSpecialty() {
		return "" + weight;
	}
}

