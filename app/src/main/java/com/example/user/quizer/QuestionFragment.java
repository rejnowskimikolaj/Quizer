package com.example.user.quizer;


import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import utils.Question;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuestionFragment extends Fragment {

    Question question;
    TextView questionTextView;
    Button answerAButton;
    Button answerBButton;
    Button answerCButton;
    Button answerDButton;
    String [] answerArr;

    private boolean fiftyUsed;

    public static final String CORRECT_ANSWER_TAG = "correct";
    public static final String NOT_CORRECT_ANSWER_TAG = "notCorrect";
    public static final String RESULT_TAG = "result";





    public QuestionFragment() {
        // Required empty public constructor
    }

    public void setAnswerArray(){

        answerArr = new String[4];

        answerArr[0]= question.getAnswerA();
        answerArr[1]= question.getAnswerB();
        answerArr[2]=question.getAnswerC();
        answerArr[3]=question.getAnswerD();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_question, container, false);
        questionTextView = (TextView) view.findViewById(R.id.question_fragment_questionTextView);
        answerAButton = (Button) view.findViewById(R.id.question_fragment_answer_a);
        answerBButton = (Button) view.findViewById(R.id.question_fragment_answer_b);
        answerCButton = (Button) view.findViewById(R.id.question_fragment_answer_c);
        answerDButton = (Button) view.findViewById(R.id.question_fragment_answer_d);

        fiftyUsed = false;

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            question = bundle.getParcelable(GameActivity.QUESTION_TAG);
        }


        questionTextView.setText(question.getQuestionText());
        answerAButton.setText(question.getAnswerA());
        answerBButton.setText(question.getAnswerB());
        answerCButton.setText(question.getAnswerC());
        answerDButton.setText(question.getAnswerD());

        setOnclickListeners();

        setAnswerArray();
        return view;
    }


    public void setOnclickListeners(){
        AnswerButtonOnClickListener abocl = new AnswerButtonOnClickListener();
        answerAButton.setOnClickListener(abocl);
        answerBButton.setOnClickListener(abocl);
        answerCButton.setOnClickListener(abocl);
        answerDButton.setOnClickListener(abocl);
    }




    public class AnswerButtonOnClickListener implements View.OnClickListener{


        @Override
        public void onClick(View view) {

            int buttonID = view.getId();
            int answerClickedIndex = 100;

            switch (buttonID){
                case R.id.question_fragment_answer_a: answerClickedIndex=0;
                    break;
                case R.id.question_fragment_answer_b: answerClickedIndex=1;
                    break;
                case R.id.question_fragment_answer_c: answerClickedIndex=2;
                    break;
                case R.id.question_fragment_answer_d: answerClickedIndex=3;

            }

            displayMessage(answerClickedIndex);

        }
    }

    public void displayMessage(int answerClickedIndex){

        GameActivity gameActivity = (GameActivity)getActivity();
        if(answerClickedIndex==question.getCorrectAnswerPointer()){
            gameActivity.onAnswerSelected(CORRECT_ANSWER_TAG,question);

        }

        else{
            gameActivity.onAnswerSelected(NOT_CORRECT_ANSWER_TAG,question);
        }
    }

    public void useCallLifebelt() {

        Button[]buttonArr =  {answerAButton,answerBButton,answerCButton,answerDButton};

        Random r = new Random();

        int can;

        can = r.nextInt(2);

        int questionPointer=-1;

        if(can==0){
            for(int i=0;i<answerArr.length;i++){
                if(question.getCorrectAnswerPointer()!=i&&buttonArr[i].getVisibility()!=View.GONE) {
                    questionPointer=i;
                    break;
                }
                }
            }

        else{
           questionPointer=question.getCorrectAnswerPointer();

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_LIGHT);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(
              //  question.getQuestionText()+" "+
                        answerArr[questionPointer])
                .setTitle(getResources().getString(R.string.i_think_that));


// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();

    }

    public void useAskLifeBelt() {

        String result="";

        String[] statsArr = new String[4];

        Button[]buttonArr =  {answerAButton,answerBButton,answerCButton,answerDButton};



        for(int i=0;i<statsArr.length;i++){
            statsArr[i]=answerArr[i]+": ";
            String singleStat="";
            if(i==question.getCorrectAnswerPointer()){
                if(fiftyUsed==false)singleStat="40%";
                else singleStat="70%";

            }

            else
            {
                if(fiftyUsed==false){
                    singleStat="20%";
                }
                else{
                    if(buttonArr[i].getVisibility()==View.GONE) singleStat="0%";
                    else singleStat="30%";

                }


            }

            statsArr[i]+=singleStat;
        }

        for(String line: statsArr){
            result+=line+"\n";

        }

        // 1. Instantiate an AlertDialog.Builder with its constructor
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),AlertDialog.THEME_HOLO_LIGHT);
// 2. Chain together various setter methods to set the dialog characteristics
        builder.setMessage(result)
                .setTitle(getResources().getString(R.string.audience_results));


// 3. Get the AlertDialog from create()
        AlertDialog dialog = builder.create();

        dialog.show();
    }

    public void useFiftyLifebelt() {

        //int pointer = question.getCorrectAnswerPointer();
        fiftyUsed=true;
        Button[]arr =  {answerAButton,answerBButton,answerCButton,answerDButton};
        String correctAnswer =answerArr[question.getCorrectAnswerPointer()];
        int unvisibledCounter =0;
        for(int i=0;i<arr.length;i++){
            if((!arr[i].getText().equals(correctAnswer))&&unvisibledCounter<=1){
                arr[i].setVisibility(View.GONE);

                unvisibledCounter++;
            }
        }

    }
}
