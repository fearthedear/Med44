package xyz.linuskinzel.med44;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by linus on 26/10/2016.
 */

public class databaseActions {
    med44dbHelper OpenHelper;
    SQLiteDatabase db;
    final Context context;

    public databaseActions(Context ctx) {
        this.context = ctx;
        OpenHelper = new med44dbHelper(this.context);
    }

    public databaseActions open() {
        db = OpenHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        OpenHelper.close();
    }

    //examples that can be put here

//    public void insertUsername(String name) {
//        //Toast.makeText(context, "chill", Toast.LENGTH_LONG).show();
//        // Create a new map of values, where column names are the keys
//        ContentValues values = new ContentValues();
//        values.put(clubtalkContract.userinfo.COLUMN_NAME_USERNAME, name);
//        open();
//        // Insert the new row, returning the primary key value of the new row
//        long newRowId = db.insert(clubtalkContract.userinfo.TABLE_NAME, null, values);
//
//        close();
//    }

//    public long insertRecord(String name2_str, String name3_str) {
//        ContentValues initialValues = new ContentValues();
//        initialValues.put(MyDBHelper.columnName2, name2_str);
//        initialValues.put(MyDBHelper.columnName3, name3_str);
//        return db.insert(MyDBHelper.tableName, null, initialValues);
//    }
//
//    public int deleteRecord(Long columnNameToBeDeleted) {
//        return db.delete(MyDBHelper.tableName, MyDBHelper.columnName1 + "=" + columnNameToBeDeleted, null);
//    }
//
//    public int updateRecord(Long idOfRecord, String name2_str, String name3_str) {
//        ContentValues initialValues = new ContentValues();
//        initialValues.put(MyDBHelper.columnName2, name2_str);
//        initialValues.put(MyDBHelper.columnName3, name3_str);
//
//        return db.update(MyDBHelper.tableName, initialValues, MyDBHelper.columnName1 + "=" + idOfRecord, null);
//
//    }
//
//    public Cursor getAllRecords() {
//        return db.query(
//                MyDBHelper.tableName,
//                new String[] {
//                        MyDBHelper.columnName1, MyDBHelper.columnName2, MyDBHelper.columnName3
//                },
//                null, null, null, null, null);
//    }


//    public Cursor getRecord(String column) {
//        Cursor mCursor = db.query(clubtalkContract.userinfo.TABLE_NAME,
//                new String[] { clubtalkContract.userinfo.COLUMN_NAME_USERNAME },
//                clubtalkContract.userinfo.COLUMN_NAME_USERNAME + "=" + column, null, null, null, null, null);
//        if (mCursor != null) {
//            mCursor.moveToFirst(); }
//        return mCursor;
//    }
}
