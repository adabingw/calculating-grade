import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SlideDown {
	
	String url = "jdbc:mysql://localhost:3306/grades";
	String username = "root";
	String password = "root";
	int c_id, u_id, a_id;
	
	public SlideDown(int c, int u, int a, int table) {
        try {	
    	   Connection connection = DriverManager.getConnection(url, username, password);
    	   System.out.println("connection success!!");
    	   
           if (table == 1) {
		 	   String sql = "SELECT MAX(course_id) as MAXID FROM course";
		 	   PreparedStatement statement = connection.prepareStatement(sql);
		 	   
		 	   ResultSet rs = statement.executeQuery();
		 	   
		 	   while(rs.next()) {
		 		   c_id = rs.getInt("MAXID");
		 		   System.out.println(c_id);
		 		   if (c != c_id) {
		 			   for (int j = c + 1; j < c_id + 1; j++) {
							String sql1 = "UPDATE course SET course_id = ? WHERE course_id = ?";
							PreparedStatement statement1 = connection.prepareStatement(sql1);
							statement1.setInt(1, j-1);
							statement1.setInt(2, j);
							
							String sql2 = "UPDATE unit SET course_id = ? WHERE course_id = ?";
							PreparedStatement statement2 = connection.prepareStatement(sql2);
							statement2.setInt(1, j-1);
							statement2.setInt(2, j);
							
							String sql3 = "UPDATE assignments SET course_id = ? WHERE course_id = ?";
							PreparedStatement statement3 = connection.prepareStatement(sql3);
							statement3.setInt(1, j-1);
							statement3.setInt(2, j);
									
							int rows1 = statement1.executeUpdate();
							int rows2 = statement2.executeUpdate();
							int rows3 = statement3.executeUpdate();
							
							if (rows1 > 0 || rows2 > 0 || rows3 > 0) {
								System.out.println("slid down!");
							}
		 			   }
		 		   }
		 	   }
           } else if (table == 2) {
		 	   String sql = "SELECT MAX(unit_id) as MAXID1 FROM unit";
		 	   PreparedStatement statement = connection.prepareStatement(sql);
		 	   
		 	   ResultSet rs = statement.executeQuery();
		 	   
		 	   while(rs.next()) {
		 		   u_id = rs.getInt("MAXID1");
		 		   System.out.println(u_id);
		 		   if (u != u_id) {
		 			   for (int j = u + 1; j < u_id + 1; j++) {
							String sql1 = "UPDATE unit SET unit_id = ? WHERE course_id = ? && unit_id = ?";
							PreparedStatement statement1 = connection.prepareStatement(sql1);
							statement1.setInt(1, j-1);
							statement1.setInt(2, c);
							statement1.setInt(3, j);
							
							String sql2 = "UPDATE assignments SET course_id = ? WHERE course_id = ?";
							PreparedStatement statement2 = connection.prepareStatement(sql2);
							statement2.setInt(1, j-1);
							statement2.setInt(2, c);
							statement2.setInt(3, j);
									
							int rows1 = statement1.executeUpdate();
							int rows2 = statement2.executeUpdate();
							
							if (rows1 > 0 || rows2 > 0) {
								System.out.println("slid down!");
							}
		 			   }
		 		   }
		 	   }
           } else if (table == 3) {
		 	   String sql = "SELECT MAX(assign_id) as MAXID2 FROM assignments";
		 	   PreparedStatement statement = connection.prepareStatement(sql);
		 	   
		 	   ResultSet rs = statement.executeQuery();
		 	   
		 	   while(rs.next()) {
		 		   a_id = rs.getInt("MAXID2");
		 		   System.out.println(u_id);
		 		   if (a != a_id) {
		 			   for (int j = a + 1; j < a_id + 1; j++) {
							String sql1 = "UPDATE assignments SET assign_id = ? WHERE course_id = ? && unit_id = ? && assign_id = ?";
							PreparedStatement statement1 = connection.prepareStatement(sql1);
							statement1.setInt(1, j-1);
							statement1.setInt(2, c);
							statement1.setInt(3, u);
							statement1.setInt(4, j);
									
							int rows1 = statement1.executeUpdate();
							
							if (rows1 > 0) {
								System.out.println("slid down!");
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
