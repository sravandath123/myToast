package com.t.mylearningprojects.livedataDB;

public class Student {

    public long mId;
    public String mName;
    public String mSex;
    public int mAge;

    public Student(long id, String name, String sex, int age) {
        mId = id;
        mName = name;
        mSex = sex;
        mAge = age;
    }

    public Student(Student student) {
        mId = student.mId;
        mName = student.mName;
        mSex = student.mSex;
        mAge = student.mAge;
    }
}
