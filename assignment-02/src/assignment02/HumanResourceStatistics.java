package assignment02;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import assignment02.employee.Employee;
import assignment02.employee.Manager;
import assignment02.employee.Trainee;
import assignment02.employee.Worker;
import assignment02.payroll.PayrollEntry;

public final class HumanResourceStatistics {
	private static ArrayList<Employee> allEmployees;

	public static List<PayrollEntry> payroll(List<Employee> employees) {
		if (employees != null && employees.size() > 0) {
			ArrayList<PayrollEntry> payrollAllEmployees = new ArrayList<PayrollEntry>();

			List<PayrollEntry> payrollAllWorkers = employees.stream().filter(e -> e instanceof Worker)
					.map(e -> e.castToWorker(e)).filter(e -> e.getSalary() != null && e.getBonus() != null)
					.map(e -> new PayrollEntry(e, e.getSalary(), e.getBonus())).collect(Collectors.toList());
			List<PayrollEntry> payrollRestEmployees = employees.stream()
					.filter(e -> !(e instanceof Worker) && e.getSalary() != null)
					.map(e -> new PayrollEntry(e, e.getSalary())).collect(Collectors.toList());

			for (PayrollEntry p : payrollAllWorkers) {
				payrollAllEmployees.add(p);
			}
			for (PayrollEntry p1 : payrollRestEmployees) {
				payrollAllEmployees.add(p1);
			}
			return payrollAllEmployees;
		}

		return null;
	}

	
	
	
	
	
	
	
	
	public static List<PayrollEntry> subordinatesPayroll(Manager manager) {
		ArrayList<PayrollEntry> subPayrolls = new ArrayList<>();
		if (manager != null) {
			List<PayrollEntry> subWorkers = manager.getImmediateSubordinates().stream().filter(e -> e instanceof Worker)
					.map(e -> e.castToWorker(e)).filter(e -> e.getSalary() != null && e.getBonus() != null)
					.map(e -> new PayrollEntry(e, e.getSalary(), e.getBonus())).collect(Collectors.toList());

			List<PayrollEntry> subRest = manager.getImmediateSubordinates().stream()
					.filter(e -> !(e instanceof Worker) && e.getSalary() != null)
					.map(e -> new PayrollEntry(e, e.getSalary())).collect(Collectors.toList());

			for (PayrollEntry p : subWorkers) {
				subPayrolls.add(p);
			}
			for (PayrollEntry p : subRest) {
				subPayrolls.add(p);
			}
			return subPayrolls;
		} else
			return null;
	}

	
	
	
	
	
	
	
	public static BigDecimal bonusTotal(List<Employee> employees) {
		if (employees != null && employees.size() > 0) {
			return employees.stream().filter(e -> e instanceof Worker && e.castToWorker(e).getBonus() != null)
					.map(e -> e.castToWorker(e).getBonus()).reduce(new BigDecimal(0), (p, n) -> p.add(n));
		} else
			return null;
	}

	
	
	
	
	
	

	public static Employee getEmployeeLongestSeniority(List<Employee> employees) {
		if (employees != null) {
			List<Date> dates = employees.stream().filter(e -> e instanceof Worker).map(e -> e.castToWorker(e))
					.map(e -> e.getEmploymentDate()).collect(Collectors.toList());
			Date minDate = getMinDate(dates);

			List<Worker> ws = null;
			ws = employees.stream().filter(e -> e instanceof Worker).map(e -> e.castToWorker(e))
					.filter(e -> e.getEmploymentDate().compareTo(minDate) == 0).collect(Collectors.toList());

			if (ws.size() == 1) {
				return ws.get(0);
			} else
				return null;

		}
		return null;
	}
	private static Date getMinDate(List<Date> dates) {
		Collections.sort(dates, new Comparator<Date>() {
			@Override
			public int compare(Date o1, Date o2) {
				int r = o1.compareTo(o2);
				return r;
			}
		});
		return dates.get(0);
	}

	
	
	
	
	
	public static BigDecimal getHighestSalaryWithoutBonus(List<Employee> employees) {
		if (employees != null && employees.size() > 0) {
			List<BigDecimal> salaries = employees.stream().filter(e -> e.getSalary() != null).map(e -> e.getSalary())
					.sorted((o1, o2) -> o1.compareTo(o2)).collect(Collectors.toList());
			return salaries.get(salaries.size() - 1);
		} else
			return null;
	}

	
	
	
	
	
	
	
	public static BigDecimal getHighestSalaryWithBonus(List<Employee> employees) {
		if (employees != null && employees.size() > 0) {
			List<BigDecimal> salaries = employees.stream().filter(e -> e instanceof Worker).map(e -> e.castToWorker(e))
					.filter(e -> e.getSalary() != null && e.getBonus() != null)
					.map(e -> e.getSalary().add(e.getBonus())).sorted((o1, o2) -> o1.compareTo(o2))
					.collect(Collectors.toList());
			return salaries.get(salaries.size() - 1);
		} else
			return null;
	}

	
	
	
	
	
	
	
	
