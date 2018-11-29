package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import junit.framework.Assert;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		Assert.assertEquals("SweBank",SweBank.getName());
		Assert.assertEquals("DanskeBank",DanskeBank.getName());
		Assert.assertEquals("Nordea",Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
		Assert.assertEquals(SEK,SweBank.getCurrency());
		Assert.assertEquals(DKK,DanskeBank.getCurrency());
		Assert.assertEquals(SEK,Nordea.getCurrency());
		
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		Assert.assertEquals("Ulrika",SweBank.getAccount("Ulrika").accountName);
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		Money SEK20 = new Money(2000,SEK);
		Nordea.deposit("Bob", SEK20);
		Assert.assertEquals(new Integer(2000),Nordea.getBalance("Bob"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
        DanskeBank.deposit("Gertrud", new Money(100,DKK));
        DanskeBank.withdraw("Gertrud", new Money(100,DKK));
		Assert.assertEquals(new Integer(0),DanskeBank.getBalance("Gertrud"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		Assert.assertEquals(new Integer(0),SweBank.getBalance("Bob"));
	}
	

	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		Money SEK20 = new Money(2000,SEK);
		Nordea.deposit("Bob", SEK20);
		Nordea.transfer("Bob",SweBank,"Ulrika", new Money(1000,SEK));
		Assert.assertEquals(new Integer(1000),Nordea.getBalance("Bob"));
	}
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		Nordea.addTimedPayment("Bob", "1", 5, 2, new Money(10,SEK), SweBank, "Bob");
	    Assert.assertEquals(true,Nordea.getAccount("Bob").timedPaymentExists("1"));
	    Nordea.removeTimedPayment("Bob", "1");
	    Assert.assertEquals(false,Nordea.getAccount("Bob").timedPaymentExists("1"));
	}
	
	@Test
	public void testTick() throws AccountDoesNotExistException {
		Nordea.getAccount("Bob").deposit(new Money(10,SEK));
		Nordea.addTimedPayment("Bob", "1", 2, 0, new Money(1000,SEK), SweBank, "Bob");
		Nordea.tick();
		 Assert.assertEquals(new Integer(1000),SweBank.getAccount("Bob").getBalance().getAmount());
	}
	
	
	
}
