package com.example.user.quizer;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LifeBeltFragment extends Fragment {

    Button callButton;
    Button askButton;
    Button fiftyButton;

    public LifeBeltFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_life_belt, container, false);

        this.callButton = (Button) view.findViewById(R.id.life_belt_call_button);
        this.askButton = (Button) view.findViewById(R.id.life_belt_askAudience_button);
        this.fiftyButton = (Button) view.findViewById(R.id.life_belt_fifty_button);

        setOnClickListeners();

        return view;
    }


    public void setOnClickListeners(){

        LifeBeltOnClickListener listener = new LifeBeltOnClickListener();
        this.callButton.setOnClickListener(listener);
        this.askButton.setOnClickListener(listener);
        this.fiftyButton.setOnClickListener(listener);
    }
    public class LifeBeltOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.life_belt_call_button: useCallButton();
                    break;
                case R.id.life_belt_askAudience_button: useAskButton();
                    break;
                case R.id.life_belt_fifty_button: useFiftyButton();
            }
        }
    }

    public void useCallButton(){
        GameActivity gameActivity = (GameActivity)getActivity();
        this.callButton.setEnabled(false);
        this.callButton.setText("");
        //this.callButton.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.invisibleButtonColor));

        gameActivity.useCallLifebelt();
    }

    public void useAskButton(){
        GameActivity gameActivity = (GameActivity)getActivity();
        this.askButton.setEnabled(false);
        this.askButton.setText("");
        //this.askButton.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.invisibleButtonColor));

        gameActivity.useAskLifebelt();
    }

    public void useFiftyButton(){
        GameActivity gameActivity = (GameActivity)getActivity();
        this.fiftyButton.setEnabled(false);
        this.fiftyButton.setText("");
        //this.fiftyButton.setBackgroundColor(ContextCompat.getColor(getContext(),R.color.invisibleButtonColor));

        gameActivity.useFiftyLifebelt();
    }


}
