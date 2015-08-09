package hugra.dotsnboxes;

import android.app.Notification;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sharedPackages.ActionRequest;
import sharedPackages.User;

/**
 * Created by graham on 09/08/15.
 */
public class OutComms extends Thread {
    private Socket csSock;
    private ObjectOutputStream csStream;

    public OutComms(Socket socket){
        csSock = socket;
    }

    public void run(){
        try {
            csStream = new ObjectOutputStream(csSock.getOutputStream());
//            csStream.writeObject(new ActionRequest(ActionRequest.CS_CONNECT, new User()));
            while(Lobby.stayAlive){
                csStream.writeObject(new ActionRequest(ActionRequest.CS_USERLIST));
                csStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
