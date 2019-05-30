package cascadeTypes;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.*;


@Entity
@Table(name="STUDENT_DETAIL")
public class Student_Detail {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int student_detail_id;
	
	private String phone_number;
    
	@OneToOne
	@JoinColumn(name="student_id")
	private Student student;
	
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public int getStudent_id() {
		return student_detail_id;
	}

	public void setStudent_id(int student_id) {
		this.student_detail_id = student_id;
	}

	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

}
