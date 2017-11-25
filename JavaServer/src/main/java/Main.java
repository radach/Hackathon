import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[]args){

        Main main = new Main();
        try {
            main.run();
        } catch (IOException e) {
            System.out.println("server creation Failed!!!");
        }


    }

    private void run() throws IOException {

        ServerSocket serv = new ServerSocket(6001);
        System.out.println("server listening to port "+6001);
        Socket sok;


        while (true) {

            sok = serv.accept();
            System.out.println("new Client");
            new Control(sok).start();
            //teste.start();
        }
    }
}
