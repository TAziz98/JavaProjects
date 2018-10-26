import java.util.HashSet;
import java.util.Set;


public class DepartmentsCollection {
    private static Set<Department> departments = new HashSet<>();

    public static Set<Department> getDepartments() {
        return departments;
    }

    
}

