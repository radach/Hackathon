package AuxClass;

import java.io.Serializable;

public class Transport implements Serializable {
    private static final long serialVersionUID = -6470090944414208496L;
    int opc;
    User user;
    boolean login;



    public  Transport(){}



    public boolean getLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public int getOpc() {
        return opc;
    }

    public void setOpc(int opc) {
        this.opc = opc;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
