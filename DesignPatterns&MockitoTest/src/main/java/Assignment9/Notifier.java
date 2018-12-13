package Assignment9;
import java.awt.List;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

    public class Notifier extends Observable {
	public static  String className;
	private Date time;
	private Map<String,URLConnection> list ;
	 
	public Notifier() {		
    list = new HashMap<>(); 
	}
	
	public URLConnection urlConnection(String url) throws IOException {
     URLConnection urlcon = new URL(url).openConnection();
	 return urlcon;
	}
	
	public Date getTime() {
		return time;
	}

	public Date getLastModifiedMethod(URLConnection urlcon) throws IOException {
			return new Date(urlcon.getLastModified());
		}
	
	public void specification(String className) {
		this.className=className;
	}
    //Notifies
   public void setDate(Date timeSet) {	   
    	this.time=timeSet;
        setChanged();
     //   for(Observer obs :this.getClass()).       
        notifyObservers(timeSet);
    }
   
   public void addConnections(String key,URLConnection connection) {
	   list.put(key, connection);
	   
   }
 
public Map<String,URLConnection> getList() {
	// TODO Auto-generated method stub
	return list;
}
}
