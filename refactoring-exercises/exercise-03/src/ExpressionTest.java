import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExpressionTest {

	@SuppressWarnings("deprecation")
	@Test
	public void testConstant() {
		Expression e = new Expression(-43);
		assertEquals(e.evaluate(), -43,0);
	}
	@Test
	public void testAddition() {
		Expression e = new Expression('+', new Expression(100), new Expression(
				-100));
		assertEquals(e.evaluate(), 0,0);
	}
	@Test
	public void testSubtraction() {
		Expression e = new Expression('-', new Expression(100), new Expression(
				-100));
		assertEquals(e.evaluate(), 200,0);
	}
	@Test
	public void testMultiplication() {
		Expression e = new Expression('*', new Expression(100), new Expression(
				-100));
		assertEquals(e.evaluate(), -10000,0);
	}
	@Test
	public void testDivision() {
		Expression e = new Expression('/', new Expression(100), new Expression(
				-100));
		assertEquals(e.evaluate(), -1,0);
	}
	@Test
	public void testComplexExpression() {
		// 1+2-3*4/5 
		// 1+2-3*4/5  = 0.6 
		Expression e = new Expression('-', new Expression('+',
				new Expression(1), new Expression(2)), new Expression('/',
				new Expression('*', new Expression(3), new Expression(4)),
				new Expression(5)));
		assertEquals(e.evaluate(), 0,6);
	}

}
