package MMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CAUCASIAN")
public class Caucasian extends Ethnicity {
	
	@Column(name="doping_test", nullable = false)
	private boolean doping_test; 

	public Caucasian(String ethnicityName, boolean isClear) {
		super(ethnicityName);
		this.setDoping_test(isClear);
		// TODO Auto-generated constructor stub
	}
	
	public Caucasian() {
		
	}

	public boolean isDoping_testClear() {
		return doping_test;
	}

	public void setDoping_test(boolean doping_test) {
		this.doping_test = doping_test;
	}
	
	
}
