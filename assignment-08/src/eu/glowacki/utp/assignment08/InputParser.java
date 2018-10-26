package eu.glowacki.utp.assignment08;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class InputParser {

    // 1. Use regular expresssions (Pattern) for validating input data
    //    U¿yæ regularnych wyra¿eñ (Pattern) do walidacji danych wejœciowych
    //
    // 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd" 
    //    Konwersjê wejœciowego ci¹gu znaków reprezentuj¹cego datê nale¿y oprzeæ np. DateFormat 
    //    SimpleDateFormat format "yyyy-MM-dd"
   
	private static DateFormat myDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static String namePattern = "\\w+\\s";
    private static String surnamePattern = "\\w+\\s";
    private static String datePattern = "\\d{4}\\-([0][1-9]|[1][0-2])\\-([0-2][1-9]|[3][0-1])";
    private static String linePattern = namePattern + surnamePattern + datePattern;

    private static Pattern myLinePattern = Pattern.compile(linePattern);

    public static List<Person> parse(File file) {
        if (file != null) {
            List<Person> persons = new ArrayList<>();
            FileReader fr = null;
            BufferedReader br = null;
            Matcher matcher = null;
            try {
                fr = new FileReader(file);
                br = new BufferedReader(fr);
                String s = null;
                while ((s = br.readLine()) != null) {
                    matcher = myLinePattern.matcher(s);
                    String src = "";
                    if (matcher.find()) {
                        src = matcher.group().trim();
                        String[] per = src.split(" ");
                        String name = per[0];
                        String surname = per[1];
                        Date birthdate = myDateFormat.parse(per[2]);
                        Person person = new Person(name, surname, birthdate);
                        persons.add(person);
                    }
                }
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (ParseException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (br != null)
                        br.close();
                    if (fr != null)
                        fr.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            return persons;
        } else {
            return null;
        }
    }
}
