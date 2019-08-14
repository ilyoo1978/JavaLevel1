package Lesson5.HW5;

public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private int salary;
    private int age;

    public Employee(String fullName, String position, String email, String phone, int salary, int age){
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }
    public void info(){
        System.out.println("Full Name: " + this.fullName);
        System.out.println("Age: " + this.age);
        System.out.println("Position: " + this.position);
        System.out.println("Email: " + this.email);
        System.out.println("Phone: " + this.phone);
        System.out.println("Salary: " + this.salary);
    }
    public boolean overAge(int overAge){
       return this.age >= overAge;
    }
    public static void main(String[] args) {
	    Employee employees[] = new Employee[5];
	    employees[0] = new Employee("John Lennon", "Vocals, Giutar", "johnny@beatles.uk", "+7(812)212-85-06", 5000000, 40);
	    employees[1] = new Employee("Paul McCartney", "Vocals, Bass, Piano", "paulisdead@beatles.uk", "+7(495)274-10-01", 5000000, 77 );
	    employees[2] = new Employee("George Harrison", "Vocals, Guitar, Sitar", "something@beatles.uk", "+5(555)555-55-66", 3000000, 58);
	    employees[3] = new Employee("Ringo Starr", "Drums, Vocals", "octopus@beatles.uk", "+1(123)123-45-67", 1000, 79);
	    employees[4] = new Employee("Gerge Martin", "Production", "geaorgemartin@abbeyroad.uk", "+0(000)000-00-00", 2000000, 90);
	    for(Employee person: employees){
	        if(person.overAge(70)){
                person.info();
                System.out.println();
            }
        }
    }
}

