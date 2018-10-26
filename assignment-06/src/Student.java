import java.util.Date;


public class Student extends Person{
    private long studentNumber;

    public Student(long studentNumber, long PESEL, String surname, String name, Date birthDate, Nationality nation) {
        super(PESEL, surname, name, birthDate, nation);
        this.studentNumber = studentNumber;
    }

    public long getStudentNumber() {
        return studentNumber;
    }

    @Override
    public int hashCode() {
        int hash = super.hashCode();
        hash = 37 * hash + (int) (this.studentNumber ^ (this.studentNumber >>> 32));
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
        final Student other = (Student) obj;
        boolean r = super.equals(obj);
        boolean r1 = studentNumber == other.getStudentNumber();  
        return r && r1;
    }

    @Override
    public String toString() {
        return super.toString() + ", studentNumber=" + studentNumber + '}';
    }
        
}

