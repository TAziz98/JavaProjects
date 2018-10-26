package eu.glowacki.utp.assignment08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public final class PersonDatabase {
    private List<Person> persons;
    
    public PersonDatabase() {
    	persons = new ArrayList<>();
    }
	// assignment 8 - factory method based on deserialization
	public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception{
		PersonDatabase db = new PersonDatabase();
		List<Person> list = db.getPersons();
		try {
			while(input.available() > 0) {
				Person p = Person.deserialize(input);
				list.add(p);
			}
		} catch (IOException e) {
			throw new Assignment08Exception(e.getMessage(), e.getCause());
		}
		return db;
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception{
		for(Person p : persons)
			p.serialize(output);
	}

	public List<Person> sortedByFirstName() {
        Collections.sort(persons, new FirstNameComparator());
        return persons;
    }

    public List<Person> sortedBySurnameFirstNameAndBirthdate() {
        Collections.sort(persons);
        return persons;
    }

    public List<Person> sortedByBirthdate() {
        Collections.sort(persons, new BirthdateComparator());
        return persons;
    }

    public List<Person> bornOnDay(Date date) {
    	if(date != null)
    		return getPersonsBornOnDay().get(date);
    	else
    		return null;
    }
    
	private Map<Date, List<Person>> getPersonsBornOnDay() {
		Map<Date, List<Person>> map = new HashMap<>();
		for (Person p : persons) {
			if (!map.containsKey(p.getBirthdate())) 
				map.put(p.getBirthdate(), new ArrayList<Person>());
			for (Date key : map.keySet()) {
				if (key.equals(p.getBirthdate())) 
					map.get(key).add(p);		
			}	
		}
		return map;
	}
    
    public List<Person> getPersons() {
		return persons;
	}
    
	private List<Person> getPersonsFromFile(){
    	return InputParser.parse(new File("People.txt"));
    }
}