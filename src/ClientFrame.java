import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


public class ClientFrame extends JFrame {
    private JPanel paymentsPanel;
    private JPanel appointmentPanel;
    private JPanel myappointmentsPanel;
    private JPanel prescriptionsPanel;
    private JTable paymentsTable;
    private JComboBox <String> departmentBox;
    private JComboBox<String> doctorBox;
    private Map<String, List<String>> departmentDoctorMap;
    private static String Name;
    private DefaultTableModel appointmentsTableModel, paymentsTableModel, prescTableModel;
    
    public ClientFrame(String Name) {
    	this.Name = Name;
        setTitle("Client Frame");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        	
        
        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 164, 539);
        getContentPane().add(panel);
        panel.setLayout(null);
        
        
        JLabel userLabel = new JLabel(Name);
        userLabel.setBounds(0, 11, 164, 48);
        panel.add(userLabel);
        
    
        appointmentPanel = new JPanel();
        appointmentPanel.setBackground(SystemColor.activeCaptionBorder);
        appointmentPanel.setBounds(184, 11, 590, 539);
        getContentPane().add(appointmentPanel);
        appointmentPanel.setLayout(null);
        
        prescriptionsPanel = new JPanel();
        prescriptionsPanel.setBackground(SystemColor.activeCaptionBorder);
        prescriptionsPanel.setBounds(184, 11, 590, 539);
        getContentPane().add(prescriptionsPanel);
       
        
        paymentsPanel = new JPanel();
        paymentsPanel.setBackground(SystemColor.activeCaptionBorder);
        paymentsPanel.setBounds(184, 11, 590, 539);
        getContentPane().add(paymentsPanel);
        
        
        myappointmentsPanel = new JPanel();
        myappointmentsPanel.setBackground(SystemColor.activeCaptionBorder);
        myappointmentsPanel.setBounds(184, 11, 590, 539);
        getContentPane().add(myappointmentsPanel);
        
        JLabel lblDepartment = new JLabel("Department");
        lblDepartment.setHorizontalAlignment(SwingConstants.CENTER);
        lblDepartment.setBounds(10, 46, 112, 44);
        appointmentPanel.add(lblDepartment);
        
        doctorBox = new JComboBox<>();
        departmentBox = new JComboBox<>();
        departmentDoctorMap = new HashMap<>();
               
        readDataFromFile();
        updateDepartmentBox();
       
        departmentBox.setBounds(162, 46, 327, 44);
        appointmentPanel.add(departmentBox);
        appointmentPanel.add(doctorBox);
        
        JLabel lblNewLabel_2 = new JLabel("Doctor");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2.setBounds(10, 143, 99, 23);
        appointmentPanel.add(lblNewLabel_2);
        
        
        JLabel lblNewLabel_3 = new JLabel("Date");
        lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_3.setBounds(10, 220, 99, 23);
        appointmentPanel.add(lblNewLabel_3);
        
        JLabel lblNewLabel_4 = new JLabel("Day");
        lblNewLabel_4.setBounds(157, 224, 46, 14);
        appointmentPanel.add(lblNewLabel_4);
        
        JLabel lblNewLabel_5 = new JLabel("Month");
        lblNewLabel_5.setBounds(281, 224, 46, 14);
        appointmentPanel.add(lblNewLabel_5);
        
        JLabel lblNewLabel_7 = new JLabel("Time");
        lblNewLabel_7.setBounds(443, 224, 46, 14);
        appointmentPanel.add(lblNewLabel_7);
        
        departmentBox.addActionListener(e -> updateDoctorBox());
        
        JComboBox<String> monthBox = new JComboBox<>();
        monthBox.setBounds(249, 249, 99, 22);
        appointmentPanel.add(monthBox);

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

        for (String month : months) {
            monthBox.addItem(month);
        }
                  
        JComboBox<String> timeBox = new JComboBox<>();
        timeBox.setBounds(404, 249, 79, 22);
        appointmentPanel.add(timeBox);
        timeBox.addItem("9.30");
        timeBox.addItem("10.00");
        timeBox.addItem("11.00");
        timeBox.addItem("12.30");
        timeBox.addItem("13.00");
        timeBox.addItem("14.00");
        timeBox.addItem("15.00");
        timeBox.addItem("16.00");
        timeBox.addItem("17.00");
                
