package Assignment9;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
//Polska-Japonska Akademia Technik Komputerowych
public class FirstReceiver extends Receivers implements Observer {

	private Date dateStoredByProgram; 
	private String URL;
	Originator originator = new Originator();
	CareTaker caretaker = new CareTaker();

    


	public void setURL(String URL) {
		this.URL = URL;
	}

	public void setDate(Date dateReturnedFromServer) {
		this.dateStoredByProgram = dateReturnedFromServer;
	}

    public Date getDateStoredByProgram() {
		return dateStoredByProgram;
	}

	//Receives notification
	@Override
    public void update(Observable o, Object dateReturnedFromServer) {
	//	if(specification())
		 if(Notifier.className.equals(this.getClass().getName())) {
		 message((Date)dateReturnedFromServer);
         this.setDate((Date)dateReturnedFromServer);
         originator.setState((Date)dateReturnedFromServer);
         caretaker.add(originator.saveStateToMemento());
         //For Testing
         //System.out.println("Current state of First Receiver: "+originator.getState());
         // originator.getStateFromMemento(caretaker.get(0));
         //System.out.println("Current state of Second Receiver: "+originator.getState());
		 }
		 else message((Date)this.dateStoredByProgram);
    }
	

	@Override
	public  String getURL() throws IOException {
		// TODO Auto-generated method stub
		return URL; //new String("http://www.pja.edu.pl/")
	}

	@Override
	public void message(Date dateReturnedFromServer) {
		// TODO Auto-generated method stub
		try {
			
		 if(!dateReturnedFromServer.equals(this.dateStoredByProgram)) 
	     System.out.println("[1st "+this.getClass().getName()+"]"+" Web page has been modified at :"+dateReturnedFromServer);	 
	else System.out.println("[1st "+this.getClass().getName()+"]"+" Last time was modified at :"+dateReturnedFromServer);
		}catch(Exception exception) {
			System.out.println("[1st "+this.getClass().getName()+"]"+"waiting...");	
		}
	
}

}