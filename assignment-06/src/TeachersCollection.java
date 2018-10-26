import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;


public class TeachersCollection {
    private static Set<Teacher> teachers = new HashSet<>();

    public static Set<Teacher> getTeachers() {
        return teachers;
    }
    
    public static List<Teacher> getTeachersSortedByPolish(){
        List<Teacher> list = teachers.stream().collect(Collectors.toList());
        list = list.stream()
               .sorted(new PolishComparator())
               .collect(Collectors.toList());
        return list;
    }
    
    public static List<Teacher> getTeachersByNationality(Locale locale){
        List<Teacher> list = teachers.stream().collect(Collectors.toList());
        list = list.stream()
                   .filter(e -> e.getNation().getLocale().equals(locale))
                   .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
                   .collect(Collectors.toList());
        Collections.sort(list, new LocalesComparator(locale));
        return list;
    }
    
}
