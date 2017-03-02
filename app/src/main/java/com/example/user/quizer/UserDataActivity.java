package com.example.user.quizer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserDataActivity extends AppCompatActivity {

    Button sendButton;
    EditText firstName;
    EditText lastName;
    EditText birthDate;
    EditText street;
    EditText house;
    EditText zipCode;
    EditText city;
    EditText country;
    EditText email;
    EditText phone;
    EditText passFirst;
    EditText passSecond;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data);

        setEditTexts();

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }


    public class SendButtonOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {


        }
    }

    public void setEditTexts(){

        this.sendButton = (Button) findViewById(R.id.user_data_send_button) ;
        this.birthDate = (EditText) findViewById(R.id.user_data_birthDate_edit_text);
        this.lastName = (EditText) findViewById(R.id.user_data_lastName_edit_text);
        this.firstName = (EditText) findViewById(R.id.user_data_firstName_edit_text);

    }
}
