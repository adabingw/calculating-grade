import javax.swing.JOptionPane;

public class qForCourses {
	
	public int AskCourses() {
		JLabel label = new JLabel("How many courses do you want to calculate for?");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		String NumberCourses = JOptionPane.showInputDialog(null, label, " ", JOptionPane.PLAIN_MESSAGE);
		if (NumberCourses != null) {
		    try {
		    	   int NumberofCourses = Integer.parseInt(NumberCourses); 
					if (NumberofCourses < 0 || NumberofCourses > 100) {
			    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			    	    System.exit(0);
					}
				    return NumberofCourses;
		    	 } catch(NumberFormatException e) {
		    	    JOptionPane.showMessageDialog(null, "Input is not a number",
		    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
		    	    System.exit(0);
		    	 } 
		} else 	System.exit(0);
		return 0; 
	} // all clear
	
	public String CourseName(int CourseNo) {
		JLabel label = new JLabel("For course " + CourseNo + ", please type in the course name: ");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		String NameofCourse = JOptionPane.showInputDialog(null, label, " ", JOptionPane.PLAIN_MESSAGE);
		String s = null;
		if (NameofCourse != null) {
			return NameofCourse;
		} else 	System.exit(0);
		return s;
	} // all clear
}
