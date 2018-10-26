package assignment03.employee;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import assignment03.HumanResourceStatistics;

public class Manager extends Worker {
	
	private static ArrayList<Employee> immediateSubordinates;
    private static ArrayList<Employee> allSubordinates;
    // attributes
    // * subordinates (a list of immediate subordinates)
    // * all subordinates (a list of subordinates in all hierarchy)
    
    public Manager(String firstName, String surname, Date dateOfBirth, BigDecimal salary, Manager manager, Date employmentDate, BigDecimal bonus) {
        super(firstName, surname, dateOfBirth, salary, manager, employmentDate, bonus);    
    }
    
    public List<Employee> getImmediateSubordinates() {
        
        List<Employee> list = HumanResourceStatistics.getAllEmployees().stream()
                .filter(e -> this.equals(e.getManager()))
                .collect(Collectors.toList());
        
        immediateSubordinates = (ArrayList<Employee>) list;
        return immediateSubordinates;

    }
    
    public List<Employee> getAllSubordinates(){
            List<Employee> employees = HumanResourceStatistics.getAllEmployees().stream()
                    .filter(e -> e.getManager() != null)
                    .collect(Collectors.toList());
            allSubordinates = (ArrayList<Employee>) employees;
            return allSubordinates;
    }

    @Override
    public String toString() {
        return super.toString() + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean r = super.equals(obj);
        return r; 
    }

    @Override
    public int hashCode() {
        int hashcode = super.hashCode();
        return hashcode;
    }
}