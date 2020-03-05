package com.example.prabuddhaabisheka.mock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class EditProfile extends AppCompatActivity {

    private Button edit,delete,search;
    private EditText uname, dob, pass;
    private RadioGroup radioGroup;
    private RadioButton male, female;
    private String gender;
    private DBHelper dbHelper;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        dbHelper = new DBHelper(this);
        Intent intent = getIntent();
        userId = intent.getStringExtra("id");

        Toast.makeText(EditProfile.this, userId, Toast.LENGTH_SHORT).show();

        uname = findViewById(R.id.userName);
        dob = findViewById(R.id.dateOfB);
        pass = findViewById(R.id.psswrd);
        edit = findViewById(R.id.btnEdit);
        delete = findViewById(R.id.btnDelete);
        search = findViewById(R.id.btnSearch);
        radioGroup = findViewById(R.id.radio);
        male = findViewById(R.id.maleR);
        female = findViewById(R.id.femaleR);

        ArrayList<User> list =  dbHelper.readAllInfo(userId, null);

        if(!list.isEmpty()){

            for (User u : list){

                uname.setText(u.getUserName());
                pass.setText(u.getPassword());
                dob.setText(u.getDateOfBirth());

                if(u.getGender() != null){

                    if(u.getGender().equals("Male")){

                        male.setChecked(true);
                    }
                    else
                    {
                        female.setChecked(true);
                    }
                }
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int view) {

                if(view == R.id.femaleR){

                    gender = "Female";
                }
                else{

                    gender = "Male";
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<User> urs = dbHelper.readAllInfo(null, uname.getText().toString());

                for (User u : urs){

                    userId = u.getUserId();
                    uname.setText(u.getUserName());
                    pass.setText(u.getPassword());
                    dob.setText(u.getDateOfBirth());

                    if(u.getGender() != null){

                        if(u.getGender().equals("Male")){

                            male.setChecked(true);
                        }
                        else
                        {
                            female.setChecked(true);
                        }
                    }
                }
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = uname.getText().toString();
                String date = dob.getText().toString();
                String pwrd = pass.getText().toString();
                if(female.isChecked()){

                    gender = "Female";
                }
                else{

                    gender = "Male";
                }

                int count = dbHelper.updateInfo(userId, userName, pwrd, date, gender);

                if(count > 0){

                    Toast.makeText(EditProfile.this, "Updated!", Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(EditProfile.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int count = dbHelper.deleteInfo(userId);

                if(count > 0){

                    Toast.makeText(EditProfile.this, "Deleted!", Toast.LENGTH_SHORT).show();
                }
                else{

                    Toast.makeText(EditProfile.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
