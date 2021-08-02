import java.util.stream.IntStream;

import javax.swing.JOptionPane;

public class averageAllCourses {
	public averageAllCourses() {
		String url = "jdbc:mysql://localhost:3306/grades";
		String username = "root";
		String password = "/////";
		
		qForCourses C = new qForCourses();
		int NCourses = C.AskCourses(); 
		System.out.println("Number of Courses: " + NCourses);
		String[] CNames = new String[NCourses];
		int[] avg = new int[NCourses];
		for (int i = 0; i < NCourses; i++) {
			CNames[i] = C.CourseName(i + 1);
			System.out.println("Course name: " + CNames[i]);
			averageOneCourse AOC = new averageOneCourse(CNames[i]);
			avg[i] = AOC.returnAverage();
			System.out.println("The course mark for " + CNames[i] + " is " + avg[i]);			
			try {				
				Connection connection = DriverManager.getConnection(url, username, password);
				System.out.println("connection success!!");
				
				String sql = "UPDATE unit SET course_name = ? WHERE course_id = ?";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, CNames[i]);
				statement.setInt(2, i + 1);
				
				String sql1 = "UPDATE assignments SET course_name = ? WHERE course_id = ?";
				PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setString(1, CNames[i]);
				statement1.setInt(2, i + 1);
						
				int rows = statement.executeUpdate();
				if (rows > 0) {
					System.out.println("COURSE_ID UNIT UPDATE SUCCESSFUL!");
				}
				
				int rows1 = statement1.executeUpdate();
				if (rows1 > 0) {
					System.out.println("COURSE_ID ASSIGNMENTS UPDATE SUCCESSFUL!");
				} 
				
				String sql2 = "INSERT INTO course (COURSE_NAME, COURSE_MARK) VALUES (?, ?)";
				PreparedStatement statement2 = connection.prepareStatement(sql2);
				statement2.setString(1, CNames[i]);
				statement2.setInt(2, avg[i]);
				
				int rows2 = statement2.executeUpdate();
				if (rows2 > 0) {
					System.out.println("COURSE TABLE update successful!!");
				}
			
				
				statement.close();
				connection.close();
			
				statement1.close();
				connection.close();
				
				statement2.close();
				connection.close();
			} catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
			}
		}

		int sumAverage = IntStream.of(avg).sum();
		int totalAverage = sumAverage / NCourses;
		System.out.println("Total average is: " + totalAverage);
		JOptionPane.showMessageDialog(null, "The total average is: " + totalAverage);
	}
}