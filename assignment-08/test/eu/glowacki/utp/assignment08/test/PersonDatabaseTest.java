package eu.glowacki.utp.assignment08.test;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment08.Assignment08Exception;
import eu.glowacki.utp.assignment08.Person;
import eu.glowacki.utp.assignment08.PersonDatabase;

public class PersonDatabaseTest {

	@Test
	public void serializeAndDeserialize() {	
		DataOutputStream out = null;
		DataInputStream in = null;
		try {
			out = new DataOutputStream(new FileOutputStream(new File("PersonDatabaseTest.dat")));
			in = new DataInputStream(new FileInputStream("PersonDatabaseTest.dat"));
			
			Person p1 = new Person("Vlad", "Dracula", new Date(0, 4, 24));
			Person p2 = new Person("Jack", "Ripper", new Date(-12, 8, 23));
			Person p3 = new Person("Alfonco", "Capone" , new Date(11, 7, 13));
			PersonDatabase db = new PersonDatabase();
			List<Person> persons = db.getPersons();
			persons.add(p1);
			persons.add(p2);
			persons.add(p3);
			
			db.serialize(out);
			
			PersonDatabase readDb = PersonDatabase.deserialize(in);
			List<Person> readPersons = readDb.getPersons();
			
			Assert.assertEquals(persons, readPersons);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Assignment08Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}