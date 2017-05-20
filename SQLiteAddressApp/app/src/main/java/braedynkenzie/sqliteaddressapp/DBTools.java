package braedynkenzie.sqliteaddressapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Braedyn Kenzie on 2017-05-19.
 *
 */

public class DBTools extends SQLiteOpenHelper {

    // Contructor; creates an SQLite database called "contactBook.db"
    public DBTools(Context appContext){
        super(appContext, "contactBook.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query = "CREATE TABLE contacts( contactID INTEGER PRIMARY KEY, firstName TEXT, " +
                "lastName TEXT, phoneNumber TEXT, emailAddress TEXT, homeAddress TEXT)";

        database.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS contacts";
        db.execSQL(query);
        onCreate(db);
    }

    public void insertContact(HashMap<String, String> queryValues){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("firstName",queryValues.get("firstName"));
        values.put("lastName",queryValues.get("lastName"));
        values.put("phoneNumber",queryValues.get("phoneNumber"));
        values.put("emailAddress",queryValues.get("emailAddress"));
        values.put("homeAddress",queryValues.get("homeAddress"));

        database.insert("contacts", null, values);

        database.close();

    }

    public int updateContact(HashMap<String, String> queryValues){

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("firstName",queryValues.get("firstName"));
        values.put("lastName",queryValues.get("lastName"));
        values.put("phoneNumber",queryValues.get("phoneNumber"));
        values.put("emailAddress",queryValues.get("emailAddress"));
        values.put("homeAddress",queryValues.get("homeAddress"));

        return database.update("contacts", values,
                "contactID" + " = ?", new String[] {queryValues.get("contactID")});

    }

    public void deleteContact(String contactID){
        SQLiteDatabase database = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM contacts WHERE contactID = '" + contactID + "'";
        database.execSQL(deleteQuery);
    }

    public ArrayList<HashMap<String, String>> getAllContacts(){
        ArrayList<HashMap<String, String>> allContactsArrayList = new ArrayList<HashMap<String, String>>();
        SQLiteDatabase database = this.getWritableDatabase();
        String selectAllQuery = "SELECT * FROM contacts ORDER BY lastName";

        Cursor cursor = database.rawQuery(selectAllQuery, null);

        if(cursor.moveToFirst()){

            do{

                HashMap<String, String> singleContactHashMap = new HashMap<String, String>();
                singleContactHashMap.put("contactID", cursor.getString(0));
                singleContactHashMap.put("firstName", cursor.getString(1));
                singleContactHashMap.put("lastName", cursor.getString(2));
                singleContactHashMap.put("phoneNumber", cursor.getString(3));
                singleContactHashMap.put("emailAddress", cursor.getString(4));
                singleContactHashMap.put("homeAddress", cursor.getString(5));

                allContactsArrayList.add(singleContactHashMap);

            } while (cursor.moveToNext());

        }

        cursor.close();
        return allContactsArrayList;

    }

    public HashMap<String, String> getContactInfo(String contactID){
        HashMap<String, String> contactHashMap = new HashMap<String,String>();
        SQLiteDatabase database = this.getReadableDatabase();
        String query = "SELECT * FROM contacts WHERE contactID = '" + contactID + "'";

        Cursor cursor = database.rawQuery(query, null);
        if(cursor.moveToFirst()){
            contactHashMap.put("contactID", cursor.getString(0));
            contactHashMap.put("firstName", cursor.getString(1));
            contactHashMap.put("lastName", cursor.getString(2));
            contactHashMap.put("phoneNumber", cursor.getString(3));
            contactHashMap.put("emailAddress", cursor.getString(4));
            contactHashMap.put("homeAddress", cursor.getString(5));
        }
        return contactHashMap;
    }
}
