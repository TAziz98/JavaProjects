package MMA;

import java.io.Serializable;
import java.util.Comparator;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="CONTRACT")
public class Contract implements Serializable,Comparable<Contract> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int contract_id;
	
	//mandatory
	@Column(name="number_of_fights", nullable = false)
	private int numberOfFightsSettledByPromotion;
	
	@Column(name="honorarium", nullable = false)
	private int honorariumSettledByPromotion;
	
	// optional Attribute
	@Column(name="performance_of_night", nullable = true)
	private Integer performanceOfNight;
	
	//checked
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="fighter_id")
	private Fighter fighter;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="promotion_id")
	private Promotion promotion;
	
	@Transient
	private static int thresholdForSigningContract = 15;

	public Contract(int numberOfFightsSettledByPromotion, int honorariumSettledByPromotion, Integer bonus,
			Fighter fighter, Promotion promotion) {
		this.thresholdCheck(fighter);
		this.setNumberOfFightsSettledByPromotion(numberOfFightsSettledByPromotion);
		this.setHonorariumSettledByPromotion(honorariumSettledByPromotion);
		this.performanceOfNight = bonus;
		setFighter(fighter);
		setPromotion(promotion);
	}
	
	public Contract() {
		
	}
	
////-------------------->|Association With An Attribute|
	public void setFighter(Fighter fighter) {
		if (fighter == null)
			throw new IllegalArgumentException("No fighter applied");
		else {
			this.fighter = fighter;
			this.fighter.setContract(this);
		}
	}

	public void setPromotion(Promotion promotion) {
		if (promotion == null)
			throw new IllegalArgumentException("No promotion applied");
		else {
	this.promotion = promotion;
			this.promotion.addContract(this);
		}
	}
	
	public void excludeFighter(Fighter fighter) {
		if (fighter == null)
			throw new IllegalArgumentException("No fighter applied");
		else {
			fighter.refuceContract(this);
			this.fighter = null;
		}
	}
	
	public void excludePromotion(Promotion promotion) {
		if (promotion == null)
			throw new IllegalArgumentException("No promotion applied");
		else {
			promotion.removeContract(this);
			this.promotion = null;
		}
	}
		
		
	public Fighter getFighter() {
		return fighter;
	}

	public Promotion getPromotion() {
		return promotion;
	}
	
	public Integer getNumberOfFightsSettledByPromotion() {
		return numberOfFightsSettledByPromotion;
	}

	public Integer getHonorariumSettledByPromotion() {
		return honorariumSettledByPromotion;
	}

	public void setNumberOfFightsSettledByPromotion(int numberOfFightsSettledByPromotion) {
		if (numberOfFightsSettledByPromotion < 1)
			throw new IllegalArgumentException("No fights settled");
		else
			this.numberOfFightsSettledByPromotion = numberOfFightsSettledByPromotion;
	}

	public void setHonorariumSettledByPromotion(int honorariumSettledByPromotion) {
		if (numberOfFightsSettledByPromotion < 0)
			throw new IllegalArgumentException("Can't be negative");
		else
			this.honorariumSettledByPromotion = honorariumSettledByPromotion;
	}


	//---------------->
	
	private void thresholdCheck(Fighter fighter) {
		if (fighter.getStatistics().totalFights() >= thresholdForSigningContract)
			return;
		else
			throw new RuntimeException("Threshold is not done");
	}

	//corrected
	public static int getThresholdForSigningContract() {
		return thresholdForSigningContract;
	}

	public static void setThresholdForSigningContract(int thresholdForSigningContract) {
		if(thresholdForSigningContract<0) 
			throw new RuntimeException("can't be negative");
		Contract.thresholdForSigningContract = thresholdForSigningContract;
	}

	public Integer getBonus() {
		return performanceOfNight;
	}

	public void setBonus(Integer bonus) {
		if (bonus == null)
			throw new IllegalArgumentException("null");
		else {
		this.performanceOfNight = bonus;
	}
	}

	@Override
	public int compareTo(Contract contract) {
		// TODO Auto-generated method stub
		return this.getHonorariumSettledByPromotion()-contract.getHonorariumSettledByPromotion();
	}

	
	
}
