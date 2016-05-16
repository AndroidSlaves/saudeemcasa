/*****************************
 * Class name: DatabaseHelper (.java)
 *
 * Purpose: Class that defines and creates the database.
 *****************************/

package api.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database information
    private static final String DATABASE_NAME = "saudeEmCasaManager";
    private static final int DATABASE_VERSION = 7;

    // Table names
    private static final String DRUGSTORE_TABLE = "[Drugstore]";
    private static final String HOSPITAL_TABLE = "[Hospital]";

    // Drugstore data
    private static final String DRUGSTORE_LATITUDE = "[latitude]";
    private static final String DRUGSTORE_LONGITUDE = "[longitude]";
    private static final String DRUGSTORE_CITY = "[city]";
    private static final String DRUGSTORE_ADDRESS = "[address]";
    private static final String DRUGSTORE_STATE = "[state]";
    private static final String DRUGSTORE_RATE = "[rate]";
    private static final String DRUGSTORE_TELEPHONE = "[telephone]";
    private static final String DRUGSTORE_NAME = "[name]";
    private static final String DRUGSTORE_TYPE = "[type]";
    private static final String DRUGSTORE_ID = "[drugstoreId]";
    private static final String DRUGSTORE_POSTALCODE = "[postalCode]";
    private static final String DRUGSTORE_GID = "[drugstoreGid]";

    // Hospital data
    private static final String HOSPITAL_LATITUDE = "[latitude]";
    private static final String HOSPITAL_LONGITUDE = "[longitude]";
    private static final String HOSPITAL_CITY = "[city]";
    private static final String HOSPITAL_ADDRESS = "[address]";
    private static final String HOSPITAL_STATE = "[state]";
    private static final String HOSPITAL_RATE = "[rate]";
    private static final String HOSPITAL_TELEPHONE = "[telephone]";
    private static final String HOSPITAL_NAME = "[name]";
    private static final String HOSPITAL_TYPE = "[type]";
    private static final String HOSPITAL_ID = "[hospitalId]";
    private static final String HOSPITAL_NUMBER = "[number]";
    private static final String HOSPITAL_DISTRICT = "[district]";
    private static final String HOSPITAL_GID = "[hospitalGid]";

    private static final String CREATE_DRUGSTORE = "CREATE TABLE " + DRUGSTORE_TABLE + " (" +
            DRUGSTORE_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            DRUGSTORE_POSTALCODE + " VARCHAR(15), " +
            DRUGSTORE_LATITUDE + "  VARCHAR(10), " +
            DRUGSTORE_LONGITUDE + " VARCHAR(10), " +
            DRUGSTORE_TELEPHONE + " VARCHAR(15), " +
            DRUGSTORE_CITY + " VARCHAR(20), " +
            DRUGSTORE_NAME + " VARCHAR(70), " +
            DRUGSTORE_TYPE + " VARCHAR(10), " +
            DRUGSTORE_ADDRESS + " VARCHAR(20), " +
            DRUGSTORE_STATE + " VARCHAR(20), " +
            DRUGSTORE_RATE + " FLOAT," +
            DRUGSTORE_GID + " VARCHAR(20)); ";

    private static final String CREATE_HOSPITAL = "CREATE TABLE " + HOSPITAL_TABLE + " ( " +
            HOSPITAL_ID + "INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            HOSPITAL_NUMBER + " VARCHAR(10), " +
            HOSPITAL_DISTRICT + " VARCHAR(50), " +
            HOSPITAL_LATITUDE + "  VARCHAR(10), " +
            HOSPITAL_LONGITUDE + " VARCHAR(10), " +
            HOSPITAL_TELEPHONE + " VARCHAR(15), " +
            HOSPITAL_CITY + " VARCHAR(20), " +
            HOSPITAL_NAME + " VARCHAR(70), " +
            HOSPITAL_TYPE + " VARCHAR(10), " +
            HOSPITAL_ADDRESS + " VARCHAR(20), " +
            HOSPITAL_STATE + " VARCHAR(20), " +
            HOSPITAL_RATE + " FLOAT," +
            HOSPITAL_GID + " VARCHAR(20)); ";

    // Constructor null database
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_DRUGSTORE);
        database.execSQL(CREATE_HOSPITAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        //Nothing to do
    }
}