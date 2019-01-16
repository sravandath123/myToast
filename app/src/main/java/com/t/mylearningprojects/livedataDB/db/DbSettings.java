package com.t.mylearningprojects.livedataDB.db;

import android.provider.BaseColumns;

public class DbSettings {

    public static final String DB_NAME = "mylearningprojects.db";
    public static final int DB_VERSION = 1;

    public class DBEntry implements BaseColumns {

        public static final String TABLE = "student";
        public static final String COL_NAME = "name";
        public static final String COL_AGE = "age";
        public static final String COL_SEX = "sex";

    }
}
