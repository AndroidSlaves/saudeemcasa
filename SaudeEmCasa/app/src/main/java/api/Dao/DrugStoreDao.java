/************************
 * Class name: DrugStoreDao (.java)
 *
 * Purpose: This class is responsible for making all the operations with the database in regard of
 *          the drugstore information.
 ************************/

package api.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import api.Helper.DatabaseHelper;
import mds.gpp.saudeemcasa.model.DrugStore;

public class DrugStoreDao extends Dao {

    // Static string that have all the database columns names.
    private static String tableColumns[] = {"latitude", "longitude", "city", "address", "state",
                                            "rate", "postalCode", "telephone", "name", "type",
                                            "drugstoreGid"};

    /*
     * Instance of DrugstoreDao store whenever it is created so when instantiated again it cannot
     * create another object.
     */
    private static DrugStoreDao instance = null;

    // Used to access the table of Drugstore's name.
    private static String tableName = "Drugstore";

    /**
     * Creates an instace of DrugstoreDao in the context. It's called only by getInstance().
     *
     * @param context
     *              Context type, receive the context where it is in
     */
    private DrugStoreDao(Context context) {
        assert (context != null) : "context must never be null";
        DrugStoreDao.database = new DatabaseHelper(context);
    }

    /**
     * Checks if there is a instance of DrugStoreDao, if there isn't, it creates a new one
     *
     * @param context
     *        receive the context to verify the isntance
     *
     * @return it return the existing context (Context).
     */
    public static DrugStoreDao getInstance(Context context) {

        if(DrugStoreDao.instance != null) {
			// Nothing to do.
        } else {
            DrugStoreDao.instance = new DrugStoreDao(context);
        }

        return DrugStoreDao.instance;
    }

    /**
     * Verifies if database is empty by selecting one object and verifying if it is null.
     *
     * @return cursor, it's a object returned from the database after executing the query command.
     */
    public boolean isDatabaseEmpty() {

        // Contains the a database command: SELECT.
        String query = "SELECT 1 FROM " + tableName;
        sqliteDatabase = database.getReadableDatabase();

        // Object returned from the database after executing the query command.
        Cursor cursor = sqliteDatabase.rawQuery(query, null);
        boolean isEmpty = false;

        if(cursor != null) {
            if(cursor.getCount() <= 0) {
                cursor.moveToFirst();
                isEmpty = true;
            } else {
                /* Nothing to do! */
            }
        } else {
            isEmpty = true;
        }

        return isEmpty;
    }

    /**
     * Method that takes an drugstore object, convert it into a value table and insert it into
     * the database
     *
     * @param drugStore
     *
     * @return outcome of the operation.
     */
    public boolean insertDrugstore(DrugStore drugStore) {

        assert (drugStore != null) : "drugStore object must never be null";
        assert (drugStore.getLatitude() != null) : "latitude field must never be null";
        assert (drugStore.getLongitude() != null) : "longitude field must never be null";
        assert (drugStore.getCity() != null) : "city field must never be null";
        assert (drugStore.getAddress() != null) : "address field must never be null";
        assert (drugStore.getState() != null) : "state field must never be null";
        assert (drugStore.getRate() > 0f) : "rate field must never be null";
        assert (drugStore.getPostalCode() != null) : "postalCode field must never be null";
        assert (drugStore.getTelephone() != null) : "telephone field must never be null";
        assert (drugStore.getName() != null) : "name field must never be null";
        assert (drugStore.getType() != null) : "type field must never be null";
        assert (drugStore.getId() != null) : "id field must never be null";

        // Open writable Database so element can be inserted.
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        // Table of values that is used to insert the element into the database.
        ContentValues values = new ContentValues();

        values.put(tableColumns[0], drugStore.getLatitude());
        values.put(tableColumns[1], drugStore.getLongitude());
        values.put(tableColumns[2], drugStore.getCity());
        values.put(tableColumns[3], drugStore.getAddress());
        values.put(tableColumns[4], drugStore.getState());
        values.put(tableColumns[5], drugStore.getRate());
        values.put(tableColumns[6], drugStore.getPostalCode());
        values.put(tableColumns[7], drugStore.getTelephone());
        values.put(tableColumns[8], drugStore.getName());
        values.put(tableColumns[9], drugStore.getType());
        values.put(tableColumns[10], drugStore.getId());

        // Verify if the table is null
        boolean result = insertAndClose(sqLiteDatabase,tableName, values) > 0;
        return result;
    }

    /**
     * Creates a list of drugstore objects from data coming from the database.
     *
     * @return list of drugstore objects
     */
    public List<DrugStore> getAllDrugStores() {

        // Contains the a database command: SELECT.
        String query = "SELECT * FROM " + tableName;

        List<DrugStore> listDrugstores = new ArrayList<DrugStore>();

        // It indicates which element of the data base it is at.
        Cursor cursor = sqliteDatabase.rawQuery(query, null);

        sqliteDatabase = database.getReadableDatabase();

        while(cursor.moveToNext()) {

            DrugStore drugStore = new DrugStore();

            drugStore.setLatitude(cursor.getString(cursor.getColumnIndex(tableColumns[0])));
            drugStore.setLongitude(cursor.getString(cursor.getColumnIndex(tableColumns[1])));
            drugStore.setCity(cursor.getString(cursor.getColumnIndex(tableColumns[2])));
            drugStore.setAddress(cursor.getString(cursor.getColumnIndex(tableColumns[3])));
            drugStore.setState(cursor.getString(cursor.getColumnIndex(tableColumns[4])));
            drugStore.setRate(cursor.getFloat(cursor.getColumnIndex(tableColumns[5])));
            drugStore.setPostalCode(cursor.getString(cursor.getColumnIndex(tableColumns[6])));
            drugStore.setTelephone(cursor.getString(cursor.getColumnIndex(tableColumns[7])));
            drugStore.setName(cursor.getString(cursor.getColumnIndex(tableColumns[8])));
            drugStore.setType(cursor.getString(cursor.getColumnIndex(tableColumns[9])));
            drugStore.setId(cursor.getString(cursor.getColumnIndex(tableColumns[10])));

            listDrugstores.add(drugStore);
        }

        return listDrugstores;
    }

    /**
     * Method that takes a list of drugstore objects and calls the insertDrugstore method for each
     * one.
     *
     * @param drugStoresList
     *                      receive a list of drugstore
     *
     * @return if the all of the drugstores was indexed, it's return true.
     */
    public boolean insertAllDrugStores(List<DrugStore> drugStoresList) {

        assert (drugStoresList != null) : "drugStoresList must never be null";
        assert (drugStoresList.size() > 0) : "drugStoresList field must never be empty";

        // Makes the interaction with each object of the list
        Iterator<DrugStore> index = drugStoresList.iterator();

        boolean resultInsertDrugStores = true;

        while(index.hasNext()) {
            resultInsertDrugStores = insertDrugstore(index.next());
        }
        return resultInsertDrugStores;
    }

    /**
     * Calls method deleteAndClose from the Dao class, which deletes the database file
     *
     * @return the database file deleted.
     */
    public long deleteAllDrugStores() {

        long resultDeleteDrugStores;
        resultDeleteDrugStores = deleteAndClose(sqliteDatabase, tableName);
        return resultDeleteDrugStores;
    }
}

