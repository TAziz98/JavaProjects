package assigment03.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.soap.SOAPBinding;

import org.junit.Assert;
import org.junit.Test;

import assignment03.HumanResourceStatistics;
import assignment03.employee.Employee;
import assignment03.employee.Trainee;
import assignment03.employee.Worker;

public final class HumanResourceStatisticsTest {
	@Test
    public void testOlderThanAndEarnMore(){
        ArrayList<Employee> emps = (ArrayList<Employee>) HumanResourceStatistics.getAllEmployees();
        List<Employee> foundEmployees = new ArrayList<>();
        Employee testEmployee = emps.get(3);
        
        for(Employee e: emps){
            if(e.isSalaryBigger(testEmployee.getSalary()) && e.isOlder(testEmployee))
                foundEmployees.add(e);
        }
        
        for (int i = 0; i < foundEmployees.size(); i++) {
            Assert.assertEquals(foundEmployees.get(i), HumanResourceStatistics.olderThanAndEarnMore(emps, testEmployee).get(i));
        }
        
    
    }
	
    @Test
    public void testPracticeLengthLongerThan(){
        ArrayList<Employee> emps = (ArrayList<Employee>) HumanResourceStatistics.getAllEmployees();
        List<Trainee> foundTrainees = new ArrayList<>();
        
       
        for(Employee e: emps){
            if(e instanceof Trainee && ((Trainee)e).getLenghOfTraining()>17){
                Trainee t = (Trainee)e;      
                foundTrainees.add(t);
            }
        }
        
        for (int i = 0; i < foundTrainees.size(); i++) {
            Assert.assertEquals(foundTrainees.get(i), HumanResourceStatistics.practiceLengthLongerThan(emps, 17).get(i));
        }
        
    
    }
    
	@Test
	public void testSeniorityLongerThanMonths() {
		ArrayList<Employee> emps = (ArrayList<Employee>) HumanResourceStatistics.getAllEmployees();
		List<Worker> foundWorkers = new ArrayList<>();
		BigDecimal b300 = new BigDecimal(300);

		for (Employee e : emps) {
			if (e instanceof Worker && ((Worker) e).getBonus() != null
					&& ((Worker) e).senioriytIsLongerThanGivenMonths(36)
					&& ((Worker) e).getBonus().compareTo(b300) < 0) {
				Worker w = (Worker) e;
				foundWorkers.add(w);
			}
		}
		
		for (int i = 0; i < foundWorkers.size(); i++) {
			Assert.assertEquals(foundWorkers.get(i),
					HumanResourceStatistics.seniorityLongerThanMonths(emps, 36).get(i));
		}
	}
    
   

    
    @Test
    public void testSeniorityBetweenOneAndThreeYears(){
        ArrayList<Employee> emps = (ArrayList<Employee>) HumanResourceStatistics.getAllEmployees();
        List<Worker> foundWorkers = new ArrayList<>();
        BigDecimal b100 = new BigDecimal(100);
        BigDecimal b10 = new BigDecimal(10);
        
        for (Employee e : emps) {
            if (e instanceof Worker && ((Worker)e).senioriytIsLongerThanGivenMonths(12) && ((Worker)e).senioriytIsShorterThanGivenMonths(36)) {
                Worker w = (Worker)e;
                foundWorkers.add(w);
            }
        }
        
        for (int i = 0; i < foundWorkers.size(); i++) {
            Assert.assertEquals(foundWorkers.get(i), HumanResourceStatistics.seniorityBetweenOneAndThreeYears(emps).get(i));
        } 
    }
    
    @Test
    public void testSeniorityLongerThanEmployee(){
        ArrayList<Employee> emps = (ArrayList<Employee>) HumanResourceStatistics.getAllEmployees();
        List<Worker> foundWorkers = new ArrayList<>();
        Employee testEmployee = emps.get(1);
        for (Employee e : emps) {
            if (e instanceof Worker && ((Worker) e).senioriytIsLongerThanGivenEmployee(testEmployee) && ((Worker) e).isSalarySmaller(((Worker) testEmployee).getSalary())) {
                Worker w = (Worker) e; 
                foundWorkers.add(w);
            }
        }
        
                
        for (int i = 0; i < foundWorkers.size(); i++) {
         Assert.assertEquals(foundWorkers.get(i), HumanResourceStatistics.seniorityLongerThanEmployee(emps, testEmployee).get(i));
        } 
    }
    
     @Test
    public void testSeniorityBetweenTwoAndFourYearsAndAgeGreaterThan(){
        ArrayList<Employee> emps = (ArrayList<Employee>) HumanResourceStatistics.getAllEmployees();
        List<Worker> foundWorkers = new ArrayList<>();
        
        for (Employee e : emps) {
            if (e instanceof Worker && ((Worker) e).seniorityIsLongerThanGivenYears(2) && ((Worker) e).senioriytIsShorterThanGivenMonths(48) && ((Worker)e).getAge()>40) {
                Worker w = (Worker)e;
                foundWorkers.add(w);
            }
        }
        
        for (int i = 0; i < foundWorkers.size(); i++) {
            Assert.assertEquals(foundWorkers.get(i), HumanResourceStatistics.seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(emps, 40).get(i));
        } 
    }
}