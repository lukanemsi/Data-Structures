package company.impl;

import java.util.Objects;

public class Employee
{

    private String name;
    private int ID;
    private Employee boss;

    public Employee(String name, Employee boss) {
        this.name = name;
        this.boss = boss;
    }
    public String getName() {
        return this.name;
    }
    public Employee getBoss() {
        return this.boss;
    }
    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return ID == employee.ID;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(ID);
    }

    @Override
    public String toString() {
        int bossID;
        if(boss != null)
            bossID = boss.getID();
        else
            bossID = this.ID;

        return "Employee{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", bossID=" + bossID +
                '}';
    }
}
