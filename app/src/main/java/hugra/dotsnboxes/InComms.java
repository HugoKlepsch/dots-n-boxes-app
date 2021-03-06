package hugra.dotsnboxes;


import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;

import java.net.Socket;
import java.util.Vector;

import sharedPackages.ActionRequest;
import sharedPackages.User;


public class InComms extends Thread {

    private Socket scSock;
    private ObjectInputStream scStream;
    public static Vector<User> userList;
    private Lobby activityReference;
    public boolean hasConnected;

    public InComms(Socket socket, Lobby activityReference){
        this.scSock = socket;
        this.activityReference = activityReference;
        hasConnected = false;


    }

    public void run(){
        try {
            final Context context = activityReference.getApplicationContext();

            scStream = new ObjectInputStream(scSock.getInputStream());
            ActionRequest actionRequest;
            while(Lobby.stayAlive){
                actionRequest = (ActionRequest)scStream.readObject();
                switch(actionRequest.getAction()){
                    case(ActionRequest.SC_USERLIST):
                        if (!actionRequest.getUserList().equals(userList)) {
                            userList = actionRequest.getUserList();
                            Log.d("in incomms sc_userlist", "inside the case for sc_userlist");
                            activityReference.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    activityReference.updateUserListDisplay(userList);
                                }
                            });
                        }

                        break;

                    case(ActionRequest.SC_LOGIN_FAILURE):
                        hasConnected = true;
                        activityReference.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Login failure!", Toast.LENGTH_LONG).show();
                            }
                        });

                        Log.d("in incomms sc_login_f", "inside the case for sc_login_failure");

                        break;

                    case(ActionRequest.SC_LOGIN_SUCCESS):
                        hasConnected = true;
                        activityReference.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Login success!", Toast.LENGTH_LONG).show();
//                                try {
//                                    Lobby.game.outComms.interrupt();
//                                    Lobby.game.outComms = null;
//                                    Lobby.game.inComms.interrupt();
//                                    Lobby.game.inComms = null;
//                                    Lobby.game.interrupt();
//                                    Lobby.game = null;
//                                    activityReference.finish();
//
//
//
//                                } catch (Exception e){
//                                    e.printStackTrace();
//                                }


                            }
                        });

                        Log.d("in incomms sc_login_s", "inside the case for sc_login_success");


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
