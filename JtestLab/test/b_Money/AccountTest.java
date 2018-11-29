package b_Money;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;

import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	

	@Test
	public void testAddWithdraw() {    
		testAccount.withdraw(new Money(10000,SEK));
			Assert.assertEquals(new Integer(9990000),testAccount.getBalance().getAmount());
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 5, 1, new Money(10000,SEK), SweBank,"Alice");
		testAccount.tick();
		Assert.assertEquals(new Integer(9990000),testAccount.getBalance().getAmount());
		

	}
	@Test
	public void testAddRemoveTimedPayment() {
		testAccount.addTimedPayment("2", 5, 1, new Money(10000,SEK), SweBank,"Alice");
		Assert.assertEquals(true,testAccount.timedPaymentExists("2"));
		testAccount.removeTimedPayment("2");
		Assert.assertEquals(false,testAccount.timedPaymentExists("2"));
	}
	
	@Test
	public void testGetBalance() {
		Assert.assertEquals(new Integer(10000000),testAccount.getBalance().getAmount());
	}
}
