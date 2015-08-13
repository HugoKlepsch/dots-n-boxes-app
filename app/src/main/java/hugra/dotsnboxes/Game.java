package hugra.dotsnboxes;

import hugra.dotsnboxes.Lobby;
import hugra.dotsnboxes.OutComms;

public class Game extends Thread {
    Lobby activityReference;
    String username;
    String password;
    public OutComms outComms;

    public Game(Lobby activityReference, String username, String password){
        this.activityReference = activityReference;
        this.username = username;
        this.password = password;
        outComms = new OutComms(username, password, activityReference);



    }

    public void run(){
        outComms.start();



    }

}
