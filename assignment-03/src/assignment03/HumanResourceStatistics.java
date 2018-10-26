package assignment03;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.jws.soap.SOAPBinding;

import assignment03.employee.Employee;
import assignment03.employee.Manager;
import assignment03.employee.Trainee;
import assignment03.employee.Worker;

public final class HumanResourceStatistics {
	private static List<Employee> allEmployees;
    // The best solution is to implement the below features as static methods having a list of Employee as the first input argument.
    // In addition the list of arguments may be augmented with parameters required for the given feature.
    // najlepiej zaimplementowaæ poni¿sze metody jako statyczne, gdzie argumentem lista pracowników i odpowiednio dodatkowo to co wymagane w danym punkcie

    // (assignment 03)
    // methods:
    //
    // * search for Employees older than given employee and earning more than him
    //   wyszukaj osoby zatrudnione (Employee), które s¹ starsze od podanej innej zatrudnionej osoby oraz zarabiaj¹ mniej od niej
    public static List<Employee> olderThanAndEarnMore(List<Employee> allEmployees, Employee employee) {
        if (allEmployees != null && allEmployees.size() > 0 && employee != null) {
            List<Employee> foundEmployees = new ArrayList<>();
            foundEmployees = allEmployees.stream()
            		.filter(e -> e.isOlder(employee) && e.isSalaryBigger(employee.getSalary()))
            		.collect(Collectors.toList());
            return foundEmployees;
        } else {
            return null;
        }
    }
    //
    // * search for Trainees whose practice length is longer than given number of days and raise their salary by 5%
    //   wyszukaj praktykantów (Trainee), których praktyka jest d³u¿sza od podanej liczby dni,
    //   a nastêpnie podnieœ ich uposa¿enie o 5%

    public static List<Trainee> practiceLengthLongerThan(List<Employee> allEmployees, int daysCount) {
        if (allEmployees != null && allEmployees.size() > 0) {
            List<Trainee> foundTrainees = new ArrayList<>();
            BigDecimal b5 = new BigDecimal(5);
            BigDecimal b100 = new BigDecimal(100);

            foundTrainees = allEmployees.stream()
            		.filter(e -> e instanceof Trainee)
            		.map(e -> (Trainee)e)
            		.filter(e -> e.lengthOfPracticeIsLongerThanGivenDays(daysCount))
            		.peek(e -> e.setSalary(e.getSalary().add((e.getSalary().multiply(b5)).divide(b100))))
            		.collect(Collectors.toList());
        
            return foundTrainees;
        } else {
            return null;
        }
    }

    //
    // * search for Workers whose seniority is longer than given number of months and give them bonus of 300 if their bonus is smaller
    //   wyszukaj pracowników o sta¿u d³u¿szym ni¿ podana liczba miesiêcy,
    //   a nastêpnie przyznaj im premiê w wysokoœci 300 jeœli ich premia jest ni¿sza
    public static List<Worker> seniorityLongerThanMonths(List<Employee> allEmployees, int monthCount) {
        if (allEmployees != null) {
            List<Worker> foundWorkers = new ArrayList<>();
            BigDecimal b300 = new BigDecimal(300);
            foundWorkers = allEmployees.stream()
            		.filter(e -> e instanceof Worker)
            		.map(e -> (Worker)e)
            		.filter(e ->  e.getBonus() != null && (e.bonusGreaterThanGivenMoney(b300) == false))        		
            		.filter(e -> e.senioriytIsLongerThanGivenMonths(monthCount))
            		.peek(e -> e.setBonus(b300))
            		.collect(Collectors.toList());      
            return foundWorkers;
        } else {
            return null;
        }
    }

    // * search for Workers whose seniority is between 1 and 3 years and give them raise of salary by 10%
    //   wyszukaj pracowników o sta¿u pomiêdzy 1 a 3 lata i przyznaj im podwy¿kê w wysokoœci 10%
    public static List<Worker> seniorityBetweenOneAndThreeYears(List<Employee> allEmployees) {
        if (allEmployees != null && allEmployees.size() > 0) {
            List<Worker> foundWorkers = new ArrayList<>();
            int m1 = 12;
            int m2 = 36;
            BigDecimal b10 = new BigDecimal(10);
            BigDecimal b100 = new BigDecimal(100);
            
            foundWorkers = allEmployees.stream()
            		.filter(e -> e instanceof Worker)
            		.map(e -> (Worker)e)
            		.filter(e -> e.senioriytIsLongerThanGivenMonths(m1) && e.senioriytIsShorterThanGivenMonths(36))
            		.peek(e -> e.setSalary(e.getSalary().add((e.getSalary().multiply(b10)).divide(b100))))
            		.collect(Collectors.toList());
            
            
            return foundWorkers;
        } else {
        return null;
        }
    }
    //
    // * search for Workers whose seniority is longer than the seniority of a given employee and earn less than him and align their salary with the given employee
    //   wyszukaj pracowników o sta¿u d³u¿szym ni¿ sta¿ podanego pracownika i którzy zarabiaj¹ mniej od niego,
    //   nastêpnie zrównaj ich wynagrodzenie z wynagrodzeniem danego pracownika
    public static List<Worker> seniorityLongerThanEmployee(List<Employee> allEmployees, Employee employee) {
        if (allEmployees != null && allEmployees.size() > 0 && employee != null) {
            List<Worker> foundWorkers = new ArrayList<>();
            
            foundWorkers = allEmployees.stream()
            		.filter(e -> e instanceof Worker)
            		.map(e -> (Worker)e)
            		.filter(e -> ((Worker)e).senioriytIsLongerThanGivenEmployee(employee) && ((Worker)e).isSalarySmaller(employee.getSalary()))
            		.peek(e -> e.setSalary(((Worker)employee).getSalary()))
            		.collect(Collectors.toList());
            
            return foundWorkers;
        } else {
            return null;
        }

    }

