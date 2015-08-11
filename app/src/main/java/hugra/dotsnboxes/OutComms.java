package hugra.dotsnboxes;


import android.content.res.Resources;

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

    public OutComms(String username, String password){
        Resources res = Resources.getSystem();
        try {
            csSock = new Socket(res.getString(R.string.defaultIP), res.getInteger(R.integer.defaultPort));
            inComms = new InComms(csSock);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.username = username;
        this.password = password;

    }

    public void run(){
        try {
            inComms.start();
            csStream = new ObjectOutputStream(csSock.getOutputStream());
            csStream.writeObject(new ActionRequest(ActionRequest.CS_CONNECT, new User(username, password)));
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
