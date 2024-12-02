package defaultP3;

import java.util.ArrayList;
public class Student{     
	
	//Mark variables
	private static char INVALID = 'I';
	private static char MARK_A = 'A';
	private static char MARK_B = 'B';
	private static char MARK_C = 'C';
	private static char MARK_D = 'D';
	private static char MARK_F = 'F';
	
	// Constructor variables + char;
   private String name;
   private int gradeLvl;
   private ArrayList<Integer> marks;
   private char mark;
  
   // Constructors
   public Student(String name){
       this.name = name; 
       this.mark=INVALID;
      
   }
  
   public Student(String name, int grade) {
   	this(name);
   	this.gradeLvl = grade;
   }
  
  
   public Student(String name, int grade, int nbMarks) {
   	this(name,grade);
   	this.marks = new ArrayList<Integer>(nbMarks);
   }
  
   public String getName() {
   	return name;
   }
   public int getGradeLevel() {
	   return gradeLvl;
   }
 
  //Setter method for marks
  public void setMarks(int marks) {
	   this.marks.add(marks);
	   mark = INVALID;
  }
 
  //Gets average
  public double getAverage() {
	   if (marks.size() == 0) {
          return 0.00;
      }
	  
	   double sum = 0;
	   for(Integer grade : marks) {
		   sum += grade;
	   }
	   return sum/marks.size() * 10;
  }
 
  //Calls getAverage and gives it a mark
  public char getGrade() {
	   if (marks.size() == 0) {
          return INVALID;
      }
	  
	   if(mark == INVALID) {
		   double avg = getAverage();
		  
		   if(avg>=80) {
			   mark = MARK_A;
		   } else if(avg>=70) {
			   mark = MARK_B;
		   } else if(avg>=60) {
			   mark = MARK_C;
		   } else if(avg>=50) {
			   mark = MARK_D;
		   } else {
			   mark = MARK_F;
		   }
	   }
	   return mark;
  }
 
 
 
}
