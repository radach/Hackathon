import AuxClass.Auction;
import AuxClass.BreackTime;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ControlAux extends Thread {
    Socket sok;
    ObjectOutputStream out;
    ArrayList<Auction> auctionsThreads;
    ArrayList<BreackTime> breakTimeThreads;
    ArrayList<Socket> lista;
    public ControlAux( ArrayList<Socket> lista, ArrayList<Auction> auctionsThreads, ArrayList<BreackTime> breakTimeThreads){

        this.auctionsThreads=auctionsThreads;
        this.breakTimeThreads=breakTimeThreads;
        this.lista=lista;
    }


    public void run(){
        try {
            MqttClient client = new MqttClient("tcp://192.168.10.170:1883", MqttClient.generateClientId());
            client.connect();
        } catch (MqttException e) {
            e.printStackTrace();
        }
        //lista.add(sok);
        while (true){
            while (!auctionsThreads.isEmpty()){
                MqttMessage message= new MqttMessage();
                //out=new ObjectOutputStream(lista.get(i).getOutputStream());
                //out.writeObject(auctionsThreads.get(0));
                Object test=(Object)auctionsThreads.get(0);
                System.out.println("");
                System.out.println(auctionsThreads.get(0).toString());
                message.setPayload(test.toString().getBytes());
                auctionsThreads.remove(0);
                }
            while (!breakTimeThreads.isEmpty()){

                breakTimeThreads.remove(0);

            }
        }




    }
}
