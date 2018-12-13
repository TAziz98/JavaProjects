package Assignment9;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
// Politechnika Warszawska
public class ThirdReceiver extends Receivers implements Observer {
	 
	private Date dateStoredByProgram; 
	private String URL;

    
    public Date getDateStoredByProgram() {
		return dateStoredByProgram;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public void setDate(Date dateReturnedFromServer) {
		this.dateStoredByProgram = dateReturnedFromServer;
	}

    //Receives notification
	@Override
    public void update(Observable o, Object dateReturnedFromServer) {
		 if(Notifier.className.equals(this.getClass().getName())) {
		 message((Date)dateReturnedFromServer);
        this.setDate((Date)dateReturnedFromServer);
		 } 
		 else message((Date)this.dateStoredByProgram) ;
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
	     System.out.println("[3rd "+this.getClass().getName()+"]"+" Web page has been modified at :"+dateReturnedFromServer);	 
	else System.out.println("[3rd "+this.getClass().getName()+"]"+" Last time was modified at :"+dateReturnedFromServer);
	}catch(Exception exception) {

		System.out.println("[3rd "+this.getClass().getName()+"]"+"waiting...");
	}
	
	}	
}
