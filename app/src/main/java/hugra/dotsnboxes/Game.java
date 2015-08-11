package hugra.dotsnboxes;

import hugra.dotsnboxes.Lobby;
import hugra.dotsnboxes.OutComms;

/**
 * Created by hugo on 8/9/15.
 */
public class Game extends Thread {
    Lobby activityReference;
    String username;
    String password;

    public Game(Lobby activityReference, String username, String password){
        this.activityReference = activityReference;
        this.username = username;
        this.password = password;
        OutComms outComms = new OutComms(username, password); //TODO finish this


    }

    public void run(){


    }

}
