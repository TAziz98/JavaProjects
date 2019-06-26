package MMA;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.query.Query;

@Entity
@Table(name="ASSOCIATION")
public class Association {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int association_id;
		
		@Column(name="association_name")
		private String associationName;
		
		public Association(String name) {
			this.associationName=name;
		}
		
		public Association() {
			// TODO Auto-generated constructor stub
		}
		
		
    @OneToMany(cascade ={CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "association",fetch = FetchType.EAGER)	
	private Set<Fighter>  fighters = new HashSet<Fighter>();
	
	public Set<Fighter> getFighters() {
		return new HashSet<>(fighters);
	}

	public void signFighter(Fighter fighter) {
		if(fighter==null) {
			throw new RuntimeException("Given parameter(fighter) is null");
		}
		else {
			if(!fighters.contains(fighter)){
				fighters.add(fighter);
				fighter.setAssociation(this);
			}else {
				throw new RuntimeException("Fighter is already in Association");
			}
		}	
	}
	
	public void unsignFighter(Fighter fighter) {
		if(fighter==null) {
			throw new RuntimeException("Given parameter(fighter) is null");
		}
		else {
			if(!fighters.contains(fighter)){
				throw new RuntimeException("Fighter is not in Association");
			}else {
				fighters.remove(fighter);
				if(fighter.getAssociation()!=null) {
				fighter.refuceAssociation(this);
				}
			}
		}	
		
	}
	

	
	public int getAssociation_id() {
		return association_id;
	}

	public void setAssociation_id(int association_id) {
		if(association_id<0)
			throw new IllegalArgumentException("can't be negative");
		else
		this.association_id = association_id;
	}

	public String getAssociationName() {
		return associationName;
	}

	public void setAssociationName(String associationName) {
		if(this.associationName==null) 
				throw new RuntimeException("Given parameter(team) is null");
			else 
			this.associationName=associationName;	
				
	}
	
	
}
