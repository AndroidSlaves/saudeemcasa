package api.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import api.Helper.DatabaseHelper;

/**
 * Created by lucas on 9/29/15.
 */
public class Dao {
    protected static DatabaseHelper database;
    protected static SQLiteDatabase sqliteDatabase;
    protected static Context context;

    //Método para inserir no banco local
    protected long insertAndClose(SQLiteDatabase sqLiteDatabase, String table, ContentValues values ) {
        assert (table != null) : "Table name never be null.";
        assert (table.length() >= 1) : "Table name must have at least one character.";

        sqLiteDatabase.insert(table, null, values);
        long resultInsert = 1;

        sqLiteDatabase.close();

        return resultInsert;
    }

    //Metodo para deletar o banco local
    protected long deleteAndClose(SQLiteDatabase sqLiteDatabase, String table) {
        int delete;

        assert (table != null) : "Table name never be null.";
        assert (table.length() >= 1) : "Table name must have at least one character.";

        delete = sqLiteDatabase.delete(table, null, null);

        sqLiteDatabase.close();

        return delete;
    }
}
