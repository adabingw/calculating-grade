import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

public class Main {
	public static void main (String[] args) {
		QuestionPane Q = new QuestionPane();
		int NUnits = Q.AskUnits(); 
		System.out.println("Number of Units: " + NUnits); // system check
				int[] unitUNweighted = new int[NUnits];
		int[] unitweighted = new int[NUnits]; 
		int[] Weights = new int[NUnits];
		
		for (int i = 0; i < NUnits; i++) {
			int Weight = Q.Unitw(i+1); 
			Weights[i] = Weight;
			int NumberofAssignments = Q.AskAssign(); 
			int[] AssignmentArray = Q.InputAssign(NumberofAssignments);
				int sumOfAssignments = IntStream.of(AssignmentArray).sum();
				int averageAssignments = (int) Math.round((sumOfAssignments / NumberofAssignments) * 0.7);
			int InputedUFinal = Q.InputUnitFinal();
		    int UnitFinaleW = (int) Math.round(InputedUFinal * 0.3);
			unitUNweighted[i] = (int) Math.round(averageAssignments + UnitFinaleW);
			unitweighted[i] = (int) Math.round(unitUNweighted[i] * ((double) Weight/100));
			
			try {
				Connection connection = DriverManager.getConnection(url, username, password);
				String sql = "INSERT INTO unit (UNIT_NO, UNIT_W, UNIT_FINAL, UNIT_MARK) VALUES (?, ?, ?, ?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setInt(1, i + 100);
				statement.setInt(2, Weights[i]);
				statement.setInt(3, InputedUFinal);
				statement.setInt(4, unitUNweighted[i]);
				
				System.out.println("connection success!!");
				
				int rows = statement.executeUpdate();
				if (rows > 0) {
					System.out.println("A row has been inserted.");
				}
				
				String sql1 = "INSERT INTO assignments (ASSIGN_NO, UNIT_NO, ASSIGN_MARK) VALUES (?, ?, ?)";
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				
				statement1.setInt(2, i + 100);

				for (int j = 0; j < NumberofAssignments; j++) {
					statement1.setInt(1, j);
					statement1.setInt(3, AssignmentArray[j]);
						
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
