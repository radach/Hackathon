import AuxClass.BreackTime;
import AuxClass.Transport;
import AuxClass.User;
import DB.DBList;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Control extends Thread {
    Socket client;
    ObjectInputStream in;
    ObjectOutputStream out;
    ArrayList<User> userList;
    ArrayList<BreackTime> breakTime;
    String username;
    DBList db;



    public  Control(Socket cli, ArrayList <User> userList, ArrayList<BreackTime> breakTime) throws SQLException, NamingException, ClassNotFoundException {
        this.client=cli;
        this.userList=userList;
        this.breakTime=breakTime;
        db=new DBList();
    }

    public void run(){
        System.out.println("init" + client.getInetAddress());
        try {

            out = new ObjectOutputStream(client.getOutputStream());
            System.out.println("stream criadas");
            out.flush();
            System.out.println("antes");
            in = new ObjectInputStream(client.getInputStream());
            System.out.println("in");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            System.out.println("menu");
            menu();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void menu() throws IOException, ClassNotFoundException {
        Transport tran;
        creatUsers();
        while (true){
            tran= (Transport) in.readObject();
            switch (tran.getOpc()){
                case 1:
                    System.out.println("antes login" + tran.getOpc()+" " +tran.getUser().getUsername());
                    out.writeObject(login(tran));
                    break;
                case 2:
                    System.out.println("criar ");
                    out.writeObject(creatBreack(tran));
                    break;
                default:
                    System.out.println("Bora tirar cafés");
                    break;
            }
        }
    }

    private Transport creatBreack(Transport tran) {
        db.creatBreack(tran,breakTime);
        return tran;
    }

    private void creatUsers() {
        User us1=new User();
        us1.setPiso(4);
        us1.setId(1);
        us1.setUsername("rsantos");
        us1.setPass("rsantos");
        us1.setBalanceFun(500);
        us1.setBalanceWork(500);
        us1.setName("rsantos");
        us1.setBio("cenas");
        userList.add(us1);
    }





    private Transport login(Transport tran) {

        return db.logUser(tran,userList);
    }



}
