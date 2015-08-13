package hugra.dotsnboxes;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.net.Uri;

import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;


import java.util.Vector;

import sharedPackages.User;

public class Lobby extends Activity implements TestPlayerCardFrag.OnFragmentInteractionListener{
    public static boolean stayAlive = true;
    private TextView userListDisplay;
    public static Game game;
    private Vector<TestPlayerCardFrag> addedFragments = new Vector<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        userListDisplay = (TextView) findViewById(R.id.activityLobby_UserListDisplay);
        game = new Game(this, LogInFrag.username, LogInFrag.password);
        game.start();

    }

    public void updateUserListDisplay(Vector<User> userList){
        userListDisplay.setText("");
        for(int i = 0;i<userList.size();i++){
            userListDisplay.append(userList.get(i).getUsername());
            userListDisplay.append("\n");

        }

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        for (int i = 0; i < addedFragments.size(); i ++){
            fragmentTransaction.remove(addedFragments.get(i));
        }
        for (int i2 = 0; i2 < 30; i2++) { //to duplicate a user x times
            for (int i = 0; i < userList.size(); i++) {
                TestPlayerCardFrag temp = TestPlayerCardFrag.newInstance(userList.get(i).getUsername(), 20);
                addedFragments.add(temp);
                fragmentTransaction.add(R.id.Lobby_FragContainer, temp);
            }
        }


        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lobby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_LogOut) {
            try {
                Lobby.game.outComms.inComms.interrupt();
                Lobby.game.outComms.inComms = null;
                Lobby.game.outComms.interrupt();
                Lobby.game.outComms = null;
                Lobby.game.interrupt();
                Lobby.game = null;
                this.finish();



            } catch (Exception e){
                e.printStackTrace();
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
