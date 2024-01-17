/*import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class FileManagements{
	public static ArrayList<String> ClientReaderTxt(){
		try {
			Scanner scanner = new Scanner(new File("database/Client.txt"));
			ArrayList<String> clients = new ArrayList<String>();
			while(scanner.hasNextLine()){
				String client = scanner.nextLine();
				clients.add( client+ "\n");
			}
			scanner.close();
			return clients;
		}
		catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			return new ArrayList<String>();
		}
	}
	  public static ArrayList<Doctor> readDoctorTxt() {
	        try {
	            Scanner scanner = new Scanner(new File("database/Doctor.txt"));
	            ArrayList<Doctor> doctors = new ArrayList<>();

	            while (scanner.hasNextLine()) {
	                String doctorInfo = scanner.nextLine();
	                String[] parts = doctorInfo.split("-");
	                if (parts.length == 4) {
	                    String name = parts[0];
	                    String department = parts[1];
	                    int id = Integer.parseInt(parts[2]);
	                    String password = parts[3];

	                    Doctor doctor = new Doctor(name, department, id, password);
	                    doctors.add(doctor);
	                } else {
	                    System.out.println("Invalid doctor information: " + doctorInfo);
	                }
	            }

	            scanner.close();
	            return doctors;
	        } catch (FileNotFoundException ex) {
	            System.out.println(ex.getMessage());
	            return new ArrayList<>();
	        }
	    }

	public static ArrayList<String> StaffReaderTxt() {
		try {
			Scanner scanner = new Scanner(new File("database/Staff.txt"));
			ArrayList<String> staffs = new ArrayList<String>();
			while(scanner.hasNextLine()) {
				String staff = scanner.nextLine();
				staffs.add(staff + "\n");
			}
			scanner.close();
			return staffs;
		}
		catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
			return new ArrayList<String>();
		}
	}
	

	
	
	
}*/
