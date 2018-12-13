package Assignment9;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class Originator {

	private Date state;

	   public void setState(Date state){
	      this.state = state;
	   }
	  
	   public Date getState(){
	      return state;
	   }

	   public Memento saveStateToMemento(){
	      return new Memento(state);
	   }

	   public void getStateFromMemento(Memento memento){
	      state = (Date) memento.getState();
	   }
}
