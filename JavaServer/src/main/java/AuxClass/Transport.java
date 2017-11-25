package AuxClass;

import java.io.Serializable;
import java.util.ArrayList;

public class Transport implements Serializable {
    private static final long serialVersionUID = -6470090944414208496L;
    int opc;
    User user;
    boolean login;
    String resullt;
    BreackTime workBreak;
    Auction auction;
    ArrayList<User> users;
    ArrayList<BreackTime> breackTimes;
    ArrayList<Auction> auctions;

    public  Transport(){}

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<BreackTime> getBreackTimes() {
        return breackTimes;
    }

    public void setBreackTimes(ArrayList<BreackTime> breackTimes) {
        this.breackTimes = breackTimes;
    }

    public ArrayList<Auction> getAuctions() {
        return auctions;
    }

    public void setAuctions(ArrayList<Auction> auctions) {
        this.auctions = auctions;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public BreackTime getWorkBreak() {
        return workBreak;
    }

    public void setWorkBreak(BreackTime workBreak) {
        this.workBreak = workBreak;
    }

    public String getResullt() {
        return resullt;
    }

    public void setResullt(String resullt) {
        this.resullt = resullt;
    }

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
