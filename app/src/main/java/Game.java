import hugra.dotsnboxes.Lobby;
import hugra.dotsnboxes.OutComms;

/**
 * Created by hugo on 8/9/15.
 */
public class Game extends Thread {
    Lobby activityReference;
    String username;
    String password;

    public Game(Lobby activityReference){
        this.activityReference = activityReference;
        OutComms outComms = new OutComms(); //TODO keep coding this, it's unfinished.

    }

    public void run(){


    }

}
