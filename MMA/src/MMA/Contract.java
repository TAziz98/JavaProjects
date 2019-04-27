package MMA;

import java.io.Serializable;

//Ok
public class Contract implements Serializable {
	//mandatory
	private int numberOfFightsSettledByPromotion;
	private int honorariumSettledByPromotion;
	// optional Attribute
	private Integer performanceOfNight;
	private Fighter fighter;
	private Promotion promotion;
	// class attribute
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

	private void setFighter(Fighter fighter) {
		if (fighter == null)
			throw new IllegalArgumentException("No fighter applied");
		else {
			this.fighter = fighter;
			this.fighter.setContract(this);
		}
	}

	private void setPromotion(Promotion promotion) {
		if (promotion == null)
			throw new IllegalArgumentException("No promotion applied");
		else {
			this.promotion = promotion;
			this.promotion.addContract(this);
		}
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

	public Fighter getFighter() {
		return fighter;
	}

	public Promotion getPromotion() {
		return promotion;
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
}
