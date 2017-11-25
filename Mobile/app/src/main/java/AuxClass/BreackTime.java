package AuxClass;

import java.io.Serializable;
import java.util.Date;

public class BreackTime implements Serializable {
    private static final long serialVersionUID = -6470090944414208496L;
    String type;
    int delay;
    Date date;
    User creator;

    public BreackTime(){}

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
