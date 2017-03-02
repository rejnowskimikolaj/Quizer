package com.example.user.quizer;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

    Button newGameButton;
    Button settingsButton;
    Button scoresButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        newGameButton = (Button) findViewById(R.id.menu_new_game_button);
        settingsButton = (Button) findViewById(R.id.menu_settings_button);
        scoresButton = (Button) findViewById(R.id.menu_scores_button);

        setListeners();

    }

    public void setListeners(){

        MenuOnClickListener listener = new MenuOnClickListener();
        newGameButton.setOnClickListener(listener);
        settingsButton.setOnClickListener(listener);
        scoresButton.setOnClickListener(listener);

    }

    public class MenuOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(getApplicationContext(),GameActivity.class);

            int buttonID = view.getId();

            switch(buttonID){
                case R.id.menu_new_game_button: intent=new Intent(getApplicationContext(),GameActivity.class);
                    break;
                case R.id.menu_settings_button: intent = new Intent(getApplicationContext(),SettingsActivity.class);
                    break;
                case R.id.menu_scores_button: intent = new Intent(getApplicationContext(),ScoresActivity.class);
                    break;

            }

            startActivity(intent);
        }
    }
}
