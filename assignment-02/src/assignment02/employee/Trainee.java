package assignment02.employee;

import java.math.BigDecimal;
import java.util.Date;

public class Trainee extends Employee {
    private Date startDate;
    private int lenghOfTraining;
    
    public Trainee(String firstName, String surname, Date dateOfBirth, BigDecimal salary, Manager manager, Date startDate, int lengthOfTraining) {
        super(firstName, surname, dateOfBirth, salary, manager);
        this.startDate = startDate;
        this.lenghOfTraining = lengthOfTraining;
    }
    
    
	// attributes:
	// * practice start date
	// * practice length (in days)

    public Date getStartDate() {
        return startDate;
    }

    public int getLenghOfTraining() {
        return lenghOfTraining;
    }

    @Override
    public String toString() {
        return super.toString() + "startDate=" + startDate + ", lenghOfTraining=" + lenghOfTraining + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean r = super.equals(obj);
        if(this==obj)
            return true;
        if(!(obj instanceof Trainee))
            return false;
        Trainee emp = (Trainee) obj;
        boolean r2 = startDate!=null && startDate.equals(emp.getStartDate());
        boolean r3 = lenghOfTraining==emp.getLenghOfTraining();
        return r && r2 && r3; 
    }

    @Override
    public int hashCode() {
        int hashcode = super.hashCode();
        hashcode = 31*hashcode+startDate.hashCode();
        hashcode = 31*hashcode+Long.valueOf(lenghOfTraining).hashCode();
        return hashcode;
    }
	
}
