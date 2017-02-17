package example.android.laioh.firebaseauth;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button registerButton;
    private EditText email_edt;
    private EditText password_edt;
    private TextView signin_tv;

    private ProgressDialog mProgressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        registerButton = (Button) findViewById(R.id.regist_btn);
        email_edt = (EditText) findViewById(R.id.email_edt);
        password_edt = (EditText) findViewById(R.id.password_edt);

        signin_tv = (TextView) findViewById(R.id.textViewSignin);

        registerButton.setOnClickListener(this);
        signin_tv.setOnClickListener(this);
    }


    private void registerUser(){
        String email = email_edt.getText().toString();
        String password = password_edt.getText().toString();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            //password is empty
            //비밀번호 6자리 이상인지도 체크해줘야함
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        mProgressDialog.setMessage("Registering User...");
        mProgressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //user is successfullt registered and logged in
                            //we will tart the profile activity here
                            //right now lets display a toast only
                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                        } else {
                            Toast.makeText(MainActivity.this, "Could not register ... please try again", Toast.LENGTH_SHORT).show();
                            mProgressDialog.dismiss();
                        }
                    }
                });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regist_btn :
                registerUser();
                break;

            case R.id.textViewSignin :

                break;
        }
    }
}
