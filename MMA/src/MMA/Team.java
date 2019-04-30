package MMA;

import java.util.HashMap;
import java.util.Map;
//----------------->Qualified Association 
public class Team {
  
	private String teamsName;

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
			if(fighters.containsKey(fighter.getNickName())){
				fighters.remove(fighter.getNickName());
			}else {
				throw new RuntimeException("Fighter is not in Team");
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