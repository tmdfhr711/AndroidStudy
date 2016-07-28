package example.android.laioh.bottomupsheetdialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/*
 * Create by Lai.OH on 2016-07-29
 */
public class BottomUpSheet extends AppCompatActivity implements View.OnClickListener{

    private Button bottomup_show_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    private void init(){
        bottomup_show_btn = (Button) findViewById(R.id.show_bottomup_button);
        bottomup_show_btn.setOnClickListener(this);
    }

    private void openBottomUpSheet(){

        View view = getLayoutInflater().inflate(R.layout.bottom_up_sheet, null);
        TextView cate1_tv = (TextView) view.findViewById(R.id.bottomup_cate1);
        TextView cate2_tv = (TextView) view.findViewById(R.id.bottomup_cate2);
        TextView cancel_tv = (TextView) view.findViewById(R.id.bottomup_cancel);

        final Dialog mBottomSheetDialog = new Dialog (BottomUpSheet.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView (view);
        mBottomSheetDialog.setCancelable (false);
        mBottomSheetDialog.getWindow ().setLayout (LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow ().setGravity (Gravity.BOTTOM);
        mBottomSheetDialog.show ();

        cate1_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomUpSheet.this, "Clicked CATEGORY #1", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });

        cate2_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomUpSheet.this, "Clicked CATEGORY #2", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });

        cancel_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BottomUpSheet.this, "CANCEL", Toast.LENGTH_SHORT).show();
                mBottomSheetDialog.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.show_bottomup_button:
                openBottomUpSheet();
                break;

        }
    }
}
