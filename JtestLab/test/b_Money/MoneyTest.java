package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
		Assert.assertEquals(new Integer(10000), SEK100.getAmount());
		Assert.assertEquals(new Integer(2000), EUR20.getAmount());
		Assert.assertEquals(new Integer(-10000), SEKn100.getAmount());
	}

	@Test
	public void testGetCurrency() {
		Assert.assertEquals(SEK, SEK100.getCurrency());
		Assert.assertEquals(EUR, EUR10.getCurrency());
		Assert.assertEquals(SEK, SEK0.getCurrency());
	}

	@Test
	public void testToString() {
		Assert.assertEquals("100 SEK", SEK100.toString());
		Assert.assertEquals("20 EUR", EUR20.toString());
		Assert.assertEquals("0 EUR", EUR0.toString());
	}

	@Test
	public void testGlobalValue() {
		Assert.assertEquals(new Integer(1500), SEK100.universalValue()); 
		Assert.assertEquals(new Integer(3000), EUR20.universalValue()); 
		Assert.assertEquals(new Integer(1500), EUR10.universalValue()); 
	}

	@Test
	public void testEqualsMoney() {
		Assert.assertEquals(new Boolean(false), SEK200.equals(EUR10)); 
		Assert.assertEquals(new Boolean(true), SEK100.equals(EUR10)); 
		Assert.assertEquals(new Boolean(true), SEK200.equals(EUR20)); 
	}

	@Test
	public void testAdd() {
		Assert.assertEquals("40 EUR", EUR20.add(EUR20).toString());
		Assert.assertEquals("300 SEK", SEK200.add(SEK100).toString());
		Assert.assertEquals("200 SEK", SEK100.add(EUR10).toString());
	}

	@Test
	public void testSub() {
		Assert.assertEquals("10 EUR", EUR20.sub(EUR10).toString());
		Assert.assertEquals("100 SEK", SEK200.sub(SEK100).toString());

	}

	@Test
	public void testIsZero() {
		Assert.assertEquals(new Boolean(false), EUR20.isZero());
		Assert.assertEquals(new Boolean(true), EUR0.isZero());
	}

	@Test
	public void testNegate() {
		Assert.assertEquals("-10 EUR", EUR10.negate().toString());
		Assert.assertEquals("100 SEK", SEKn100.negate().toString());
		
	}
	@Test
	public void testCompareTo() {
		Assert.assertEquals(0, SEK100.compareTo(SEK100));
		Assert.assertEquals(-1, SEK100.compareTo(EUR20));
		Assert.assertEquals(1, SEK200.compareTo(SEK0));
	}


}
