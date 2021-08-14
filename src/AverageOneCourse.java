import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

public class AverageOneCourse {
	
	int sum;
	int UnitWeightLOL;
	int assign;
	String[] unitInfo = new String[2];
	
	public AverageOneCourse(String courseName, boolean guest, String user_id, String name, boolean end, String pswrd) {
		String url = "jdbc:mysql://localhost:3306/grades";
		String username = "root";
		String password = "root";
	
		QForUnits q = new QForUnits();
		int nUnits = q.askUnits(courseName); 
		System.out.println("Number of Units: " + nUnits); // system check
		int[] unitUNweighted = new int[nUnits];
		int[] unitweighted = new int[nUnits]; 
		int[] weights = new int[nUnits];
		
		for (int i = 0; i < nUnits; i++) {
			String[] unitInfo = q.unitw(i+1, courseName);
		    try {
		    	UnitWeightLOL = Integer.parseInt(unitInfo[0]);
				System.out.println("Unit weight is " + UnitWeightLOL + "%"); // system check
				if (UnitWeightLOL < 0 || UnitWeightLOL > 100) {
		    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
		    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
		    	    System.exit(0);
				}
	    	 } catch(NumberFormatException e) {
	    	    JOptionPane.showMessageDialog(null, "Input is not a number",
	    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
	    	    System.exit(0);
	    	 }
		    
		    weights[i] = UnitWeightLOL;
			System.out.println("LALALA WEIGHTS[I] IS" + weights[i]);
			int numberofAssignments = q.askAssign(i + 1, courseName); 
			int[] assigns = new int[numberofAssignments];
			String assignN;
			String[] assignName = new String[numberofAssignments];
			String[][] assignmentArray = q.inputAssign(numberofAssignments, i + 1, courseName);
				
		    try {
		    	for(int j = 0; j < numberofAssignments; j++) {
			    	assign = Integer.parseInt(assignmentArray[0][j]);
			    	assignN = assignmentArray[1][j];
			    	assigns[j] = assign;
			    	assignName[j] = assignN;
		    	}
				System.out.println("Unit weight is " + UnitWeightLOL + "%"); // system check
				if (UnitWeightLOL < 0 || UnitWeightLOL > 100) {
		    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
		    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
		    	    System.exit(0);
				}
	    	 } catch(NumberFormatException e) {
	    	    JOptionPane.showMessageDialog(null, "Input is not a number",
	    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
	    	    System.exit(0);
	    	 }
			
			int sumOfAssignments = IntStream.of(assigns).sum();
			int averageAssignments = (int) Math.round((sumOfAssignments / numberofAssignments) * 0.7);
			int InputedUFinal = q.inputUnitFinal(i + 1, courseName);
			int UnitFinaleW = (int) Math.round(InputedUFinal * 0.3);
			unitUNweighted[i] = (int) Math.round(averageAssignments + UnitFinaleW);
			unitweighted[i] = (int) Math.round(unitUNweighted[i] * ((double) weights[i]/100));
			
			if (guest == false) {
		
				try {
					Connection connection = DriverManager.getConnection(url, username, password);
					String sql = "INSERT INTO unit (UNIT_ID, UNIT_W, UNIT_FINAL, UNIT_MARK, USER_ID, COURSE_NAME, UNIT_NAME) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement statement = connection.prepareStatement(sql);
					statement.setInt(1, i + 100);
					statement.setInt(2, weights[i]);
					statement.setInt(3, InputedUFinal);
					statement.setInt(4, unitUNweighted[i]);
					statement.setString(5, user_id);
					statement.setString(6, courseName);
					statement.setString(7, unitInfo[1]);
				
					System.out.println("connection success!!");
				
					int rows = statement.executeUpdate();
					if (rows > 0) {
						System.out.println("A row has been inserted.");
					}
					
					String sql1 = "INSERT INTO assignments (ASSIGN_ID, UNIT_ID, ASSIGN_MARK, USER_ID, COURSE_NAME, UNIT_NAME, ASSIGN_NAME) VALUES (?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement statement1 = connection.prepareStatement(sql1);
				
					statement1.setInt(2, i + 100);
					statement1.setString(4, user_id);
					statement1.setString(5, courseName);
					statement1.setString(6, unitInfo[1]);
	
					for (int j = 0; j < numberofAssignments; j++) {
						statement1.setInt(1, 1000 + j);
						statement1.setInt(3, assigns[j]);
						statement1.setString(7, assignName[j]);
						
						System.out.println("connection success!!");
				
						int rows1 = statement1.executeUpdate();
						if (rows1 > 0) {
							System.out.println("Assignment Update Success!!");
						}
					}
				
					statement1.close();
					connection.close();
				
					statement.close();
					connection.close();
				} catch (SQLException e) {
					System.out.println("& i oop");
					e.printStackTrace();
				}
			}
			}
		sum = IntStream.of(unitweighted).sum();
		double WeightSum = Arrays.stream(weights).sum();
		if (WeightSum != 100.0) {
			JOptionPane.showMessageDialog(null, "Unit weights calculation error",
	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		} else {
			System.out.println("The final mark for this course is: " + sum);
			JOptionPane.showMessageDialog(null, "The final mark for this course is: " + sum);
		}
		if (guest == false && end == true) {
			new UserMain(user_id, name, pswrd, null);
		}
	}
	
	public int returnAverage() {
		return sum;
	}
}
