/*****************************
 * Class name: Dao (.java)
 *
 * Purpose: Insert Object information in the database.
 ****************************/

package api.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import api.Helper.DatabaseHelper;

public class Dao {
    //This attribute is used to define strings related to the columns of database instead of numbers.
    protected static DatabaseHelper database;
    //This is the database will be used to store objects information
    protected static SQLiteDatabase sqliteDatabase;
    //Context is the screen that Dao class will be used.
    protected static Context context;
    //Used to log system.
    private final String TAG = Dao.class.getSimpleName();

    /**
     * Method used to insert data on database. After insertion database needs to be closed. The
     * parameters refers to database used to store data, table of database that refers data, and
     * the value of data.
     *
     * @param sqLiteDatabase:SQLiteDatabase
     * @param table: String
     * @param values:ContentValues
     *
     */
    protected long insertAndClose(SQLiteDatabase sqLiteDatabase, String table,
                                  ContentValues values) {
        assert (table != null) : "Table name never be null.";
        assert (table.length() >= 1) : "Table name must have at least one character.";

        sqLiteDatabase.insert(table, null, values);
        Log.i(TAG, "Information stored on table.");
        long resultInsert = 1;
        sqLiteDatabase.close();
        Log.i(TAG, "Database closed after insertion.");

        return resultInsert;
    }

    /**
     * Method used to delete data on database. After deletion database needs to
     * be closed. The parameters refers to database used to database used in
     * deletion, and the table that will be deleted.
     *
     * @param sqLiteDatabase:SQLiteDatabase
     * @param table: String
     *
     */
    protected long deleteAndClose(SQLiteDatabase sqLiteDatabase, String table) {
        assert (table != null) : "Table name never be null.";
        assert (table.length() >= 1) : "Table name must have at least one character.";

        int deleteFromDatabase = 0;
        deleteFromDatabase = sqLiteDatabase.delete(table, null, null);
        Log.i(TAG, "Called SQL table deletion.");
        sqLiteDatabase.close();
        Log.i(TAG, "Database closed after deletion.");

        return deleteFromDatabase;
    }
}
