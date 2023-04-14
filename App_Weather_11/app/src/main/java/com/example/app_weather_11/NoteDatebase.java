package com.example.app_weather_11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class NoteDatebase extends SQLiteOpenHelper {

    public static final int DB_VERSION = 2;
    public static String DB_NAME = "NotesDB.db";
    public static String DB_TABLE = "NotesTAble";

    public static String COLUMN_ID = "NotesId";
    public static String COLUMN_TITLE = "NotesTitles";
    public static String COLUMN_DETAILS = "NotesDetails";
    public static String COLUMN_DATE = "NotesData";
    public static String COLUMN_TIME = "NotesTime";

    public NoteDatebase(@Nullable Context context) {super(context, DB_NAME, null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " +  DB_TABLE + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT," +
                COLUMN_DETAILS + " TEXT, " +
                COLUMN_DATE + " TEXT," +
                COLUMN_TIME + " TEXT" + ")";

        sqLiteDatabase.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i >= i1) return;
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(sqLiteDatabase);
    }
    public long AddNote(NoteModel noteModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TITLE, noteModel.getNoteTitle());
        contentValues.put(COLUMN_DETAILS, noteModel.getNoteDetails());
        contentValues.put(COLUMN_DATE, noteModel.getNoteDate());
        contentValues.put(COLUMN_TIME, noteModel.getNoteTime());

        long ID = db.insert(DB_TABLE, null, contentValues);
        Log.d("Inserted", "id ->"+ ID );
        return ID;
    }
    public List<NoteModel> getNote(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<NoteModel> allNote = new ArrayList<>();

        String queryStatement = "SELECT * FROM " + DB_TABLE;
        Cursor cursor = db.rawQuery(queryStatement, null);

        if (cursor.moveToFirst()){
            do {
                NoteModel noteModel = new NoteModel();
                noteModel.setId(cursor.getInt(0));
                noteModel.setNoteTitle(cursor.getString(1));
                noteModel.setNoteDetails(cursor.getString(2));
                noteModel.setNoteDate(cursor.getString(3));
                noteModel.setNoteTime(cursor.getString(4));

                allNote.add(noteModel);
            }while (cursor.moveToNext());
        }
        return allNote;
    }
    public NoteModel getNotes(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        String[] query = new String[] {COLUMN_ID,COLUMN_TITLE,COLUMN_DETAILS,COLUMN_DATE,COLUMN_TIME};
        Cursor cursor = db.query(DB_TABLE,query,COLUMN_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();

        return  new NoteModel(
            Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4));
    }
    void  deleteNote(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(DB_TABLE,COLUMN_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
}
