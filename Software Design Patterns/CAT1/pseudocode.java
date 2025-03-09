public class Employee {
  private String name;
  private String surname;
  private double salary;

  public Employee(String name, String surname, double salary) {
    this.name = name;
    this.surname = surname;
    this.salary = salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }
  public double calculateSalary() {
    return salary;
  }
}

public class Intern extends Employee {
    
  public Intern(String name, String surname) {
    super(name, surname, 0); // Interns do not have regular salary         
  }
  public void setSalary(double salary) {
    this.salary = 0; // Interns do not have regular salary 
  }
  public double calculateSalary() {
    throw new UnsupportedOperationException("Interns do not have salary");
  }
}

public class Contractor extends Employee {
  private double hourlyRate;
  private int hoursMonth;
  public Contractor(String name, String surname, double hourlyRate, int hoursMonth) {
    super(name, surname, 0); // Contractors do not have regular salary 
    this.hourlyRate = hourlyRate;
    this.hoursMonth = hoursMonth;
  }
  public double calculateSalary() {
    return hourlyRate * hoursMonth;
  }
}

public class Payroll {
  private List < Employee > employees;
  public Payroll {
    employees = new List < Employee > ();
  }

  public double calculateTotalPayroll() {
    double totalSalary = 0;
    if (employees != null) {
      for (Employee employee: employees) {
        if (!employee instanceof Intern) {
          totalSalary += employee.calculateSalary();
        }
      }
    }
    return totalSalary;
  }