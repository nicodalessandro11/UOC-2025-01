public abstract class Employee {
    protected String name;
    protected String surname;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public abstract double calculateSalary();
}

public class RegularEmployee extends Employee {
    private double monthlySalary;

    public RegularEmployee(String name, String surname, double monthlySalary) {
        super(name, surname);
        this.monthlySalary = monthlySalary;
    }

    public double calculateSalary() {
        return monthlySalary;
    }
}

public class Intern extends Employee {
    public Intern(String name, String surname) {
        super(name, surname);
    }

    public double calculateSalary() {
        return 0;  // Interns don't get paid, no exceptions needed
    }
}

public class Contractor extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public Contractor(String name, String surname, double hourlyRate, int hoursWorked) {
        super(name, surname);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

public class Payroll {
    private List<Employee> employees;

    public Payroll() {
        employees = new List<Employee>();
    }

    public double calculateTotalPayroll() {
        double totalSalary = 0;
        for (Employee employee : employees) {
            total += employee.calculateSalary(); // Polymorphism
        }
        return totalSalary;
    }
}


if(!(employee instanceof Intern || employee instanceof Volunteer)) {
    totalSalary += employee.calculateSalary();
}
