package assignment01;

public class Car implements IAggregable<Car, Integer>, IDeeplyCloneable<Car> {

	private int hoursePower;
	
	public Car(int hoursePower) {
		this.hoursePower = hoursePower;
	}

	@Override
	public Integer aggregate(Integer intermediateResult) {
		if (intermediateResult == null) {
			return hoursePower;
		} else
			return hoursePower + intermediateResult;
	}

	@Override
	public Car deepClone() {
		Car car = new Car(this.hoursePower);
		return car;

	}

	@Override
	public Integer getResult() {
		return hoursePower;
	}

	@Override
	public String toString() {
		return "Car [hoursePower=" + hoursePower + "]";
	}
	
}