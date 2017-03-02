package com.example.user.quizer;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameInfoFragment extends Fragment {

    TextView gameInfoPointsTextView;
    TextView levelTextview;
    TextView cashTextView;

    public GameInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_game_info, container, false);
        gameInfoPointsTextView = (TextView) view.findViewById(R.id.game_info_points_textview);
      //  levelTextview = (TextView) view.findViewById(R.id.fragment_game_LEVEL_textview);
        cashTextView=(TextView) view.findViewById(R.id.game_info_cash_text_view);

        return view;
    }


    public void displayQuestionNumber(int number){

        gameInfoPointsTextView.setText((number+1)+"");
        cashTextView.setText(getCashAmout(number-1)+"");

    }

    public void endGame(){

       // levelTextview.setText(R.string.your_max_level);
    }

    public int getCashAmout(int level){

        int result=0;
        switch (level){
            case 0:result=100;
                break;
            case 1:result=200;
                break;
            case 2:result=300;
                break;
            case 3:result=500;
                break;
            case 4:result=1000;
                break;
            case 5:result=2000;
                break;
            case 6:result=4000;
                break;
            case 7:result=8000;
                break;
            case 8:result=16000;
                break;
            case 9:result=32000;
                break;
            case 10:result=64000;
                break;
            case 11:result=125000;
                break;
            case 12:result=250000;
                break;
            case 13:result=500000;
                break;
            case 14:result=1000000;
                break;

        }

        return result;
    }
}
