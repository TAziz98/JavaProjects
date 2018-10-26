package assignment01.sample;

import assignment01.IAggregable;
import assignment01.IDeeplyCloneable;

public class Word implements IAggregable<Word, String>, IDeeplyCloneable<Word>{
	
	private String letters;
	
	
	public Word(String letters) {
		this.letters = letters;
	}

	@Override
	public Word deepClone() {
		Word clone = new Word(this.letters);
		return clone;
	}

	@Override
	public String aggregate(String intermediateResult) {
		if(intermediateResult == null)
			return letters;
		else return  intermediateResult + letters ;
	}

	@Override
	public String getResult() {
		return letters;
	}

	@Override
	public String toString() {
		return "Word [letters=" + letters + "]";
	}
	

}
