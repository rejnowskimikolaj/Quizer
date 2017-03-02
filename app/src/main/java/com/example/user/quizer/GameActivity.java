package com.example.user.quizer;

import android.app.AlertDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import utils.Question;
import utils.QuestionLoader;
import utils.SavingUtil;

public class GameActivity extends AppCompatActivity {

    public static final String QUESTION_TAG = "question";
    public static final String QUESTION_NUMBER="number";
    private List<Question> questions;
    private ListView levelListView;

    private List<String> levelList;

    QuestionFragment questionFragment;
    GameInfoFragment gameInfoFragment;
    LifeBeltFragment lifeBeltFragment;
    public int questionNumber;

    final private int QUESTIONMAXDIFFICULTY=14;


    public void setLevelList(){
        levelList = new ArrayList<>();
        int [] arr = {100,200,300,500,1000,2000,4000,8000,16000,32000,64000,125000,250000,500000,1000000};

        for(int i:arr){
            levelList.add(i+"");
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setLevelList();
        questions = new ArrayList<>();
        QuestionLoader questionLoader= new QuestionLoader(R.raw.questions,this);
        questionLoader.loadQuestions();
        questions=questionLoader.getQuestionList();

       // Log.d("QUESTIONSLIST", questions.size()+"");

        Bundle questionArguments = new Bundle();
        questionArguments.putParcelable(QUESTION_TAG,getDifficultyQuestion(questionNumber));
        questionFragment = new QuestionFragment();
        questionFragment.setArguments(questionArguments);

        questionNumber=0;

        gameInfoFragment=new GameInfoFragment();

        lifeBeltFragment = new LifeBeltFragment();

        getFragmentManager().beginTransaction()
                .add(R.id.game_activity_game_info_frame, gameInfoFragment).commit();

        getFragmentManager().beginTransaction()
                .add(R.id.game_activity_question_frame, questionFragment).commit();

        getFragmentManager().beginTransaction()
                .add(R.id.activity_game_lifeBelt_frame, lifeBeltFragment).commit();

        displayLevelDialog(questionNumber-1);


    }

    public void onAnswerSelected(String text,Question question){
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();


        if(text.equals(QuestionFragment.CORRECT_ANSWER_TAG)) {

            if(questionNumber==QUESTIONMAXDIFFICULTY){
                ///WIN
                questionNumber++;


                GameFinishedFragment gameFinishedFragment = getNewFinishedFragmentWithResult(this.questionNumber,GameFinishedFragment.GAME_WON_TAG,question);
                getFragmentManager().beginTransaction()
                        .replace(R.id.game_activity_question_frame, gameFinishedFragment).commit();
                    gameInfoFragment.endGame();

            }


            else {
                ///NEXT QUESTION
                displayLevelDialog(questionNumber);
                questionNumber++;

                Bundle questionArguments = new Bundle();
                questionArguments.putParcelable(QUESTION_TAG, getDifficultyQuestion(questionNumber));
                questionFragment = new QuestionFragment();
                questionFragment.setArguments(questionArguments);

                getFragmentManager().beginTransaction()
                        .replace(R.id.game_activity_question_frame, questionFragment).commit();
            }
        }

        else {

            //LOST
           // SavingUtil.cleanScore(this);


            GameFinishedFragment gameFinishedFragment = getNewFinishedFragmentWithResult(this.questionNumber,GameFinishedFragment.GAME_LOST_TAG,question);
            getFragmentManager().beginTransaction()
                    .replace(R.id.game_activity_question_frame, gameFinishedFragment).commit();
           // Toast.makeText(this, ReadingUtil.readScore(this),Toast.LENGTH_LONG).show();
            gameInfoFragment.endGame();
        }

        gameInfoFragment.displayQuestionNumber(questionNumber);


    }

    public Question getDifficultyQuestion(int questionNumber){


        List<Question> difficultyQuestions = new ArrayList<>();
        Random r = new Random();
        for(Question q: questions){
            if (q.getDifficulty()<=questionNumber) {
                if (q.getDifficulty() == questionNumber) {
                    difficultyQuestions.add(q);
                }
            }

            else break;

        }

        return difficultyQuestions.get(r.nextInt(difficultyQuestions.size()));
    }

    public GameFinishedFragment getNewFinishedFragmentWithResult(int questionNumber,String text,Question question){

            Bundle gameFinishedArguments = new Bundle();
            gameFinishedArguments.putString(QuestionFragment.RESULT_TAG,text);
            gameFinishedArguments.putParcelable(QUESTION_TAG,question);
            gameFinishedArguments.putInt(QUESTION_NUMBER,questionNumber);


            GameFinishedFragment gameFinishedFragment = new GameFinishedFragment();

            gameFinishedFragment.setArguments(gameFinishedArguments);

            return  gameFinishedFragment;

    }


        public void useCallLifebelt() {
            this.questionFragment.useCallLifebelt();
        }

        public void useAskLifebelt() {
            this.questionFragment.useAskLifeBelt();
        }

        public void useFiftyLifebelt() {
            this.questionFragment.useFiftyLifebelt();
        }

        public void displayLevelDialog(final int level){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            levelListView = new ListView(this);
            levelListView.setBackgroundColor(getResources().getColor(R.color.darkblue));


            ArrayAdapter adapter = new ArrayAdapter(this,R.layout.level_dialog_item,levelList) {





                @NonNull
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    TextView textView = (TextView) super.getView(position, convertView, parent);


                        textView.setText(levelList.get(position));


                        if(position<=level) {
                            textView.setText(levelList.get(position));
                            textView.setTextColor(getResources().getColor(R.color.green));
                            Log.d("ADAPTER", "position: "+position+" level: "+level);
                        }

                        else if(position==level+1){
                            textView.setText(getString(R.string.question_for)+levelList.get(position));
                        }

                        else {
                            textView.setText(levelList.get(position));

                            textView.setTextColor(getResources().getColor(R.color.answerButtonTextColor));

                        }



                    return textView;
                }
            };


            levelListView.setAdapter(adapter);


            builder.setView(levelListView);
           // builder.setTitle("Your progress");

            AlertDialog levelAlert;
            levelAlert = builder.create();
            levelAlert.show();
        }

    public String getTodayDateString(){
        Time today = new Time(Time.getCurrentTimezone());

        today.setToNow();
        //Then, you can get all the date fields you want, like, for example:

        String result="";
        result+=today.monthDay+"-"+(today.month+1)+"-"+today.year;
//        textViewDay.setText(today.monthDay + "");             // Day of the month (1-31)
//        textViewMonth.setText(today.month + "");              // Month (0-11)
//        textViewYear.setText(today.year + "");                // Year
//        textViewTime.setText(today.format("%k:%M:%S"));  // Current time

        return result;
    }

    public void displayYouWonDialog(){

    }

}