	public static List<Employee> getEmployeesSurnameStartsA(Manager manager) {
		if (manager != null) {
			return manager.getImmediateSubordinates().stream().filter(e -> e.getSurname().startsWith("A"))
					.collect(Collectors.toList());
		} else
			return null;
	}

	
	
	
	
	
	
	
	public static List<Employee> getEmployeesSalaryBigger1000(List<Employee> employees) {
		final BigDecimal oneThousand = new BigDecimal(1000);
		if (employees != null && employees.size() > 0) {
			return employees.stream().filter(e -> e.getSalary().compareTo(oneThousand) > 0)
					.collect(Collectors.toList());
		} else
			return null;
	}
	
	
	
	
	
	public static ArrayList<Employee> getAllEmployees() {
		if (allEmployees == null) {
			allEmployees = createEmployees();
			return allEmployees;
		} else
			return allEmployees;

	}
	private static ArrayList<Employee> createEmployees() {
		ArrayList<Employee> employees = new ArrayList<>();
		Manager director = new Manager("Vito", "Corleone", new Date(70, 4, 12), new BigDecimal(10000), null,
				new Date(90, 5, 9), null);
		employees.add(director);
		String[] namesForTrainees = { "Mark", "Adam", "Steven", "Marta", "Vova", "Lana" };
		String[] surnamesForTrainees = { "Kolodin", "Lallana", "Gerrard", "Abate", "Zvarych", "Winters" };
		String[] namesForWorkers = { "Arnold", "Michael", "Milla", "Bruce", "Jackie", "Bob", "Brian", "Leo", "Dominik",
				"Alfonco" };
		String[] surnamesForWorkers = { "Schwarzenegger", "Corleone", "Stallone", "Lee", "Chan", "Marley", "Harthfield",
				"Adler", "Toretto", "Capone" };
		String[] namesForManagers = { "Luca", "Peter", "Santino", "Tom" };
		String[] surnamesForManagers = { "Brasi", "Clemenza", "Corleone", "Hagen" };

		for (int i = 0; i < 20; i++) {
			if (i < 4) {
				employees.add(new Manager(namesForManagers[i], surnamesForManagers[i], new Date(75, 0 + i, 2 + i),
						new BigDecimal(6000 + i * 200), director, new Date(92, 1 + i, 3 + i),
						new BigDecimal(100 + i * 30)));
			}
			if (i >= 4 && i < 14) {
				if (i <= 7) {
					employees.add(new Worker(namesForWorkers[i - 4], surnamesForWorkers[i - 4], new Date(80, 6, i),
							new BigDecimal(4000 + 100 * (i - 3)), (Manager) employees.get(i - 3),
							new Date(95, 1 + i, 2 + i), new BigDecimal(10 + 30 * i)));
				}
				if (i > 7 && i <= 11) {
					employees.add(new Worker(namesForWorkers[i - 4], surnamesForWorkers[i - 4], new Date(82, 6, i),
							new BigDecimal(4000 + 100 * (i - 6)), (Manager) employees.get(i - 7),
							new Date(96, 1 + i, 2 + i), new BigDecimal(10 + 30 * (i - 3))));
				}
				if (i > 11) {
					employees.add(new Worker(namesForWorkers[i - 4], surnamesForWorkers[i - 4], new Date(84, 6, i),
							new BigDecimal(4000 + 100 * (i - 8)), (Manager) employees.get(i - 11),
							new Date(92, 1 + i - 4, 2 + i), new BigDecimal(10 + 30 * i - 6)));
				}
			}
			if (i >= 14) {
				if (i <= 17) {
					employees.add(new Trainee(namesForTrainees[i - 14], surnamesForTrainees[i - 14], new Date(90, 9, i),
							new BigDecimal(1600 + 10 * i), (Manager) employees.get(i - 13), new Date(115, 3, i + 4),
							i));
				}
				if (i > 17) {
					employees.add(new Trainee(namesForTrainees[i - 14], surnamesForTrainees[i - 14], new Date(96, 2, i),
							new BigDecimal(1600 + 10 * i), (Manager) employees.get(i - 17), new Date(115, 3, i + 4),
							i));
				}
			}

		}
		return employees;
	}
	/// ...
	// rest of the methods specified in the assignment description
}
