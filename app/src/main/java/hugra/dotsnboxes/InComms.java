package hugra.dotsnboxes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;

import sharedPackages.ActionRequest;

/**
 * Created by graham on 09/08/15.
 */
public class InComms extends Thread {

    private Socket scSock;
    private ObjectInputStream scStream;

    public InComms(Socket socket) throws IOException{
        scSock = socket;


    }

    public void run(){
        try {
            scStream = new ObjectInputStream(scSock.getInputStream());
            ActionRequest actionRequest;
            while(Lobby.stayAlive){
                actionRequest = (ActionRequest)scStream.readObject();
                switch (actionRequest.getAction()){
                    case ActionRequest.SC_USERLIST: //TODO update
                        break;
                }

            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
