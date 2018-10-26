package assignment01;

import java.util.ArrayList;
import java.util.List;

public class MyContainer <S extends IAggregable<S, R> & IDeeplyCloneable<S>, R> implements IContainer<S, R> {
	
	private ArrayList<S> list;
	
	@Override
	public List<S> elements() {
		list = new ArrayList<>();
		return list;
	}

	@Override
	public R aggregateAllElements() {
		if(list != null && list.size() > 0) {
			R result = null;
			for(S elem: list) {
				if(elem != null)
					result = elem.aggregate(result);
			}
			return result;
		}
		else return null;
	}

	@Override
	public S cloneElementAtIndex(int index) {
		S s = null;
		if(list != null && index < list.size()) {
			if(list.get(index) != null) {
				s = list.get(index).deepClone();
				return s;
			}
			else return s;
		}
		else return s;	
	}

}
