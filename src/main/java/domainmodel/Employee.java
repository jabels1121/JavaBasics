package domainmodel;

public class Employee {

    private static int id;
    private int employeeId, salary;
    private String name;
    private String position;
    private String department;

    public Employee() {
        this("A", "B", 1);
        System.out.println("Empty constructor was called");
    }

    private Employee(String name, String position, int salary, String department) {
        this.salary = salary;
        this.name = name;
        this.position = position;
        this.department = department;
        this.employeeId = id++;
        System.out.println("Constructor with 4 parameters was called");
    }

    public Employee(String name, String position, int salary) {
        this(name, position, salary, "IT");
        System.out.println("Constructor with 3 parameters was called");
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public int getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", salary=" + salary +
                ", name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
