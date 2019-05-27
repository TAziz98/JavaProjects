package MMA;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name="Ethnicity")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Ethnicity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ethnicity_id;
	
	public int getEthnicity_id() {
		return ethnicity_id;
	}

	public void setEthnicity_id(int ethnicity_id) {
		this.ethnicity_id = ethnicity_id;
	}

	private String ethnicityName;
	public Ethnicity(String ethnicityName) {
		this.setEthnicityName(ethnicityName);
	}
	
	public Ethnicity()
	{
		
	}

	public String getEthnicityName() {
		return ethnicityName;
	}

	public void setEthnicityName(String ethnicityName) {
		if(ethnicityName==null)
		throw new RuntimeException("Internet catalogue URL can not be null");
		else
		this.ethnicityName = ethnicityName;
	}
}
