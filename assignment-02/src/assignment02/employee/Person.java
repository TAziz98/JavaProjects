package assignment02.employee;

import java.util.Date;

public abstract class Person {
    private final String firstName; // backing field
    private String surname;

    @Override
    public String toString() {
        return "Person{" + "firstName=" + firstName + ", surname=" + surname + ", dateOfBirth=" + dateOfBirth + ", age=" + age + '}';
    }
    private Date dateOfBirth;
    private int age;
    // To implement an attribute means that you provide a backing field and
    // getter or optionally setter for read-write properties/attributes
    // 
    // NO BACKING FIELDS SHOULD BE PROVIDED FOR DERIVED ATTRIBUTES
    // THOSE SHOULD BE COMPUTED ON-LINE
    //
    // attributes:
    // * first name (read-only)
    // * surname (read-only)
    // * birth date (read-only) --- date MUST BE represented by an instance of
    // type designed for date representation (either Date or LocalDate)
    //
    // * age (derived --- computed based on birth date) --- implemented as a
    // getter calculating the difference between the current date and birth date

    protected Person(String firstName, String surname, Date dateOfBirth) {
        this.firstName = firstName;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        age = new Date().getYear() - this.dateOfBirth.getYear();
    }
    

    

    public String getFirstName() { // getter
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(!(obj instanceof Person))
            return false;
        Person p = (Person) obj;
        boolean r = firstName!=null && firstName.equals(p.getFirstName());
        boolean r1 = surname!=null && surname.equals(p.getSurname());
        boolean r2 = dateOfBirth!=null && dateOfBirth.equals(p.getDateOfBirth());
        boolean r3 =  age==p.getAge();
        return r && r1 && r2 && r3;
    }

    @Override
    public int hashCode() {
        int hashCode = 31;
        hashCode = 31*hashCode+firstName.hashCode();
        hashCode = 31*hashCode+surname.hashCode();
        hashCode = 31*hashCode+dateOfBirth.hashCode();
        hashCode = 31*hashCode+Long.valueOf(age).hashCode();
        return hashCode;      
    }
}