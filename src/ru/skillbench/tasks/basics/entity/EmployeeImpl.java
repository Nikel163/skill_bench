package ru.skillbench.tasks.basics.entity;

public class EmployeeImpl implements Employee{

    private int salary;
    private String firstName;
    private String lastName;
    private Employee manager;

    public EmployeeImpl() {
        this.salary = 1000;
        this.firstName = "";
        this.lastName = "";
        this.manager = null;
    }

    public EmployeeImpl(int salary, String firstName, String lastName) {
        this.salary = salary;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public int getSalary() {
        return this.salary;
    }

    @Override
    public void increaseSalary(int value) {
        this.salary += value;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public void setManager(Employee manager) {
        this.manager = manager;
    }

    @Override
    public String getManagerName() {
        return (this.manager == null) ?
                "No manager" :
                this.manager.getFullName();
    }

    @Override
    public Employee getTopManager() {
        if (this.manager == null) {
            return this;
        }

        return this.manager.getTopManager();
    }
}
