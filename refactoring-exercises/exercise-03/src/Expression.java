public class Expression {

	private char op;

	private Expression left;

	private Expression right;

	//private int constant; ERROR
	//everywhere constant changed to double
	private double constant;

	public Expression(double constant) {
		this.op = 'c';
		this.constant = constant;
	}

	public Expression(char op, Expression left, Expression right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}

	public double evaluate() {
		switch (op) {
		case 'c':
			return constant;
		case '+':
			return left.evaluate() + right.evaluate();
		case '-':
			return left.evaluate() - right.evaluate();
		case '*':
			return left.evaluate() * right.evaluate();
		case '/':
			return left.evaluate() / right.evaluate();
		default:
			throw new IllegalStateException();
		}
	}
}
