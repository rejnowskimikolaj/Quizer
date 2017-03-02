package utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by User on 2017-01-13.
 */

public class Question implements Parcelable {





    private String questionText;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;
    private int correctAnswerPointer;
    private int difficulty;


    public Question(String questionText, String answerA, String answerB, String answerC, String answerD, int correctAnswerPointer, int difficulty) {
        this.questionText = questionText;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.correctAnswerPointer = correctAnswerPointer;
        this.difficulty = difficulty;
    }

    public Question(String[] arr){
        if(arr.length==7){
            this.questionText = arr[0];
            this.answerA=arr[1];
            this.answerB=arr[2];
            this.answerC=arr[3];
            this.answerD=arr[4];
            this.correctAnswerPointer=Integer.parseInt(arr[5]);
            this.difficulty = Integer.parseInt((arr[6]));
        }

        else throw  new IllegalArgumentException("Cannot make a Question:" +arr[0]);
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC;
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD;
    }

    public int getCorrectAnswerPointer() {
        return correctAnswerPointer;
    }

    public void setCorrectAnswerPointer(int correctAnswerPointer) {
        this.correctAnswerPointer = correctAnswerPointer;
    }



    protected Question(Parcel in) {
        questionText = in.readString();
        answerA = in.readString();
        answerB = in.readString();
        answerC = in.readString();
        answerD = in.readString();
        correctAnswerPointer = in.readInt();
        difficulty = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(questionText);
        dest.writeString(answerA);
        dest.writeString(answerB);
        dest.writeString(answerC);
        dest.writeString(answerD);
        dest.writeInt(correctAnswerPointer);
        dest.writeInt(difficulty);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };
}
