package utils;

import android.content.Context;

import com.example.user.quizer.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 2017-01-13.
 */

public class QuestionLoader {

    private int resourceID;
    private Context context;
    private List<Question> questionList;

    public QuestionLoader(int resourceID, Context context){
        this.resourceID = resourceID;
        this.context = context;

    }

    public void loadQuestions(){

        this.questionList = new ArrayList<>();

        String line="";
        InputStream is= context.getResources().openRawResource(resourceID);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        try {
            while((line = br.readLine())!=null){


                questionList.add(getQuestionFromString(line));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Question getQuestionFromString(String textQuestion){
        String[] arr = textQuestion.split(";");

       return new Question(arr);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }
}
