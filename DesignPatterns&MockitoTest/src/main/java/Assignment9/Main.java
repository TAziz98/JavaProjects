package Assignment9;
import java.io.IOException;
import java.net.URLConnection;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
	   public static void main(String[] args) throws IOException, InterruptedException  
	    { 
          
		   Notifier notifier = new Notifier();
		   FirstReceiver receiver = new FirstReceiver();
		   receiver.setURL(new String("http://www.pja.edu.pl/"));
		   notifier.addObserver(receiver);
		   notifier.addConnections(receiver.getClass().getName(),notifier.urlConnection(receiver.getURL()));
		   
		   SecondReceiver secondreceiver = new SecondReceiver();
		   secondreceiver.setURL(new String("https://www.kozminski.edu.pl/"));
		   notifier.addObserver(secondreceiver);
		   notifier.addConnections(secondreceiver.getClass().getName(),notifier.urlConnection(secondreceiver.getURL()));
		   
		   ThirdReceiver thirdreceiver = new ThirdReceiver();
		   thirdreceiver.setURL(new String("https://www.pw.edu.pl/"));
		   notifier.addObserver(thirdreceiver);
		   notifier.addConnections(thirdreceiver.getClass().getName(),notifier.urlConnection(thirdreceiver.getURL()));
		
		   while(true) {
		   Thread thread = new Thread(){
	       public void run(){
	    	   
 		   for (Map.Entry<String, URLConnection> connection : notifier.getList().entrySet()) {
 			    try {
 			    	notifier.specification(connection.getKey());
					notifier.setDate(notifier.getLastModifiedMethod(connection.getValue()));
				} catch (IOException exception) {
					// TODO Auto-generated catch block
					System.out.println(exception);
				}	
				
			}
		   }   
		   };
		   thread.start();
		   //every minute
 		    thread.sleep(60000);
	
	    }
	    }
}
