import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

public class Main {
	public static void main (String[] args) {
		QuestionPane Q = new QuestionPane();
		int NUnits = Q.AskUnits(); 
		System.out.println("Number of Units: " + NUnits); // system check
		int[] unitweighted = new int[NUnits]; 
		double[] Weights = new double[NUnits];
		for (int i = 0; i < NUnits; i++) {
			double Weight = Q.Unitw(i+1); 
			Weights[i] = Weight;
			int NumberofAssignments = Q.AskAssign(); 
			int AssignmentAvg = Q.InputAssign(NumberofAssignments);
			unitweighted[i] = (int) ((AssignmentAvg + Q.InputUnitFinal()) * Weight);
		}
 		int sum = IntStream.of(unitweighted).sum();
		double WeightSum = Arrays.stream(Weights).sum();
		if (WeightSum != 1.0) {
			JOptionPane.showMessageDialog(null, "Unit weights calculation error",
    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
    	    System.exit(0);
		} else {
		System.out.print("The final mark for this course is: " + sum);
		JOptionPane.showMessageDialog(null, "The final mark for this course is: " + sum);
		}
	}
}