import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Department implements Comparable<Department>{
    private String name;
    private List<Teacher> employees;

    public Department(String name) {
        this.name = name;
        employees = new ArrayList<>();
    }

    public List<Teacher> getEmployees() {
        return employees;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Department o){
        int r = this.getName().compareTo(o.getName());
        if(r != 0) return r;
        if(this.getEmployees().containsAll(o.getEmployees()) && this.getEmployees().size() == o.getEmployees().size())
            return 0;
        if(this.getEmployees().size() > o.getEmployees().size()){
            return 1;
        }
        else return -1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.name);
        hash = 11 * hash + Objects.hashCode(this.employees);
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
        final Department other = (Department) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.employees, other.employees)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
