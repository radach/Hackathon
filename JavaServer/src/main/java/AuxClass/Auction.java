package AuxClass;

import java.io.Serializable;
import java.util.Date;

public class Auction implements Serializable {
    private static final long serialVersionUID = -6470090944414208496L;

    String type;
    int delay;
    Date date;



    public Auction(){

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
