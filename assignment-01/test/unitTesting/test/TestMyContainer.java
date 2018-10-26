package unitTesting.test;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import eu.glowacki.utp.assignment01.Car;
import eu.glowacki.utp.assignment01.MyContainer;
import eu.glowacki.utp.assignment01.sample.Word;

public class TestMyContainer {
	private MyContainer<Car, Integer> cars;
	private MyContainer<Word, String> words;
	private Car car1; 
	private Car car2; 
	private Word word1;
	private Word word2;
	private ArrayList<Car> listForCars;
	private ArrayList<Word> listForWords;
	
	@Before
	public void setUp() {
		cars = new MyContainer<>();
		car1 = null;
		car2 = null;
		listForCars = new ArrayList<>();
		
		words = new MyContainer<>();
		word1 = new Word("ala");
		word2 = new Word("qwer");
		
	}

	@Test
	public void testElements() { 
		Assert.assertEquals(listForCars, cars.elements());
	}
	
	@Test
	public void testAggregateAllElements() {
		/*listForCars = (ArrayList<Car>)cars.elements();
		listForCars.add(car1);
		listForCars.add(car2);
		Assert.assertEquals(new Integer(0), cars.aggregateAllElements());*/
		listForWords = (ArrayList<Word>)words.elements();
		listForWords.add(word1);
		listForWords.add(word2);
		Assert.assertEquals("alaqwer", words.aggregateAllElements());
	}
	
	
	@Test
	public void testCloneElementAtIndex() {
		listForCars = (ArrayList<Car>) cars.elements();
		listForCars.add(car1);
		listForCars.add(car2);
		Assert.assertEquals(null, cars.cloneElementAtIndex(1));
	}
	
}