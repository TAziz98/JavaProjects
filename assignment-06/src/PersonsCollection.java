import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;


public class PersonsCollection {
    private static Set<Person> persons = new HashSet<>();

    public static Set<Person> getPersons() {
        return persons;
    }
    
    public static List<Person> getPersonsSortedByPolish(){
        List<Person> list = persons.stream().collect(Collectors.toList());
        list = list.stream()
               .sorted(new PolishComparator())
               .collect(Collectors.toList());
        return list;
    }
    
    public static List<Person> getPersonsByNationality(Locale locale){
        List<Person> list = persons.stream().collect(Collectors.toList());
        list = list.stream()
                   .filter(e -> e.getNation().getLocale().equals(locale))
                   .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                   .collect(Collectors.toList());
        Collections.sort(list, new LocalesComparator(locale));
        return list;
    }
    
}
