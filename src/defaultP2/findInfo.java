package defaultP2;

public class findInfo {
	private double nums;
	private String brand;
	private String type;
	
	public findInfo(String brand, String type, double nums) {
		this.brand = brand;
		this.type = type;
		this.nums = nums;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setNums(int nums) {
		this.nums = nums;
	}
	
	public String getBrand() {
		return this.brand;
	}
	
	public String getType() {
		return this.type;
	}
	
	public double getNums() {
		return this.nums;
	}
	
	
}

