package xyz.linuskinzel.med44;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by linus on 26/10/2016.
 */

public class med44dbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "clubTalks.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String TIMESTAMP = "DEFAULT CURRENT_TIMESTAMP NOT NULL";
    private static final String SQL_CREATE_VACCINES =
            //// TODO: 26/10/2016 Add correct field types
            "CREATE TABLE " + med44Contract.vaccines.TABLE_NAME + " (" +
                    med44Contract.vaccines._ID + " INTEGER PRIMARY KEY," +
                    med44Contract.vaccines.COLUMN_NAME_COUNTRY + TEXT_TYPE + COMMA_SEP +
                    med44Contract.vaccines.COLUMN_NAME_VACCINES_REQUIRED + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_PRESCRIPTIONS =
            "CREATE TABLE " + med44Contract.prescriptions.TABLE_NAME + " (" +
                    med44Contract.prescriptions._ID + " INTEGER PRIMARY KEY," +
                    med44Contract.prescriptions.COLUMN_NAME_DRUG + TEXT_TYPE + COMMA_SEP +
                    med44Contract.prescriptions.COLUMN_NAME_DRUG_INFO + TEXT_TYPE + COMMA_SEP +
                    med44Contract.prescriptions.COLUMN_NAME_DATE_PRESCRIBED + TEXT_TYPE + COMMA_SEP +
                    med44Contract.prescriptions.COLUMN_NAME_TAKE_FOR_HOW_LONG + TEXT_TYPE + COMMA_SEP +
                    med44Contract.prescriptions.COLUMN_NAME_HOW_MANY_DAILY + TEXT_TYPE + COMMA_SEP +
                    med44Contract.prescriptions.COLUMN_NAME_HOW_TAKE_WHEN + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_TRAVELS =
            "CREATE TABLE " + med44Contract.travels.TABLE_NAME + " (" +
                    med44Contract.travels._ID + " INTEGER PRIMARY KEY," +
                    med44Contract.travels.COLUMN_NAME_COUNTRY + TEXT_TYPE  +
                    " )";
    private static final String SQL_CREATE_DOC_VISITS =
            "CREATE TABLE " + med44Contract.doc_visits.TABLE_NAME + " (" +
                    med44Contract.doc_visits._ID + " INTEGER PRIMARY KEY," +
                    med44Contract.doc_visits.COLUMN_NAME_DIAGNOSED + TEXT_TYPE + COMMA_SEP +
                    med44Contract.doc_visits.COLUMN_NAME_DATE + TEXT_TYPE + COMMA_SEP +
                    med44Contract.doc_visits.COLUMN_NAME_IMAGE + TEXT_TYPE +
                    " )";
    private static final String SQL_CREATE_MED_CONDITIONS =
            "CREATE TABLE " + med44Contract.med_conditions.TABLE_NAME + " (" +
                    med44Contract.med_conditions._ID + " INTEGER PRIMARY KEY," +
                    med44Contract.med_conditions.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    med44Contract.med_conditions.COLUMN_NAME_DATE_DIAGNOSED + TEXT_TYPE +
                    " )";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + med44Contract.vaccines.TABLE_NAME + ";" +
                    "DROP TABLE IF EXISTS " + med44Contract.prescriptions.TABLE_NAME + ";" +
                    "DROP TABLE IF EXISTS " + med44Contract.travels.TABLE_NAME + ";" +
                    "DROP TABLE IF EXISTS " + med44Contract.doc_visits.TABLE_NAME + ";" +
                    "DROP TABLE IF EXISTS " + med44Contract.med_conditions.TABLE_NAME;

    private static final String SQL_INSERT_VACCINES =
            "INSERT INTO " + med44Contract.vaccines.TABLE_NAME +
            "(" + med44Contract.vaccines.COLUMN_NAME_COUNTRY + COMMA_SEP +
                    med44Contract.vaccines.COLUMN_NAME_VACCINES_REQUIRED + ")" +
                    "VALUES ('Malaysia','Routine Vaccinations: MMR, DTaP\nMost Travellers: Typhoid, Hepatitis A\nSome Travellers: Hepatitis B, Rabies, Cholera and Japanese Encephalitis')"
                        + COMMA_SEP + "('Thailand','Routine Vaccinations: MMR, DTaP\nMost Travellers: Typhoid, Hepatitis A\nSome Travellers: Hepatitis B, Rabies, Cholera and Japanese Encephalitis')"
                        + COMMA_SEP + "('Philippines','Routine Vaccinations: MMR, DTaP\nMost Travellers: Typhoid, Hepatitis A\nSome Travellers: Hepatitis B, Rabies, Cholera and Japanese Encephalitis')"
                        + COMMA_SEP + "('Indonesia','Routine Vaccinations: MMR, DTaP\nMost Travellers: Typhoid, Hepatitis A\nSome Travellers: Hepatitis B, Rabies, Cholera and Japanese Encephalitis')"
                        + COMMA_SEP + "('China','Routine Vaccinations: MMR, DTaP\nMost Travellers: Typhoid, Hepatitis A\nSome Travellers: Hepatitis B, Rabies, Cholera, Yellow Fever, Tick-Borne Encephalitis and Japanese Encephalitis')";



    public med44dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(SQL_CREATE_VACCINES);
        db.execSQL(SQL_CREATE_DOC_VISITS);
        db.execSQL(SQL_CREATE_MED_CONDITIONS);
        db.execSQL(SQL_CREATE_PRESCRIPTIONS);
        db.execSQL(SQL_CREATE_TRAVELS);
        db.execSQL(SQL_INSERT_VACCINES);

    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //  upgrade policy is to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}