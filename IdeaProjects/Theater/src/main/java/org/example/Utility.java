package org.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Utility {
    private final TasksManagement tasksManagement;

    public Utility(TasksManagement tasksManagement) {
        this.tasksManagement = tasksManagement;
    }

    public Set<String> filter40Hours() {
        Map<Integer,String> employeesHours = new HashMap<>();
        tasksManagement.taskToEmployee.forEach((employee, task) -> {
            int nr = tasksManagement.calculateEmployeeWorkDuration(employee.idEmployee);
            if (nr > 40)
                employeesHours.put(nr, employee.name);
        });
        Collection<String>  c =  employeesHours.values();
        Set<String> rez = new HashSet<>(c);
        rez.addAll(c);
        return rez;
//        for (Map.Entry<Employee, List<Task>> e : tasksManagement.taskToEmployee.entrySet()) {
//            int nr = tasksManagement.calculateEmployeeWorkDuration(e.getKey().idEmployee);
//            if (nr > 40) {
//                employeesHours.put(e.getKey().name, nr);
//            }
//        }
//        for (String name : employeesHours.keySet()) {
//            System.out.println(name);
//        }
//        for (Map.Entry<String,Integer> e : employeesHours.entrySet()){
//            System.out.println(e.getKey() + " : " + e.getValue());
//        }
//        return employeesHours.keySet();
    }
    public Map<Employee, Map<String, Integer>> calcTaskStatusForEmployees(){
        Map<Employee, Map<String, Integer>> employeesHours = new HashMap<>();
//        for (Map.Entry<Employee, List<Task>> e : tasksManagement.taskToEmployee.entrySet()) {
//            Map<String, Integer> employeeHours = new HashMap<>();
//            int nrOfCompletedTasks = 0, nrOfUncompletedTasks = 0;
//            for (Task task : e.getValue()) {
//                if (task.equals("Completed")) {
//                    nrOfCompletedTasks++;
//                }
//                if (task.equals("Uncompleted")) {
//                    nrOfUncompletedTasks++;
//                }
//            }
//            employeeHours.put("Completed", nrOfCompletedTasks);
//            employeeHours.put("Uncompleted", nrOfUncompletedTasks);
//            employeesHours.put(e.getKey().name, employeeHours);
//        }
//        return employeesHours;

        tasksManagement.taskToEmployee.forEach((employee, tasks) -> {
            Map<String, Integer> employeeHours = new HashMap<>();
            AtomicInteger nrCompleted = new AtomicInteger(0);
            AtomicInteger nrUncompleted = new AtomicInteger(0);
            tasks.forEach((task) -> {
                if (task.statusTask.equals("Complete"))
                    nrCompleted.incrementAndGet();
                else nrUncompleted.incrementAndGet();
            });
            employeeHours.put("Completed", nrCompleted.get());
            employeeHours.put("Uncompleted", nrUncompleted.get());
            employeesHours.put(employee, employeeHours);
        });
        return employeesHours;
    }
}
