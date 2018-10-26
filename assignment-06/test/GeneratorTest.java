import org.junit.Test;


public class GeneratorTest {
    
    public GeneratorTest() {
        Generator.generateData();
    }
    @Test
    public void test(){
        System.out.println(StudentsCollection.getStudents().size() + " students");
        System.out.println(TeachersCollection.getTeachers().size() + " teachers");
        System.out.println(PersonsCollection.getPersons().size() + " persons");
        System.out.println(StudentGroupsCollection.getStudentGroups().size() + " studentGroups");
        System.out.println(DepartmentsCollection.getDepartments().size() + " depts");
        System.out.println(SubjectsCollection.getSubjects().size() + " subjects");
    }
    
}
