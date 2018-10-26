import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


enum Sex {MALE, FEMALE}
public class PESEL {
    private static int[] checkArr = {9,7, 3, 1, 9, 7, 3, 1, 9, 7};
    private static DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static int[] zeroArr = new int[11];
    
    private static boolean validatePESEL(String pesel){
        boolean result = false;
        if(pesel != null){
            int[] peselArr = getPeselArray(pesel);
            if(!(Arrays.equals(peselArr, zeroArr))) {
            	int sum = getSum(peselArr);
            	result = finalValidation(sum, peselArr[peselArr.length-1]);
            }
        }
        return result;
    }
    
    private static int[] getPeselArray(String pesel) {
        int[] arr = new int[11];
        if (pesel.length() == 11) {
            int i = 0;
            while (i < 11) {
                arr[i] = Integer.valueOf(pesel.substring(i, i + 1));
                i++;
            }
        }
        return arr;
    }
    
    private static int getSum(int[] arr){
        int i = 0;
        int sum = 0;
        while(i<10){
            sum += arr[i] * checkArr[i];
            i++;
        }
        return sum;
    }
    
    private static boolean finalValidation(int sum, int checkDigit){
        int myDigit = sum % 10;
        if(myDigit == checkDigit)
            return true;
        else
            return false;
    }
    
    private static Date getDate(String pesel){
        Date date = null;
        StringBuilder sb = new StringBuilder();
        int year = Integer.valueOf(pesel.substring(0, 2));
        int month = Integer.valueOf(pesel.substring(2, 4));
        
        if(month > 80){
            year += 1800;
            month -= 80;
        }
        else if(month > 60){
            year += 2200;
            month -= 60;
        }
        else if(month > 40){
            year += 2100;
            month -= 40;
        }
        else if(month > 20){    
            year += 2000;
            month -= 20;
        }
        else
            year += 1900;
        
        int day = Integer.valueOf(pesel.substring(4, 6));
        sb.append(year);
        sb.append("-");
        sb.append(month);
        sb.append("-");
        sb.append(day);
        
        try {
            date = myDateFormat.parse(sb.toString());
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return date;
    }
    
    private static Sex getSex(String pesel){
        if(Integer.valueOf(pesel.substring(9, 10)) % 2 != 0)
            return Sex.MALE;
        else 
            return Sex.FEMALE;
    }
}

