import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.IntStream;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class FixCalculations {
	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = "root";
	int id, u_id, a_id, sum, sum1, avg, unit_f, unitMark, u_w;
//	int[] wUnit;
	
	public FixCalculations() {
		try {
	           Connection connection = DriverManager.getConnection(url, username, password);
		 	   String sql = "SELECT MAX(course_id) as MAXID FROM course";
		 	   PreparedStatement statement = connection.prepareStatement(sql);
		 	   
		 	   ResultSet rs = statement.executeQuery();
		 	   
		 	   while(rs.next()) {
		 		   id = rs.getInt("MAXID");
		 		   System.out.println(id);
		 		   ///// check weighting
		 		   for (int i = 1; i < id+1; i++) {
		 			   System.out.println(i);
				 	   String sql1 = "SELECT SUM(unit_w) as sumweight FROM unit where course_id = ?";
				 	   PreparedStatement statement1 = connection.prepareStatement(sql1);
				 	   statement1.setInt(1, i);
				 	   
				 	   ResultSet rs1 = statement1.executeQuery();
				 	   
				 	   String sql2 = "SELECT MAX(unit_id) as MAXID1 FROM unit where course_id = ?";
				 	   PreparedStatement statement2 = connection.prepareStatement(sql2);
				 	   statement2.setInt(1, i);
				 	   
				 	   ResultSet rs2 = statement2.executeQuery();
				 	   
				 	   while(rs1.next() && rs2.next()) {
  				 		   u_id = rs2.getInt("MAXID1");
				 		   System.out.println(u_id);
				 		   if (u_id < 100) {
				 			   u_id = 99;
				 		   }
				 		   int[] wUnit = new int[u_id - 99];
				 		   sum = rs1.getInt("sumweight");
				 		   System.out.println(sum);
				 		   if (sum != 100) {
				 				JLabel label = new JLabel("Please fix your weightings for course " + i + " so that calculations can be made.");
				 				label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
				 				label.setHorizontalAlignment(SwingConstants.CENTER);
					    	    JOptionPane.showMessageDialog(null, label,
					    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
				 		   } else {
							   ///// check marks						 	   
						 		   for (int j = 100; j < u_id+1; j++) {
						 			   System.out.println("j:" + j);
								 	   String sql3 = "SELECT MAX(assign_id) as MAXID2 FROM assignments where course_id = ? && unit_id = ?";
								 	   PreparedStatement statement3 = connection.prepareStatement(sql3);
								 	   statement3.setInt(1, i);
								 	   statement3.setInt(2, j);
								 	   
								 	   ResultSet rs3 = statement3.executeQuery();
								 	   
								 	   while(rs3.next()) {
								 		   a_id = rs3.getInt("MAXID2");
								 		   System.out.println(a_id);
									 	   String sql4 = "SELECT SUM(assign_mark) as summark FROM assignments where unit_id = ? && course_id = ?";
									 	   PreparedStatement statement4 = connection.prepareStatement(sql4);
									 	   statement4.setInt(1, j);
									 	   statement4.setInt(2, i);
									 	   
									 	   ResultSet rs4 = statement4.executeQuery();
									 	   
									 	   while(rs4.next()) {
									 		   sum1 = rs4.getInt("summark");
									 		   System.out.println(sum1);
									 		   System.out.println("a_id - 999 is " + (a_id - 999));
									 		   avg = sum1 / (a_id-999);
										 	   String sql5 = "SELECT unit_final FROM unit where unit_id = ? && course_id = ?";
										 	   System.out.println(avg);
										 	   PreparedStatement statement5 = connection.prepareStatement(sql5);
										 	   statement5.setInt(1, j);
										 	   statement5.setInt(2, i);
										 	   
										 	   ResultSet rs5 = statement5.executeQuery();
										 	   
										 	   while(rs5.next()) {
										 		   unit_f = rs5.getInt("unit_final");
										 		   System.out.println(unit_f);
										 		   unitMark = (int) ((unit_f * 0.3) + (avg * 0.7));
										 		   System.out.println("UNIT MARK:" + unitMark);
											 	   String sql6 = "SELECT unit_w FROM unit where unit_id = ? && course_id = ?";
											 	   PreparedStatement statement6 = connection.prepareStatement(sql6);
											 	   statement6.setInt(1, j);
											 	   statement6.setInt(2, i);
											 	   
											 	   ResultSet rs6 = statement6.executeQuery();
											 	   
											 	   while(rs6.next()) {
											 		   u_w = rs6.getInt("unit_w");
											 		   System.out.println(u_w);
											 		   wUnit[j - 100] = (int) (unitMark * (double) (u_w/ (double) 100));
////////**************
											 		   System.out.println(wUnit[j-100]);
											 		   
														String sql8 = "UPDATE unit SET unit_mark = ? WHERE unit_id = ? && course_id = ?";
														PreparedStatement statement8 = connection.prepareStatement(sql8);
														statement8.setInt(1, unitMark);
														statement8.setInt(2, j);
														statement8.setInt(3, i);
																
														int rows8 = statement8.executeUpdate();
														
														if (rows8 > 0) {
															System.out.println("UNIT MARK UPDATE SUCCESSFUL!");
														}
											 		   
											 	   }
											 	   
										 	   }
									 	   }
								 	   }
						 		   }
						 	   
								int newCourseMark = IntStream.of(wUnit).sum();
								System.out.println("NEW COURSE MARK:" + newCourseMark);
								
								String sql7 = "UPDATE course SET course_mark = ? WHERE course_id = ?";
								PreparedStatement statement7 = connection.prepareStatement(sql7);
								statement7.setInt(1, newCourseMark);
								statement7.setInt(2, i);
										
								int rows7 = statement7.executeUpdate();
								if (rows7 > 0) {
									System.out.println("COURSE_MARK UPDATE SUCCESSFUL!");
								}
				 	   }
				 		   
				 	   }
		 		   }
		 	   }
		 	   
			} catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
			}
	}
	
}
