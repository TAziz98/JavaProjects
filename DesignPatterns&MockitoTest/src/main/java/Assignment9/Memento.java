package Assignment9;
import java.io.Serializable;
import java.util.Date;

public class Memento implements Serializable{

	 private Date state;

	   public Memento(Date state){
	      this.state = state;
	   }

	   public Date getState(){
	      return state;
	   }
}
