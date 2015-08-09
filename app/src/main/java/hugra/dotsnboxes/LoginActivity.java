package hugra.dotsnboxes;


import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    private EditText logInUsernameEditText;
    private EditText logInPasswordEditText;
    private EditText signUpUsernameEditText;
    private EditText signUpPasswordEditText;

    private Button logInButton;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        logInFrag = new LogInFrag();
        signUpFrag = new SignUpFrag();

        logInUsernameEditText = (EditText) findViewById(R.id.LogInFrag_UsernameBox);
        logInPasswordEditText = (EditText) findViewById(R.id.LogInFrag_PasswordBox);
        logInButton = (Button) findViewById(R.id.LogInFrag_LogInButton);


        signUpUsernameEditText = (EditText) findViewById(R.id.SignUpFrag_UsernameBox);
        signUpPasswordEditText = (EditText) findViewById(R.id.SignUpFrag_PasswordBox);
        signUpButton = (Button) findViewById(R.id.SignUpFrag_SignUpButton);


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

    class ClickHandler implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Log.d("onclickhandler", (v == logInButton) ? "true" : "false");
        }
    }


}
