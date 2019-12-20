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

    // Database Version
    private static final int DATABASE_VERSION = 3;
    // Database Name
    private static final String DATABASE_NAME = "concert.db";


    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME ,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create table user(email text primary key, password text)");


        String CREATE_TABLE_CONCERT = "CREATE TABLE concert (" +
                "id TEXT PRIMARY KEY," +
                "naam TEXT," +
                "url TEXT," +
                "image TEXT," +
                "datum TEXT," +
                "genres TEXT)";
        db.execSQL(CREATE_TABLE_CONCERT);

        insertConcerten(db);

    }

    private void insertConcerten(SQLiteDatabase db)
    {
        db.execSQL("INSERT INTO concert (id, naam,url,image,datum,genres) VALUES ('1', 'Rock werchter 2019','https://www.rockwerchter.be/nl/','https://assets.rockwerchter.be/files/cache/history_detail/files/rw19-v2-5d306b4f52a6a.jpg','2019-06-27','ROCK, POP');");

    }

    // rawQuery-methode
    public List<Concert> getConcerten() {
        List<Concert> lijstConcerten = new ArrayList<Concert>();

        String selectQuery = "SELECT  * FROM concert ORDER BY naam";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Concert concert = new Concert(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3),cursor.getString(4),cursor.getString(5));
                lijstConcerten.add(concert);
            } while (cursor.moveToNext());
        }



        cursor.close();
        db.close();
        return lijstConcerten;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS user");
        db.execSQL("DROP TABLE IF EXISTS concert");

        // Create tables again
        onCreate(db);
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

    public void insertFavorite(Concert concert){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id",concert.getId());
        contentValues.put("naam",concert.getNaam());
        contentValues.put("url",concert.getUrl());
        contentValues.put("image",concert.getImage());
        contentValues.put("datum",concert.getDate());
        contentValues.put("genres",concert.getGenres());
        db.insert("concert",null,contentValues);
    }

    public boolean checkConcert(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        boolean output = false;

        Cursor cursor = db.query(
                "concert",      // tabelnaam
                new String[] { "id", "naam", "url", "image", "datum", "genres" }, // kolommen
                "id = ?",  // selectie
                new String[] { String.valueOf(id) }, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);          // ??

        Concert concert = new Concert();

        if (cursor.moveToFirst()) {
            output = true;
        }
        cursor.close();
        db.close();

        return output;
    }

    public Concert getConcert(String id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                "concert",      // tabelnaam
                new String[] { "id", "naam", "url", "image", "datum", "genres" }, // kolommen
                "id = ?",  // selectie
                new String[] { String.valueOf(id) }, // selectieparameters
                null,           // groupby
                null,           // having
                null,           // sorting
                null);          // ??

        Concert concert = new Concert();

        if (cursor.moveToFirst()) {
            concert = new Concert(cursor.getString(0),
                    cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        }
        cursor.close();
        db.close();
        return concert;
    }

    public List<Concert> getReviewableConcerts(String nu) {
        List<Concert> lijst = new ArrayList<>();

        String selectQuery = "SELECT * FROM concert WHERE datum < '"+nu+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        Concert concert = new Concert();

        if (cursor.moveToFirst()) {
            do {
                concert = new Concert(cursor.getString(0),
                        cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
                lijst.add(concert);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return lijst;
    }

    public boolean updateConcert(Concert concert) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("score", concert.getScore());


        int numrows = db.update(
                "concert",
                values,
                "id = ?",
                new String[] { String.valueOf(concert.getId()) });

        db.close();
        return numrows > 0;
    }


}
