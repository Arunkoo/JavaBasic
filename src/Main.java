import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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

        //task2: Return Top performer employee not just name but whole object
        //catch is if  list is empty we should handle it using <optional> ?
        Optional<Employee> topEarner = emp.stream()
                .max(Comparator.comparingDouble(Employee::calculatePay));

        //task3: Count Employee By Type

        long hourlyCount = emp.stream().filter(e-> e instanceof HourlyEmployee).count();
        long SalariedCount = emp.stream().filter(e-> e instanceof SalariedEmployee).count();


        topEarner.ifPresent(employee ->
                System.out.println("Top earner is " + employee.getName() + " who earns "+ employee.calculatePay())
        );

        System.out.println("earning over 50,000 "+ earning_over_50000);
        System.out.println("total payroll " + totalPayroll);
        System.out.println("payment sorted by descending order " + sortedByDescOrder);
        System.out.println("HourlyCount: " + hourlyCount + " " + "SalariedCount: " + SalariedCount);
    }
}
