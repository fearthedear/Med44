package xyz.linuskinzel.med44;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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


    //PRESCRIPTION TABLE RELETED FUNCTIONS
    public Cursor getAllPrescriptionRecords() {
        return db.query(
                med44Contract.prescriptions.TABLE_NAME,
                new String[] {
                        med44Contract.prescriptions.COLUMN_NAME_DRUG, med44Contract.prescriptions.COLUMN_NAME_HOW_MANY_DAILY,
                        med44Contract.prescriptions.COLUMN_NAME_TAKE_FOR_HOW_LONG, med44Contract.prescriptions._ID
                },
                null, null, null, null, null);
    }


    public Cursor getPrescriptionRecord(String column) {
        Cursor mCursor = db.query(med44Contract.prescriptions.TABLE_NAME,
                new String[] { med44Contract.prescriptions.COLUMN_NAME_DRUG },
                med44Contract.prescriptions.COLUMN_NAME_DRUG + "=" + column, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst(); }
        return mCursor;
    }

    public void insertPrescription(String drug, int perday, int fordays) {
        ContentValues values = new ContentValues();
        values.put(med44Contract.prescriptions.COLUMN_NAME_DRUG, drug);
        values.put(med44Contract.prescriptions.COLUMN_NAME_HOW_MANY_DAILY, perday);
        values.put(med44Contract.prescriptions.COLUMN_NAME_TAKE_FOR_HOW_LONG, fordays);

        open();
        long newRowID =  db.insert(med44Contract.prescriptions.TABLE_NAME, null, values);
        close();
    }

    public int deletePrescription(String id1) {
        return db.delete(med44Contract.prescriptions.TABLE_NAME, med44Contract.prescriptions._ID + "=" + id1, null);
    }


    //CONDTIONS RELATED DATABASE ACTIONS
    public Cursor getAllConditionRecords() {
        return db.query(
                med44Contract.med_conditions.TABLE_NAME,
                new String[] {
                        med44Contract.med_conditions.COLUMN_NAME_NAME, med44Contract.med_conditions.COLUMN_NAME_DATE_DIAGNOSED,
                        med44Contract.med_conditions._ID
                },
                null, null, null, null, null);
    }

    public void insertCondition(String condition, String date) {
        ContentValues values = new ContentValues();
        values.put(med44Contract.med_conditions.COLUMN_NAME_NAME, condition);
        values.put(med44Contract.med_conditions.COLUMN_NAME_DATE_DIAGNOSED, date);

        open();
        long newRowID = db.insert(med44Contract.med_conditions.TABLE_NAME, null, values);
    }
    public int deleteCondition(String id1) {
        return db.delete(med44Contract.med_conditions.TABLE_NAME, med44Contract.med_conditions._ID + "=" + id1, null);
    }


    //VISITS RELATED DATABASE ACTIONS
    public Cursor getAllVisits() {
        return db.query(
                med44Contract.doc_visits.TABLE_NAME,
                new String[]{
                        med44Contract.doc_visits.COLUMN_NAME_DATE, med44Contract.doc_visits.COLUMN_NAME_DIAGNOSED,
                        med44Contract.doc_visits.COLUMN_NAME_IMAGE, med44Contract.doc_visits._ID
                },
                null, null, null, null, null);
    }

    public void insertVisit(String date, String diagnosed, String image) {
        ContentValues values = new ContentValues();
        values.put(med44Contract.doc_visits.COLUMN_NAME_DATE, date);
        values.put(med44Contract.doc_visits.COLUMN_NAME_DIAGNOSED, diagnosed);
        values.put(med44Contract.doc_visits.COLUMN_NAME_IMAGE, image);

        open();
        long newRowID = db.insert(med44Contract.doc_visits.TABLE_NAME, null, values);
    }
    public int deleteVisit(String id1) {
        return db.delete(med44Contract.doc_visits.TABLE_NAME, med44Contract.doc_visits._ID + "=" + id1, null);
    }

    //VACCINES RELATED DATABASE ACTIONS
    public Cursor getVaccinesByCountry(String country) {
        String[] args = { country };
        return db.query(
                med44Contract.vaccines.TABLE_NAME,
                new String[]{
                        med44Contract.vaccines.COLUMN_NAME_VACCINES_REQUIRED,
                },
                "country=?", args, null, null, null);
        }
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
