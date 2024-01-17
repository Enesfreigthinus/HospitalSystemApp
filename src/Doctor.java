
public class Doctor {
    String name;
    String department;
    int id;
    String password;

    public Doctor(String name, String department, int id, String password) {
        this.name = name;
        this.department = department;
        this.id = id;
        this.password = password;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
