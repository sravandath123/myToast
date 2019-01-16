package com.t.mylearningprojects.livedataDB;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import com.t.mylearningprojects.livedataDB.db.DbSettings;
import com.t.mylearningprojects.livedataDB.db.StudentDBHelper;

import java.util.ArrayList;
import java.util.List;

public class StudentViewModel  extends AndroidViewModel {

    private StudentDBHelper mStudentDBHelper;
    private MutableLiveData<List<Student>> mStudent;
    public StudentViewModel(@NonNull Application application) {
        super(application);
        mStudentDBHelper = new StudentDBHelper(application);
    }

    public MutableLiveData<List<Student>> getStudent() {
        if (mStudent == null) {
            mStudent = new MutableLiveData<>();
            loadStud();
        }

        return mStudent;
    }
    private void loadStud() {
        List<Student> newStudent = new ArrayList<>();
        SQLiteDatabase db = mStudentDBHelper.getReadableDatabase();
        Cursor cursor = db.query(DbSettings.DBEntry.TABLE,
                new String[]{
                        DbSettings.DBEntry._ID,
                        DbSettings.DBEntry.COL_NAME,
                        DbSettings.DBEntry.COL_SEX,
                        DbSettings.DBEntry.COL_AGE
                },
                null, null, null, null, null);
        while (cursor.moveToNext()) {
            int idxId = cursor.getColumnIndex(DbSettings.DBEntry._ID);
            int idxName = cursor.getColumnIndex(DbSettings.DBEntry.COL_NAME);
            int idxSex = cursor.getColumnIndex(DbSettings.DBEntry.COL_SEX);
            int idxAge = cursor.getColumnIndex(DbSettings.DBEntry.COL_AGE);
            newStudent.add(new Student(cursor.getLong(idxId), cursor.getString(idxName),cursor.getString(idxSex),
                    cursor.getInt(idxAge)));
        }

        cursor.close();
        db.close();
        mStudent.setValue(newStudent);
    }
    public void addStudent(String name, String sex, int age) {

        SQLiteDatabase db = mStudentDBHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DbSettings.DBEntry.COL_NAME, name);
        values.put(DbSettings.DBEntry.COL_SEX, sex);
        values.put(DbSettings.DBEntry.COL_AGE, age);
        long id = db.insertWithOnConflict(DbSettings.DBEntry.TABLE,
                null,
                values,
                SQLiteDatabase.CONFLICT_REPLACE);
        db.close();


        List<Student> studentList = mStudent.getValue();

        ArrayList<Student> clonedSudent;
        if (studentList == null) {
            clonedSudent = new ArrayList<>();
        } else {
            clonedSudent = new ArrayList<>(studentList.size());
            for (int i = 0; i < studentList.size(); i++) {
                clonedSudent.add(new Student(studentList.get(i)));
            }
        }

        Student stu = new Student(id, name, sex,age);
        clonedSudent.add(stu);
        mStudent.setValue(clonedSudent);
    }

}
