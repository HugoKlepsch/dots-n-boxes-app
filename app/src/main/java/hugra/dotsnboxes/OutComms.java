package hugra.dotsnboxes;

import android.app.Notification;
import android.content.res.Resources;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;

import sharedPackages.ActionRequest;
import sharedPackages.User;

/**
 * Created by graham on 09/08/15.
 */
public class OutComms extends Thread {
    private Socket csSock;
    private ObjectOutputStream csStream;

    public OutComms(){
        Resources res = Resources.getSystem();
        try {
            csSock = new Socket(res.getString(R.string.defaultIP), res.getInteger(R.integer
                    .defaultPort));
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void run(){
        try {
            csStream = new ObjectOutputStream(csSock.getOutputStream());
            csStream.writeObject(new ActionRequest(ActionRequest.CS_CONNECT, new User(null, null)
            )); //TODO FIX THIS
            while(Lobby.stayAlive){
                csStream.writeObject(new ActionRequest(ActionRequest.CS_USERLIST));
                csStream.flush();
                Thread.sleep(1000); //TODO consider small rewrite so that it only requests a userlist when the user hits a button
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
