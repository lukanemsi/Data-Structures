package company.impl;
import company.tree.Tree;
import java.util.*;

public class Company {

    private final Employee CEO;
    private final Tree<Employee> employeesTree;
    private final Map<Integer,Employee> employeesMap;
    private final Queue<Integer> availableIDs;
    private static int availableID = 1;
    private final String name;

    public Company(String name, Employee CEO)
    {
        this.name = name;
        this.CEO = CEO;
        employeesTree = new Tree<>(CEO);
        availableIDs = new PriorityQueue<>();
        employeesMap = new HashMap<>();
        employeesMap.put(0,CEO);
    }

    public void addEmployee(Employee newEmployee)
    {
        if(newEmployee == null || newEmployee.getBoss() == null)
            return;
        Employee boss = newEmployee.getBoss();
        if(!employeesMap.containsKey(boss.getID()))
            return;
        newEmployee.setID(availableIDs.stream().min(Integer::compareTo).orElse(availableID));
        if(newEmployee.getID() == availableID)
            availableID++;
        availableIDs.remove(newEmployee.getID());
        employeesMap.put(newEmployee.getID(),newEmployee);
        employeesTree.insert(newEmployee,boss);
    }

    public void fireEmployee(int ID)
    {
        if(ID == CEO.getID())
            return;
        Employee employee = employeesMap.get(ID);
        if(employee == null)
            return;
        employeesMap.remove(ID);
        employeesTree.remove(employee);
        availableIDs.add(ID);
        getInferiors(ID).forEach(i -> i.setBoss(employee.getBoss()));
    }
    public List<Employee> allEmployees()
    {
        return employeesTree.toList();
    }
    public Employee findCommonBoss(Employee e1, Employee e2){return employeesTree.LCA(e1,e2);}
    public Employee findByID(int ID) {
        return employeesMap.get(ID);
    }
    private List<Employee> getInferiors(int ID)
    {
        List<Employee> inferiorList = new ArrayList<>();
        employeesMap.values().stream().filter(e -> e.getBoss() != null).filter(e -> e.getBoss().getID() == ID).forEach(inferiorList::add);
        return inferiorList;
    }


}
