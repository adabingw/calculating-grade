import javax.swing.JOptionPane;

public class qForUnits {

	public int AskUnits(String courseName) {
		JLabel label = new JLabel("How many units are in this course?");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		String NumberUnits = JOptionPane.showInputDialog(null, label, courseName, JOptionPane.PLAIN_MESSAGE);
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
		} else 	System.exit(0);
		return 0; 
	} // all clear
	
	public int Unitw(int UnitNo, String courseName) {
		JLabel label = new JLabel("For unit " + UnitNo + ", please type in the weighting (discard the percent sign): ");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		String UnitwLOL = JOptionPane.showInputDialog(null, label, courseName, JOptionPane.PLAIN_MESSAGE);
		if (UnitwLOL != null) {
			try {
			    	int UnitWeightLOL = Integer.parseInt(UnitwLOL);
				System.out.println("Unit weight is " + UnitWeightLOL + "%"); // system check
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
			} else 	System.exit(0);
		 return 0;
			
	} // all clear
	
	public int AskAssign(int UnitNo, String courseName) {
		JLabel label = new JLabel("How many assignments are in this unit?");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		String NoAssign = JOptionPane.showInputDialog(null, label, courseName + " Unit " + UnitNo, JOptionPane.PLAIN_MESSAGE);
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
	
	public int[] InputAssign(int Assign, int UnitNo, String courseName) {
		System.out.println("Assign is " + Assign); // system check
		String[] Assignments = new String[Assign]; 
		int[] AssignmentsLOL = new int[Assign]; 
				
		for (int i = 0; i < Assign; i++) {
			System.out.println("For Assignment " + (i+1)); // system check
			JLabel label = new JLabel("Mark of Assignment "+ (i+1));
			label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			Assignments[i] = JOptionPane.showInputDialog(null, label, courseName + " Unit " + UnitNo, JOptionPane.PLAIN_MESSAGE);						
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
		return AssignmentsLOL;
	}
	
	public int InputUnitFinal(int UnitNo, String courseName) {
			JLabel label = new JLabel("Input your unit final mark: ");
			label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			label.setHorizontalAlignment(SwingConstants.CENTER);
			String UnitF = JOptionPane.showInputDialog(null, label, courseName + " Unit " + UnitNo, JOptionPane.PLAIN_MESSAGE);
			if (UnitF != null) {				
			    try {
				    int UnitFinale = Integer.parseInt(UnitF);
				    System.out.println("Unit final: " + UnitFinale); // system check
					if (UnitFinale < 0 || UnitFinale > 100) {
				    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
				    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
				    	    System.exit(0);
						}
					return UnitFinale;			    	 
				} catch(NumberFormatException e) {
			        JOptionPane.showMessageDialog(null, "Input is not a number",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			        System.exit(0);
			    	 } 
			} else System.exit(0);
			return 0;
	}
	
}
