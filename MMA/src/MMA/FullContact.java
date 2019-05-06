package MMA;

import java.util.List;

public interface FullContact {
	
	public int getNumberOfTechniques();
	public void addTechnique(String technique);
	public void removeTechnique(String technique);
	public List<String> getListOfTechniques();
	
	
}
