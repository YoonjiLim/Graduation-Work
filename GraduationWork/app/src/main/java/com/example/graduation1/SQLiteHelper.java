package com.example.graduation1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {



    SQLiteHelper(Context context,
                 String name,
                 SQLiteDatabase.CursorFactory factory,
                 int version){
        super(context,name,factory,version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }


    public void insertBoard(byte[] image,String title, String content , String time){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO BOARD (image,title,content,time) VALUES(?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindBlob(1,image);
        statement.bindString(2,title);
        statement.bindString(3,content);
        statement.bindString(4,time);
        statement.executeInsert();
    }

    public void insertRew(String user, String content , String time, String boardID){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "INSERT INTO REW (user,content,time,boardID) VALUES(?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindString(1,user);
        statement.bindString(2,content);
        statement.bindString(3,time);
        statement.bindString(4,boardID);
        statement.executeInsert();
    }

    public void updateBoard(byte[] image,String title, String content , String time , String id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "UPDATE BOARD SET image =? , title =? , content = ? , time = ?  WHERE id=? ";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.bindBlob(1,image);
        statement.bindString(2,title);
        statement.bindString(3,content);
        statement.bindString(4,time);
        statement.bindString(5,id);

        statement.execute();
        database.close();
    }



    public void deleteBoard(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sql = "DELETE FROM BOARD WHERE id =?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1,id);
        statement.execute();
        statement.close();
    }


    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i,int il){

    }
}
