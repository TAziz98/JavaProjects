import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class Generator {
    private static Random r = new Random();
    private static List<Person> persons = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static List<StudentGroup> studentsGroups = new ArrayList<>();
    private static List<Department> departments = new ArrayList<>();
    private static List<Subject> subjects = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();

    private static void generateStudents() {
        String[] names = {"Mark", "Adam", "Steven", "Marta", "Vova",
            "Lana", "Arnold", "Michael", "Milla", "Bruce",
            "Jackie", "Bob", "Richard", "Leo", "Vlad",
            "Alfonco", "Luca", "Peter", "Santino", "Tom",
            "Leroy", "Tayler", "Marla", "Adam", "Hannibal",
            "Sergio", "Andriy", "Martin", "Brady", "Kim",
            "Daniel", "Mario", "Fidel", "Victor", "Carli",
            "Alex", "Hope", "Robert", "Chuck", "David",
            "Kurt", "Chester", "Donald", "Kelley", "Gianluigi",
            "Andrea", "Frank", "Deborah", "Nikola", "Bill",
            "Mark", "Adam", "Steven", "Marta", "Vova",
            "Lana", "Arnold", "Michael", "Milla", "Bruce",
            "Alfonco", "Luca", "Peter", "Santino", "Tom",
            "Leroy", "Tayler", "Marla", "Adam", "Hannibal",
            "Daniel", "Mario", "Fidel", "Victor", "Carli",
            "Alex", "Hope", "Robert", "Chuck", "David",
            "Kurt", "Chester", "Donald", "Kelley", "Gianluigi",
            "Andrea", "Frank", "Deborah", "Nikola", "Bill",
            "Jackie", "Bob", "Richard", "Leo", "Vlad",
            "Alfonco", "Luca", "Peter", "Santino", "Tom"};
        
        String[] surnames = {"Kolodin", "Lallana", "Gerrard", "Abate", "Zvarych",
            "Winters", "Schwarzenegger", "Corleone", "Stallone", "Lee",
            "Chan", "Marley", "Dawkins", "Adler", "Dracula",
            "Capone", "Brasi", "Clemenza", "Corleone", "Hagen",
            "Sane", "Durden", "Singer", "Johnson", "Lecter",
            "Aguero", "Yarmolenko", "Scorsese", "Hartfield", "Kardashian",
            "Sturridge", "Balotelli", "Castro", "Valdes", "Lloyd",
            "Morgan", "Solo", "Dawney", "Palahniuk", "De Gea",
            "Cobain", "Bennington", "Trump", "O'Hara", "Buffon",
            "Pirlo", "Lampard", "Hartfield", "Tesla", "Hodges",
            "Capone", "Brasi", "Clemenza", "Corleone", "Hagen",
            "Sane", "Durden", "Singer", "Johnson", "Lecter",
            "Aguero", "Yarmolenko", "Scorsese", "Hartfield", "Kardashian",
            "Sturridge", "Balotelli", "Castro", "Valdes", "Lloyd",
            "Cobain", "Bennington", "Trump", "O'Hara", "Buffon",
            "Pirlo", "Lampard", "Hartfield", "Tesla", "Hodges",
            "Kolodin", "Lallana", "Gerrard", "Abate", "Zvarych",
            "Winters", "Schwarzenegger", "Corleone", "Stallone", "Lee",
            "Sturridge", "Balotelli", "Castro", "Valdes", "Lloyd",
            "Morgan", "Solo", "Dawney", "Palahniuk", "De Gea"};
        Long pesel = 97082718754L;
        Long studNumber = 1L;
        int a = 0;
        int i = 0;
        Student s = null;
        while (i < 100) {
            a = r.nextInt(Nationality.values().length);
            Nationality n = Nationality.values()[a];    
            if (i < 29) {
                s = new Student(studNumber + i, pesel + i, surnames[i], names[i], new Date(90, a, i), n);
            }
            if (i >= 29 && i < 58) {
                s = new Student(studNumber + i, pesel + i, surnames[i], names[i], new Date(90, a, i - 28), n);
            }
            if (i >= 58 && i < 87) {
                s = new Student(studNumber + i, pesel + i, surnames[i], names[i], new Date(90, a, i - 57), n);
            }
            if (i >= 87) {
                s = new Student(studNumber + i, pesel + i, surnames[i], names[i], new Date(90, a, i - 86), n);
            }
            students.add(s);
            i++;
        }
    }

    private static void generateTeachers() {
        String[] names = {"Mark", "Adam", "Steven", "Marta", "Vova",
                          "Lana", "Arnold", "Michael", "Milla", "Bruce",};
        String[] surnames = {"Cobain", "Bennington", "Trump", "O'Hara", "Buffon",
                             "Pirlo", "Lampard", "Hartfield", "Tesla", "Hodges"};
        Long pesel = 97082713456L;
        int a = 0;
        int b = 0;
        int i = 0;
        while (i < 10) {
            a = r.nextInt(Nationality.values().length);
            b = r.nextInt(Degree.values().length);
            Nationality n = Nationality.values()[a];
            Degree d = Degree.values()[b];
            Teacher t = new Teacher(d, new Date((116-i), a, i+1), pesel+i, surnames[i], names[i], new Date(87, a, i+1), n);
            teachers.add(t);
            i++;
        }
    }
    
    private static void getPersons(){
        for(Student s : students)
            persons.add(s);
        for(Teacher t : teachers)
            persons.add(t);
    
    }
    
    private static void generateStudentsGroups(){
        int i = 0;
        StudentGroup group = null;
        while(i<12){
            String name = "group number " + String.valueOf(i+1);
            group = new StudentGroup(name);
            if(i<10){
                for (int j = 0+i*(8); j < (i*8)+8; j++)
                    group.getStudents().add(students.get(j));
            }
            else{
                for (int j = 80 + ((i-10)*10) ; j < 90 + ((i-10)*10); j++)
                    group.getStudents().add(students.get(j));
            }
            studentsGroups.add(group);
            i++;
        }
    }
    
    private static void generateDepartments(){
        int j = 0;
        Department d1 = new Department("Programming");
        for (int i = 0; i < 4; i++) {
            d1.getEmployees().add(teachers.get(i));
        }
        
        Department d2 = new Department("Languages");
        for (int i = 4; i < 7; i++) {
            d2.getEmployees().add(teachers.get(i));
        }
        
        Department d3 = new Department("Databases");
        for (int i = 7; i < 10; i++) {
            d3.getEmployees().add(teachers.get(i));
        }
        departments.add(d1);
        departments.add(d2);
        departments.add(d3);
    }
    
    private static void generateSubjects(){
        String[] names = {"JAVA", "C++" , "PYTHON", "C#", "RBD", "SDB", "WSI", "ENG", "ESP", "FRA"};
        int i = 0;
        Subject s = null;
        while(i<10){
            if(i<4)
                s = new Subject(names[i], departments.get(0), departments.get(0).getEmployees().get(i));
            if(i>=4 && i<7)
                s = new Subject(names[i], departments.get(1), departments.get(1).getEmployees().get(i-4));
            if(i>=7)
                s = new Subject(names[i], departments.get(2), departments.get(2).getEmployees().get(i-7));
            subjects.add(s);
            i++;
        }
    }
    public static void generateData(){
        Generator.generateStudents();
        for(Student s : students)
            StudentsCollection.getStudents().add(s);
        Generator.generateTeachers();
        for(Teacher t : teachers)
            TeachersCollection.getTeachers().add(t);
        Generator.getPersons();
        for(Person p : persons)
            PersonsCollection.getPersons().add(p);
        Generator.generateStudentsGroups();
        for(StudentGroup gr : studentsGroups)
            StudentGroupsCollection.getStudentGroups().add(gr);
        Generator.generateDepartments();
        for(Department dept : departments)
            DepartmentsCollection.getDepartments().add(dept);
        Generator.generateSubjects();
        for(Subject s : subjects)
            SubjectsCollection.getSubjects().add(s);
    }
}
    
