package cascadeTypes;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="STUDENT")
public class Student {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int student_id;
	
	//	@Transient
	@Column(name="Full_Name", nullable = false)
	private String name;

	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, mappedBy = "student")
	private Student_Detail student_detail;
	
   public Student_Detail getStudent_detail() {
		return student_detail;
	}
	public void setStudent_detail(Student_Detail student_detail) {
		this.student_detail = student_detail;
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
