package assignment08.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import assignment08.Assignment08Exception;
import assignment08.Person;

public class PersonTest {

	@Test
	public void serializeAndDeserialize() {
		DataOutputStream out = null;
		DataInputStream in = null;
		try {
			out = new DataOutputStream(new FileOutputStream(new File("PersonTest.dat")));
			in = new DataInputStream(new FileInputStream("PersonTest.dat"));
			
			Person person = new Person("Vlad", "Dracula", new Date(0, 4, 24));
			person.serialize(out);
			
			Person readPerson = Person.deserialize(in);
			
			Assert.assertEquals(person, readPerson);
			
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