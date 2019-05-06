package MMA;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//----------------->Qualified Association 
public class Team {
  
	private String teamsName;
    private Compartment compartment;
    private Workout workout;
    

	public Compartment getCompartment() {
		return compartment;
	}

	public void setCompartment(Compartment compartment) {
		if(compartment==null)
			 throw new RuntimeException("Given parameter(compartment) is null");
		else {
		this.compartment = compartment;
		}
	}
	
	
	public Workout getWorkout() {
		return workout;
	}

	public void doWorkout(Workout workout) {
		if(workout==null)
			 throw new RuntimeException("Given parameter(workout) is null");
		else
		this.workout = workout;
	}
	
	private Map<String,Fighter>  fighters = new HashMap<String,Fighter>();
	
    public Team(String teamName) {
    	this.setTeamsName(teamName);
    }
	
    // add
	public void signFighter(Fighter fighter) {
		if(fighter==null) {
			throw new RuntimeException("Given parameter(fighter) is null");
		}
		else {
			if(!fighters.containsKey(fighter.getNickName())){
				fighters.put(fighter.getNickName(), fighter);
				fighter.setTeam(this);
			}else {
				throw new RuntimeException("Fighter is already in Team");
			}
		}	
	}
	
	//remove
	public void unsignFighter(Fighter fighter) {
		if(fighter==null) {
			throw new RuntimeException("Given parameter(fighter) is null");
		}
		else {
			if(!fighters.containsKey(fighter.getNickName())){
				throw new RuntimeException("Fighter is not in Team");
			}else {
				fighters.remove(fighter.getNickName());
				if(fighter.getTeam()!=null) {
				fighter.refuceTeam(this);	
				}
			}
		}	
	}

	public Fighter getFighter(String nickName) {
		if( nickName== null){
			throw new RuntimeException("Given parameter(NickName) is  null");
		}
		else {
			if(!fighters.containsKey(nickName)) {
				throw new RuntimeException("Fighter is not in Team");
			}
			return fighters.get(nickName);
		}
	}
	
	public Map<String, Fighter> getFighters() {
		return new HashMap<>(fighters);
	}
	public String getTeamsName() {
		return teamsName;
	}

	public void setTeamsName(String teamsName) {
		if(teamsName==null)
			throw new RuntimeException("Given parameter(teamsName) is  null");
		else
		this.teamsName = teamsName;
	}
	
}
//--------------------->