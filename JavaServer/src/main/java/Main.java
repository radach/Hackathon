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
        System.out.println("server listening to port "+6001);
        Socket sok;
        userList=new ArrayList<>();
        breakTime= new ArrayList<BreackTime>();


        while (true) {

            sok = serv.accept();
            System.out.println("new Client");
            new Control(sok,userList, breakTime).start();
            //teste.start();
        }
    }
}
