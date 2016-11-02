package example.android.laioh.edittextexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText name_input_edt;
    private EditText phone_input_edt;
    private TextView name_output_tv;
    private TextView phone_output_tv;
    private Button name_submit_btn;
    private Button phone_submit_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        init();
    }

    private void init(){
        name_input_edt = (EditText) findViewById(R.id.name_input_edt);
        phone_input_edt = (EditText) findViewById(R.id.phone_input_edt);
        name_output_tv = (TextView) findViewById(R.id.name_output_tv);
        phone_output_tv = (TextView) findViewById(R.id.phone_output_tv);
        name_submit_btn = (Button) findViewById(R.id.name_submit_btn);
        phone_submit_btn = (Button) findViewById(R.id.phone_submit_btn);

        name_submit_btn.setOnClickListener(this);
        phone_submit_btn.setOnClickListener(this);
    }

    private void showNameData(){
        //EditText에 입력이 되었는지 확인
        if (name_input_edt.getText().toString().length() != 0){
            name_output_tv.setText("출력결과 : " + name_input_edt.getText().toString());
            name_input_edt.setText("");
        } else {
            name_output_tv.setText("출력결과 : 입력값이 없습니다.");
        }
    }

    private void showPhoneData(){
        //EditText에 입력이 되었는지 확인
        if (phone_input_edt.getText().toString().length() != 0){
            phone_output_tv.setText("출력결과 : " + phone_input_edt.getText().toString());
            phone_input_edt.setText("");
        } else {
            phone_output_tv.setText("출력결과 : 입력값이 없습니다.");
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.name_submit_btn:
                showNameData();
                break;
            case R.id.phone_submit_btn:
                showPhoneData();
                break;
        }
    }
}
