import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Test;


public class CollectionsTest {
    
    public CollectionsTest() {
        Generator.generateData();
    }
    
    @Test
    public void personCollectionsTest() {
        List<Person> sortByPolishActual = PersonsCollection.getPersonsSortedByPolish();
        List<Person> sortByPolishExpected = new ArrayList<>(PersonsCollection.getPersons());
        Collections.sort(sortByPolishExpected, new PolishComparator()); 
        Assert.assertEquals(sortByPolishActual, sortByPolishExpected);
        
        Locale locale = new Locale("Ukrainian");
        List<Person> sortByNationalityActual = PersonsCollection.getPersonsByNationality(locale);
        List<Person> sortByNationalityExpected = new ArrayList<>(PersonsCollection.getPersons());
        sortByNationalityExpected = sortByNationalityExpected.stream()
                   .filter(e -> e.getNation().getLocale().equals(locale))
                   .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                   .collect(Collectors.toList());
        Collections.sort(sortByNationalityExpected, new LocalesComparator(locale)); 
        Assert.assertEquals(sortByNationalityExpected, sortByNationalityActual);
        
    }
    
    @Test
    public void teacherCollectionsTest() {
        List<Teacher> sortByPolishActual = TeachersCollection.getTeachersSortedByPolish();
        List<Teacher> sortByPolishExpected = new ArrayList<>(TeachersCollection.getTeachers());
        Collections.sort(sortByPolishExpected, new PolishComparator()); 
        Assert.assertEquals(sortByPolishActual, sortByPolishExpected);
        
        Locale locale = new Locale("Ukrainian");
        List<Teacher> sortByNationalityActual = TeachersCollection.getTeachersByNationality(locale);
        List<Teacher> sortByNationalityExpected = new ArrayList<>(TeachersCollection.getTeachers());
        sortByNationalityExpected = sortByNationalityExpected.stream()
                   .filter(e -> e.getNation().getLocale().equals(locale))
                   .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                   .collect(Collectors.toList());
        Collections.sort(sortByNationalityExpected, new LocalesComparator(locale)); 
        Assert.assertEquals(sortByNationalityExpected, sortByNationalityActual);
    }
    
    @Test
    public void studentCollectionsTest() {
        List<Student> sortByPolishActual = StudentsCollection.getStudentsSortedByPolish();
        List<Student> sortByPolishExpected = new ArrayList<>(StudentsCollection.getStudents());
        Collections.sort(sortByPolishExpected, new PolishComparator()); 
        Assert.assertEquals(sortByPolishActual, sortByPolishExpected);
        
        Locale locale = new Locale("Ukrainian");
        List<Student> sortByNationalityActual = StudentsCollection.getStudentsByNationality(locale);
        List<Student> sortByNationalityExpected = new ArrayList<>(StudentsCollection.getStudents());
        sortByNationalityExpected = sortByNationalityExpected.stream()
                   .filter(e -> e.getNation().getLocale().equals(locale))
                   .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                   .collect(Collectors.toList());
        Collections.sort(sortByNationalityExpected, new LocalesComparator(locale)); 
        Assert.assertEquals(sortByNationalityExpected, sortByNationalityActual);
    }
    
}
