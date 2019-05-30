package enumMany.copy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="Employee")
public class Student {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int student_id;
	
	//	@Transient
	@Column(name="Full_Name", nullable = false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name="EMPLOYEES_ADDRESSES", joinColumns = @JoinColumn(name="employee_id"), inverseJoinColumns = @JoinColumn(name="address_id"))
    private List<Address> studentAddress = new ArrayList<>();
	

    public List<Address> getStudentAddress() {
		return studentAddress;
	   }
    
    public int getStudent_id() {
 		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
