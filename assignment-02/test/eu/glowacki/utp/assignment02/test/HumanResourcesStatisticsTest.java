package eu.glowacki.utp.assignment02.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.glowacki.utp.assignment02.HumanResourceStatistics;
import eu.glowacki.utp.assignment02.employee.Employee;
import eu.glowacki.utp.assignment02.employee.Manager;
import eu.glowacki.utp.assignment02.employee.Worker;
import eu.glowacki.utp.assignment02.employee.Trainee;
import eu.glowacki.utp.assignment02.payroll.PayrollEntry;

public class HumanResourcesStatisticsTest {
	private ArrayList<Employee> emps;
	// Create a HR structure which resembles the below one:
	//
	// Director (an instance of Manager class (Director does not have a manager)
	//   |- Manager01
	//        |- Worker
	//        |- Worker
	//        |- Trainee
	//        |- ...
	//   |- Manager02
	//        |- ...
	//   |- ...
	//   |- Worker
	//   |- Worker
	//   |- Trainee
	
	@Before
	public void setUp() {
		emps = HumanResourceStatistics.getAllEmployees();
	}
	
	
	
	
	@Test
    public void testPayroll(){
        ArrayList<PayrollEntry> myTestPayrolls  = new ArrayList<>();
        
        for(Employee e: emps){
            if(e instanceof Worker && ((Worker)e).getBonus() != null && ((Worker)e).getSalary() != null )
                myTestPayrolls.add(new PayrollEntry(e, ((Worker) e).getSalary(), ((Worker) e).getBonus()));
            if(e instanceof Trainee)
                myTestPayrolls.add(new PayrollEntry(e, e.getSalary()));
        }
        
        for(int i = 0; i<myTestPayrolls.size(); i++){
        Assert.assertTrue(myTestPayrolls.get(i).equals(HumanResourceStatistics.payroll(emps).get(i)));            
        }
    }
    
    @Test
    public void testSubordinatesPayroll(){
        ArrayList<PayrollEntry> myTestPayrolls  = new ArrayList<>();
        
        Manager m = (Manager) emps.get(1);
        ArrayList<Employee> immediateSubs = m.getImmediateSubordinates();
        
        for(Employee e: immediateSubs){
            if(e instanceof Worker && ((Worker)e).getBonus() != null && ((Worker)e).getSalary() != null)
                myTestPayrolls.add(new PayrollEntry(e, ((Worker) e).getSalary(), ((Worker) e).getBonus()));
            if(e instanceof Trainee)
                myTestPayrolls.add(new PayrollEntry(e, e.getSalary()));
        }
        for(int i = 0; i<myTestPayrolls.size(); i++){
            Assert.assertTrue(myTestPayrolls.get(i).equals(HumanResourceStatistics.subordinatesPayroll(m).get(i)));
        }
        
    }
    
    @Test
    public void testBonusTotal(){ 
        BigDecimal total = new BigDecimal(0);
        
        for(Employee e: emps){
            if(e instanceof Worker && ((Worker)e).getBonus() != null){
                total = total.add(e.castToWorker(e).getBonus());
            }
        }
        Assert.assertEquals(total, HumanResourceStatistics.bonusTotal(emps));
        
    }
    
    @Test
    public void testEmployeeLongestSeniority(){
        Employee foundEmployee = emps.get(0);
        
        Assert.assertEquals(foundEmployee, HumanResourceStatistics.getEmployeeLongestSeniority(emps));
    }
    
    @Test
    public void testHighestSalaryWithoutBonus(){
        BigDecimal foundBigDecimal = emps.get(0).getSalary();
        
        Assert.assertEquals(foundBigDecimal, HumanResourceStatistics.getHighestSalaryWithoutBonus(emps));
    }
    
    @Test
    public void testHighestSalaryWithBonus(){
        BigDecimal foundBigDecimal = ((Worker)emps.get(4)).getSalary().add(((Worker)emps.get(4)).getBonus());
        
        Assert.assertEquals(foundBigDecimal, HumanResourceStatistics.getHighestSalaryWithBonus(emps));
    }
    
    @Test
    public void testEmployeesSurnameStartsA(){
        Manager m = (Manager) emps.get(4);
        List<Employee> startsWithA = new ArrayList<>();
        for(Employee e: m.getImmediateSubordinates()){
            if(e.getSurname().startsWith("A"))
                startsWithA.add(e);
//            System.out.println(e);
        }
        
        for(int i = 0; i<startsWithA.size(); i++){
            Assert.assertTrue(startsWithA.get(i).equals(HumanResourceStatistics.getEmployeesSurnameStartsA(m).get(i)));
        }
    }
    
    @Test
    public void testEmployeesSalaryBigger1000(){
        List<Employee> biger1000 = new ArrayList<>();
        for (Employee e : emps) {
            if (e.getSalary().compareTo(new BigDecimal(1000)) == 1) {
                biger1000.add(e);
//            System.out.println(e);
            }

            for (int i = 0; i < biger1000.size(); i++) {

                Assert.assertEquals(biger1000.get(i), (HumanResourceStatistics.getEmployeesSalaryBigger1000(emps)).get(i));
            }
        }
    }

	/// ...
	// rest of the methods specified in the assignment description
}