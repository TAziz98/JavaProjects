import java.util.HashSet;
import java.util.Set;


public class StudentGroupsCollection {
    private static  Set<StudentGroup> groups = new HashSet<>();

    public static Set<StudentGroup> getStudentGroups() {
        return groups;
    }
    
}
