package Assignment9;

import static org.junit.Assert.*;

import java.io.IOException;import java.net.URLConnection;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import junit.framework.Assert;
public class TestClass {

	@Test
	public void testLastTimeModifiedFirstReceiver() throws IOException {	
	    Originator originator = new Originator();
	    Notifier notifier = mock(Notifier.class);
	    CareTaker caretaker = new CareTaker();
		FirstReceiver receiver = mock(FirstReceiver.class);
		receiver.setURL(new String("http://www.pja.edu.pl/"));
		notifier.setDate(notifier.getLastModifiedMethod(notifier.urlConnection(receiver.getURL())));
		originator.setState((Date)receiver.getDateStoredByProgram());
        caretaker.add(originator.saveStateToMemento()); 
      	when(receiver.getDateStoredByProgram()).thenReturn(originator.getState());
		Assert.assertEquals(originator.getState(),receiver.getDateStoredByProgram());
		
	}
	
	@Test
	public void testLastTimeModifiedSecondReceiver() throws IOException {	
	    Originator originator = new Originator();
	    Notifier notifier = mock(Notifier.class);
	    CareTaker caretaker = new CareTaker();
		SecondReceiver secondreceiver = mock(SecondReceiver.class);
		secondreceiver.setURL(new String("https://www.kozminski.edu.pl/"));
		notifier.setDate(notifier.getLastModifiedMethod(notifier.urlConnection(secondreceiver.getURL())));
		originator.setState((Date)secondreceiver.getDateStoredByProgram());
        caretaker.add(originator.saveStateToMemento()); 
      	when(secondreceiver.getDateStoredByProgram()).thenReturn(originator.getState());
		Assert.assertEquals(originator.getState(),secondreceiver.getDateStoredByProgram());
		
	}
	@Test
	public void testLastTimeModifiedThirdReceiver() throws IOException {	
	    Originator originator = new Originator();
	    Notifier notifier = mock(Notifier.class);
	    CareTaker caretaker = new CareTaker();
		ThirdReceiver thirdreceiver = mock(ThirdReceiver.class);
		thirdreceiver.setURL(new String("https://www.pw.edu.pl/"));
		notifier.setDate(notifier.getLastModifiedMethod(notifier.urlConnection(thirdreceiver.getURL())));
		originator.setState((Date)thirdreceiver.getDateStoredByProgram());
        caretaker.add(originator.saveStateToMemento()); 
      	when(thirdreceiver.getDateStoredByProgram()).thenReturn(originator.getState());
		Assert.assertEquals(originator.getState(),thirdreceiver.getDateStoredByProgram());
		
	}
	
	
	
	@Test
		public void testgetURL() throws IOException {	
			FirstReceiver receiver = mock(FirstReceiver.class);
	        when(receiver.getURL()).thenReturn(new String("http://www.pja.edu.pl/"));
			Assert.assertEquals(new String("http://www.pja.edu.pl/"),receiver.getURL());
			
			SecondReceiver secondreceiver = mock(SecondReceiver.class);
	        when(secondreceiver.getURL()).thenReturn(new String("https://www.kozminski.edu.pl/"));
			Assert.assertEquals(new String("https://www.kozminski.edu.pl/"),secondreceiver.getURL());
			
			ThirdReceiver thirdreceiver = mock(ThirdReceiver.class);
	        when(thirdreceiver.getURL()).thenReturn(new String("https://www.pw.edu.pl/"));
			Assert.assertEquals(new String("https://www.pw.edu.pl/"),thirdreceiver.getURL());
			
		}
		
	}
	
