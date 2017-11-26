package AuxClass;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class SocketClient implements Serializable {
    private static final long serialVersionUID = -6470090944414208496L;

    Socket sok;
    ObjectInputStream in;
    ObjectOutputStream out;
    public SocketClient() {

    }

    public void connect() throws IOException {
        //Your connection code will come here
        sok= new Socket("192.168.10.165",6001);
        try {
            out = new ObjectOutputStream(sok.getOutputStream());
            out.flush();
            in = new ObjectInputStream(sok.getInputStream());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnect() throws IOException {
        //Your disconnection code will come here
        sok.close();
    }

    public Transport sendMessage(Transport message) throws IOException, ClassNotFoundException {
        // message sending will code
        out.writeObject(message);
        return (Transport) in.readObject();
    }


}