package hugra.dotsnboxes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Vector;

import sharedPackages.User;

public class Lobby extends AppCompatActivity {
    public static boolean stayAlive = true;
    private TextView userListDisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        userListDisplay = (TextView) findViewById(R.id.activityLobby_UserListDisplay);
    }

    public void updateUserListDisplay(Vector<User> userList){
        for(int i = 0;i<userList.size();i++){
            userListDisplay.append(userList.get(i).getUsername());
            userListDisplay.append("\n");
        }
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