    //
    // * search for Workers whose seniority is between 2 and 4 years and whose age is greater than given number of years
    //   wyszukaj pracowników o sta¿u pomiêdzy 2 i 4 lata i starszych ni¿ podana liczba lat
    //
    public static List<Worker> seniorityBetweenTwoAndFourYearsAndAgeGreaterThan(List<Employee> allEmployees, int age) {
        if (allEmployees != null && allEmployees.size() > 0) {
            List<Worker> foundWorkers = new ArrayList<>();
            
            foundWorkers = allEmployees.stream()
            		.filter(e -> e instanceof Worker && ((Worker)e).senioriytIsLongerThanGivenMonths(24) &&
            				((Worker)e).senioriytIsShorterThanGivenMonths(48) && e.getAge()>age)
            		.map(e -> (Worker)e)
            		.collect(Collectors.toList());
            		
            return foundWorkers;

        }
        else
        return null;
    }

    public static List<Employee> getAllEmployees() {
        if (allEmployees == null) {
            allEmployees = createEmployees();
            return allEmployees;
        } else {
            return allEmployees;
        }
    }

    private static ArrayList<Employee> createEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        Manager director = new Manager("Vito", "Corleone", new Date(70, 4, 12), new BigDecimal(1000), null, new Date(90, 5, 9), new BigDecimal(400));
        employees.add(director);
        String[] namesForTrainees = {"Mark", "Adam", "Steven", "Marta", "Vova",
            "Lana"};
        String[] surnamesForTrainees = {"Kolodin", "Lallana", "Gerrard", "Abate", "Zvarych",
            "Winters"};
        String[] namesForWorkers = {"Arnold", "Michael", "Milla", "Bruce",
            "Jackie", "Bob", "Brian", "Leo", "Dominik",
            "Alfonco"};
        String[] surnamesForWorkers = {"Schwarzenegger", "Corleone", "Stallone", "Lee",
            "Chan", "Marley", "Harthfield", "Adler", "Toretto",
            "Capone"};
        String[] namesForManagers = {"Luca", "Peter", "Santino", "Tom"};
        String[] surnamesForManagers = {"Brasi", "Clemenza", "Corleone", "Hagen"};

        for (int i = 0; i < 20; i++) {
            if (i < 4) {
                employees.add(new Manager(namesForManagers[i], surnamesForManagers[i],
                        new Date(75, 0 + i, 2 + i), new BigDecimal(6000 - i * 200),
                        director, new Date(92, 1 + i, 3 + i), new BigDecimal(10 + i * 30)));
            }
            if (i >= 4 && i < 14) {
                if (i <= 7) {
                    employees.add(new Worker(namesForWorkers[i - 4], surnamesForWorkers[i - 4],
                            new Date(80, 6, i), new BigDecimal(4000 + 100 * (i - 3)),
                            (Manager) employees.get(i - 3), new Date(98, 1 + i, 2 + i), new BigDecimal(20 + 30 * i)));
                }
                if (i > 7 && i <= 11) {
                    employees.add(new Worker(namesForWorkers[i - 4], surnamesForWorkers[i - 4],
                            new Date(73, 6, i), new BigDecimal(4000),
                            (Manager) employees.get(i - 7), new Date(114, 1 + i, 2 + i), new BigDecimal(100)));
                }
                if (i > 11) {
                    employees.add(new Worker(namesForWorkers[i - 4], surnamesForWorkers[i - 4],
                            new Date(84, 6, i), new BigDecimal(4000 + 100 * (i - 8)),
                            (Manager) employees.get(i - 11), new Date(115, 9, 2 + i), new BigDecimal(100)));
                }
            }
            if (i >= 14) {
                if (i <= 17) {
                    employees.add(new Trainee(namesForTrainees[i - 14], surnamesForTrainees[i - 14],
                            new Date(90, 9, i), new BigDecimal(1600 + 10 * i),
                            (Manager) employees.get(i - 13), new Date(115, 3, i + 4), i));
                }
                if (i > 17) {
                    employees.add(new Trainee(namesForTrainees[i - 14], surnamesForTrainees[i - 14],
                            new Date(96, 2, i), new BigDecimal(1600 + 10 * i),
                            (Manager) employees.get(i - 17), new Date(115, 3, i + 4), i));
                }
            }

        }
        return employees;
    }
    /// ...
    // rest of the methods specified in the assignment description
}