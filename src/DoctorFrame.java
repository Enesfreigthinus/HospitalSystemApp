import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class DoctorFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static String Name;
	private DefaultTableModel appointmentsTableModel;
	private JPanel appointmentPanel, prescPanel;
	private JTable appointmentsTable;
	private Map<String, String> doctorDepartments = new HashMap<>();
	private JTextField idField, diagnoseField, medicField, usageField;
	

	public DoctorFrame(String Name) {
		this.Name=Name;
		
		try (BufferedReader br = new BufferedReader(new FileReader("database/Doctor.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 4) {
                    String doctorName = parts[0].trim();
                    String department = parts[1].trim();
                    doctorDepartments.put(doctorName, department);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Doctor Frame");
		setSize(1000, 600);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10,11,164,539);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel doctorLabel = new JLabel(Name);
		doctorLabel.setBounds(9, 11, 164, 48);
		panel.add(doctorLabel);
		
		JButton btnAppointments = new JButton("Appointments");
		btnAppointments.setBounds(0, 187, 164, 38);
		panel.add(btnAppointments);
		btnAppointments.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showMyAppointments();
				appointmentPanel.setVisible(true);
				prescPanel.setVisible(false);
				
			}
		});
		
		JButton btnLogOut = new JButton ("Log Out");
		btnLogOut.setBounds(0, 334, 164, 38);
		panel.add(btnLogOut);
		btnLogOut.addActionListener(new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				LogInScreen loginscreen = new LogInScreen();
        		loginscreen.setVisible(true);
        		dispose();
				
			}
		});
		
		 	JButton btnPrescriptions = new JButton("Prescriptions");
	        btnPrescriptions.setBounds(0, 262, 164, 38);
	        panel.add(btnPrescriptions);
	        btnPrescriptions.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					appointmentPanel.setVisible(false);
					prescPanel.setVisible(true);
					
				}
			});
	        
	        
	        
	        prescPanel = new JPanel();
			prescPanel.setBackground(SystemColor.activeCaptionBorder);
			prescPanel.setBounds(184, 11, 590, 539);
			getContentPane().add(prescPanel);
			prescPanel.setLayout(null);
			
			idField = new JTextField();
			diagnoseField = new JTextField();
			medicField = new JTextField();
			usageField = new JTextField();
			
			idField.setBounds(186, 46, 268, 33);
			prescPanel.add(idField);
			
			JLabel nameLabel = new JLabel("Client Name");
			nameLabel.setBounds(25, 46, 120, 33);
			prescPanel.add(nameLabel);
			nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			diagnoseField.setBounds(186, 143, 268, 33);
			prescPanel.add(diagnoseField);
			
			JLabel diagnoseLabel = new JLabel("Diagnose");
			diagnoseLabel.setBounds(25, 143, 120, 33);
			prescPanel.add(diagnoseLabel);
			diagnoseLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			medicField.setBounds(186, 240, 268, 33);
			prescPanel.add(medicField);
			
			JLabel medicLabel = new JLabel("Medicine");
			medicLabel.setBounds(10, 240, 155, 33);
			prescPanel.add(medicLabel);
			medicLabel.setHorizontalAlignment(SwingConstants.CENTER);
			
			JLabel lblNewLabel = new JLabel("Usage");
			lblNewLabel.setBounds(73, 319, 92, 14);
			prescPanel.add(lblNewLabel);
			
			usageField.setBounds(186, 310, 268, 33);
			prescPanel.add(usageField);
			
			JButton btnConfirmPresc = new JButton("Confirm Prescription");
			btnConfirmPresc.setBounds(36, 428, 129, 33);
			prescPanel.add(btnConfirmPresc);
			btnConfirmPresc.addActionListener(new ActionListener() {	
				@Override
				public void actionPerformed(ActionEvent e) {
					String name = (String) idField.getText();
					String diagnose = (String) diagnoseField.getText();
					String medic = (String) medicField.getText();
					String usage = (String) usageField.getText();
					
					try(BufferedWriter writer = new BufferedWriter(new FileWriter("database/Prescription.txt", true))){
						writer.write(name + "-" + diagnose + "-" + medic + "-" + usage + "\n");
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}
					
				}
			});
			
			
		
		 	appointmentPanel = new JPanel();
	        appointmentPanel.setBackground(SystemColor.activeCaptionBorder);
	        appointmentPanel.setBounds(184, 11, 590, 539);
	        getContentPane().add(appointmentPanel);
	        
		
	        appointmentsTableModel = new DefaultTableModel();
	        appointmentsTableModel.addColumn("Client Name");
	        appointmentsTableModel.addColumn("Day");
	        appointmentsTableModel.addColumn("Month");
	        appointmentsTableModel.addColumn("Time");
		
	        appointmentsTable = new JTable(appointmentsTableModel);
	        JScrollPane appointmentsScrollPane = new JScrollPane(appointmentsTable);
	        appointmentPanel.add(appointmentsScrollPane, BorderLayout.CENTER);
	        
	        appointmentsTable.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (SwingUtilities.isRightMouseButton(e)) {
	                    int row = appointmentsTable.rowAtPoint(e.getPoint());
	                    appointmentsTable.clearSelection();
	                    appointmentsTable.addRowSelectionInterval(row, row);
	                }
	            }
	        });
		
	        JButton btnDelete = new JButton("Delete");
	        btnDelete.setBounds(10, 490, 100, 38);
	        appointmentPanel.add(btnDelete);

	        btnDelete.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                deleteSelectedAppointments();
	            }
	        });		
		
		


		
			contentPane = new JPanel();
			setLocationRelativeTo(null);
			setVisible(true);
	}
	
	  private void showMyAppointments() {
	        appointmentsTableModel.setRowCount(0);
	        try (BufferedReader br = new BufferedReader(new FileReader("database/Appointments.txt"))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] parts = line.split("-");
	                if (parts.length == 6  && parts[2].trim().equals(Name)) {
	                	 String client = parts[0].trim();
	                     String day = parts[3].trim();
	                     String month = parts[4].trim();
	                     String time = parts[5].trim();
	                    
	                     appointmentsTableModel.addRow(new Object[]{client, day, month, time});
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	  private void deleteSelectedAppointments() {
		    int[] selectedRows = appointmentsTable.getSelectedRows();
		    if (selectedRows.length > 0) {
		        DefaultTableModel model = (DefaultTableModel) appointmentsTable.getModel();

		        for (int i = selectedRows.length - 1; i >= 0; i--) {
		            int selectedRow = selectedRows[i];

		            
		            String client = (String) model.getValueAt(selectedRow, 0);
		            String day = (String) model.getValueAt(selectedRow, 1);
		            String month = (String) model.getValueAt(selectedRow, 2);
		            String time = (String) model.getValueAt(selectedRow, 3);

		            
		            model.removeRow(selectedRow);

		            
		            updateAppointmentsFile(client, day, month, time);
		        }
		    }
		}
	  
	  
	  private void updateAppointmentsFile(String client, String day, String month, String time) {
		    try (BufferedReader br = new BufferedReader(new FileReader("database/Appointments.txt"));
		         BufferedWriter writer = new BufferedWriter(new FileWriter("database/Appointments_tmp.txt"))) {

		        String line;
		        boolean deleted = false;
		        while ((line = br.readLine()) != null) {
		            String[] parts = line.split("-");
		            if (parts.length == 6
		                    && parts[0].trim().equals(client)
		                    && parts[3].trim().equals(day)
		                    && parts[4].trim().equals(month)
		                    && parts[5].trim().equals(time)) {
		                
		                deleted = true;
		                continue;
		            }
		           
		            writer.write(line);
		            writer.newLine();
		        }

		        if (!deleted) {
		            System.out.println("Error: Appointment not found.");
		        } else {
		            System.out.println("Appointment deleted successfully.");
		        }

		    } catch (IOException e) {
		        e.printStackTrace();
		    }


	        
		    Path tmpPath = Path.of("database/Appointments_tmp.txt");
		    Path originalPath = Path.of("database/Appointments.txt");
		    try {
		        Files.move(tmpPath, originalPath, StandardCopyOption.REPLACE_EXISTING);
		        System.out.println("File updated successfully.");
		    } catch (IOException e) {
		        System.out.println("Error updating file: " + e.getMessage());
		    }
	        
	    }
	    
	    	

	  	public void displayName() {
		  JLabel userLabel = new JLabel("User: " + Name);
		  userLabel.setBounds(0, 11, 164, 48);
    
	  	}
		public static void main(String[] args) {
			SwingUtilities.invokeLater(() -> {
				new DoctorFrame(Name).setVisible(true);
				});
			}
}
