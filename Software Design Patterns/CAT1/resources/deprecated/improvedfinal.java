public abstract class Employee {
    private String name;
    private String surname;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public abstract double calculateSalary();
}

public class RegularEmployee extends Employee {
    private double salary;

    public RegularEmployee(String name, String surname, double salary) {
        super(name, surname);
        this.salary = salary;
    }

    public void updateSalary(double newSalary) {
        this.salary = newSalary;
    }

    @Override
    public double calculateSalary() {
        return salary;
    }
}

public class Intern extends Employee {
    public Intern(String name, String surname) {
        super(name, surname);
    }

    @Override
    public double calculateSalary() {
        return 0;
    }
}

public class Contractor extends Employee {
    private double hourlyRate;
    private int hoursMonth;

    public Contractor(String name, String surname, double hourlyRate, int hoursMonth) {
        super(name, surname);
        this.hourlyRate = hourlyRate;
        this.hoursMonth = hoursMonth;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursMonth;
    }
}

public class Payroll {
    private List<Employee> employees;

    public Payroll() {
        employees = new ArrayList<>(); 
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public double calculateTotalPayroll() {
        double totalSalary = 0;
        for (Employee employee : employees) {
            totalSalary += employee.calculateSalary(); // Polymorphism
        }
        return totalSalary;
    }
}
