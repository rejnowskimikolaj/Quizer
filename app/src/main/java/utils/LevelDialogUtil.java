package utils;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.example.user.quizer.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by RENT on 2017-01-17.
 */

public class LevelDialogUtil {

    private List<String> levelList;

    public LevelDialogUtil(){

        levelList = new ArrayList<>();
        int [] arr = {100,200,300,500,1000,2000,4000,8000,16000,32000,64000,125000,500000,1000000};

        for(int i:arr){
            levelList.add(i+"");
        }

        Log.d("DIALOGUTIL", "LevelDialogUtil: "+levelList);
    }

    public ArrayAdapter getAdapter(Context context){

        return new ArrayAdapter(context, R.layout.level_dialog_item,levelList);

    }


}
