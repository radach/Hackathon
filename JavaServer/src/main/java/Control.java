import AuxClass.Auction;
import AuxClass.BreackTime;
import AuxClass.Transport;
import AuxClass.User;
import DB.DBList;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import javax.naming.NamingException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Control extends Thread {
    Socket client;
    MqttClient client2;

    ObjectInputStream in;
    ObjectOutputStream out;
    ObjectInputStream in2;
    ObjectOutputStream out2;
    ArrayList<User> userList;
    ArrayList<BreackTime> breakTime;
    ArrayList<Auction>auctions;
    ArrayList<Socket> lista;

    ArrayList<Auction>auctionsThreads;
    ArrayList<BreackTime> breakTimeThreads;



    String username;
    DBList db;



    public  Control(Socket cli, ArrayList<User> userList, ArrayList<BreackTime> breakTime, ArrayList<Auction> auctions, ArrayList<Socket> lista, ArrayList<Auction> auctionsThreads, ArrayList<BreackTime> breakTimeThreads) throws SQLException, NamingException, ClassNotFoundException {
        this.client=cli;
        //this.client2=sok2;
        this.userList=userList;
        this.breakTime=breakTime;
        this.auctions=auctions;
        db=new DBList();
        this.lista=lista;
        this.auctionsThreads=auctionsThreads;
        this.breakTimeThreads=breakTimeThreads;
    }

    public void run(){

        try {
            client2 = new MqttClient("tcp://192.168.10.165:1883", MqttClient.generateClientId());
            client2.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }


        System.out.println("init" + client.getInetAddress());
        try {

            out = new ObjectOutputStream(client.getOutputStream());
            //out2 = new ObjectOutputStream(client2.getOutputStream());
            System.out.println("stream criadas");
            out.flush();
            //out2.flush();
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
            //if (tran.getOpc()!=1)

            switch (tran.getOpc()){
                case 1:
                    System.out.println("antes login" + tran.getOpc()+" " +tran.getUser().getUsername());
                    out.writeObject(login(tran));
                    break;
                case 2:
                    System.out.println("criar break");
                    out.writeObject(creatBreack(tran));
                    break;
                case 3:
                    System.out.println("criar auction");
                    out.writeObject(creatAuction(tran));
                    break;
                case 4:
                    System.out.println("dataBase Dump");
                    out.writeObject(readDatabase(tran));
                    break;
                default:
                    System.out.println("Bora tirar cafés");
                    break;
            }
        }
    }



    private Transport readDatabase(Transport tran) {
        tran.setUsers(userList);
        tran.setBreackTimes(breakTime);
        tran.setAuctions(auctions);
        return tran;
    }

    private Transport creatAuction(Transport tran) {
        db.creatAuction(tran,auctions);
        //auctionsThreads.add(tran.getAuction());
        MqttMessage message= new MqttMessage();
        String msg=tran.getAuction().getUser().getUsername()+"/auction/"+tran.getAuction().getType()+"/"+tran.getAuction().getDate()+"/"+tran.getAuction().getDelay()+"/"+tran.getAuction().getMax();
        message.setPayload(msg.getBytes());
        System.out.println(msg);
        try {
            client2.publish("data",message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
        return tran;
    }

    private Transport creatBreack(Transport tran) {
        db.creatBreack(tran,breakTime);
        MqttMessage message= new MqttMessage();
        String msg= tran.getWorkBreak().getCreator().getUsername()+"/break/"+tran.getWorkBreak().getType()+"/"+tran.getWorkBreak().getDate()+"/"+tran.getWorkBreak().getDelay();
        message.setPayload(msg.getBytes());
        System.out.println(msg);
        try {
            client2.publish("data",message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
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
        User us2=new User();
        us2.setPiso(8);
        us2.setId(2);
        us2.setUsername("rsantos2");
        us2.setPass("rsantos2");
        us2.setBalanceFun(500);
        us2.setBalanceWork(500);
        us2.setName("rsantos2");
        us2.setBio("cenas2");
        userList.add(us1);
        userList.add(us2);
    }





    private Transport login(Transport tran) {
        tran = db.logUser(tran,userList);
        return tran;
    }



}
