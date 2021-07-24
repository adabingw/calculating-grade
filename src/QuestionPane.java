import java.util.stream.IntStream;

import javax.swing.JOptionPane;

public class QuestionPane {

	public int AskUnits() {
		String NumberUnits = JOptionPane.showInputDialog(null, "How many units are in this course?");
		if (NumberUnits != null) {
		    try {
		    	   int NumberofUnits = Integer.parseInt(NumberUnits); 
					if (NumberofUnits < 0 || NumberofUnits > 100) {
			    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			    	    System.exit(0);
					}
				    return NumberofUnits;
		    	 } catch(NumberFormatException e) {
		    	    JOptionPane.showMessageDialog(null, "Input is not a number",
		    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
		    	    System.exit(0);
		    	 } 
		} else System.exit(0);
		 return 0; 
	} // all clear
	
	public double Unitw(int UnitNo) {
			String UnitwLOL = JOptionPane.showInputDialog(null, "For unit " + UnitNo + ", please type in the weighting (in decimal form): ");
			if (UnitwLOL != null) {
			    try {
				    	double UnitWeightLOL = Double.parseDouble(UnitwLOL);
						System.out.println("Unit weight is " + UnitWeightLOL); // system check
						if (UnitWeightLOL < 0 || UnitWeightLOL > 100) {
				    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
				    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
				    	    System.exit(0);
						}
					    return UnitWeightLOL;
			    	 } catch(NumberFormatException e) {
			    	    JOptionPane.showMessageDialog(null, "Input is not a number",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			    	    System.exit(0);
			    	 } 
			} else System.exit(0);
		 return 0;
	} // all clear
	
	public int AskAssign() {
		String NoAssign = JOptionPane.showInputDialog(null, "How many assignments are in this unit?");
		if (NoAssign != null) {
		    try {
		    	   int AssignNo = Integer.parseInt(NoAssign); 
				    System.out.println("Number of Assignments: " + AssignNo); // system check
					if (AssignNo < 0 || AssignNo > 100) {
			    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			    	    System.exit(0);
					}
				    return AssignNo;
		    	 } catch(NumberFormatException e) {
		    	   System.out.println("input is not an int value"); 
		    	    JOptionPane.showMessageDialog(null, "Input is not a number",
		    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
		    	    System.exit(0);
		    	 } 
		} else System.exit(0);  
		return 0;
	} // all clear
	
	public int InputAssign(int Assign) {
		System.out.println("Assign is " + Assign); // system check
		String[] Assignments = new String[Assign]; 
		int[] AssignmentsLOL = new int[Assign]; 
		for (int i = 0; i < Assign; i++) {
			System.out.println("For Assignment " + (i+1)); // system check
			Assignments[i] = JOptionPane.showInputDialog(null, "Mark of Assignment "+ (i+1));
			if (Assignments[i] != null) {
			    try {
					AssignmentsLOL[i] = Integer.parseInt(Assignments[i]);
				    System.out.println("Assignment mark is " + AssignmentsLOL[i]); // system check
					if (AssignmentsLOL[i] < 0 || AssignmentsLOL[i] > 100) {
				    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
				    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
				    	    System.exit(0);
						}
			    	 } catch(NumberFormatException e) {
			    	   System.out.println("input is not an int value"); 
			    	    JOptionPane.showMessageDialog(null, "Input is not a number",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			    	    System.exit(0);
			    	 }
				} else System.exit(0);
		}
 		int sum = IntStream.of(AssignmentsLOL).sum();
 		int average = (int) ((sum / Assign) * 0.7);
 		return average;
	}
	
	public int InputUnitFinal() {
			String UnitF = JOptionPane.showInputDialog(null, "Input your unit final mark: ");
			if (UnitF != null) {				
			    try {
				    int UnitFinale = Integer.parseInt(UnitF);
				    System.out.println("Unit final: " + UnitFinale); // system check
					if (UnitFinale < 0 || UnitFinale > 100) {
				    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
				    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
				    	    System.exit(0);
						}
				    int UnitFinaleW = (int) (UnitFinale * 0.3);
					return UnitFinaleW;			    	 
				} catch(NumberFormatException e) {
			        JOptionPane.showMessageDialog(null, "Input is not a number",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			        System.exit(0);
			    	 } 
			} else System.exit(0);
			return 0;
	}
	
}
