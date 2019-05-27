package MMA;

//FIXED



import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.boot.registry.classloading.spi.ClassLoaderService.Work;
@Entity
@Table(name="WORKOUT")
public class Workout implements FullContact,Grappling,CrossFit {
   
	  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int workout_id;
		
	@Column(name="name_of_workout", length=30)
	private String nameOfWorkout;
	
	@ElementCollection
	private List<String> listOfCounterAttacks ;
	
	@ElementCollection
	private List<String> listOfTechniques;
	
	@ElementCollection
    private List<String> listOfActivities;
    
	@Column(name="type_of_workout")
	@Enumerated(EnumType.STRING)
    private WorkoutTypes workoutType;

    
    public Workout(String workoutName, WorkoutTypes workoutType,String action) {
    	this.setNameOfWorkout(workoutName);
    	if(workoutType!=null) {
    	switch (workoutType) {
		case FullContact:
			organizeFullContact();
			addTechnique(action);
			break;
		case CrossFit:
			organizeCrossFit();
			addActivity(action);
			break;
		case Grappling:
			organizeGrappling();
			addCounterAttack(action);
			break;
		}
    	
    	}else throw new RuntimeException("Workout type is null");
  	
	}
    
    public Workout() {
    	
    }
    
    public void organizeCrossFit() {
    	this.workoutType=WorkoutTypes.CrossFit;
    	 listOfActivities = new ArrayList<>();
    	this.listOfCounterAttacks = null;
    	this.listOfTechniques=null;
    }

	@Override
	public int getNumberOfActivities() {
		// TODO Auto-generated method stub
		if(!this.workoutType.equals(WorkoutTypes.CrossFit))
			throw new RuntimeException("Workout types doesn't correspond ");
		else
		return listOfActivities.size();
	}

	@Override
	public void addActivity(String activity) {
		// TODO Auto-generated method stub
		if(this.workoutType.equals(WorkoutTypes.CrossFit)) {
		if(activity==null)
			throw new RuntimeException("Given parameter(activity) is null");
		    else {
			if(!listOfActivities.contains(activity))
				listOfActivities.add(activity);
			else
		    throw new RuntimeException("Given parameter(activity) is already in list");
		}
		}else throw new RuntimeException("Workout type doesn't correspond");
	}

	@Override
	public void removeActivity(String activity) {
		// TODO Auto-generated method stub
		if(!this.workoutType.equals(WorkoutTypes.CrossFit))
			throw new RuntimeException("Workout types doesn't correspond ");
		else {
		if(activity==null)
			throw new RuntimeException("Given parameter(activity) is null");
		    else {
			if(!listOfActivities.contains(activity))
				throw new RuntimeException("Given parameter(activity) is not in list");
			else
				listOfActivities.remove(activity);
		}
		}
		
	}

	@Override
	public List<String> getListOfActivities() {
		// TODO Auto-generated method stub
		if(!this.workoutType.equals(WorkoutTypes.CrossFit))
			throw new RuntimeException("Workout types doesn't correspond ");
		else
		return new ArrayList<String>(listOfActivities);
	}

	@Override
	public int identifyHeartRate(Fighter fighter) {
		// TODO Auto-generated method stub
		if(!this.workoutType.equals(WorkoutTypes.CrossFit))
			throw new RuntimeException("Workout types doesn't correspond ");
		else
		return 220-fighter.getAge()*getNumberOfActivities();
	}

	
    public void organizeGrappling() {
    	this.workoutType=WorkoutTypes.Grappling;
    	listOfCounterAttacks = new ArrayList<>();
    	this.listOfActivities = null;
    	this.listOfTechniques=null;
    }
	
	@Override
	public int getNumberOfCounterAttacks() {
		// TODO Auto-generated method stub
		if(!this.workoutType.equals(WorkoutTypes.Grappling))
			throw new RuntimeException("Workout types doesn't correspond ");
		else
		return listOfCounterAttacks.size() ;
	}

	@Override
	public void addCounterAttack(String counterAttack) {
		// TODO Auto-generated method stub
		if(this.workoutType.equals(WorkoutTypes.Grappling)) {
		if(counterAttack==null)
		throw new RuntimeException("Given parameter(counterAttack) is null");
		else {
			if(!listOfCounterAttacks.contains(counterAttack))
		 listOfCounterAttacks.add(counterAttack);
			else
				throw new RuntimeException("Given parameter(counterAttack) is already in List");
		}
		}else throw new RuntimeException("Workout type doesn't correspond");
	}

	@Override
	public void removeCounterAttack(String counterAttack) {
		// TODO Auto-generated method stub
		if(!this.workoutType.equals(WorkoutTypes.Grappling))
			throw new RuntimeException("Workout types doesn't correspond ");
		else {
		if(counterAttack==null)
			throw new RuntimeException("Given parameter(counterAttack) is null");
			else {
				if(listOfCounterAttacks.contains(counterAttack))
			 listOfCounterAttacks.remove(counterAttack);
				else
					throw new RuntimeException("Given parameter(counterAttack) is not in List");
			}
		}
		
	}

	@Override
	public List<String> getListOfCounterAttacks() {
		// TODO Auto-generated method stub
		if(!this.workoutType.equals(WorkoutTypes.Grappling))
			throw new RuntimeException("Workout types doesn't correspond ");
		else
		return new ArrayList<>(listOfCounterAttacks);
	}
	
    public void organizeFullContact() {
    	this.workoutType=WorkoutTypes.FullContact;
    	listOfTechniques = new ArrayList<>();
    	this.listOfActivities = null;
    	this.listOfCounterAttacks=null;
    }
	
	@Override
	public int getNumberOfTechniques() {
		// TODO Auto-generated method stub
		if(!this.workoutType.equals(WorkoutTypes.FullContact))
			throw new RuntimeException("Workout types doesn't correspond ");
		else
		return listOfTechniques.size();
	}

	@Override
	public void addTechnique(String technique) {
		// TODO Auto-generated method stub
		if(this.workoutType.equals(WorkoutTypes.FullContact)) {
		if(technique==null)
			throw new RuntimeException("Given parameter(technique) is null");
		    else {
			if(!listOfTechniques.contains(technique))
		    listOfTechniques.add(technique);
			else
		    throw new RuntimeException("Given parameter(technique) is already in list");
		}
		}else throw new RuntimeException("Workout type doesn't correspond");
	}

	@Override
	public void removeTechnique(String technique) {
		// TODO Auto-generated method stub
		if(!this.workoutType.equals(WorkoutTypes.FullContact))
			throw new RuntimeException("Workout types doesn't correspond ");
		else {
		if(technique==null)
			throw new RuntimeException("Given parameter(technique) is null");
		    else {
			if(listOfTechniques.contains(technique))
			listOfTechniques.remove(technique);
			else
		    throw new RuntimeException("Given parameter(technique) is not in list");
		}
		}
		
	}

	@Override
	public List<String> getListOfTechniques() {
		// TODO Auto-generated method stub
		if(!this.workoutType.equals(WorkoutTypes.FullContact))
			throw new RuntimeException("Workout types doesn't correspond ");
		else
		return new ArrayList<String>(listOfTechniques);
	}

	public String getNameOfWorkout() {
		return nameOfWorkout;
	}

	public void setNameOfWorkout(String nameOfWorkout) {
		if(nameOfWorkout==null)
			throw new RuntimeException("Given parameter(nameOfWorkout) is null");
		else
		this.nameOfWorkout = nameOfWorkout;
	}
	public WorkoutTypes getWorkoutType() {
		return workoutType;
	}

}

