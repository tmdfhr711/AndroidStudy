package example.android.laioh.firebaseauth;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class SigninActivity extends AppCompatActivity implements View.OnClickListener{

    private Button signin_btn;
    private EditText email_edt;
    private EditText password_edt;
    private TextView signup_tv;

    private ProgressDialog mProgressDialog;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        mProgressDialog = new ProgressDialog(this);

        mFirebaseAuth = FirebaseAuth.getInstance();

        if (mFirebaseAuth.getCurrentUser() != null) {
            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
        }

        signin_btn = (Button) findViewById(R.id.signin_btn);
        email_edt = (EditText) findViewById(R.id.email_edt);
        password_edt = (EditText) findViewById(R.id.password_edt);
        signup_tv = (TextView) findViewById(R.id.textViewSignUp);

        signin_btn.setOnClickListener(this);
        signup_tv.setOnClickListener(this);
    }


    private void userLogin(){
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

        mFirebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        mProgressDialog.dismiss();
                        if (task.isSuccessful()) {
                            //start the profile actiity
                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        }
                    }
                });

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signin_btn :
                    userLogin();
                break;

            case R.id.textViewSignUp :
                startActivity(new Intent(this, MainActivity.class));
                finish();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }
}
