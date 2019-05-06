package MMA;

public abstract class Ethnicity {
	private String ethnicityName;
	public Ethnicity(String ethnicityName) {
		this.setEthnicityName(ethnicityName);
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
