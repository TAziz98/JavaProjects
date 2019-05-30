package defaultInheritanceStrategy;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
@DiscriminatorValue(value = "employee_type")
public class Employee extends Person {

	@Column(name="salary", columnDefinition = "DECIMAL(7,2)")
	private Double salary;
	
	@Column(name="date_of_joining")
	private Date doj;
	
	@Column(name="dept_name", length=30)
	private String deptName;
	
	@Column(name="bonus", precision = 6, scale = 3)
	private BigDecimal bonus;

	@Column(name = "email", length = 30, unique = true)
	private String email;

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
