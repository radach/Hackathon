package AuxClass;

import java.io.Serializable;

public class User  implements Serializable{
    private static final long serialVersionUID = -6470090944414208496L;
    int id;
    String username;
    String name;
    String pass;
    String bio;
    int piso;
    int balanceWork;
    int balanceFun;




    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getBalanceWork() {
        return balanceWork;
    }

    public void setBalanceWork(int balanceWork) {
        this.balanceWork = balanceWork;
    }

    public int getBalanceFun() {
        return balanceFun;
    }

    public void setBalanceFun(int balanceFun) {
        this.balanceFun = balanceFun;
    }




    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
