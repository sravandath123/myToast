package com.t.mylearningprojects.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import com.t.mylearningprojects.R;

public class ParentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public void sendMessage(View view) {
        Intent intent = null;
        if (this instanceof TestAActivity){
            intent=new Intent(this,TestBActivity.class);
            Log.e("ACTIVITY",TestBActivity.class.getSimpleName());
        }
        if (this instanceof TestBActivity){
            intent=new Intent(this,TestCActivity.class);
            Log.e("ACTIVITY",TestCActivity.class.getSimpleName());
        }
        if (this instanceof TestCActivity){
            intent=new Intent(this,TestBActivity.class);
            Log.e("ACTIVITY",TestBActivity.class.getSimpleName());
        }
        startActivity(intent);
    }
}
