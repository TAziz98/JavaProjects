import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import org.junit.Assert;
import org.junit.Test;


public class PESEL_Test {
    private final Method validatePESEL;
    private final Method getDate;
    private final Method getSex;
    private String correctPESEL = "99090839731";
    private String invalidPESEL = "99090833333";
    

    public PESEL_Test() throws NoSuchMethodException{
        validatePESEL = PESEL.class.getDeclaredMethod("validatePESEL", String.class);
        validatePESEL.setAccessible(true);
        getDate = PESEL.class.getDeclaredMethod("getDate", String.class);
        getDate.setAccessible(true);
        getSex = PESEL.class.getDeclaredMethod("getSex", String.class);
        getSex.setAccessible(true);
    }
    
    @Test
    public void validatePeselTest() {
        try {
            Assert.assertTrue((boolean)validatePESEL.invoke(PESEL.class, correctPESEL));
            Assert.assertFalse((boolean)validatePESEL.invoke(PESEL.class, invalidPESEL));
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void getDateTest(){
        try {
            Date myDate = new Date(99, 8, 8);
            Date actualDate = (Date)getDate.invoke(PESEL.class, correctPESEL);
            System.out.println(actualDate);
            Assert.assertEquals(myDate, actualDate);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void getSexTest(){
        try {
            Sex mySex = Sex.MALE;
            Sex actualSex = (Sex)getSex.invoke(PESEL.class, correctPESEL);
            System.out.println(actualSex.toString());
            Assert.assertEquals(mySex, actualSex);
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        } catch (InvocationTargetException ex) {
            ex.printStackTrace();
        }
    }
}