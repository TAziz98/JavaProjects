package MMA;

import java.util.List;

public interface CrossFit {
	
 public int getNumberOfActivities();
 public void addActivity(String activity);
 public void removeActivity(String activity);
 public List<String> getListOfActivities();
 public int identifyHeartRate(Fighter fighter);

}
