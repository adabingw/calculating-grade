import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;

public class ViewDatabase {
	public ViewDatabase(String user) {
		String url = "jdbc:mysql://localhost:3306/grades";
		String username = "root";
		String password = " ";
		JTable jt, jt1, jt2;
		JFrame f;
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("COURSE ID");
		model.addColumn("COURSE NAME");
		model.addColumn("COURSE MARK");
		DefaultTableModel model1 = new DefaultTableModel();
		model1.addColumn("COURSE ID");
		model1.addColumn("UNIT ID");
		model1.addColumn("UNIT WEIGHT");
		model1.addColumn("UNIT FINAL");
		model1.addColumn("COURSE NAME");
		DefaultTableModel model2 = new DefaultTableModel();
		model2.addColumn("COURSE ID");
		model2.addColumn("UNIT ID");
		model2.addColumn("ASSIGNMENT ID");
		model2.addColumn("ASSIGNMENT MARK");
		model2.addColumn("COURSE NAME");

		
 	   try {
    	   Connection connection = DriverManager.getConnection(url, username, password);

 			f = new JFrame();
 			f.setBackground(Color.WHITE);
 			f.setTitle("JTable Example");
 			f.setLayout(new BorderLayout(0, 0));
  			 	   
	 	   String sql = "SELECT course_id, course_name, course_mark FROM course WHERE user_id = ?";
	 	   PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, user);
	 	   
	 	   ResultSet rs = statement.executeQuery();
	 	   
	 	   
	 	   while(rs.next()) {
	 		   String cID = rs.getString("course_id");
	 		   String cNAME = rs.getString("course_name");
	 		   String cMARK = rs.getString("course_mark");
	 		   
	            String[] data = {cID, cNAME, cMARK};
	            
	            model.addRow(data);
	 	   }
	 	   
		    jt = new JTable(model) {
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int row, int column) {                
	                return false;               
	        }
		    };
		    jt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		    
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		    
		 	   String sql1 = "SELECT course_id, course_name, unit_w, unit_id, unit_final FROM unit WHERE user_id = ?";
		 	   PreparedStatement statement1 = connection.prepareStatement(sql1);
				statement1.setString(1, user);
		 	   
		 	   ResultSet rs1 = statement1.executeQuery();
		 	   
		 	   
		 	   while(rs1.next()) {
		 		   String cID = rs1.getString("course_id");
		 		   String uID = rs1.getString("unit_id");
		 		   String uW = rs1.getString("unit_w");
		 		   String uFINAL = rs1.getString("unit_final");
		 		   String cNAME = rs1.getString("course_name");
		 		   
		            String[] data = {cID, uID, uW, uFINAL, cNAME};
		            
		            model1.addRow(data);
		 	   }
		 	   
			    jt1 = new JTable(model1) {
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int row, int column) {                
		                return false;               
		        }
			    };
			    jt1.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		    
			    
			 	   String sql2 = "SELECT course_id, assign_id, assign_mark, unit_id, course_name FROM assignments WHERE user_id = ?";
			 	   PreparedStatement statement2 = connection.prepareStatement(sql2);
				   statement2.setString(1, user);
			 	   
			 	   ResultSet rs2 = statement2.executeQuery();
			 	   
			 	   while(rs2.next()) {
		                
			 		   String cID = rs2.getString("course_id");
			 		   String uID = rs2.getString("unit_id");
			 		   String aID = rs2.getString("assign_id");
			 		   String aMARK = rs2.getString("assign_mark");
			 		   String cNAME = rs2.getString("course_name");
			 		   
			            String[] data = {cID, uID, aID, aMARK, cNAME};
			            
			            model2.addRow(data);
			 	   }
			 	   
				    jt2 = new JTable(model2) {
						private static final long serialVersionUID = 1L;

						public boolean isCellEditable(int row, int column) {                
			                return false;               
			        }
				    };
				    jt2.setFont(new Font("Times New Roman", Font.PLAIN, 12));
			    
 		    JScrollPane sp = new JScrollPane(jt);
 		    JScrollPane sp1 = new JScrollPane(jt1);
 		    JScrollPane sp2 = new JScrollPane(jt2);

 		    sp.getViewport().setBackground(Color.white);
 		    sp.setPreferredSize(new Dimension(700, 250));
		    f.getContentPane().add(sp, BorderLayout.PAGE_START);
		    
 		    sp1.getViewport().setBackground(Color.white);
 		    sp1.setPreferredSize(new Dimension(700, 250));
		    f.getContentPane().add(sp1, BorderLayout.CENTER);
		    
		    sp2.getViewport().setBackground(Color.white);
 		    sp2.setPreferredSize(new Dimension(700, 250));
		    f.getContentPane().add(sp2, BorderLayout.PAGE_END);
		    
 		    f.pack();
 		    f.setResizable(false);
			f.setLocationRelativeTo(null);
		    
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.setVisible(true);
 	   } catch (SQLException e) {
				System.out.println("& i oop");
				e.printStackTrace();
	     }
	}
}
