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
        Employee e2 = new HourlyEmployee("Rahul", 8, 12);
        System.out.println(e1.Describe());
        System.out.println(e2.Describe());
    }
}
