package org.example;

import java.io.Serializable;

public abstract class Task implements Serializable {
    public final int idTask;
    public String statusTask;

    public abstract int estimateDuration();

    public Task(int idTask) {
        this.idTask = idTask;
        this.statusTask = "Uncompleted";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            if (((Task) obj).idTask == this.idTask) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int hashCode() {
        return idTask;
    }
}
