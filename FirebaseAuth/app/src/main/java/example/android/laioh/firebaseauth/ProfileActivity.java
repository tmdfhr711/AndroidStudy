package example.android.laioh.firebaseauth;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mFirebaseAuth;

    private TextView useremail_tv;
    private Button logout_btn;

    private DatabaseReference mDatabaseReference;

    private EditText named_edt, address_edt;
    private Button save_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        mFirebaseAuth = FirebaseAuth.getInstance();
        if (mFirebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, SigninActivity.class));
        }

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        useremail_tv = (TextView) findViewById(R.id.profile_useremail);

        useremail_tv.setText("Welcome " + user.getEmail());
        logout_btn = (Button) findViewById(R.id.profile_logout);
        named_edt = (EditText) findViewById(R.id.username_edt);
        address_edt = (EditText) findViewById(R.id.useraddress_edt);
        save_btn = (Button) findViewById(R.id.save_btn);

        save_btn.setOnClickListener(this);
        logout_btn.setOnClickListener(this);
    }

    private void saveUserInformation(){
        String name = named_edt.getText().toString();
        String address = address_edt.getText().toString();

        UserInformation userInformation = new UserInformation(name, address);

        FirebaseUser user = mFirebaseAuth.getCurrentUser();

        mDatabaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this, "Information Savaed...", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_logout:
                mFirebaseAuth.signOut();
                finish();
                startActivity(new Intent(this, SigninActivity.class));
                break;

            case R.id.save_btn :
                saveUserInformation();
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
