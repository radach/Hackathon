package DB;

import AuxClass.Auction;
import AuxClass.BreackTime;
import AuxClass.Transport;
import AuxClass.User;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBList implements DBControl {
    public DBList() throws NamingException, ClassNotFoundException, SQLException {

    }
    @Override
    public Transport logUser(Transport tran, Object userList){
        ArrayList<User> newList=(ArrayList<User>)userList;
        for (int i=0;i<newList.size();i++){
            System.out.println(newList.get(i).getUsername()+" "+newList.get(i).getPass());
            if (tran.getUser().getUsername().equals(newList.get(i).getUsername())&&tran.getUser().getPass().equals(newList.get(i).getPass())) {
                System.out.println("user found");
                tran.setUser(newList.get(i));
                tran.setLogin(true);
                tran.setResullt("true: Login");
                return tran;
            }

        }
        System.out.println("Failed to Login");
        //userList.add(tran.getUser());
        tran.setResullt("false:Invalid Credentials");
        return tran;
    }

    @Override
    public Transport creatBreack(Transport tran, Object breakList) {
        System.out.println("creat Break");
        ArrayList<BreackTime> newList= (ArrayList<BreackTime>) breakList;
        newList.add(tran.getWorkBreak());
        tran.setResullt("true:break created");
        System.out.println(newList.size());

        return tran;
    }

    @Override
    public Transport creatAuction(Transport tran, Object auctionList){
        ArrayList<Auction> auctions = (ArrayList<Auction>) auctionList;
        auctions.add(tran.getAuction());
        tran.setResullt("true:auction created");
        return tran;
    }

}
