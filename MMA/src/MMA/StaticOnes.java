package MMA;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "class_attribute")
public class StaticOnes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "threshold_for_signing_contract", nullable = false)
	private int thresholdForSigningContract;

	public StaticOnes( int thresholdForSigningContract) {
		this.setThresholdForSigningContract(thresholdForSigningContract);
	}

	public StaticOnes() {
	}

	@PrePersist
	private void saving() {
		setThresholdForSigningContract(Contract.getThresholdForSigningContract());
	}

	@PostLoad
	private void setAttributes() {
		Contract.setThresholdForSigningContract(getThresholdForSigningContract());
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getThresholdForSigningContract() {
		return thresholdForSigningContract;
	}

	public void setThresholdForSigningContract(int thresholdForSigningContract) {
		if(thresholdForSigningContract<0)
			throw new IllegalArgumentException("Can't be smaller than 0");
		else
		this.thresholdForSigningContract = thresholdForSigningContract;
	}

}
