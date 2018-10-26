import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;


public class PolishComparator implements Comparator<Person>{
    Collator pl = Collator.getInstance(new Locale("Polish"));
            
    @Override
    public int compare(Person o1, Person o2) {
    	int r = Long.compare(o1.getPESEL(), o2.getPESEL());
        if(r != 0) return r;
        r = pl.compare(o1.getSurname(), (o2.getSurname()));
        if(r != 0) return r;
        r = pl.compare(o1.getName(), (o2.getName()));
        if(r != 0) return r;
        r = o1.getBirthDate().compareTo(o2.getBirthDate());
        if(r != 0) return r;
        r = pl.compare(o1.getNation().toString(), (o2.getNation().toString()));
        if(o1 instanceof Teacher && o2 instanceof Teacher){
            Teacher t1 = (Teacher) o1;
            Teacher t2 = (Teacher) o2;
            r = pl.compare(t1.getDegree().toString(), (t2.getDegree().toString()));
            if(r != 0) return r;
            r = t1.getHiredate().compareTo(t2.getHiredate());
        }
        if(o1 instanceof Student && o2 instanceof Student){
            Student s1 = (Student)o1;
            Student s2 = (Student)o2;
            r = Long.compare(s1.getStudentNumber(), s2.getStudentNumber());
        }
        return r;
    }
    
}
