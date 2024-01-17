import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class LogInScreen extends JFrame {
	private JTextField idField;
	private JPasswordField passwordField;
	private String Name;



    public LogInScreen() {
 
        setTitle("Login Frame");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(null);
        
        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setBackground(Color.WHITE);
        comboBox.setName("User Type");
        comboBox.setBounds(515, 422, 139, 50);
        comboBox.addItem("Client");
        comboBox.addItem("Doctor");
        comboBox.addItem("Staff");
        
        JButton loginbtn = new JButton("Log In");
        loginbtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
        loginbtn.setBackground(new Color(135, 206, 235));
        loginbtn.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		String userType = (String)comboBox.getSelectedItem();
        		String id = idField.getText();
        		String password = new String(passwordField.getText());
        		 try {
                     BufferedReader reader = new BufferedReader(new FileReader("database/"+ userType + ".txt"));
                     String line;
                     while ((line = reader.readLine()) != null) {
                         String[] parts = line.split("-"); 
                         if (parts[1].equals(id) && parts[2].equals(password)) {
                             JOptionPane.showMessageDialog(null, "Login successful!");
                             dispose();
                             Name = parts[0];
                             if(userType.equals("Client")) {
                            	 ClientFrame clientFrame = new ClientFrame(Name);
                                 clientFrame.setVisible(true);
                             }
                             
                             return;
                         } else if(parts[2].equals(id) && parts[3].equals(password)) {
                        	 JOptionPane.showMessageDialog(null, "Login successful!");
                        	 dispose();
                        	 Name = parts[0];
                        	 if(userType.equals("Doctor")) {
                            	 DoctorFrame doctorFrame = new DoctorFrame(Name);
                                 doctorFrame.setVisible(true);
                        	 }
                         } else if(parts[3].equals(id) && parts[4].equals(password)) {
                        	 JOptionPane.showMessageDialog(null, "Login successful!");
                        	 dispose();
                        	 Name = parts[0];
                        	 if(userType.equals("Staff")) {
                            	 StaffFrame staffFrame = new StaffFrame(Name);
                                 staffFrame.setVisible(true);
                        	 }
                        	 
                         }
                     }
                     
                 } catch (IOException ex) {
                	 JOptionPane.showMessageDialog(null, "Invalid ID or password.");
                     ex.printStackTrace();
                 }
        		
        	}
        });
        
        loginbtn.setBounds(300, 422, 120, 50);
        getContentPane().add(loginbtn);
                 
        idField = new JTextField("Enter your ID number");
        idField.setFont(new Font("Arial", Font.PLAIN, 15));
        idField.setForeground(new Color(204, 204, 204));
    
        idField.addFocusListener(new FocusListener() {
        	@Override
        	public void focusGained(FocusEvent e) {
        	if(idField.getText().equals("Enter your ID number")) {
        		idField.setText("");
        		idField.setForeground(Color.BLACK);
        	}
        }

			@Override
			public void focusLost(FocusEvent e) {
				if(idField.getText().isEmpty()) {
					idField.setText("Enter your ID number");
					idField.setForeground(new Color(204, 204, 204));
				}
				
			}
       });
              
        passwordField = new JPasswordField("Enter your password");
        passwordField.setFont(new Font("Arial", Font.PLAIN, 15));
        passwordField.setForeground(new Color(204, 204, 204)); 
        
        passwordField.addFocusListener(new FocusListener() {	
			@Override
			public void focusLost(FocusEvent e) {
				if(String.valueOf(passwordField.getPassword()).isEmpty()) {
					passwordField.setText("Enter your password");
					 passwordField.setForeground(new Color(204, 204, 204));
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if(String.valueOf(passwordField.getPassword()).equals("Enter your password")) {
	        		passwordField.setText("");
	        		passwordField.setForeground(Color.BLACK);
	        	}
				
			}
		});
        
        passwordField.setBounds(300, 320, 353, 34);
        getContentPane().add(passwordField);
        passwordField.setColumns(10);
                
        setVisible(true);
        
        idField.setBounds(300, 260, 354, 34);
        getContentPane().add(idField);
        idField.setColumns(10);
                
        JLabel label = new JLabel("User Type");
        label.setBounds(515, 400, 139, 20);
        getContentPane().add(label);
        getContentPane().add(comboBox);
        JLabel lblNewLabel = new JLabel("Medela Hospital Service System");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 24));
        lblNewLabel.setBounds(322, 138, 317, 65);
        getContentPane().add(lblNewLabel);
        setLocationRelativeTo(null);
    }  
    public String getName() {
        return Name;
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LogInScreen().setVisible(true);
            }
        });
    }
}
