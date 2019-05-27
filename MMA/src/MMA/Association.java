package MMA;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ASSOCIATION")
public class Association {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int association_id;
		
		
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "association",fetch = FetchType.EAGER)	
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
}
