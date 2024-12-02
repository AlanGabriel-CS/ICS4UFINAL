package defaultP3;

import javax.swing.JOptionPane;
import java.util.ArrayList;
public class Gradebook {
	private String className;
	private ArrayList<Student> students;
	public static void main(String[] args) {
		Gradebook book = new Gradebook("ICS4U");
		book.welcome();
		book.loadGrades();
		book.createGradeBook();
	}
	public void loadGrades() {
		String valStr;
		int nbStudents = 0;
		int nbGrades = 0;
		boolean valid = false;
		while (!valid) {
			try {
				valStr = JOptionPane.showInputDialog("Enter the number of Students:");
				nbStudents = Integer.parseInt(valStr);
				if (nbStudents <= 0) {
					JOptionPane.showMessageDialog(null,
							"Invalid input for number of students. Please enter a valid number.");
					valid = false;
				} else {
					System.out.println("Number of Students: " + nbStudents);
					valid = true;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null,
						"Invalid input for number of students. Please enter a valid number.");
				valid = false;
			}
		}
		valid = false;
		while (!valid) {
			try {
				valStr = JOptionPane.showInputDialog("Enter the number of Grades:");
				nbGrades = Integer.parseInt(valStr);
				if (nbGrades < 0) {
					valid = false;
					JOptionPane.showMessageDialog(null,
							"Invalid input for number of grades. Please enter a valid number.");
				} else {
					System.out.println("Number of grades: " + nbGrades);
					valid = true;
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Invalid input for number of grades. Please enter a valid number.");
				valid = false;
			}
		}
		System.out.println();
		// Load up our Students
		Student student;
		String name = "";
		int grade = 0;
		String gradeLvl;
		String gradeStr;
		for (int i = 0; i < nbStudents; i++) {
			valid = false;
			while (!valid) {
				name = JOptionPane.showInputDialog("Enter Student " + (i + 1) + "'s name:");
				try {
					Integer.parseInt(name);
					JOptionPane.showMessageDialog(null, "Invalid name. Please enter a name without numbers.");
					valid = false;
				} catch (NumberFormatException e) {
					System.out.println("Student " + (i + 1) + ": " + name);
					valid = true;
				}
			}
			valid = false;
			while (!valid) {
				gradeLvl = JOptionPane.showInputDialog("Enter Grade level:");
				try {
					grade = Integer.parseInt(gradeLvl);
					if (grade <= 12 && grade > 0) {
						valid = true;
						System.out.println(name + "'s Grade Level: " + grade);
					} else {
						JOptionPane.showMessageDialog(null, "Invalid input. Enter a Grade between 1-12.");
						valid = false;
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Invalid input for grade level. Please enter a valid number.");
					valid = false;
				}
			}
			student = new Student(name, grade, nbGrades);
			if (nbGrades != 0) {
				System.out.print(name + "'s Marks: ");
			}
			valid = false;
			for (int j = 0; j < nbGrades; j++) {
				valid = false;
				while (!valid) {
					gradeStr = JOptionPane.showInputDialog("Enter mark " + (j + 1) + " for " + name + ":");
					try {
						grade = Integer.parseInt(gradeStr);
						if (grade <= 10 && grade >= 0) {
							valid = true;
							System.out.print(grade);
							if ((j + 1) != nbGrades) {
								System.out.print(", ");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Invalid input. Grade must be between 0-10");
							valid = false;
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Invalid input for grade. Please enter a valid number.");
						valid = false;
					}
				}
				student.setMarks(grade);
			}
			students.add(student);
			System.out.println();
			System.out.println();
		}
	}
	public double classAvg() {
		double sum = 0.0;
		for (int i = 0; i < students.size(); i++) {
			sum += (double) (students.get(i)).getAverage();
		}
		return (double) sum / students.size();
	}
	public double highestAvg() {
		double highest = (students.get(0)).getAverage();
		double temp = 0.0;
		for (int i = 1; i < students.size(); i++) {
			temp = (students.get(i)).getAverage();
			if (highest < temp) {
				highest = temp;
			}
		}
		return highest;
	}
	public double diffFromAvg(double avg, int idx) {
		double myStudent;
		myStudent = students.get(idx).getAverage();
		if (avg > myStudent) {
			return (double) (myStudent - avg);
		} else {
			return (double) (myStudent - avg);
		}
	}
	public void createGradeBook() {
		Student myStudent;
		int len = 21;
		double diff = 0.0;
		System.out.println("ICS4U STUDENT GRADE BOOK");
		System.out.println();
		System.out.printf("%-1s%7.1f%1s\n", "Class Average:", classAvg(), "%");
		System.out.printf("%-1s%7.1f%1s\n\n", "Highest Average:", highestAvg(), "%");
		System.out.printf("%-14s %-18s %-14s %-13s %-12s\n", "Student", "Grade Level", "Average", "Grade",
				"Average +/-");
		for (int i = 0; i < students.size(); i++) {
			myStudent = students.get(i);
			len -= myStudent.getName().length();
			System.out.printf("%-1s%" + len + "d%18.1f%-1s%12s", myStudent.getName(), myStudent.getGradeLevel(),
					myStudent.getAverage(), "%", myStudent.getGrade());
			diff = diffFromAvg(classAvg(), i);
			if (diff >= 0) {
				System.out.printf("%13s%6.1f%-1s\n", "+", diff, "%");
			} else {
				diff *= -1;
				System.out.printf("%13s%6.1f%-1s\n", "-", diff, "%");
			}
			len = 21;
		}
	}
	public Gradebook(String className) {
		this.className = className;
		this.students = new ArrayList<Student>();
	}
	public void welcome() {
		System.out.printf("                                       _______\n"
				+ "                                      /      /,\n"
				+ "                                     /      //\n" + "                                    /______//\n"
				+ "                                   (______(/\n\n%n");
		System.out.println("                       WELCOME TO ICS4U STUDENT GRADE BOOK ");
		System.out.println();
	}
}

