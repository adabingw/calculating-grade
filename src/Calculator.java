import java.util.Scanner;

public class Calculator {
	
	@SuppressWarnings({ "resource" })
	int UnitGradeCalc() {
		int NumberofAssignments;
		int AssignmentTotal = 0;
		int AssignmentAverage;
		double UnitCourseMark;
		Scanner sc=new Scanner(System.in);
		System.out.print("How many assignments do you wish to report?");
		NumberofAssignments = sc.nextInt();
		int[] AssignmentGrade = new int[NumberofAssignments];
		System.out.println("Type in the marks for each assignment:");
		for(int i = 0; i < NumberofAssignments; i++)  {
			System.out.print(i+1 + ".");
			AssignmentGrade[i]=sc.nextInt();  
		}
	    for(int i = 0; i < NumberofAssignments; i++) {
	        AssignmentTotal += AssignmentGrade[i];
	    }
	    AssignmentAverage = AssignmentTotal / NumberofAssignments;
	    UnitCourseMark = (AssignmentAverage * 0.7);
	    
	    int UnitFinal;
	    double UnitFinalWeight;
	    System.out.println("Type your unit final mark for this unit.\n");
			Scanner sc1=new Scanner(System.in);
			UnitFinal = sc1.nextInt();	    
		UnitFinalWeight = (UnitFinal * 0.3);

	    double UnitMark;
	    UnitMark = UnitCourseMark + UnitFinalWeight;
	    System.out.println("Your mark for this unit is: " + UnitMark);
		return (int) UnitMark;
	}
	
//	@SuppressWarnings("resource")
//	int AskNumberofUnits () {
//		int NumberofUnits;	
//		Scanner sc2 = new Scanner(System.in);
//		System.out.print("How many units are in your course?");
//		NumberofUnits = sc2.nextInt();
//		return NumberofUnits;
	}
	
/*	@SuppressWarnings({ "resource" })
	int[] CourseGradeCalc(int units) {
		Scanner sc3 = new Scanner(System.in);
		System.out.println("Type in the weighting for each unit:");
		int[] UnitWeighting = new int[units];
		for(int i = 0; i < units; i++)  {
			System.out.print(i+1 + ".");
			UnitWeighting[i] = sc3.nextInt();  
		}
		return UnitWeighting[units];
	}
*/	
// }