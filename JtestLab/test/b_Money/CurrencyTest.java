package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		
	}

	@Test
	public void testGetName() {
	Assert.assertEquals("SEK", SEK.getName());
	Assert.assertEquals("DKK", DKK.getName());
	Assert.assertEquals("EUR", EUR.getName());
	}
	
	@Test
	public void testGetRate() {
		Assert.assertEquals(0.15, SEK.getRate());
		Assert.assertEquals(0.20, DKK.getRate());
		Assert.assertEquals(1.5, EUR.getRate());
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.43);
		Assert.assertEquals(0.43, SEK.getRate());	
		DKK.setRate(0.39);
		Assert.assertEquals(0.39, DKK.getRate());	
		EUR.setRate(0.62);
		Assert.assertEquals(0.62, EUR.getRate());	
	}
	
	@Test
	public void testGlobalValue() {
		Assert.assertEquals(new Integer(15), SEK.universalValue(100));
		Assert.assertEquals(new Integer(20), DKK.universalValue(100));
		Assert.assertEquals(new Integer(150), EUR.universalValue(100));
	}
	
	@Test
	public void testValueInThisCurrency() {
		Assert.assertEquals(new Integer(133), SEK.valueInThisCurrency(100,DKK));
		Assert.assertEquals(new Integer(750), DKK.valueInThisCurrency(100,EUR));
	}

}
