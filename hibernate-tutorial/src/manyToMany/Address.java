package manyToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int address_id;
	
	private String address_detail;

	@ManyToMany(cascade = CascadeType.ALL,mappedBy = "studentAddress", fetch = FetchType.EAGER)
	private List<Student> studentList = new ArrayList<>();
	

	public List<Student> getStudentList() {
		return studentList;
	}

	public int getAddress_id() {
		return address_id;
	}

	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

}
