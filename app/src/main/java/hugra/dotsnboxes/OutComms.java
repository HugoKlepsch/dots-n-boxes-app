package hugra.dotsnboxes;


import android.content.res.Resources;
import android.util.Log;

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
    public InComms inComms;
    private String username;
    private String password;
    private Lobby activityReference;

    public OutComms(String username, String password, Lobby activityReference){
        Resources res = Resources.getSystem();
        this.username = username;
        this.password = password;
        this.activityReference = activityReference;




    }

    public void run(){
        try {
            try {
//            csSock = new Socket(res.getString(R.string.defaultIP), (res.getInteger(R.integer.defaultPort));
//            csSock = new Socket("198.27.14.217", (res.getInteger(R.integer.defaultPort)));
//            csSock = new Socket(res.getString(R.string.defaultIP), (7070));
                csSock = new Socket("192.168.0.214", (7070));

                inComms = new InComms(csSock, activityReference);
            } catch (IOException e) {
                e.printStackTrace();
            }
            inComms.start();
            csStream = new ObjectOutputStream(csSock.getOutputStream());
            csStream.writeObject(new ActionRequest(ActionRequest.CS_CONNECT, new User(username, password)));
            csStream.flush();
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
