package AuxClass;

import java.io.Serializable;
import java.util.Date;

public class BreackTime implements Serializable {
    private static final long serialVersionUID = -6470090944414208496L;
    String tipo;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
