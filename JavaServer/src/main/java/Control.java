import AuxClass.Transport;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Control extends Thread {
    Socket client;
    ObjectInputStream in;
    ObjectOutputStream out;

    String username;



    public  Control(Socket cli){
        this.client=cli;
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

        while (true){
            tran= (Transport) in.readObject();
            switch (tran.getOpc()){
                case 1:
                    System.out.println("antes login" + tran.getOpc()+" " +tran.getUser().getUsername());
                    out.writeObject(login(tran));
                    break;
                default:
                    System.out.println("Bora tirar cafés");
                    break;
            }
        }




    }

    private Transport login(Transport tran) {

        if (tran.getUser().getUsername().equals("rsantos") && tran.getUser().getPass().equals("rsantos")) {
            tran.setLogin(true);
            username=tran.getUser().getUsername();
            System.out.println("user Login");

        }
        else
            tran.setLogin(false);
        return tran;
    }


}
