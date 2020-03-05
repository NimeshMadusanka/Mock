package com.example.prabuddhaabisheka.mock;

import android.provider.BaseColumns;

public final class UserProfile {

    private UserProfile() {    }

    public static class Users implements BaseColumns {

        public final static String TABLE_NAME = "userInfo";
        public final static String COLUMN_USERNAME = "userName";
        public final static String COLUMN_PASSWORD = "password";
        public final static String COLUMN_GENDER = "gender";
        public final static String COLUMN_DOB = "dateOfBirth";
    }
}
