import AuxClass.Auction;
import AuxClass.BreackTime;
import AuxClass.User;

import javax.naming.NamingException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    ArrayList<User>userList;
    ArrayList<BreackTime>breakTime;
    ArrayList<Auction>auctions;
    ArrayList<Socket> lista;

    ArrayList<Auction>auctionsThreads;
    ArrayList<BreackTime> breakTimeThreads;

    public static void main(String[]args){

        Main main = new Main();
        try {
            main.run();
        } catch (IOException e) {
            System.out.println("server creation Failed!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    private void run() throws IOException, SQLException, NamingException, ClassNotFoundException {

        ServerSocket serv = new ServerSocket(6001);
        //ServerSocket serv2 = new ServerSocket(6002);
        System.out.println("server listening to port "+6001);
        Socket sok;
        //Socket sok2;
        userList=new ArrayList<>();
        breakTime= new ArrayList<>();
        auctions = new ArrayList<>();
        lista= new ArrayList<>();
        auctionsThreads=new ArrayList<>();
        breakTimeThreads=new ArrayList<>();

        while (true) {

            sok = serv.accept();
            //sok2 = serv2.accept();
            //sok2=null;
            System.out.println("new Client");
            new Control(sok,userList, breakTime, auctions,lista,auctionsThreads,breakTimeThreads).start();
            //teste.start();
        }
    }
}
