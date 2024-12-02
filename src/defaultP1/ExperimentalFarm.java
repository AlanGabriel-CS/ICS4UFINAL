package defaultP1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
public class ExperimentalFarm {
	public Plot[][] plot;
	ArrayList<String[]> rawCrop = new ArrayList<String[]>(8);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExperimentalFarm farm = new ExperimentalFarm();
		farm.welcome();
		farm.loadFarm("./src/DefaultP/U4FarmsCrops.txt");
		System.out.printf("%1s%8s\n", "Highest Yield of Farm", "**");
		System.out.printf("%1s%9s\n", "Lowest Yield of Farm", "--");
		System.out.printf("%1s%2s\n", "Highest Yield of Crop Type", "*");
		System.out.printf("%1s%3s\n", "Lowest Yield of Crop Type", "-");
		farm.printPlot();
		ArrayList<String> diffCrops = farm.diffCropTypes();
		String cropType;
		int col = 0;
		boolean valid = false;
		cropType = JOptionPane.showInputDialog("Which Crop Type Would You Like To Compare?");
		while (!valid) {
			col = Integer.parseInt(JOptionPane.showInputDialog("Which column would you like to compare to"));
			if (col > farm.plot[0].length - 1) {
				JOptionPane.showMessageDialog(null, "Column isn't found on the farm!");
			} else {
				valid = true;
			}
		}
		valid = false;
		for (int i = 0; i < diffCrops.size(); i++) {
			if (cropType.equals(diffCrops.get(i))) {
				valid = true;
				break;
			}
		}
		int len = cropType.length();
		if (valid) {
			System.out.println();
			System.out.printf("%-1s%1s%" + (len + 4) + "s%1s\n", "", "Highest Yield of", cropType + ": ",
					farm.getHighestYield(cropType));
			System.out.printf("%-1s%1s%" + (len + 5) + "s%1s\n", "", "Lowest Yield of", cropType + ": ",
					farm.getLowestYield(cropType));
			System.out.printf("%-1s%1s%" + (len + 4) + "s%.2f\n", "", "Average Yield of", cropType + ": ",
					farm.cropAvgYield(cropType));
			System.out.printf("%-1s%1s%" + (len + 6) + "s", "", "Sorted List of", cropType + ": ");
			Plot[] sorted = farm.sortedCrop(cropType);
			for (int i = 0; i < sorted.length; i++) {
				if (i + 1 == sorted.length) {
					System.out.print(sorted[i]);
				} else {
					System.out.print(sorted[i] + ", ");
				}
			}
			System.out.printf("\n%-1s%1s%" + (len + 7) + "s%1s\n", "", "Same Crops in", " column " + col + ": ",
					farm.sameCrop(col) ? "The crops are the same" : "The crops are not the same");
			System.out.printf("%-1s%1s%" + (len + 8) + "s%1s\n", "", "Max Yield of", "farm: ", farm.maxYield());
			System.out.printf("%-1s%1s%" + (len + 8) + "s%1s\n", "", "Min Yield of", "farm: ", farm.minYield());
			System.out.printf("%-1s%1s%" + (len + 4) + "s%1.2f\n", "", "Average Yield of", cropType + ": ",
					farm.farmPlotsAvgYield());
		} else {
			System.out.println();
			System.out.printf("%-1s%1s%" + (len + 4) + "s%1s\n", "", "Highest Yield of", cropType + ": ",
					cropType + " is not found in farm");
			System.out.printf("%-1s%1s%" + (len + 5) + "s%1s\n", "", "Lowest Yield of", cropType + ": ",
					cropType + " is not found in farm");
			System.out.printf("%-1s%1s%" + (len + 4) + "s%1s\n", "", "Average Yield of", cropType + ": ", "N/A");
			System.out.printf("%-1s%1s%" + (len + 6) + "s%1s\n", "", "Sorted List of", cropType + ": ",
					"No crops to sort");
			System.out.printf("%-1s%1s%" + (len + 7) + "s%1s\n", "", "Same Crops in", " column " + col + ": ",
					farm.sameCrop(col) ? "The crops are the same" : "The crops are not the same");
			System.out.printf("%-1s%1s%" + (len + 8) + "s%1s\n", "", "Max Yield of", "farm: ", farm.maxYield());
			System.out.printf("%-1s%1s%" + (len + 8) + "s%1s\n", "", "Min Yield of", "farm: ", farm.minYield());
			System.out.printf("%-1s%1s%" + (len + 4) + "s%1.2f\n", "", "Average Yield of", cropType + ": ",
					farm.farmPlotsAvgYield());
		}
	}
	public void welcome() {
		System.out.println("                           _.-^-._    .--.   ");
		System.out.println("                        .-'   _   '-. |__|   ");
		System.out.println("                       /     |_|     \\|  |   ");
		System.out.println("                      /               \\  |   ");
		System.out.println("                     /|     _____     |\\ |   ");
		System.out.println("                      |    |==|==|    |  |   ");
		System.out.println("  |---|---|---|---|---|    |--|--|    |  |   ");
		System.out.println("  |---|---|---|---|---|    |==|==|    |  |   ");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println();
		System.out.println("           Welcome to Alan's Farm!");
		System.out.println();
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
	}
	public void loadFarm(String cropFile) {
		String line;
		String values[] = null;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(cropFile));
			while ((line = reader.readLine()) != null) {
				values = line.split(",");
				rawCrop.add(values);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Initialize Plot
		String cropRow[] = rawCrop.get(0);
		int nbCols = cropRow.length / 2;
		plot = new Plot[rawCrop.size()][nbCols];
		this.loadCrops(rawCrop);
	}
	public void loadCrops(ArrayList<String[]> rawCrop) {
		String cropRow[] = null;
		int nbCol = 0;
		int idx = 2;
		for (int i = 0; i < plot.length; i++) {
			cropRow = rawCrop.get(i);
			for (int j = 0; j < plot[0].length; j++) {
				try {
					nbCol = Integer.parseInt(cropRow[(j * idx) + 1]);
					plot[i][j] = new Plot(cropRow[j * idx], nbCol);
				} catch (NumberFormatException e) {
					System.out.println("Column " + (1 + j) + " is not a number - " + e.getMessage());
					System.exit(0);
				}
			}
		}
		specialCrops();
	}
	public void printPlot() {
		System.out.println();
		int highestLen = longestLenCol();
		int space = 0;
		for (int i = 0; i < plot[0].length; i++) {
			if (highestLen % 2 == 0) {
				space = (highestLen + 5) / 2 + 8;
				System.out.printf("%" + space + "s%" + (space - 10) + "s", i, "");
			} else {
				space = (highestLen + 4) / 2 + 8;
				System.out.printf("%" + space + "s%" + (space - 9) + "s", i, "");
			}
		}
		System.out.println();
		for (int i = 0; i < plot.length; i++) {
			System.out.printf("%2s", "");
			for (int j = 0; j < plot[0].length; j++) {
				System.out.printf("%5s", "");
				for (int k = 0; k < highestLen + 4; k++) {
					System.out.print("~");
				}
				System.out.printf("%1s", "");
			}
			System.out.println();
			System.out.printf("%2s", i);
			for (int j = 0; j < plot[0].length; j++) {
				System.out.print(plot[i][j].toString(plot, highestLen));
			}
			System.out.println();
			System.out.printf("%2s", "");
			for (int j = 0; j < plot[0].length; j++) {
				System.out.printf("%5s", "");
				for (int k = 0; k < highestLen + 4; k++) {
					System.out.print("~");
				}
				System.out.printf("%1s", "");
			}
			System.out.println();
			System.out.println();
		}
	}
	public int longestLenCol() {
		int highestLen = 0;
		int tempLen = 0;
		for (int i = 0; i < plot[0].length; i++) {
			for (int j = 0; j < plot.length; j++) {
				tempLen = plot[j][i].getCropType().length();
				if (tempLen > highestLen) {
					highestLen = tempLen;
				}
			}
		}
		return highestLen;
	}
	// different crop types
	public ArrayList<String> diffCropTypes() {
		ArrayList<String> cropTypes = new ArrayList<String>();
		boolean found = false;
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[0].length; j++) {
				String cropType = plot[i][j].getCropType();
				found = false;
				for (int k = 0; k < cropTypes.size(); k++) {
					if (cropTypes.get(k).equals(cropType)) {
						found = true;
						break;
					}
				}
				if (!found) {
					cropTypes.add(cropType);
				}
			}
		}
		return cropTypes;
	}
	// reinitialize with special crops - high/highest yield and low/lowest yield
	public void specialCrops() {
		ArrayList<String> cropTypes = diffCropTypes();
		Plot[] highCrop = new Plot[cropTypes.size()];
		Plot[] lowCrop = new Plot[cropTypes.size()];
		for (int i = 0; i < cropTypes.size(); i++) {
			highCrop[i] = getHighestYield(cropTypes.get(i));
			lowCrop[i] = getLowestYield(cropTypes.get(i));
		}
		int max = maxYield();
		int min = minYield();
		for (int i = 0; i < cropTypes.size(); i++) {
			for (int j = 0; j < plot.length; j++) {
				for (int k = 0; k < plot[j].length; k++) {
					if (plot[j][k].getCropType().equals(highCrop[i].getCropType())
							&& plot[j][k].getYield() == highCrop[i].getYield()) {
						if (plot[j][k].getYield() == max) {
							plot[j][k] = new Plot(plot[j][k].getCropType(), plot[j][k].getYield(), "**");
						} else {
							plot[j][k] = new Plot(highCrop[i].getCropType(), highCrop[i].getYield(), "*");
						}
					}
					if (plot[j][k].getCropType().equals(lowCrop[i].getCropType())
							&& plot[j][k].getYield() == lowCrop[i].getYield()) {
						if (plot[j][k].getYield() == min) {
							plot[j][k] = new Plot(lowCrop[i].getCropType(), lowCrop[i].getYield(), "--");
						} else {
							if (plot[j][k].getCropType().equals(lowCrop[i].getCropType())
									&& plot[j][k].getCropType().equals(highCrop[i].getCropType())
									&& plot[j][k].getYield() == lowCrop[i].getYield()
									&& plot[j][k].getYield() == highCrop[i].getYield()) {
								plot[j][k] = new Plot(lowCrop[i].getCropType(), lowCrop[i].getYield());
							} else {
								plot[j][k] = new Plot(lowCrop[i].getCropType(), lowCrop[i].getYield(), "-");
							}
						}
					}
				}
			}
		}
	}
	// MAIN METHODS
	public Plot getHighestYield(String cropType) {
		Plot highPlot = null;
		Plot tempPlot = null;
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length; j++) {
				tempPlot = plot[i][j];
				if (tempPlot.getCropType().equals(cropType)) {
					if (highPlot == null || tempPlot.getYield() > highPlot.getYield()) {
						highPlot = tempPlot;
					}
				}
			}
		}
		return highPlot;
	}
	public Plot getLowestYield(String cropType) {
		Plot lowPlot = null;
		Plot tempPlot = null;
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[i].length; j++) {
				tempPlot = plot[i][j];
				if (tempPlot.getCropType().equals(cropType)) {
					if (lowPlot == null || tempPlot.getYield() < lowPlot.getYield()) {
						lowPlot = tempPlot;
					}
				}
			}
		}
		return lowPlot;
	}
	public boolean sameCrop(int col) {
		String checkCrop = plot[0][col].getCropType();
		String tempCrop;
		for (int i = 0; i < plot.length; i++) {
			tempCrop = plot[i][col].getCropType();
			if (!checkCrop.equals(tempCrop)) {
				return false;
			}
		}
		return true;
	}
	public int maxYield() {
		int high = plot[0][0].getYield();
		int temp = 0;
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[0].length; j++) {
				temp = plot[i][j].getYield();
				if (high < temp) {
					high = temp;
				}
			}
		}
		return high;
	}
	public int minYield() {
		int low = plot[0][0].getYield();
		int temp = 0;
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[0].length; j++) {
				temp = plot[i][j].getYield();
				if (low > temp) {
					low = temp;
				}
			}
		}
		return low;
	}
	public double cropAvgYield(String cropType) {
		double sum = 0;
		int cnt = 0;
		Plot myPlot = null;
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[0].length; j++) {
				myPlot = plot[i][j];
				if (myPlot.getCropType().equals(cropType)) {
					sum += myPlot.getYield();
					cnt++;
				}
			}
		}
		return sum / cnt;
	}
	public double farmPlotsAvgYield() {
		int cnt = 0;
		double sum = 0;
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[0].length; j++) {
				sum += plot[i][j].getYield();
				cnt++;
			}
		}
		return sum / cnt;
	}
	public Plot[] sortedCrop(String cropType) {
		String tempCrop;
		int cnt = 0;
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[0].length; j++) {
				tempCrop = plot[i][j].getCropType();
				if (tempCrop.equals(cropType)) {
					cnt++;
				}
			}
		}
		Plot myPlot[] = new Plot[cnt];
		cnt = 0;
		for (int i = 0; i < plot.length; i++) {
			for (int j = 0; j < plot[0].length; j++) {
				if (plot[i][j].getCropType().equals(cropType)) {
					myPlot[cnt] = plot[i][j];
					cnt++;
				}
			}
		}
		Plot tempPlot = null;
		for (int i = 0; i < myPlot.length; i++) {
			for (int j = i; j < myPlot.length; j++) {
				if (myPlot[i].getYield() > myPlot[j].getYield()) {
					tempPlot = myPlot[i];
					myPlot[i] = myPlot[j];
					myPlot[j] = tempPlot;
				}
			}
		}
		return myPlot;
	}
}

