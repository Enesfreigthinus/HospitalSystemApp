


public class Person {
	protected int id;
	protected String name;
	protected String surname;
	protected String password;
	protected int age;

	
	public Person(int id, String name, String surname, String password, int age, double height, double weight) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.age = age;
	
		
	}
	public int getId() {
        return id;
    }
	
	public String getSurname() {
        return surname;
    }
	
	public int getAge() {
        return age;
    }


    public void setId(int id) {
        this.id = id;
    }
    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) {
    	this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }
    

    @Override
    public String toString() {
        return "Person{" +
                "ID =" + id +
                ", Name ='" + name + '\'' +
                ", Surname ='" + surname + '\'' +
                ", Password =" + password +
                ", Age = " + age +

                '}';
    }


}
