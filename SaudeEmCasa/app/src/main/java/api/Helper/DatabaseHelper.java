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

    // Name of the whole database.
    private static final String DATABASE_NAME = "saudeEmCasaManager";

    // Represents the database version in this system, this varies with updates.
    private static final int DATABASE_VERSION = 7;

    // Name of the drugstore table of the database.
    private static final String DRUGSTORE_TABLE = "[Drugstore]";

    // Name of the Hospital table in the database.
    private static final String HOSPITAL_TABLE = "[Hospital]";

    // Represents the column of the drugstore database for the drugstore latitude.
    private static final String DRUGSTORE_LATITUDE = "[latitude]";

    // Represents the column of the drugstore database for the drugstore longetude.
    private static final String DRUGSTORE_LONGITUDE = "[longitude]";

    // Represents the column of the drugstore database for the city where it is located.
    private static final String DRUGSTORE_CITY = "[city]";

    // Represents the column of the drugstore database for the drugstore address.
    private static final String DRUGSTORE_ADDRESS = "[address]";

    // Represents the column of the drugstore database for the state where the drugstore is.
    private static final String DRUGSTORE_STATE = "[state]";

    // Represents the column of the drugstore database for the drugstore rate.
    private static final String DRUGSTORE_RATE = "[rate]";

    // Represents the column of the drugstore database for the drugstore telephone.
    private static final String DRUGSTORE_TELEPHONE = "[telephone]";

    // Represents the column of the drugstore database for the drugstore commercial name.
    private static final String DRUGSTORE_NAME = "[name]";

    // Represents the column of the drugstore database for the type of the drugstore.
    private static final String DRUGSTORE_TYPE = "[type]";

    // Represents the column of the drugstore database for its ID.
    private static final String DRUGSTORE_ID = "[drugstoreId]";

    // Represents the column of the drugstore database for the drugstore postal code
    private static final String DRUGSTORE_POSTALCODE = "[postalCode]";

    // Represents the column of the drugstore database for the government ID.
    private static final String DRUGSTORE_GID = "[drugstoreGid]";

    // Represents the hospital latitude in the database.
    private static final String HOSPITAL_LATITUDE = "[latitude]";

    // Represents the hospital longetude in the database.
    private static final String HOSPITAL_LONGITUDE = "[longitude]";

    // Represents column with the name of the city where the hospital is located.
    private static final String HOSPITAL_CITY = "[city]";

    // Represents the hospital address in the database.
    private static final String HOSPITAL_ADDRESS = "[address]";

    // Represents the hospital state in the database.
    private static final String HOSPITAL_STATE = "[state]";

    // Represents the hospital rate in the database.
    private static final String HOSPITAL_RATE = "[rate]";

    // Represents the hospital telephone in the database.
    private static final String HOSPITAL_TELEPHONE = "[telephone]";

    // Represents the hospital type in the database.
    private static final String HOSPITAL_NAME = "[name]";

    // Represents the hospital type in the database.
    private static final String HOSPITAL_TYPE = "[type]";

    // Represents the hospital Id in the database.
    private static final String HOSPITAL_ID = "[hospitalId]";

    // Represents the hospital number in the database.
    private static final String HOSPITAL_NUMBER = "[number]";

    // Represents the hospital district in the database.
    private static final String HOSPITAL_DISTRICT = "[district]";

    // Represents the hospital government ID in the database.
    private static final String HOSPITAL_GID = "[hospitalGid]";

    // The SQL command line which is the junction of the strings related to the drugstore.
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

    // The SQL command line which is the junction of the strings related to the Hospital.
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

    //Create tables to drugstores and hospital
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_DRUGSTORE);
        database.execSQL(CREATE_HOSPITAL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        /* Nothing to do! */
    }
}
