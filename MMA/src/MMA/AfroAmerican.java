package MMA;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="AFRO_AMERICAN")
public class AfroAmerican extends Ethnicity {
	private boolean medical_inspection;
	
	public AfroAmerican(String ethnicityName,boolean isHealthy) {
		super(ethnicityName);
		this.setMedical_inspection(isHealthy);
		// TODO Auto-generated constructor stub
	}
	
	public AfroAmerican() {
		
	}

	public boolean isMedical_inspectionHealthy() {
		return medical_inspection;
	}

	public void setMedical_inspection(boolean medical_inspection) {
		this.medical_inspection = medical_inspection;
	}
 
}
