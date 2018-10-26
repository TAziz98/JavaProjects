package eu.glowacki.utp.assignment02.payroll;

import java.math.BigDecimal;

import eu.glowacki.utp.assignment02.employee.Employee;

public final class PayrollEntry {

    private final Employee _employee;
    private final BigDecimal _salaryPlusBonus;

    public PayrollEntry(Employee employee, BigDecimal salary, BigDecimal bonus) {
        _employee = employee;
        _salaryPlusBonus = salary.add(bonus); // validate whether salary and bonus are not null
    }

    public PayrollEntry(Employee _employee, BigDecimal salary) {
        this._employee = _employee;
        this._salaryPlusBonus = salary;
    }

    @Override
    public String toString() {
        return "PayrollEntry{" + "_employee=" + _employee.toString() + ", _salaryPlusBonus=" + _salaryPlusBonus + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj)
            return true;
        if(!(obj instanceof PayrollEntry))
            return false;
        PayrollEntry p = (PayrollEntry) obj;
        boolean r = _employee!=null && _employee.equals(p.getEmployee());
        boolean r2 = _salaryPlusBonus!=null && _salaryPlusBonus.equals(p.getSalaryPlusBonus());
        return r && r2;
    }

    @Override
    public int hashCode() {
        int hashCode = 31;
        hashCode = 31*hashCode+_employee.hashCode();
        hashCode = 31*hashCode+_salaryPlusBonus.hashCode();
        return hashCode;
    }
    

    public Employee getEmployee() {
        return _employee;
    }

    public BigDecimal getSalaryPlusBonus() {
        return _salaryPlusBonus;
    }
    
    

}
