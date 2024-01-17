import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
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
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class StaffFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static String Name;

	private JPanel contentPane;
	private JPanel clientListpanel, doctorListpanel, billingPanel;
	private DefaultTableModel clientsTableModel;
	private JTable clientsTable, doctorsTable;
	private DefaultTableModel doctorsTableModel;
	private JComboBox<String> idBox;
	private JComboBox<String> departmentBox;
	private Map<String, List<String>> billingMap;
	private JTextField billField;
	
	
	public StaffFrame(String Name) {
		this.Name = Name;
		setTitle("Staff Frame");
		setSize(1000, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10,11,164,539);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel staffLabel = new JLabel(Name);
		staffLabel.setBounds(9, 11, 164, 48);
		panel.add(staffLabel);
		
		JButton btnClientList = new JButton("Client List");
		btnClientList.setBounds(0, 187, 164, 38);
		panel.add(btnClientList);
		
		btnClientList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showClients();
				clientListpanel.setVisible(true);
				doctorListpanel.setVisible(false);
			}
		});
		JButton btnDoctorList = new JButton("Doctor List");
		btnDoctorList.setBounds(0, 236, 164, 38);
		panel.add(btnDoctorList);
		
		btnDoctorList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showDoctor();
				clientListpanel.setVisible(false);
				doctorListpanel.setVisible(true);
				billingPanel.setVisible(false);
			}
		});
		
		JButton btnBilling = new JButton("Billing");
		btnBilling.setBounds(0, 285, 164, 38);
		panel.add(btnBilling);
		btnBilling.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clientListpanel.setVisible(false);
				doctorListpanel.setVisible(false);
				billingPanel.setVisible(true);
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
		
		clientListpanel = new JPanel();
		clientListpanel.setBackground(SystemColor.activeCaptionBorder);
		clientListpanel.setBounds(184, 11, 590, 539);
		getContentPane().add(clientListpanel);
		
		doctorListpanel = new JPanel();
		doctorListpanel.setBackground(SystemColor.activeCaptionBorder);
		doctorListpanel.setBounds(184, 11, 590, 539);
		getContentPane().add(doctorListpanel);
		
		billingPanel = new JPanel();
		billingPanel.setBackground(SystemColor.activeCaptionBorder);
		billingPanel.setBounds(184, 11, 590, 539);
		getContentPane().add(billingPanel);
		billingPanel.setLayout(null);
		
		idBox = new JComboBox<>();
		departmentBox = new JComboBox<>();
		billingMap = new HashMap<>();
		billField = new JTextField();	
		
		idBox.setBounds(186, 46, 268, 33);
		billingPanel.add(idBox);
		
		JLabel idlabel = new JLabel("Name");
		idlabel.setBounds(10,46, 112, 33);
		billingPanel.add(idlabel);
		idlabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		departmentBox.setBounds(186, 143, 268, 33);
		billingPanel.add(departmentBox);
		
		JLabel deplabel = new JLabel("Department");
		deplabel.setBounds(10, 143, 135, 33);
		billingPanel.add(deplabel);
		deplabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		billField.setBounds(186, 240, 268, 33);
		billingPanel.add(billField);
		
		JLabel feelabel = new JLabel("Fee($)");
		feelabel.setBounds(10, 240, 135, 33);
		billingPanel.add(feelabel);
		feelabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnSetFee = new JButton("Confirm Fee");
	    btnSetFee.setBounds(162, 450, 141, 23);
	    billingPanel.add(btnSetFee);
	    btnSetFee.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = (String) idBox.getSelectedItem();
				String department = (String) departmentBox.getSelectedItem();
				String fee = (String) billField.getText();
				
				try(BufferedWriter writer = new BufferedWriter(new FileWriter("database/Payments.txt", true))){
					writer.write(id + "-" + department + "-" + fee +"\n");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		});
	    readDataFromFile();
		updateIdBox();
	    idBox.addActionListener(e -> updateDepBox());
		
		clientsTableModel = new DefaultTableModel();
		clientsTableModel.addColumn("Client Name");
		clientsTableModel.addColumn("Department");
		clientsTableModel.addColumn("Doctor");
		clientsTableModel.addColumn("Day");
		clientsTableModel.addColumn("Month");
		clientsTableModel.addColumn("Time");
	
		JTable clientsTable = new JTable(clientsTableModel);
		JScrollPane clientsScrollPane = new JScrollPane(clientsTable);
		clientListpanel.add(clientsScrollPane, BorderLayout.CENTER);
		
		doctorsTableModel = new DefaultTableModel();
		doctorsTableModel.addColumn("Doctor Name");
		doctorsTableModel.addColumn("Department");
		
		JTable doctorsTable = new JTable(doctorsTableModel);
		JScrollPane doctorsScrollPane = new JScrollPane(doctorsTable);
		doctorListpanel.add(doctorsScrollPane, BorderLayout.CENTER);
		
		
		
		contentPane = new JPanel();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private void readDataFromFile() {
		billingMap.clear();
		
		try(BufferedReader br = new BufferedReader(new FileReader("database/Appointments.txt"))){
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split("-");
				if(parts.length == 6) {
					String clientName = parts[0].trim();
					String department = parts[1].trim();
					
					billingMap.computeIfAbsent(clientName, k -> new ArrayList<>()).add(department);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void updateDepBox() {
		departmentBox.removeAllItems();
		Object selectedIdObj = idBox.getSelectedItem();
		
		if(selectedIdObj != null) {
			String selectedId = selectedIdObj.toString();
			List<String> departments = billingMap.get(selectedId);
			
			if(departments != null) {
				for(String department : departments) {
					departmentBox.addItem(department);
				}
			}
		}
	}
	

    ItemListener itemListener = new ItemListener() {
        public void itemStateChanged(ItemEvent event) {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                Object item = event.getItem();
                if (item != null) {
                    updateDepBox();
                }
            }
        }
    };
    
    private void updateIdBox() {
        idBox.removeItemListener(itemListener);
        idBox.removeAllItems();

        readDataFromFile();

        String selectedId = null;

        if (departmentBox.getItemCount() > 0) {
            selectedId = departmentBox.getSelectedItem().toString();
        }

        for (String id : billingMap.keySet()) {
            System.out.println("id: " + id);
            idBox.addItem(id);
        }

        idBox.addItemListener(itemListener);

        if (selectedId != null) {
            idBox.setSelectedItem(selectedId);
        }

        updateDepBox();
    }
	
	private void showClients() {
		clientsTableModel.setRowCount(0);
		
		try(BufferedReader br = new BufferedReader(new FileReader("database/Appointments.txt"))){
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split("-");
				if(parts.length == 6) {
					String client = parts[0].trim();
					String department = parts[1].trim();
					String doctor = parts[2].trim();
					String day = parts[3].trim();
					String month = parts[4].trim();
					String time = parts[5].trim();
					clientsTableModel.addRow(new Object[] {client, department, doctor, day, month, time});
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showDoctor() {
		doctorsTableModel.setRowCount(0);
		
		try(BufferedReader br = new BufferedReader(new FileReader("database/Doctor.txt"))){
			String line;
			while((line = br.readLine()) != null) {
				String[] parts = line.split("-");
				if(parts.length == 4) {
					String doctor = parts[0].trim();
					String department = parts[1].trim();
					doctorsTableModel.addRow(new Object[] {doctor, department});		
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
	}
    public void displayName() {
    	JLabel userLabel = new JLabel("User: " + Name);
        userLabel.setBounds(0, 11, 164, 48);
        
    }
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new StaffFrame(Name).setVisible(true);
			});
		}
			
	}


