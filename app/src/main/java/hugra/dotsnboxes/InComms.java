package hugra.dotsnboxes;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Vector;

import sharedPackages.ActionRequest;
import sharedPackages.User;

/**
 * Created by graham on 09/08/15.
 */
public class InComms extends Thread {

    private Socket scSock;
    private ObjectInputStream scStream;
    public static Vector<User> userList;
    private Lobby activityReference;

    public InComms(Socket socket, Lobby activityReference) throws IOException{
        this.scSock = socket;
        this.activityReference = activityReference;


    }

    public void run(){
        try {
            scStream = new ObjectInputStream(scSock.getInputStream());
            ActionRequest actionRequest;
            while(Lobby.stayAlive){
                actionRequest = (ActionRequest)scStream.readObject();
                switch(actionRequest.getAction()){
                    case(ActionRequest.SC_USERLIST):
                        userList = actionRequest.getUserList();
                        activityReference.updateUserListDisplay(userList);
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
