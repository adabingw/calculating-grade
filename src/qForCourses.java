import javax.swing.JOptionPane;

public class qForCourses {
	
	public int AskCourses() {
		String NumberCourses = JOptionPane.showInputDialog(null, "How many courses do you want to calculate for?");
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
		String NameofCourse = JOptionPane.showInputDialog(null, "For course " + CourseNo + ", please type in the course name: ");
		String s = null;
		if (NameofCourse != null) {
			return NameofCourse;
		} else 	System.exit(0);
		return s;
	} // all clear
}
