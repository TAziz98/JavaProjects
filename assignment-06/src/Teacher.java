import java.util.Date;
import java.util.Objects;


enum Degree{ASSOCIATE, BACHELOR, MASTER, DOCTOR}
public class Teacher extends Person {
    private Degree degree;
    private Date hiredate;

    public Teacher(Degree degree, Date hiredate, long PESEL, String surname, String name, Date birthDate, Nationality nation) {
        super(PESEL, surname, name, birthDate, nation);
        this.degree = degree;
        this.hiredate = hiredate;
    }

    public Degree getDegree() {
        return degree;
    }

    public Date getHiredate() {
        return hiredate;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 89 * hash + Objects.hashCode(this.degree);
        hash = 89 * hash + Objects.hashCode(this.hiredate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean r = super.equals(obj);
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Teacher other = (Teacher) obj;
        boolean r1 = degree != null && degree.toString().equals(other.getDegree().toString());
        boolean r2 = hiredate != null && hiredate.equals(other.getHiredate());
        return r && r1 && r2;
    }

    @Override
    public String toString() {
        return super.toString() + ", degree=" + degree + ", hiredate=" + hiredate + '}';
    }
    
}
