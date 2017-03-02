package com.example.user.quizer;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import java.util.ArrayList;
import java.util.List;

import utils.ReadingUtil;
import utils.SavingUtil;

public class ScoresActivity extends Activity {

    ListView scoresContainer;
    List<String> scoreList;
    Button deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        scoresContainer = (ListView) findViewById(R.id.scores_list_view);
        loadScores();

        final ArrayAdapter adapter = new ArrayAdapter(this,R.layout.score_list_view_item,scoreList);
        deleteButton = (Button) findViewById(R.id.activity_scores_delete_button) ;
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreList.clear();
                SavingUtil.cleanScore(getApplicationContext());
                loadScores();
                scoresContainer.setAdapter(adapter);
            }
        });

        scoresContainer.setAdapter(adapter);
    }

    public void loadScores(){


        this.scoreList = new ArrayList<>();

        String scores = ReadingUtil.readScore(getApplicationContext());
        if(scores.length()==0) return;

        String[] scoreArr = scores.split("[\\r\\n]+");




        for(String line:scoreArr){
            String scoreLine ="";
            String[] lineArr = line.split(";");
            Log.d("LINEARR", lineArr.length+"");
            scoreLine+=lineArr[0]+","+lineArr[1]+", "+getResources().getString(R.string.max_level)+": " +lineArr[2];
            scoreList.add(scoreLine);
        }

    }
}
