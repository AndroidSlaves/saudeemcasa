/*****************************
 * Class name: Dao (.java)
 *
 * Purpose: Insert Object information in the database.
 ****************************/

package api.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import api.Helper.DatabaseHelper;

public class Dao {
    protected static DatabaseHelper database;
    protected static SQLiteDatabase sqliteDatabase;
    protected static Context context;

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
    protected long insertAndClose(SQLiteDatabase sqLiteDatabase, String table, ContentValues values) {
        assert (table != null) : "Table name never be null.";
        assert (table.length() >= 1) : "Table name must have at least one character.";

        sqLiteDatabase.insert(table, null, values);
        long resultInsert = 1;

        sqLiteDatabase.close();

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

        int delete;

        delete = sqLiteDatabase.delete(table, null, null);

        sqLiteDatabase.close();

        return delete;
    }
}
