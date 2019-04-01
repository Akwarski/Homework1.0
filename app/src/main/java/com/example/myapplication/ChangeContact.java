package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ChangeContact extends AppCompatActivity {
    String[] tabString;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    Button cancel;
    boolean checked = false;
    String nameSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_contact);
        tabString = getResources().getStringArray(R.array.contacts);
        rb1 = findViewById(R.id.RadioButton1);
        rb1.setText(new String((Base64.decode(tabString[0],Base64.DEFAULT))));
        rb2 = findViewById(R.id.RadioButton2);
        rb2.setText(new String((Base64.decode(tabString[1],Base64.DEFAULT))));
        rb3 = findViewById(R.id.RadioButton3);
        rb3.setText(new String((Base64.decode(tabString[2],Base64.DEFAULT))));
        rb4 = findViewById(R.id.RadioButton4);
        rb4.setText(new String((Base64.decode(tabString[3],Base64.DEFAULT))));
        rb5 = findViewById(R.id.RadioButton5);
        rb5.setText(new String((Base64.decode(tabString[4],Base64.DEFAULT))));
        cancel = findViewById(R.id.CANCEL);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void confirm(View view) {
        if(checked) {
            Intent intent = new Intent();
            intent.putExtra("nameSet", nameSet);
            setResult(RESULT_OK, intent);
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Select contact", Toast.LENGTH_LONG).show();
        }
    }

    public void chooseName(View view) {
        checked = ((RadioButton) view).isChecked();

        if(checked) {
            switch (view.getId()) {
                case R.id.RadioButton1:
                    nameSet = tabString[0];
                    break;
                case R.id.RadioButton2:
                    nameSet = tabString[1];
                    break;
                case R.id.RadioButton3:
                    nameSet = tabString[2];
                    break;
                case R.id.RadioButton4:
                    nameSet = tabString[3];
                    break;
                case R.id.RadioButton5:
                    nameSet = tabString[4];
                    break;
            }
        }
    }
}
