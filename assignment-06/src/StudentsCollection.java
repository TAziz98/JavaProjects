import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;


public class StudentsCollection {
    private static Set<Student> students = new HashSet<>();
    
    public static Set<Student> getStudents() {
        return students;
    }
    
    public static List<Student> getStudentsSortedByPolish(){
        List<Student> list = students.stream().collect(Collectors.toList());
        list = list.stream()
               .sorted(new PolishComparator())
               .collect(Collectors.toList());
        return list;
    }
    
    public static List<Student> getStudentsByNationality(Locale locale){
        List<Student> list = students.stream().collect(Collectors.toList());
        list = list.stream()
                   .filter(e -> e.getNation().getLocale().equals(locale))
            
                   .collect(Collectors.toList());
        Collections.sort(list, new LocalesComparator(locale));
        return list;
    }
    
}
