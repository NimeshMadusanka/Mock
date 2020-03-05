package com.example.prabuddhaabisheka.mock;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "UserInfo.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE =
                "CREATE TABLE " + UserProfile.Users.TABLE_NAME + " (" +
                        UserProfile.Users._ID + " INTEGER PRIMARY KEY," +
                        UserProfile.Users.COLUMN_USERNAME + " TEXT," +
                        UserProfile.Users.COLUMN_DOB + " TEXT," +
                        UserProfile.Users.COLUMN_GENDER + " TEXT," +
                        UserProfile.Users.COLUMN_PASSWORD + " TEXT )";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addInfo(String username, String password){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(UserProfile.Users.COLUMN_USERNAME, username);
        contentValues.put(UserProfile.Users.COLUMN_PASSWORD, password);

        long rowId = sqLiteDatabase.insert(UserProfile.Users.TABLE_NAME, null, contentValues);

        return rowId;
    }

    public int updateInfo(String userId, String userName, String password, String dob, String gender){

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserProfile.Users.COLUMN_USERNAME, userName);
        values.put(UserProfile.Users.COLUMN_PASSWORD, password);
        values.put(UserProfile.Users.COLUMN_GENDER, gender);
        values.put(UserProfile.Users.COLUMN_DOB, dob);

        String selection = UserProfile.Users._ID + " = ?";
        String args[] = {userId};

        int count = sqLiteDatabase.update(UserProfile.Users.TABLE_NAME, values, selection, args);

        return count;
    }

    public ArrayList readAllInfo(){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] projection = {

                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_USERNAME,
                UserProfile.Users.COLUMN_DOB,
                UserProfile.Users.COLUMN_GENDER,
                UserProfile.Users.COLUMN_PASSWORD
        };

        String sortOrder = UserProfile.Users._ID + " DESC";

        Cursor cursor = sqLiteDatabase.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        ArrayList<User> list = new ArrayList<>();

        if (cursor.getCount() > 0){

            while(cursor.moveToNext()){

                User newUser = new User();

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(UserProfile.Users._ID));
                String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_USERNAME));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_DOB));
                String gen = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_GENDER));
                String pass = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_PASSWORD));

                newUser.setUserId(id+"");
                newUser.setUserName(user);
                newUser.setDateOfBirth(date);
                newUser.setGender(gen);
                newUser.setPassword(pass);

                list.add(newUser);
            }
        }

        return list;
    }

    public ArrayList readAllInfo(String userId, String userName){

        String selection;
        String[] args = {""};

        if(userId == null){

            selection = UserProfile.Users.COLUMN_USERNAME + " LIKE ?";
            args[0] = userName;
        }
        else
        {
            selection = UserProfile.Users._ID + " = ?";
            args[0] = userId;
        }

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String[] projection = {

                UserProfile.Users._ID,
                UserProfile.Users.COLUMN_USERNAME,
                UserProfile.Users.COLUMN_DOB,
                UserProfile.Users.COLUMN_GENDER,
                UserProfile.Users.COLUMN_PASSWORD
        };



        String sortOrder = UserProfile.Users._ID + " DESC";

        Cursor cursor = sqLiteDatabase.query(
                UserProfile.Users.TABLE_NAME,
                projection,
                selection,
                args,
                null,
                null,
                sortOrder
        );

        ArrayList<User> list = new ArrayList<>();

        if (cursor.getCount() > 0){

            while(cursor.moveToNext()){

                User newUser = new User();

                int id = cursor.getInt(cursor.getColumnIndexOrThrow(UserProfile.Users._ID));
                String user = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_USERNAME));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_DOB));
                String gen = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_GENDER));
                String pass = cursor.getString(cursor.getColumnIndexOrThrow(UserProfile.Users.COLUMN_PASSWORD));

                newUser.setUserId(id+"");
                newUser.setUserName(user);
                newUser.setDateOfBirth(date);
                newUser.setGender(gen);
                newUser.setPassword(pass);

                list.add(newUser);
            }
        }

        return list;
    }

    public int deleteInfo(String username){

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        String selection = UserProfile.Users._ID + " = ?";
        String[] args = {username};

        int deletedRows = sqLiteDatabase.delete(UserProfile.Users.TABLE_NAME, selection, args);

        return deletedRows;
    }
}
