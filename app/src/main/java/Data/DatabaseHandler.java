package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.vmessenger.vaibhav.vencryption.decryptionActivity;

import java.util.ArrayList;
import java.util.List;

import Model.encdata;
import Utils.Util;

public class DatabaseHandler extends SQLiteOpenHelper {

    private String CREATE_TABLE = "create table " + Util.TABLE_NAME + "(" + Util.KEY_DATA + " varchar," + Util.KEY_ENCRYPTED_DATA + " varchar primary key);";

   // private String CREATE_ENCRYPT_TABLE="CREATE TABLE "+ Util.TABLE_NAME+"("+Util.KEY_DATA +" STRING PRIMARY KEY, "
    //        +Util.KEY_ENCRYPTED_DATA+" VARCHAR"+")";

    public DatabaseHandler(Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("RANDOM", "DB CREATION :" + CREATE_TABLE);
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Util.TABLE_NAME);
        onCreate(sqLiteDatabase);

    }


    public void addData(encdata ed){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues value=new ContentValues();
        value.put(Util.KEY_DATA,ed.getPlaintext());
        value.put(Util.KEY_ENCRYPTED_DATA,ed.getEncryptedtext());

        //insert to row
        db.insert(Util.TABLE_NAME,null,value);
        db.close();
    }


    public List<encdata> getData() {
        List<encdata> encList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectAll="SELECT * FROM " + Util.TABLE_NAME;
        try {
            Cursor cursor=db.rawQuery(selectAll,null);
            cursor.moveToFirst();
            do {
                encdata enc = new encdata();
                enc.setEncryptedtext(cursor.getString(cursor.getColumnIndex(Util.KEY_ENCRYPTED_DATA)));
                enc.setPlaintext(cursor.getString(cursor.getColumnIndex(Util.KEY_DATA)));
                encList.add(enc);
            } while (cursor.moveToNext());
            cursor.close();
        } catch (SQLException e) {
            Log.e(this.getClass().getSimpleName(), "Error: " + e);
        }
        db.close();
        return encList;
    }



}

