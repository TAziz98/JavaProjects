import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class StudentGroup implements Comparable<StudentGroup>{
    private String name;
    private Set<Student> students;

    public StudentGroup(String name) {
        this.name = name;
        students = new HashSet<>();
    }

    public Set<Student> getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }
    
    
    @Override
    public int compareTo(StudentGroup o) {
        int r = this.getName().compareTo(o.getName());
        if(r != 0) return r;
        if(this.getStudents().containsAll(o.getStudents()) && this.getStudents().size() == o.getStudents().size())
            return 0;
        if(this.getStudents().size() > o.getStudents().size()){
            return 1;
        }
        else return -1;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.name);
        hash = 89 * hash + Objects.hashCode(this.students);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final StudentGroup other = (StudentGroup) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.students, other.students)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
