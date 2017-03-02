package utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by User on 2017-01-18.
 */

public class ReadingUtil {

    private static final String scoreFileName = "scores_file";

    public static  String readScore(Context context){
        String line ="";
        try {
            FileInputStream fileInputStream = context.openFileInput(scoreFileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();


            while((line=bufferedReader.readLine())!=null){
                stringBuffer.append(line+"\n");
            }
            Log.d("READ_SCORE", stringBuffer.toString());

            return stringBuffer.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("READ_SCORE", "readScore: no scores!");
        return "";
    }
}
