package assignment03.employee;

import java.math.BigDecimal;
import java.util.Date;
import java.util.function.Predicate;

public class Worker extends Employee {

	// (assignment 02)
	// attributes:
	// * employment date
	// * bonus
	
	// (assignment 03)
	// attributes:
	// * has bonus
	//
	// methods:
	// * seniority is longer than given number of years (seniority - sta¿)
	// * seniority is longer than given number of months
	// * has bonus greater than given amount of money
	private Date employmentDate;
    private BigDecimal bonus;
    private boolean hasBonus;
    
    public Worker(String firstName, String surname, Date dateOfBirth, BigDecimal salary, Manager manager, Date employmentDate, BigDecimal bonus) {
        super(firstName, surname, dateOfBirth, salary, manager);
        this.employmentDate = employmentDate;
        this.bonus = bonus;
        if(bonus != null)
            hasBonus = true;
        else hasBonus  = false;
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

    public boolean isHasBonus() {
        return hasBonus;
    }
    
    public boolean seniorityIsLongerThanGivenYears(int years){
        int seniority = new Date().getYear() - this.getEmploymentDate().getYear();
        Predicate<Integer> predicate = e -> seniority > e;
        return predicate.test(years);
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }
    
    public boolean senioriytIsLongerThanGivenMonths(int months){
        int seniority = this.getSeniorityInMonths();
        Predicate<Integer> predicate = e -> seniority > e;
        return predicate.test(months);
    }
    
    public boolean senioriytIsShorterThanGivenMonths(int months){
        int seniority = this.getSeniorityInMonths();
        Predicate<Integer> predicate = e -> seniority < e;
        return predicate.test(months);
    }
    
    public boolean senioriytIsLongerThanGivenEmployee(Employee employee){
        if(employee != null && employee instanceof Worker) {
    	int seniority = this.getSeniorityInMonths();
        Predicate<Employee> predicate = e -> seniority > ((Worker)e).getSeniorityInMonths();
        return predicate.test(employee);
        }
        else return false;
    }
    private int getSeniorityInMonths(){
        Date d1 = this.getEmploymentDate();
        Date d = new Date();
        int seniority = 0;
        
        if(d1.getMonth() > d.getMonth()){
            int y = d.getYear() - d1.getYear() - 1;
            int m = 12 - ((d1.getMonth()+1) - (d.getMonth()+1));
            seniority = y * 12 + m; 
        }
        else{
            int y = d.getYear() - d1.getYear();           
            int m = (d.getMonth()+1) - (d1.getMonth()+1);
            seniority = y * 12 + m;
        }
        return seniority;
    }
    
    public boolean bonusGreaterThanGivenMoney(BigDecimal sum){
        if(this.hasBonus){
            Predicate<BigDecimal> predicate = e -> {
                if(this.getBonus().compareTo(e) > 0)
                    return true;
                if(this.getBonus().compareTo(e) < 0)
                    return false;
                else return false;      
            };
            return predicate.test(sum);
        }
        else return false;
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
        return super.toString() +  "employmentDate=" + employmentDate + ", bonus=" + bonus + "hasBonus=" + hasBonus +'}';
    }
    
	
}