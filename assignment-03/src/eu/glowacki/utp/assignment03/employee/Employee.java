package eu.glowacki.utp.assignment03.employee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Predicate;

public abstract class Employee extends Person {
	private BigDecimal salary;
    private Manager manager;
    
    protected Employee(String firstName, String surname, Date dateOfBirth, BigDecimal salary, Manager manager) {
        super(firstName, surname, dateOfBirth);
        this.salary = salary;
        this.manager = manager;
    }
   
    public BigDecimal getSalary() {
        return salary;
    }

    public Manager getManager() {
        return manager;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
    
    public boolean isSalaryBigger(BigDecimal money){
        if(money != null){
            Predicate<BigDecimal> predicate = e -> {
                if(this.getSalary().compareTo(e) > 0)
                    return true;
                if(this.getSalary().compareTo(e) < 0)
                    return false;
                else return false;                 
            };
            return predicate.test(money);
        }
        return false;
    }
    
    public boolean isSalarySmaller(BigDecimal money){
        if(money != null){
            Predicate<BigDecimal> predicate = e -> {
                if(this.getSalary().compareTo(e) < 0)
                    return true;
                if(this.getSalary().compareTo(e) > 0)
                    return false;
                else return false;                 
            };
            return predicate.test(money);
        }
        return false;
    }
    
    public int compareSalary(Employee p) {
        if (p != null) {
            Comparable<Employee> c = e -> {
                if (e.getSalary() != null) {
                    if (this.getSalary().compareTo(e.getSalary()) > 0) {
                        return 1;
                    }
                    if (this.getSalary().compareTo(e.getSalary()) == 0) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
                return -1;

            };
            return c.compareTo(p);
        } else {
            return -1;
        }
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
	// * compare salary with other employee salary
}