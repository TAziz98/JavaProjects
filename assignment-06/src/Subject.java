import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Subject implements Comparable<Subject>{
    private String name;
    private Department dept;
    private Teacher lecturer;
    private List<Student> students;

    public Subject(String name, Department dept, Teacher lecturer) {
        this.name = name;
        this.dept = dept;
        this.lecturer = lecturer;
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Department getDept() {
        return dept;
    }

    public Teacher getLecturer() {
        return lecturer;
    }

    public List<Student> getStudents() {
        return students;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.dept);
        hash = 37 * hash + Objects.hashCode(this.lecturer);
        hash = 37 * hash + Objects.hashCode(this.students);
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
        final Subject other = (Subject) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.dept, other.dept)) {
            return false;
        }
        if (!Objects.equals(this.lecturer, other.lecturer)) {
            return false;
        }
        if (!Objects.equals(this.students, other.students)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Subject o) {
        int r = this.getName().compareTo(o.getName());
        if(r != 0) return r;
        r = this.getDept().compareTo(o.getDept());
        if(r != 0) return r;
        r = this.getLecturer().compareTo(o.getLecturer());
        if(r != 0) return r;
        if(this.getStudents().containsAll(o.getStudents()) && this.getStudents().size() == o.getStudents().size())
            return 0;
        if(this.getStudents().size() > o.getStudents().size()){
            return 1;
        }
        else return -1;
        
    }
    
    
    
    
}
