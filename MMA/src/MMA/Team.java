package MMA;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.*;
//----------------->Qualified Association 
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.query.Query;

import util.HibernateUtil;


@Entity
@Table(name="TEAM")
public class Team {
  
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int team_id;
	
	@Column(name="team_name", length=30)
	private String teamsName;
	
  
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE,CascadeType.REFRESH})
	@JoinColumn(name="workout_id")
    private Workout workout;
    
	@OneToOne(cascade =  {CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="compartment_id")
    private Compartment compartment;
    
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


	 public static void removeSpecificTeam(int id) {
		  
		  Session session = HibernateUtil.getSessionFactory().openSession();
		  try {
				session.beginTransaction();
				 Query query=session.createQuery("delete from Team  where team_id=:id");  
			      query.setParameter("id",id); 
				 int deleted = query.executeUpdate();
			    System.out.println("deleted");
		  }
			   finally {
			   session.close();		
			}
	  }
	
	
	
	@OneToMany(cascade ={CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "team",fetch = FetchType.EAGER)	
	@MapKey
	private Map<String,Fighter>  fighters = new HashMap<String,Fighter>();
	
    public Team(String teamName) {
    	this.setTeamsName(teamName);
    }
	
    public Team() {
    	
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