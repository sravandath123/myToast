package com.t.mylearningprojects.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.t.mylearningprojects.R;

public class TestBActivity extends ParentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_a);
    }
}
