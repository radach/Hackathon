package AuxClass;

import java.io.Serializable;
import java.util.Date;

public class Auction implements Serializable {
    private static final long serialVersionUID = -6470090944414208496L;

    String type;
    int delay;
    Date date;
    User user;
    int max;



    public Auction(){

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
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
