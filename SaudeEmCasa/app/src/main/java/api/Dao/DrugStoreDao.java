/*****************************
 * Class name: DrugStoreDao (.java)
 *
 * Purpose: This class is responsible for making all the operations with the database in regard of
 *          the drugstore information.
 *****************************/

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

    private static String tableColumns[] = {"latitude", "longitude", "city", "address", "state",
            "rate", "postalCode", "telephone", "name", "type", "drugstoreGid"};
    private static DrugStoreDao instance = null;
    private static String tableName = "Drugstore";

    private DrugStoreDao(Context context) {
        assert (context != null) : "context must never be null";
        DrugStoreDao.database = new DatabaseHelper(context);
    }

    public static DrugStoreDao getInstance(Context context) {
        if(DrugStoreDao.instance != null) {
			// Nothing to do.
        } else {
            DrugStoreDao.instance = new DrugStoreDao(context);
        }

        return DrugStoreDao.instance;
    }

    public boolean isDatabaseEmpty() {
        String query = "SELECT 1 FROM " + tableName;
        sqliteDatabase = database.getReadableDatabase();
        Cursor cursor = sqliteDatabase.rawQuery(query, null);
        boolean isEmpty = false;

        if(cursor != null) {
            if(cursor.getCount() <= 0) {
                cursor.moveToFirst();
                isEmpty = true;
            } else {
                //Nothing to do.
            }
        } else {
            isEmpty = true;
        }

        return isEmpty;
    }

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

        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

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

        boolean result = insertAndClose(sqLiteDatabase,tableName, values) > 0;
        return result;
    }

    public List<DrugStore> getAllDrugStores() {
        String query = "SELECT * FROM " + tableName;
        List<DrugStore> listDrugstores = new ArrayList<DrugStore>();
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

    public boolean insertAllDrugStores(List<DrugStore> drugStoresList) {
        assert (drugStoresList != null) : "drugStoresList must never be null";
        assert (drugStoresList.size() > 0) : "drugStoresList field must never be empty";

        Iterator<DrugStore> index = drugStoresList.iterator();
        boolean resultInsertDrugStores = true;

        while(index.hasNext()) {
            resultInsertDrugStores = insertDrugstore(index.next());
        }

        return resultInsertDrugStores;
    }

    public long deleteAllDrugStores() {
        long resultDeleteDrugStores;
        resultDeleteDrugStores = deleteAndClose(sqliteDatabase, tableName);
        return resultDeleteDrugStores;
    }
}
