package eu.glowacki.utp.assignment08;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;


public class Person implements Comparable<Person> {

	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;

	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}

	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception{
		try {
			output.writeUTF(_firstName);
			output.writeUTF(_surname);
			output.writeLong(_birthdate.getTime());
		} catch (IOException e) {
			throw new Assignment08Exception(e.getMessage(), e.getCause());
		}
		
		
	}
	
	public static Person deserialize(DataInputStream input) throws Assignment08Exception{
		Person p = null;
		try {
			String name = input.readUTF();
			String surname = input.readUTF();
			Date date = new Date(input.readLong());
			p = new Person(name, surname, date);
		} catch (IOException e) {
			throw new Assignment08Exception(e.getMessage(), e.getCause());
		}
		return p;
		
	}

	@Override
    public int compareTo(Person otherPerson) {
        int result = this.getSurname().compareTo(otherPerson.getSurname());
        if (result != 0) {
            return result;
        }
        result = this.getFirstName().compareTo(otherPerson.getFirstName());
        if (result != 0) {
            return result;
        }
        return this.getBirthdate().compareTo(otherPerson.getBirthdate());
    }

    public String getFirstName() {
        return _firstName;
    }

    public String getSurname() {
        return _surname;
    }

    public Date getBirthdate() {
        return _birthdate;
    }

    @Override
    public String toString() {
        return "Person{" + "_firstName=" + _firstName + ", _surname=" + _surname + ", _birthdate=" + _birthdate + '}';
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(!(obj instanceof Person))
            return false;
        Person p = (Person) obj;
        boolean r = _firstName!=null && _firstName.equals(p.getFirstName());
        boolean r1 = _surname!=null && _surname.equals(p.getSurname());
        boolean r2 = _birthdate!=null && _birthdate.equals(p.getBirthdate());
        return r && r1 && r2;
    }

    @Override
    public int hashCode() {
        int hashCode = 31;
        hashCode = 31*hashCode+_firstName.hashCode();
        hashCode = 31*hashCode+_surname.hashCode();
        hashCode = 31*hashCode+_birthdate.hashCode();
        return hashCode;      
    }
}