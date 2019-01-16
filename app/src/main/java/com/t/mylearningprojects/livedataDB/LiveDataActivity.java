package com.t.mylearningprojects.livedataDB;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import com.t.mylearningprojects.R;

import java.util.List;

public class LiveDataActivity   extends AppCompatActivity {
    private StudentViewModel mStudentViewModel;
    private List<Student> mStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livedata);

        final TextView textView=findViewById(R.id.tv);

        mStudentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);

        final Observer<List<Student>> studentObserver=new Observer<List<Student>>() {
            @Override
            public void onChanged(@Nullable List<Student> students) {
                 String s="";
                 for (Student student:students)
                   s=s+student.mName+"  "+student.mSex+"  "+student.mAge+" \n";
                 textView.setText(s);
            }
        };
        mStudentViewModel.getStudent().observe(this, studentObserver);


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             mStudentViewModel.addStudent("Sravan","male",24);
            //    add(LiveDataActivity.this,LiveDataActivity.class);
            }
        });

    }
    public void add(Context context,Activity o){
        if (o instanceof LiveDataActivity){
            startActivity(new Intent(context,LiveDataActivity.class));
        }
    }
}