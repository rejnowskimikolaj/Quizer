package utils;

import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by User on 2017-01-18.
 */

public class SavingUtil {

    private final static String scoreFileName = "scores_file";

    public static void saveScore(String userName, String date, int level, Context context){
        String line = userName+";"+date+";"+level+"\n";
        try {
            FileOutputStream fileOutputStream =context.openFileOutput(scoreFileName,context.MODE_APPEND);
            fileOutputStream.write(line.getBytes());
            fileOutputStream.close();
            Log.d("SAVING_UTIL", "score saved: "+line);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void cleanScore(Context context){
        String line = "";
        try {
            FileOutputStream fileOutputStream =context.openFileOutput(scoreFileName,context.MODE_PRIVATE);
            fileOutputStream.write(line.getBytes());
            fileOutputStream.close();
            Log.d("SAVING_UTIL", "score cleared");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