        JComboBox<String> dayBox = new JComboBox<>();
        dayBox.setBounds(98, 249, 99, 22);
        appointmentPanel.add(dayBox);

        for (int i = 1; i <= 30; i++) {
            dayBox.addItem(String.valueOf(i));
        }
        
        JButton btnNewButton = new JButton("Set Appointment");
        btnNewButton.setBounds(162, 450, 141, 23);
        appointmentPanel.add(btnNewButton);
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String department = (String) departmentBox.getSelectedItem();
				String doctor = (String) doctorBox.getSelectedItem();
				String day = (String) dayBox.getSelectedItem();
				String month = (String) monthBox.getSelectedItem();
				String time = (String) timeBox.getSelectedItem();
				String name = (String) userLabel.getText();
				
				try(BufferedWriter writer = new BufferedWriter(new FileWriter("database/Appointments.txt", true))){
					writer.write(name + "-" + department + "-" + doctor + "-" + day + "-" + month + "-" + time + "\n");		
				} catch(IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});
        
        departmentBox.setBounds(186, 46, 268, 33);
        appointmentPanel.add(departmentBox);
        
        doctorBox.setBounds(186, 143, 268, 23);
        appointmentPanel.add(doctorBox);
            
       

        

        JButton btnAppointment = new JButton(" New Appointment");
        btnAppointment.setBounds(0, 236, 164, 38);
        panel.add(btnAppointment);
        btnAppointment.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
                appointmentPanel.setVisible(true);
                paymentsPanel.setVisible(false);
                prescriptionsPanel.setVisible(false);
                myappointmentsPanel.setVisible(false);
        	}
        });
        
        JButton btnPayments = new JButton("Payments");
        btnPayments.setBounds(0, 334, 164, 38);
        panel.add(btnPayments);
        btnPayments.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e) {
        		showMyPayments();
                paymentsPanel.setVisible(true);
                appointmentPanel.setVisible(false);
                myappointmentsPanel.setVisible(false);
                prescriptionsPanel.setVisible(false);
        	}
        });
        
        paymentsTableModel = new DefaultTableModel();
        paymentsTableModel.addColumn("Department");
        paymentsTableModel.addColumn("Fee");

        paymentsTable = new JTable(paymentsTableModel);
        JScrollPane scrollPane = new JScrollPane(paymentsTable);
        paymentsPanel.add(scrollPane, BorderLayout.CENTER);
        
        
        JButton btnLogOut = new JButton("Log Out");
        btnLogOut.setBounds(0, 388, 164, 38);
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
        btnPrescriptions.setBounds(0, 285, 164, 38);
        panel.add(btnPrescriptions);
        
        btnPrescriptions.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showMyPresc();
				prescriptionsPanel.setVisible(true);
				appointmentPanel.setVisible(false);
                paymentsPanel.setVisible(false);
                myappointmentsPanel.setVisible(false);
			}
		});
        
        prescTableModel = new DefaultTableModel();
        prescTableModel.addColumn("Diagnose");
        prescTableModel.addColumn("Medicine");
        prescTableModel.addColumn("Usage");
        
        
        JTable prescTable = new JTable(prescTableModel);
        JScrollPane prescScrollPane = new JScrollPane(prescTable);
        prescriptionsPanel.add(prescScrollPane, BorderLayout.CENTER);
        
        setVisible(true);
        
        JButton btnMyAppointments = new JButton("My Appointments");
        btnMyAppointments.setBounds(0, 187, 164, 38);
        panel.add(btnMyAppointments);
        
        btnMyAppointments.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMyAppointments();
                myappointmentsPanel.setVisible(true);
                appointmentPanel.setVisible(false);
                paymentsPanel.setVisible(false);
                prescriptionsPanel.setVisible(false);
            }
        });
       

        
        appointmentsTableModel = new DefaultTableModel();
        appointmentsTableModel.addColumn("Doctor");
        appointmentsTableModel.addColumn("Department");
        appointmentsTableModel.addColumn("Day");
        appointmentsTableModel.addColumn("Month");
        appointmentsTableModel.addColumn("Time");
        
        JTable appointmentsTable = new JTable(appointmentsTableModel);
        JScrollPane appointmentsScrollPane = new JScrollPane(appointmentsTable);
        myappointmentsPanel.add(appointmentsScrollPane, BorderLayout.CENTER);
        
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void readDataFromFile() {
        departmentDoctorMap.clear();

        try (BufferedReader br = new BufferedReader(new FileReader("database/Doctor.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 4) {
                    String doctorName = parts[0].trim();
                    String doctorDepartment = parts[1].trim();

                    departmentDoctorMap.computeIfAbsent(doctorDepartment, k -> new ArrayList<>()).add(doctorName);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void updateDoctorBox() {
        doctorBox.removeAllItems();
        Object selectedDepartmentObject = departmentBox.getSelectedItem();

        if (selectedDepartmentObject != null) {
            String selectedDepartment = selectedDepartmentObject.toString();
            System.out.println("Selected department is: " + selectedDepartment);

            List<String> doctors = departmentDoctorMap.get(selectedDepartment);

            if (doctors != null) {
                System.out.println("Doctors for the selected department: " + doctors);
                for (String doctor : doctors) {
                    doctorBox.addItem(doctor);
                }
            } else {
                System.out.println("No doctors found for the selected department.");
            }
        } else {
            System.out.println("Selected department is null.");
        }
    }



    ItemListener itemListener = new ItemListener() {
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object item = event.getItem();
                if (item != null) {
                    updateDoctorBox();
                }
            }
        }
    };
  
    private void updateDepartmentBox() {
        departmentBox.removeItemListener(itemListener);
        departmentBox.removeAllItems();

        readDataFromFile();

        String selectedDepartment = null;

        if (departmentBox.getItemCount() > 0) {
            selectedDepartment = departmentBox.getSelectedItem().toString();
        }

        for (String department : departmentDoctorMap.keySet()) {
            System.out.println("Department: " + department);
            departmentBox.addItem(department);
        }

        departmentBox.addItemListener(itemListener);

        if (selectedDepartment != null) {
            departmentBox.setSelectedItem(selectedDepartment);
        }

        updateDoctorBox();
    }
    
    private void showMyAppointments() {

        
        appointmentsTableModel.setRowCount(0);

        try (BufferedReader br = new BufferedReader(new FileReader("database/Appointments.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("-");
                if (parts.length == 6 && parts[0].trim().equals(Name)) {
                    String doctor = parts[2].trim();
                    String department = parts[1].trim();
                    String day = parts[3].trim();
                    String month = parts[4].trim();
                    String time = parts[5].trim();

                    appointmentsTableModel.addRow(new Object[]{doctor, department, day, month, time});
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private void showMyPayments() {
    	paymentsTableModel.setRowCount(0);
    	try(BufferedReader br = new BufferedReader(new FileReader("database/Payments.txt"))){
    		String line;
    		while((line = br.readLine()) != null) {
    			String[] parts = line.split("-");
    			if(parts.length == 3 && parts[0].trim().equals(Name)) {
    				String dep = parts[1].trim();
    				String fee = parts[2].trim();
    				 paymentsTableModel.addRow(new Object[]{dep,fee});		
    			}
    		}
    	}	catch (IOException e) {
            e.printStackTrace();
    	}
    }
    
    private void showMyPresc() {
    	prescTableModel.setRowCount(0);
    	try(BufferedReader br = new BufferedReader(new FileReader("database/Prescription.txt"))){
    		String line;
    		while((line = br.readLine()) != null) {
    			String[] parts = line.split("-");
    			if(parts.length == 4 && parts[0].trim().equals(Name)) {
    				String diag = parts[1];
    				String medic = parts[2];
    				String use = parts[3];
    				prescTableModel.addRow(new Object[] {diag, medic, use});
    			}
    		}
    	}catch (IOException e) {
            e.printStackTrace();
    	}
    }
    public void displayName() {
    	JLabel userLabel = new JLabel("User: " + Name);
        userLabel.setBounds(0, 11, 164, 48);
        
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClientFrame(Name).setVisible(true);
        });
    }
}
