package org.example;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class ComplexTask extends Task {

    public ArrayList<Task> tasks;

    public ComplexTask(int idTask) {
        super(idTask);
    }

    @Override
    public int estimateDuration() {
//        int rez = 0;
//        for (Task task : tasks) {
//            rez += task.estimateDuration();
//        }
        if (super.statusTask.equals("Completed"))
            return 0;
        AtomicInteger ai = new AtomicInteger(0);
        tasks.forEach(task -> {
            ai.addAndGet(task.estimateDuration());
        });
        return ai.get();
    }
    public void addTask(Task task) {
        tasks.add(task);
    }
    public void deleteTask(Task task) {
        tasks.remove(task);
    }
}
