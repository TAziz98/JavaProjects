package eu.glowacki.utp.assignment02.employee;

import java.math.BigDecimal;
import java.util.Date;

public class Worker extends Employee {
    private Date employmentDate;
    private BigDecimal bonus;
    
    public Worker(String firstName, String surname, Date dateOfBirth, BigDecimal salary, Manager manager, Date employmentDate, BigDecimal bonus) {
        super(firstName, surname, dateOfBirth, salary, manager);
        this.employmentDate = employmentDate;
        this.bonus = bonus;
    }
    
	// attributes
	// * employment date
	// * bonus

    public Date getEmploymentDate() {
        return employmentDate;
    }

    public BigDecimal getBonus() {
        return bonus;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean r = super.equals(obj);
        if(this==obj)
            return true;
        if(!(obj instanceof Worker))
            return false;
        Worker emp = (Worker) obj;
        boolean r2 = employmentDate!=null && employmentDate.equals(emp.getEmploymentDate());
        boolean r3 = bonus!=null && bonus.equals(emp.getBonus());
        return r && r2 && r3; 
    }

    @Override
    public int hashCode() {
        int hashcode = super.hashCode();
        hashcode = 31*hashcode+employmentDate.hashCode();
        hashcode = 31*hashcode+bonus.hashCode();
        return hashcode;
    }

    @Override
    public String toString() {
        return super.toString() +  "employmentDate=" + employmentDate + ", bonus=" + bonus + '}';
    }
    
	
}
