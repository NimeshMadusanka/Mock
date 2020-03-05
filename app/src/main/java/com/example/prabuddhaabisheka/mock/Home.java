package com.example.prabuddhaabisheka.mock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    private Button login, reg;
    private EditText uname, pass;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelper = new DBHelper(this);

        login = findViewById(R.id.btnLogin);
        reg = findViewById(R.id.btnReg);
        uname = findViewById(R.id.uname);
        pass = findViewById(R.id.pass);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<User> list = dbHelper.readAllInfo();

                for (User u : list){

                    if(u.getUserName().equals(uname.getText().toString())){
                        if(u.getPassword().equals(pass.getText().toString())){

                            Intent intent = new Intent(Home.this, ProfileManagement.class);
                            intent.putExtra("id", u.getUserId());
                            startActivity(intent);
                        }
                    }
                }
            }
        });

        reg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String user = uname.getText().toString();
                String passwrd = pass.getText().toString();

                dbHelper.addInfo(user, passwrd);

                Toast.makeText(Home.this, "User Registered!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
