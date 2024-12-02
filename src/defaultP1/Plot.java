package defaultP1;
public class Plot {
	private String cropType;
	private int yield;
	private String special;
	public Plot(String cropType, int yield) {
		this.cropType = cropType;
		this.yield = yield;
		special = "";
	}
	public Plot(String cropType, int yield, String special) {
		this(cropType, yield);
		this.setSpecial(special);
	}
	public String getCropType() {
		return cropType;
	}
	public int getYield() {
		return yield;
	}
	public String getSpecial() {
		return special;
	}
	public void setSpecial(String special) {
		this.special = special;
	}
	public String toString(Plot plot[][], int len) {
		int space = len - this.cropType.length();
		space += 5 - special.length();
		if (this.yield < 10) {
			return String.format("%5s%-1s%" + space + "s", "|", this.cropType + this.special, "0" + this.yield + "|");
		}
		return String.format("%5s%-1s%" + space + "s", "|", this.cropType + this.special, this.yield + "|");
	}
	public String toString() {
		return this.cropType + " " + this.yield;
	}
}

