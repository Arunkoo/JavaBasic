import java.util.Comparator;
import java.util.List;

interface Payable{
    double calculatePay();
}

abstract class Employee implements  Payable {
    private final String name;

    Employee(String name){this.name = name;};
    public String Describe(){
        return  name + " earns " + calculatePay();
    }

    @Override
    public abstract double calculatePay();

    public String getName() {
        return name;
    }
}

class SalariedEmployee extends  Employee {
    private final double salary;

    SalariedEmployee(String name, double salary) {
        super(name);
        this.salary = salary;
    }

    @Override
    public double calculatePay() {
        return salary;
    }
}

class HourlyEmployee extends Employee {

    private final double hoursWorked;
    private final double hourlyRate;

    HourlyEmployee(String name, double hoursWorked, double hourlyRate) {
        super(name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculatePay() {
        return hoursWorked * hourlyRate;
    }
}

public class Main {
    public static void main(String[] args){
        Employee e1 = new SalariedEmployee("Arun", 120000);
        Employee e3 = new SalariedEmployee("Aman", 100000);
        Employee e4 = new SalariedEmployee("Suman", 60000);
        Employee e2 = new HourlyEmployee("Rahul", 8, 12);
        Employee e5 = new HourlyEmployee("Sumit", 5, 24);
        Employee e6 = new HourlyEmployee("Ronit", 930, 3500);

        List<Employee> emp = List.of(e1, e2, e3, e4, e5,e6);

        //get all employee over 50000 salary...
        List<String> earning_over_50000 = emp.stream()
                .filter(n-> n.calculatePay() > 50000)
                .map(Employee::getName)
                .toList();

        //computing total payroll...
        double totalPayroll = emp.stream()
                        .mapToDouble(Employee::calculatePay).sum();

        //task1: Sort by pay descending order...
        List<String> sortedByDescOrder = emp.stream()
                .sorted(Comparator.comparingDouble(Employee::calculatePay).reversed())
                .map(Employee::getName)
                .toList();

        System.out.println("earning over 50,000 "+ earning_over_50000);
        System.out.println("total payroll " + totalPayroll);
        System.out.println("payment sorted by descending order " + sortedByDescOrder);
    }
}
