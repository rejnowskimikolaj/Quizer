package com.example.user.quizer;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.text.format.Time;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import utils.Question;
import utils.SavingUtil;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFinishedFragment extends Fragment {

    TextView correctAnswerTextView;
    private int questionNumber;
    LinearLayout layout;
    Button goToMenuButton;
    public static final String GAME_WON_TAG = "won";
    public static final String GAME_LOST_TAG = "lost";
    private Button saveScoreButton;



    public GameFinishedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_game_finished, container, false);
       correctAnswerTextView = (TextView) view.findViewById(R.id.game_finished_correct_answer) ;

        layout = (LinearLayout) view.findViewById(R.id.fragmet_game_finished_frame_layout);


        Bundle bundle = this.getArguments();

        this.questionNumber = bundle.getInt(GameActivity.QUESTION_NUMBER);


        goToMenuButton = (Button) view.findViewById(R.id.game_finished_go_to_menu_button);

        goToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MenuActivity.class);
                startActivity(intent);
            }
        });

        if (bundle != null) {


            if(bundle.getString(QuestionFragment.RESULT_TAG).equals(GAME_WON_TAG)){

            }
           else{


                Question question= bundle.getParcelable(GameActivity.QUESTION_TAG);
                String correctAnswer = question.getQuestionText()+" ";
                String[] answersArr = {question.getAnswerA(),question.getAnswerB(),question.getAnswerC(),question.getAnswerD()};
                correctAnswer+=answersArr[question.getCorrectAnswerPointer()];

                correctAnswerTextView.setText(correctAnswer);


            }
        }

        saveScoreButton = (Button) view.findViewById(R.id.game_finished_button);

        saveScoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alert = new AlertDialog.Builder(getContext(),R.style.MyDialogTheme);


                // Set an EditText view to get user input
                final EditText input = new EditText(getContext());
                input.setHeight(600);
                input.setBackground(getActivity().getApplicationContext().getDrawable(R.drawable.edit_text_blue_gradient));
                input.setTextColor(getActivity().getApplicationContext().getColor(R.color.answerButtonTextColor));
                input.setGravity(Gravity.CENTER_HORIZONTAL);
                input.setHint(getResources().getString(R.string.name));
                input.setHintTextColor(getActivity().getApplicationContext().getColor(R.color.answerButtonTextColor));
                alert.setView(input);


                alert.setPositiveButton(getResources().getString(R.string.saveShort),new DialogOnClickListener(R.id.game_finished_button,questionNumber, input));

                alert.show().getWindow().setLayout(600,500);
            }
        });

        return view;
    }

    public class DialogOnClickListener implements DialogInterface.OnClickListener {

        private int buttonId;
        private int questionNumber;
        private TextView input;
        public DialogOnClickListener(int buttonId, int questionNumber, TextView input){
            this.buttonId=buttonId;
            this.questionNumber=questionNumber;
            this.input = input;
        }
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            Button saveButton = (Button) (getActivity().findViewById(buttonId));
            saveButton.setVisibility(View.INVISIBLE);
            SavingUtil.saveScore(input.getText().toString(),getTodayDateString(),questionNumber,getContext());


        }
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
}
