import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class qForUnits {
	
    protected JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane = null;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent)parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }
	
	public int AskUnits(String courseName) {
		JLabel label = new JLabel("How many units are in this course?");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
        final JButton okay = new JButton("Ok");
        okay.setBackground(Color.WHITE);
        okay.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(okay);
            }
        });
        okay.setEnabled(false);
        
        final JButton cancel = new JButton("Cancel");
        cancel.setBackground(Color.WHITE);
        cancel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(cancel);
            }
        });

        final JTextField field = new JTextField();
        
        field.getDocument().addDocumentListener(new DocumentListener() {
            protected void update() {
                okay.setEnabled(field.getText().length() > 0);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });
        
        Object[] message = {
        	label, field,
        };

        int value = JOptionPane.showOptionDialog(
        	     null,
        	     message,
        	     courseName,
        	     JOptionPane.YES_NO_OPTION, 
        	     JOptionPane.QUESTION_MESSAGE, 
        	     null, 
        	     new Object[]{okay, cancel}, 
        	     okay);
        if (value == 0) {
	        String numberofUnits = field.getText();
	        
	        try {
			   int noUnits = Integer.parseInt(numberofUnits); 
						if (noUnits < 0 || noUnits > 100) {
				    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
				    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
				    	    System.exit(0);
						}
					    return noUnits;
			    	 } catch(NumberFormatException e) {
			    	    JOptionPane.showMessageDialog(null, "Input is not a number",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			    	    System.exit(0);
			    	 } 
        } else if (value == 1) {
        	JOptionPane.getRootFrame().dispose();   
        } else System.exit(0);
		return 0;
	} // all clear
	
	public String[] Unitw(int UnitNo, String courseName) {
			String[] unitInfo = new String[2];
			JLabel l = new JLabel("For unit " + UnitNo + ", please type in the weighting (discard the percent sign) and the name: ");
			l.setFont(new Font("Times New Roman", Font.PLAIN, 15));
			l.setHorizontalAlignment(SwingConstants.CENTER);
			
			JTextField w = new JTextField(10);
		    JLabel label = new JLabel("Weighting:");
		    label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		    label.setHorizontalAlignment(SwingConstants.LEFT);
		    
			JTextField n = new JTextField(10);		    
		    JLabel label2 = new JLabel("Name");
		    label2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		    label2.setHorizontalAlignment(SwingConstants.LEFT);
		    
	        final JButton okay = new JButton("Ok");
	        okay.setBackground(Color.WHITE);
	        okay.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	        okay.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane pane = getOptionPane((JComponent)e.getSource());
	                pane.setValue(okay);
	            }
	        });
	        okay.setEnabled(false);
	        
	        final JButton cancel = new JButton("Cancel");
	        cancel.setBackground(Color.WHITE);
	        cancel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	        cancel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane pane = getOptionPane((JComponent)e.getSource());
	                pane.setValue(cancel);
	            }
	        });
	        
	        w.getDocument().addDocumentListener(new DocumentListener() {
	            protected void update() {
	                okay.setEnabled(w.getText().length() > 0 && n.getText().length() > 0);
	            }

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                update();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                update();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                update();
	            }
	        });
	        
	        n.getDocument().addDocumentListener(new DocumentListener() {
	            protected void update() {
	                okay.setEnabled(w.getText().length() > 0 && n.getText().length() > 0);
	            }

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                update();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                update();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                update();
	            }
	        });
		    
			Object[] message = {
					l,
					label, w,
					label2, n,
			};
		    
	        int value = JOptionPane.showOptionDialog(
	        	     null,
	        	     message,
	        	     courseName,
	        	     JOptionPane.YES_NO_OPTION, 
	        	     JOptionPane.QUESTION_MESSAGE, 
	        	     null, 
	        	     new Object[]{okay, cancel}, 
	        	     okay);
			
	        if (value == 0) {        
		        unitInfo[0] = w.getText();
			    unitInfo[1] = n.getText();
	        } else if (value == 1) {
	        	JOptionPane.getRootFrame().dispose();   
	        } else System.exit(0);
	        return unitInfo;
	} // all clear
	
	public int AskAssign(int UnitNo, String courseName) {
		JLabel label = new JLabel("How many assignments are in this unit?");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
        final JButton okay = new JButton("Ok");
        okay.setBackground(Color.WHITE);
        okay.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(okay);
            }
        });
        okay.setEnabled(false);
        
        final JButton cancel = new JButton("Cancel");
        cancel.setBackground(Color.WHITE);
        cancel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(cancel);
            }
        });

        final JTextField field = new JTextField();
        
        field.getDocument().addDocumentListener(new DocumentListener() {
            protected void update() {
                okay.setEnabled(field.getText().length() > 0);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });
        
        Object[] message = {
        	label, field,
        };

        int value = JOptionPane.showOptionDialog(
        	     null,
        	     message,
        	     courseName + " Unit " + UnitNo,
        	     JOptionPane.YES_NO_OPTION, 
        	     JOptionPane.QUESTION_MESSAGE, 
        	     null, 
        	     new Object[]{okay, cancel}, 
        	     okay);
        if (value == 0) {
	        String numberofAssignments = field.getText();
	        
	        try {
			   int assignmentNumber = Integer.parseInt(numberofAssignments); 
						if (assignmentNumber < 0 || assignmentNumber > 100) {
				    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
				    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
				    	    System.exit(0);
						}
					    return assignmentNumber;
			    	 } catch(NumberFormatException e) {
			    	    JOptionPane.showMessageDialog(null, "Input is not a number",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			    	    System.exit(0);
			    	 } 
        } else if (value == 1) {
        	JOptionPane.getRootFrame().dispose();   
        } else System.exit(0);
		return 0;
	} // all clear
	
	public String[][] InputAssign(int Assign, int UnitNo, String courseName) {

		System.out.println("Assign is " + Assign); // system check
		String[][] Assignments = new String[2][Assign]; 
		
		for (int i = 0; i < Assign; i++) {
			JTextField n = new JTextField();
		    JLabel label = new JLabel("Name of assignment " + (i+1) + ":");
		    label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		    label.setHorizontalAlignment(SwingConstants.LEFT);
		    
			JTextField mark = new JTextField();		    
		    JLabel label2 = new JLabel("Mark of assignment " + (i+1) + ":");
		    label2.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		    label2.setHorizontalAlignment(SwingConstants.LEFT);
		    
	        final JButton okay = new JButton("Ok");
	        okay.setBackground(Color.WHITE);
	        okay.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	        okay.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane pane = getOptionPane((JComponent)e.getSource());
	                pane.setValue(okay);
	            }
	        });
	        okay.setEnabled(false);
	        
	        final JButton cancel = new JButton("Cancel");
	        cancel.setBackground(Color.WHITE);
	        cancel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
	        cancel.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane pane = getOptionPane((JComponent)e.getSource());
	                pane.setValue(cancel);
	            }
	        });
	        
	        mark.getDocument().addDocumentListener(new DocumentListener() {
	            protected void update() {
	                okay.setEnabled(mark.getText().length() > 0 && n.getText().length() > 0);
	            }

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                update();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                update();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                update();
	            }
	        });
	        
	        n.getDocument().addDocumentListener(new DocumentListener() {
	            protected void update() {
	                okay.setEnabled(mark.getText().length() > 0 && n.getText().length() > 0);
	            }

	            @Override
	            public void insertUpdate(DocumentEvent e) {
	                update();
	            }

	            @Override
	            public void removeUpdate(DocumentEvent e) {
	                update();
	            }

	            @Override
	            public void changedUpdate(DocumentEvent e) {
	                update();
	            }
	        });
		    
			Object[] message = {
					label, n,
					label2, mark,
			};
			
			int value = JOptionPane.showOptionDialog(
	        	     null,
	        	     message,
	        	     courseName + " Unit " + UnitNo,
	        	     JOptionPane.YES_NO_OPTION, 
	        	     JOptionPane.QUESTION_MESSAGE, 
	        	     null, 
	        	     new Object[]{okay, cancel}, 
	        	     okay);
		    
		    if (value == 0) {
		       Assignments[0][i] = mark.getText();
		       Assignments[1][i] = n.getText();
		    } else if (value == 1) {
	        	JOptionPane.getRootFrame().dispose();   
	        } else System.exit(0); 
		}
		return Assignments;
	}
	
	public int InputUnitFinal(int UnitNo, String courseName) {
		JLabel label = new JLabel("Input your unit final mark: ");
		label.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
        final JButton okay = new JButton("Ok");
        okay.setBackground(Color.WHITE);
        okay.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        okay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(okay);
            }
        });
        okay.setEnabled(false);
        
        final JButton cancel = new JButton("Cancel");
        cancel.setBackground(Color.WHITE);
        cancel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane pane = getOptionPane((JComponent)e.getSource());
                pane.setValue(cancel);
            }
        });

        final JTextField field = new JTextField();
        
        field.getDocument().addDocumentListener(new DocumentListener() {
            protected void update() {
                okay.setEnabled(field.getText().length() > 0);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }
        });
        
        Object[] message = {
        	label, field,
        };

        int value = JOptionPane.showOptionDialog(
        	     null,
        	     message,
        	     courseName + " Unit " + UnitNo,
        	     JOptionPane.YES_NO_OPTION, 
        	     JOptionPane.QUESTION_MESSAGE, 
        	     null, 
        	     new Object[]{okay, cancel}, 
        	     okay);
        if (value == 0) {
	        String unitFinal = field.getText();
	        
	        try {
			   int unitFinalMark = Integer.parseInt(unitFinal); 
						if (unitFinalMark < 0 || unitFinalMark > 100) {
				    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
				    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
				    	    System.exit(0);
						}
					    return unitFinalMark;
			    	 } catch(NumberFormatException e) {
			    	    JOptionPane.showMessageDialog(null, "Input is not a number",
			    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
			    	    System.exit(0);
			    	 } 
        } else if (value == 1) {
        	JOptionPane.getRootFrame().dispose();   
        } else System.exit(0);
		return 0;
	}
	
}
