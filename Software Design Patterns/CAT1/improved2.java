public abstract class Employee {
    private String name;
    private String surname;

    public Employee(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public abstract double calculateSalary();  // Método polimórfico
}

// Regular Employee con salario fijo
public class RegularEmployee extends Employee {
    private double salary;

    public RegularEmployee(String name, String surname, double salary) {
        super(name, surname);
        this.salary = salary;
    }

    @Override
    public double calculateSalary() {
        return salary;
    }
}

// Intern sin salario
public class Intern extends Employee {
    public Intern(String name, String surname) {
        super(name, surname);
    }

    @Override
    public double calculateSalary() {
        return 0;  // Interns no ganan dinero
    }
}

// Contractor con pago por horas
public class Contractor extends Employee {
    private double hourlyRate;
    private int hoursWorked;

    public Contractor(String name, String surname, double hourlyRate, int hoursWorked) {
        super(name, surname);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public double calculateSalary() {
        return hourlyRate * hoursWorked;
    }
}

// Payroll mejorado sin modificar código existente
public class Payroll {
    private List<Employee> employees;

    public Payroll() {
        employees = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public double calculateTotalPayroll() {
        return employees.stream().mapToDouble(Employee::calculateSalary).sum();
    }
}
