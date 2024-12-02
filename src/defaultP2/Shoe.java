package defaultP2;
public class Shoe {
	private String brand;
	private String type;
	private double price;
	// idx 0, 1, 2, 3, 4, 5
	// Size 7, 8, 9, 10, 11, 12
	private int quantity[];
	public Shoe(String brand, String type) {
		this.brand = brand;
		this.type = type;
		this.quantity = new int[6];
	}
	public Shoe(String brand, String type, double price) {
		this(brand, type);
		this.price = price;
	}
	public boolean isOnSale(int size) {
		return false;
	}
	public double getBasePrice() {
		return this.price;
	}
	public double getPrice(int size) {
		if (isOnSale(size)) {
			return (getBasePrice() - (getBasePrice() * 0.2));
		} else {
			return getBasePrice();
		}
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity(int size) {
		if (size < 7) {
			throw new RuntimeException("Invalid Shoe Size: " + size);
		}
		return quantity[size - 7];
	}
	public void setQuantity(int size, int quantity) {
		if (size < 7) {
			throw new RuntimeException("Invalid Shoe Size: " + size);
		}
		this.quantity[size - 7] = quantity;
	}
	public String getBrand() {
		return brand;
	}
	public String getType() {
		return type;
	}
	public String toString() {
		return "Brand: " + brand + " Type: " + type + " Price: " + getPrice(7) + displayQuantity();
	}
	public String getSpecialty() {
		return "";
	}
	public String displayQuantity() {
		StringBuilder output = new StringBuilder(" Qty: ");
		for (int i = 0; i < quantity.length; i++) {
			output.append(quantity[i]);
			if (i < quantity.length - 1) {
				output.append(",");
			}
		}
		return output.toString();
	}
}
