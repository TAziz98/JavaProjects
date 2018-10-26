import java.util.Date;
import java.util.Objects;


public abstract class Person implements Comparable<Person>{
    private long PESEL;
    private String surname;
    private String name;
    private Date birthDate;
    private Nationality nation;

    public Person(long PESEL, String surname, String name, Date birthDate, Nationality nation) {
        this.PESEL = PESEL;
        this.surname = surname;
        this.name = name;
        this.birthDate = birthDate;
        this.nation = nation;
    }

    public long getPESEL() {
        return PESEL;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Nationality getNation() {
        return nation;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + (int) (this.PESEL ^ (this.PESEL >>> 32));
        hash = 19 * hash + Objects.hashCode(this.surname);
        hash = 19 * hash + Objects.hashCode(this.name);
        hash = 19 * hash + Objects.hashCode(this.birthDate);
        hash = 19 * hash + Objects.hashCode(this.nation);
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
        final Person other = (Person) obj;
        if (this.PESEL != other.PESEL) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.birthDate, other.birthDate)) {
            return false;
        }
        if (this.nation != other.nation) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Person p) {
        int r = Long.compare(this.getPESEL(), p.getPESEL());
        if(r != 0) return r;
        r = this.getSurname().compareTo(p.getSurname());
        if(r != 0) return r;
        r = this.getName().compareTo(p.getName());
        if(r != 0) return r;
        r = this.getBirthDate().compareTo(p.getBirthDate());
        if(r != 0) return r;
        r = this.getNation().toString().compareTo(p.getNation().toString());
        if(p instanceof Teacher){
            Teacher t = (Teacher) p;
            r = ((Teacher)this).getDegree().toString().compareTo(t.getDegree().toString());
            if(r != 0) return r;
            r = ((Teacher)this).getHiredate().compareTo(t.getHiredate());
        }
        if(p instanceof Student){
            Student s = (Student)p;
            r = Long.compare(((Student)this).getStudentNumber(), s.getStudentNumber());
        }
        return r;
    }

    @Override
    public String toString() {
        return "Person{" + "PESEL=" + PESEL + ", surname=" + surname + ", name=" + name + ", birthDate=" + birthDate + ", nation=" + nation + '}';
    }
    
    
    
}
