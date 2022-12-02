package company.main;
import company.tree.Tree;

public class Main
{
    public static void main(String[] args) {
        Employee ceo = new Employee("Luka", null);
        ceo.setID(0);
        Company company = new Company("UFC", ceo);
        Employee saba = new Employee("Saba",ceo);
        Employee nika = new Employee("Nika",saba);
        Employee givi = new Employee("Givi",saba);
        Employee gio = new Employee("Gio",nika);

        company.addEmployee(saba);
        company.addEmployee(nika);
        company.addEmployee(givi);
        company.addEmployee(gio);
        System.out.println(company.allEmployees());
    }

}
