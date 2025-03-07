package org.example;

import java.security.InvalidParameterException;

public class SimpleTask extends Task {
    public int startHour;
    public int endHour;

    public SimpleTask(int idTask, int startHour, int endHour) throws IncorrectInput {
        super(idTask);
        this.startHour = startHour;
        this.endHour = endHour;
        if (startHour > endHour)
            throw new IncorrectInput("startHour should be smaller than endHour");
    }
    public SimpleTask(int idTask){
        super(idTask);
    }
    @Override
    public int estimateDuration() {
        if (super.statusTask.equals("Completed"))
            return 0;
        return endHour - startHour;
    }

}
