package org.example;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class TasksManagement {
    public Map<Employee, List<Task>> taskToEmployee = new HashMap<>();

    public void assignTaskToEmployee(int idEmployee, Task task) {
        taskToEmployee.forEach((employee, tasks) -> {
            if (employee.idEmployee == idEmployee) {
                tasks.add(task);
            }
        });
    }
    public int calculateEmployeeWorkDuration(int idEmployee) {
//        int rez = 0;
//        for (Map.Entry<Employee, List<Task>> e : taskToEmployee.entrySet()){
//            if (e.getKey().idEmployee == idEmployee){
//                for (Task task : e.getValue()){
//                    if (task.statusTask.equals("Completed"))
//                        rez += task.estimateDuration();
//                }
//            }
//        }
        List<Task> t = taskToEmployee.get(new Employee(idEmployee));
        AtomicInteger atomicRez = new AtomicInteger(0);
        t.forEach((task) -> {
            if (task.statusTask.equals("Uncompleted"))
                atomicRez.addAndGet(task.estimateDuration());
        });
        return atomicRez.get();
    }
    public void modifyTaskStatus(int idEmployee, int idTask) throws IncorrectInput {
//        for (Map.Entry<Employee, List<Task>> e : taskToEmployee.entrySet()){
//            if (e.getKey().idEmployee == idEmployee){
//                for (Task task : e.getValue()){
//                    if (task.idTask == idTask){
//                        if (task.statusTask.equals("Completed"))
//                            task.statusTask = "Uncompleted";
//                        else task.statusTask = "Completed";
//                    }
//                }
//            }
//        }
        List<Task> t = taskToEmployee.get(new Employee(idEmployee));
        t.forEach((task) -> {
            if (task.idTask == idTask){
                if (task.statusTask.equals("Completed"))
                    task.statusTask = "Uncompleted";
                else task.statusTask = "Completed";
            }
        });

    }
}
