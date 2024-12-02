package defaultPackage;
import javax.swing.JOptionPane;
public class WhatIsMyTemp {
	public static void main(String[] args) {
		String[] cities = new String[3];
		int[] cityTemps = new int[3];
		int numOfCity = 0;
		printWelcome();
		inputCity(cities, cityTemps, numOfCity);
		numOfCity = cityTemps.length;
		double avgTempFin = averageTemp(cityTemps, numOfCity);
		System.out.printf("\n%s%1.2f%s \n", "Average Temperature: ", avgTempFin, "°C");
		int lowTempFin = lowTemp(cityTemps, numOfCity);
		System.out.printf("%s%d%s \n", "Lowest Temperature: ", lowTempFin, "°C");
		int highTempFin = highTemp(cityTemps, numOfCity);
		System.out.printf("%s%d%s \n", "Highest Temperature: ", highTempFin, "°C");
		String[] categoryTempFin = tempCategory(cities, cityTemps, numOfCity);
		System.out.printf("%s \n%s \n%s \n", categoryTempFin[0], categoryTempFin[1], categoryTempFin[2]);
		String longestCityFin = longestName(cities, numOfCity);
		System.out.printf("%s%s\n", "Longest City Name: ", longestCityFin);
		String mostVowelsFin = mostVowels(cities, numOfCity);
		System.out.printf("%s%s\n", "Most Vowels City: ", mostVowelsFin);
	}
	public static void printWelcome() {
		System.out.println("Welcome to City & Temperature!");
		String cityArt = 
				  "                       .|\n" 
				+ "                       | |\n"
				+ "                       |'|            ._____\n"
				+ "               ___    |  |            |.   |' .---\''|\n"
				+ "       _    .-'   '-. |  |     .--'|  ||   | _|    |\n"
				+ "    .-'|  _.|  |    ||   '-__  |   |  |    ||      |\n"
				+ "    |' | |.    |    ||       | |   |  |    ||      |\n"
				+ "____|  '-'     '    ''       '-'   '-.'    '`      |____\n"
				+ " ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
		System.out.println(cityArt);
	}
	public static void inputCity(String[] cities, int[] cityTemps, int numOfCity) {
		String inputCity = "";
		JOptionPane.showMessageDialog(null, "Please Enter Your Three Cities And Their Temperatures.");
		for (int i = 0; i < 3; i++) {
			boolean valid = false;
			while (!valid) {
				try {
					inputCity = JOptionPane.showInputDialog(null, "Enter the name of City " + (i + 1));
					Integer.parseInt(inputCity);
					JOptionPane.showMessageDialog(null, "City names cannot contain numbers.");
				} catch (NumberFormatException e) {
					if (!inputCity.trim().isEmpty()) {
						cities[i] = inputCity;
						valid = true;
					} else {
						JOptionPane.showMessageDialog(null, "City names cannot be empty.");
					}
				}
			}
			valid = false;
			while (!valid) {
				try {
					String temperatureInput = JOptionPane.showInputDialog(null,
							"Enter the temperature for " + cities[i]);
					cityTemps[i] = Integer.parseInt(temperatureInput);
					valid = true;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Please enter a valid temperature as an integer.");
				}
			}
		}
		for (int i = 0; i < 3; i++) {
			System.out.println("City: " + cities[i] + ", Temperature: " + cityTemps[i] + "°C");
		}
	}
	public static double averageTemp(int[] cityTemps, int numOfCity) {
		double sumTemp = 0;
		double avgTemp = 0;
		for (int i = 0; i < numOfCity; i++) {
			sumTemp += cityTemps[i];
		}
		avgTemp = (double) sumTemp / numOfCity;
		return avgTemp;
	}
	public static int lowTemp(int cityTemps[], int numOfCity) {
		int lowTemp = cityTemps[0];
		for (int i = 0; i < numOfCity; i++) {
			if (cityTemps[i] < lowTemp) {
				lowTemp = cityTemps[i];
			}
		}
		return lowTemp;
	}
	public static int highTemp(int cityTemps[], int numOfCity) {
		int highTemp = cityTemps[0];
		for (int i = 0; i < numOfCity; i++) {
			if (cityTemps[i] > highTemp) {
				highTemp = cityTemps[i];
			}
		}
		return highTemp;
	}
	public static String[] tempCategory(String cities[], int cityTemps[], int numOfCity) {
		// 0 -10 -> Cold; 10+ - 20 -> Nice; 20+ Perfect
		String[] finCategory = new String[3];
		for (int i = 0; i < numOfCity; i++) {
			if (cityTemps[i] <= 10) {
				finCategory[i] = cities[i] + ": Cold";
			} else {
				if (cityTemps[i] >= 10 && cityTemps[i] <= 20) {
					finCategory[i] = cities[i] + ": Nice";
				} else {
					if (cityTemps[i] >= 20) {
						finCategory[i] = cities[i] + ": Perfect";
					}
				}
			}
		}
		return finCategory;
	}
	public static String longestName(String cities[], int numOfCity) {
		String longestCity = "";
		for (int i = 0; i < numOfCity; i++) {
			if (longestCity.length() < cities[i].length()) {
				longestCity = cities[i];
			}
		}
		return longestCity;
	}
	public static String mostVowels(String cities[], int numOfCity) {
		String vowel = "aeiou";
		int finCount = 0;
		String mostVow = "";
		for (int i = 0; i < numOfCity; i++) {
			int countVow = 0;
			String city = cities[i].toLowerCase();
			for (int j = 0; j < city.length(); j++) {
				char check = city.charAt(j);
				if (vowel.indexOf(check) != -1) {
					countVow++;
				}
			}
			if (countVow > finCount) {
				finCount = countVow;
				mostVow = cities[i];
			}
		}
		return mostVow;
	}
}
