package hugra.dotsnboxes;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;


public class LoginActivity extends Activity implements LogInFrag.OnFragmentInteractionListener,
        SignUpFrag.OnFragmentInteractionListener {

    private RadioGroup logInSignUpRadioGroup;
    private RadioButton logIn;
    private RadioButton signUp;
    private FragmentManager fragmentManager;
    private LogInFrag logInFrag;
    private SignUpFrag signUpFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        logInFrag = new LogInFrag();
        signUpFrag = new SignUpFrag();

        fragmentTransaction.add(R.id.loginActivity_FragmentContainer, logInFrag);
        fragmentTransaction.commit();

        logInSignUpRadioGroup = (RadioGroup) findViewById(R.id.loginActivity_logInSignUpRadioGroup);
        logIn = (RadioButton) findViewById(R.id.loginActivity_LogInRadioButton);
        signUp = (RadioButton) findViewById(R.id.loginActivity_SignUpRadioButton);

        logInSignUpRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean isLogInSelected;
                isLogInSelected = checkedId == logIn.getId();
                FragmentTransaction fragTransaction = fragmentManager.beginTransaction();

                if (isLogInSelected){
                    fragTransaction.remove(signUpFrag);
                    fragTransaction.add(R.id.loginActivity_FragmentContainer, logInFrag);
                    fragTransaction.commit();
                } else {
                    fragTransaction.remove(logInFrag);
                    fragTransaction.add(R.id.loginActivity_FragmentContainer, signUpFrag);
                    fragTransaction.commit();
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
