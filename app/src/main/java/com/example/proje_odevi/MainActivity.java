package com.example.proje_odevi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.EditTextPreference;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.altitem){
            Toast.makeText(this, "Dil değiştirildi.", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final  EditText et1=(EditText)findViewById(R.id.editText2);
        final EditText et2= (EditText)findViewById(R.id.editText);
        final TextView tv1 = (TextView)findViewById(R.id.textView2);
        final TextView tv2 = (TextView)findViewById(R.id.textView3);

        final Button btnkaydol = findViewById(R.id.button);
        btnkaydol.setBackgroundColor(Color.parseColor("#ff0006"));
        btnkaydol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        btnkaydol.setBackgroundColor(Color.parseColor("#ffffff"));


                    }

                    @Override
                    public void onFinish() {
                        btnkaydol.setBackgroundColor(Color.parseColor("#ff0006"));


                    }
                }.start();

                Intent intent = new Intent(getApplicationContext(),uye_kayit.class);
                startActivity(intent);
            }
        });
        final Button btngiris = findViewById(R.id.button2);
        btngiris.setBackgroundColor(Color.parseColor("#ff0006"));
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        btngiris.setBackgroundColor(Color.parseColor("#ffffff"));


                    }

                    @Override
                    public void onFinish() {
                        btngiris.setBackgroundColor(Color.parseColor("#ff0006"));


                    }
                }.start();

                if(et1.getText().toString().equals("12345678901")&& et2.getText().toString().equals("123456")){
                    Intent i = new Intent(getApplicationContext(), giris.class);
                    i.putExtra("id", et1.getText().toString());
                    startActivity(i);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Kullanıcı adı veya şifre yanlış",Toast.LENGTH_SHORT).show();
                }

            }
        });


        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("user");


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String value = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });





    }
}
