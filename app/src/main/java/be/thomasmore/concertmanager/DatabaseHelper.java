package be.thomasmore.concertmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context)
    {
        super(context,"login.db" ,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create table user(email text primary key, password text)");


        String CREATE_TABLE_Concert = "CREATE TABLE concert (" +
                "id TEXT PRIMARY KEY," +
                "naam TEXT," +
                "url TEXT," +
                "image TEXT," +
                "datum TEXT," +
                "genres TEXT";
        db.execSQL(CREATE_TABLE_Concert);

        insertConcerten(db);

    }

    private void insertConcerten(SQLiteDatabase db) {JSONObject json = new JSONObject();



        db.execSQL("INSERT INTO concert (id, naam,url,image,datum,genres) VALUES ('1', 'Rock werchter 2019','https://www.rockwerchter.be/nl/','https://assets.rockwerchter.be/files/cache/history_detail/files/rw19-v2-5d306b4f52a6a.jpg','2019-06-27','ROCK, POP');");

    }

    // rawQuery-methode
    public List<Concert> getConcerten() {
        List<Concert> lijstConcerten = new ArrayList<Concert>();

        String selectQuery = "SELECT  * FROM concert ORDER BY naam";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

       /* if (cursor.moveToFirst()) {
            do {
                Concert concert = new Concert(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5));
                lijstConcerten.add(concert);
            } while (cursor.moveToNext());
        }
        
        */

        cursor.close();
        db.close();
        return lijstConcerten;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion)
    {
        db.execSQL("drop table if exists user");
    }

    public boolean insert(String email,String password)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        long ins =db.insert("user",null,contentValues);
        if (ins==1) return false;
        else return true;
    }
    public Boolean CheckEmail(String email)
    {
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from user where email=?",new String[]{email});
        if(cursor.getCount()>0)return false;
        else return true;

    }

    public Boolean emailPasswoord(String email,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where email=? and password=?", new String[]{email,password});
        if (cursor.getCount()>0) return true;
        else return false;

    }

}
