package org.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ProjectManager {
    private final TasksManagement tasksManagement;
    private final Utility utility;
    private Set<Employee> employees = new HashSet<>();
    private Set<Task> tasks = new HashSet<>();


    public ProjectManager() {
        this.tasksManagement = new TasksManagement();
        this.utility = new Utility(tasksManagement);
    }

    public void addEmployees(String name) throws IncorrectInput {
        if (name.isEmpty()) throw new IncorrectInput("Name can not be null");
//        int newId = 0;
//        for (Employee employee : employees) {
//            if (employee.idEmployee > newId) {
//                newId = employee.idEmployee;
//            }
//        }
//        newId++;
//        Employee newEmployee = new Employee(newId, name);

        AtomicInteger count = new AtomicInteger(0);
        employees.forEach(employee -> {
            if (employee.idEmployee > count.get()) {
                count.set(employee.idEmployee);
            }
        });
        count.incrementAndGet();
        Employee newEmployee = new Employee(count.get(), name);
        employees.add(newEmployee);
        tasksManagement.taskToEmployee.put(newEmployee, new ArrayList<>());
    }
    public void addTaskSimple(int start, int end) throws IncorrectInput{
        int newId = 0;
        for (Task task : tasks) {
            if (task.idTask > newId) {
                newId = task.idTask;
            }
        }
        newId++;
        tasks.add(new SimpleTask(newId, start, end));

    }
    public void addTaskComplex(){
        int newId = 0;
        for (Task task : tasks) {
            if (task.idTask > newId) {
                newId = task.idTask;
            }
        }
        newId++;
        tasks.add(new ComplexTask(newId));
    }
    public void addTaskToEmployee(int idEmployee, int idTask) throws IncorrectInput {
        if (!taskExists(idTask)) throw new IncorrectInput("Task dose not exists");
        if (!employeeExists(idEmployee)) throw new IncorrectInput("Employee dose not exists");

        Task taskToBeAdded = null;
        for (Task task : tasks) {
            if (task.idTask == idTask) {
                taskToBeAdded = task;
            }
        }

        tasksManagement.assignTaskToEmployee(idEmployee, taskToBeAdded);
    }
    public void addTaskToTask(int complexTaskId, int taskId) throws IncorrectInput {
        if (!taskExists(taskId)) throw new IncorrectInput("Task dose not exists");
        if (!taskExists(complexTaskId)) throw new IncorrectInput("Complex task dose not exists");
        Task taskToBeAdded = null;
        for (Task task : tasks) {
            if (task.idTask == taskId) {
                taskToBeAdded = task;
            }
        }
        for (Task task : tasks) {
            if (task.idTask == complexTaskId) {
                ((ComplexTask)task).addTask(taskToBeAdded);
            }
        }
    }
    /*
    public Map<String, Object> viewEmployee(Employee employee) {
        Map<String, Object> map = new HashMap<>();
        //List<Task> tasksOfAnEmployee = new ArrayList<>();
        map.put("Tasks", tasksManagement.taskToEmployee.get(employee));
        map.put("Duration", tasksManagement.calculateEmployeeWorkDuration(employee.idEmployee));
        return map;
    }
     */
    public String viewEmployees() {
        String info = "";
        Set<Integer> s = new HashSet<>();
        for (Map.Entry<Employee, List<Task>> entry : tasksManagement.taskToEmployee.entrySet()){
            s.add(entry.getKey().idEmployee);
            info += entry.getKey().name + "\n";
            info += "number of tasks = " + entry.getValue().size() + "\n";
            int totalTime = 0;
            for (Task task : entry.getValue()) {
                info += "Task Id = " + task.idTask + " Estimated Duration = " + task.estimateDuration() + '\n';
                totalTime += task.estimateDuration();
            }
            info += "Total Task Time = " + totalTime + '\n';
        }
        for (Employee employee : employees) {
            if (!s.contains(employee.idEmployee)) {
                info += "Employee Id = " + employee.idEmployee + " No tasks " + '\n';
            }
        }
        return info;
    }
    public void modifyStatusOfTask(int idEmployee, int idTask) throws IncorrectInput {
        if (!taskExists(idTask)) throw new IncorrectInput("Task dose not exists");
        if (!employeeExists(idEmployee)) throw new IncorrectInput("Employee dose not exists");
        tasksManagement.modifyTaskStatus(idEmployee, idTask);
    }
    public String calcTaskStatusForEmployees(){
        Map<Employee, Map<String, Integer>> map = utility.calcTaskStatusForEmployees();
        Iterator<Map.Entry<Employee, Map<String, Integer>>> it = map.entrySet().iterator();
        StringBuilder info = new StringBuilder("");
        while (it.hasNext()){
            Map.Entry<Employee, Map<String, Integer>> entry = it.next();
            info.append(entry.getKey().name + " with id = " + entry.getKey().idEmployee + " : Uncompleted Tasks = " + entry.getValue().get("Uncompleted") + " : Completed Tasks = " + entry.getValue().get("Completed") + "\n");
        }

        return info.toString();
    }
    public String filter40Hours(){
        Set<String> set = utility.filter40Hours();
        String info = "";
        for (String s : set) {
            info += s + "\n";
        }
        return info;
    }
    public int calculateEmployeeWorkDuration(int idEmployee) throws IncorrectInput {
        if (!employeeExists(idEmployee)) throw new IncorrectInput("employee dose not exist");
        return tasksManagement.calculateEmployeeWorkDuration(idEmployee);
    }
    public boolean employeeExists(int idEmployee) {
        return employees.contains(new Employee(idEmployee, ""));
    }
    public boolean taskExists(int idTask) throws IncorrectInput {
        return tasks.contains(new SimpleTask(idTask, 0, 0)) || tasks.contains(new ComplexTask(idTask));
    }
    public String simpleViewEmployees() {
        String info = "";
        for (Employee e : employees){
            info += e.name + " With id = " + e.idEmployee + "\n";
        }
        return info;
    }
    public String simpleViewTasks() {
        String info = "";
        for (Task t : tasks){
            if (t instanceof SimpleTask){
                info += "Simple Task Id = " + t.idTask + "start hour = " + ((SimpleTask) t).startHour + " end hour" + ((SimpleTask)t).endHour + "\n";
            }
            else if (t instanceof ComplexTask){
                info += "Complex Task Id = " + t.idTask + "\n";
            }
        }
        return info;
    }
}
