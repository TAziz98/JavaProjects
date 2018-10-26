package assignment01.sample;

import assignment01.IAggregable;
import assignment01.IDeeplyCloneable;

public class Person implements IAggregable<Person, Integer>, IDeeplyCloneable<Person> {
	
	private int _age;
	
	public Person(int _age) {
		this._age = _age;
	}

	public Integer aggregate(Integer intermediateResult) {
		if  (intermediateResult == null) {
			return _age;
		}
		return _age + intermediateResult;
	}
	
	public Person deepClone() {
		Person clone = new Person(this._age);
		return clone;
	}

	@Override
	public Integer getResult() {
		return _age;
	}
	
}