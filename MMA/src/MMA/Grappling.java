package MMA;

import java.util.List;

public interface Grappling {
	
	public int getNumberOfCounterAttacks();
	public void addCounterAttack(String counterAttack);
	public void removeCounterAttack(String counterAttack);
	public List<String> getListOfCounterAttacks();
		

}
