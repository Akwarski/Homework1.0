package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView iv;
    int drawable, rand = 0, sound = 0, flag = 0, temporary;
    String name = "", temp = "";
    MediaPlayer mp;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv = findViewById(R.id.imageView);
        tv = findViewById(R.id.textView);

        randNum();

        final FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mp != null){
                    mp.stop();
                    mp = null;
                }else{
                    mp = MediaPlayer.create(getApplicationContext(), sound);
                    mp.start();

                    Snackbar.make(view, "playing...", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void randNum(){
        Random r = new Random();
        rand = r.nextInt(5);
        choosePicture(rand);
    }

    public void choosePicture(int i){
        switch (i){
            case 0 :
                drawable = R.drawable.avatar_1;
                break;
            case 1:
                drawable = R.drawable.avatar_2;
                break;
            case 2:
                drawable = R.drawable.avatar_3;
                break;
            case 3:
                drawable = R.drawable.avatar_4;
                break;
            case 4:
                drawable = R.drawable.avatar_5;
                break;
        }
        iv.setImageResource(drawable);
    }

    public void changeContact(View view) {
        Intent intent = new Intent(getApplicationContext(), ChangeContact.class);
        startActivityForResult(intent,1);
    }

    public void changeSound(View view) {
        if(!tv.getText().equals("")) {
            Intent intent = new Intent(getApplicationContext(), ChangeSound.class);
            startActivityForResult(intent, 2);
        }else
            Toast.makeText(this, "First choose contact", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1) {
            if (resultCode == RESULT_OK) {
                temp = name;
                name = data.getStringExtra("nameSet");

                if(!temp.equals(name)){
                    tv.setText(new String((Base64.decode(name,Base64.DEFAULT))));
                    randNum();
                }

            }else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "I'm back!", Toast.LENGTH_SHORT).show();
            }
        }

        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                sound = data.getIntExtra("sound", 0);
            }else if(resultCode == RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "I'm back!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void stopPlayer(){
        if(mp != null){
            mp.release();
            mp = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
