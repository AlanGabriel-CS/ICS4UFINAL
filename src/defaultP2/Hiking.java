package defaultP2;

public class Hiking extends Shoe {
	private double traction;
	public Hiking(String brand, String type, double price, int traction) {
		super(brand, type, price);
		this.traction = traction;
	}
	public Hiking(String brand, String type, double price) {
		super(brand, type, price);
	}
	public String getSpecialty() {
		return "" + traction;
	}
	public String toString() {
		return "Hiking - Brand: " + this.getBrand() + " Price: " + this.getPrice(0) + " Traction: " + traction + " "
				+ super.displayQuantity();
	}
}


