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

public class qForCourses {
	
    protected JOptionPane getOptionPane(JComponent parent) {
        JOptionPane pane = null;
        if (!(parent instanceof JOptionPane)) {
            pane = getOptionPane((JComponent)parent.getParent());
        } else {
            pane = (JOptionPane) parent;
        }
        return pane;
    }
	
	public int AskCourses() {
		JLabel label = new JLabel("How many courses do you want to calculate for?");
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
        	     " ",
        	     JOptionPane.YES_NO_OPTION, 
        	     JOptionPane.QUESTION_MESSAGE, 
        	     null, 
        	     new Object[]{okay, cancel}, 
        	     okay);
        if (value == 0) {
	        String numberofCourses = field.getText();
	        
	        try {
			   int noCourses = Integer.parseInt(numberofCourses); 
						if (noCourses < 0 || noCourses > 100) {
				    	    JOptionPane.showMessageDialog(null, "Input out of bounds",
				    	    	      "ERROR", JOptionPane.ERROR_MESSAGE);
				    	    System.exit(0);
						}
					    return noCourses;
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
	
	public String CourseName(int CourseNo) {
		String s = null;
		JLabel label = new JLabel("For course " + CourseNo + ", please type in the course name: ");
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
        	     " ",
        	     JOptionPane.YES_NO_OPTION, 
        	     JOptionPane.QUESTION_MESSAGE, 
        	     null, 
        	     new Object[]{okay, cancel}, 
        	     okay);
        if (value == 0) {
	        String nameofCourse = field.getText();
	        System.out.println(nameofCourse);
	        return nameofCourse;
        } else if (value == 1) {
        	JOptionPane.getRootFrame().dispose();   
        } else System.exit(0);
		return s;
		
	} // all clear
}
