public class Client extends Person {
    private String email;
    private String phoneNumber;
    private float height;
    private float weight;
    

    public Client(int id, String name, String surname, String password, int age, String email, String phoneNumber, float height, float weight) {
        super(id, name, surname, password, age, weight, weight);
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.height=height;
        this.weight=weight;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Client{" +
                "ID =" + id +
                ", Name ='" + name + '\'' +
                ", Surname ='" + surname + '\'' +
                ", Date Of Birth =" + password +
                ", Age = " + age +
                ", Email ='" + email + '\'' +
                ", Phone Number ='" + phoneNumber + '\'' +
                '}';
    }
}
