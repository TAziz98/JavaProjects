package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.util.Date;

public abstract class Employee extends Person {
    private BigDecimal salary;
    private Manager manager;
    
    protected Employee(String firstName, String surname, Date dateOfBirth, BigDecimal salary, Manager manager) {
        super(firstName, surname, dateOfBirth);
        this.salary = salary;
        this.manager = manager;
    }
    

    //
    // attributes:
    // * salary (use BigDecimal type for representing currency values)
    // * manager (empty if at top of hierarchy)

    public BigDecimal getSalary() {
        return salary;
    }

    public Manager getManager() {
        return manager;
    }
    
    public Worker castToWorker(Employee e){
        Worker w = (Worker) e;
        return w;
    }

    @Override
    public String toString() {
        return super.toString() + "salary=" + salary + ", manager=" + manager + '}';
    }

    @Override
    public boolean equals(Object obj) {
        boolean r = super.equals(obj);
        if(this==obj)
            return true;
        if(!(obj instanceof Employee))
            return false;
        Employee emp = (Employee) obj;
        boolean r2 = salary!=null && salary.equals(emp.getSalary());
        
        boolean r3;
        if(manager==null && emp.getManager()==null){
            r3 = true;
        }
        else
            r3 = manager!=null && manager.equals(emp.getManager());
        return r && r2 && r3; 
    }

    @Override
    public int hashCode() {
        int hashcode = super.hashCode();
        hashcode = 31*hashcode+salary.hashCode();
        hashcode = 31*hashcode+manager.hashCode();
        return hashcode;
    }
    
    
    
}
