package api.Dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import api.Helper.DatabaseHelper;
import mds.gpp.saudeemcasa.model.DrugStore;
import mds.gpp.saudeemcasa.model.Hospital;

/**
 * Created by lucas on 9/27/15.
 */

public class DrugStoreDao extends Dao{

    private static String tableColumns[]={"latitude","longitude","city","address","state","rate","postalCode","telephone","name","type","drugstoreGid"};

    private static DrugStoreDao instance;

    private static String tableName = "Drugstore";

    private DrugStoreDao( Context context ) {
        DrugStoreDao.database = new DatabaseHelper( context );
    }

    public static DrugStoreDao getInstance( Context context ) {
        if( DrugStoreDao.instance != null ) {
			/* !Nothing To Do. */

        } else {
            DrugStoreDao.instance = new DrugStoreDao( context );
        }

        return DrugStoreDao.instance;
    }

    public boolean isDbEmpty(){
        sqliteDatabase = database.getReadableDatabase();

        String query = "SELECT  1 FROM " + tableName;

        Cursor cursor = sqliteDatabase.rawQuery( query, null );

        boolean isEmpty = false;

        if( cursor != null ) {

            if( cursor.getCount() <= 0 ) {
                cursor.moveToFirst();

                isEmpty = true;

            } else {}

        } else {

            isEmpty = true;
        }

        return isEmpty;
    }

    public boolean insertDrugstore(DrugStore drugStore) {

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


        boolean result = insertAndClose(sqLiteDatabase,tableName, values)>0;
        return result;
    }

    public List<DrugStore> getAllDrugStores() {

        sqliteDatabase = database.getReadableDatabase();

        String query = "SELECT * FROM " + tableName;

        Cursor cursor = sqliteDatabase.rawQuery(query, null);

        List<DrugStore> listDrugstores = new ArrayList<DrugStore>();

        while( cursor.moveToNext() ) {

            DrugStore drugStore = new DrugStore();

            drugStore.setLatitude(cursor.getString(cursor
                    .getColumnIndex(tableColumns[0])));

            drugStore.setLongitude(cursor.getString(cursor
                    .getColumnIndex(tableColumns[1])));
            drugStore.setCity(cursor.getString(cursor
                    .getColumnIndex(tableColumns[2])));
            drugStore.setAddress(cursor.getString(cursor
                    .getColumnIndex(tableColumns[3])));
            drugStore.setState(cursor.getString(cursor
                    .getColumnIndex(tableColumns[4])));
            drugStore.setRate(cursor.getFloat(cursor
                    .getColumnIndex(tableColumns[5])));
            drugStore.setPostalCode(cursor.getString(cursor
                    .getColumnIndex(tableColumns[6])));
            drugStore.setTelephone(cursor.getString(cursor
                    .getColumnIndex(tableColumns[7])));
            drugStore.setName(cursor.getString(cursor
                    .getColumnIndex(tableColumns[8])));
            drugStore.setType(cursor.getString(cursor
                    .getColumnIndex(tableColumns[9])));
            drugStore.setId(cursor.getString(cursor
                    .getColumnIndex(tableColumns[10])));

            listDrugstores.add(drugStore);
        }

        //sqliteDatabase.close();

        return listDrugstores;
    }

    public boolean insertAllDrugStores(List<DrugStore> drugStoresList) {
        Iterator<DrugStore> index = drugStoresList.iterator();

        boolean result = true;

        while( index.hasNext() ) {
            result = insertDrugstore(index.next());
        }

        return result;
    }

    public long deleteAllDrugStores() {
        long result;

        result = deleteAndClose(sqliteDatabase, tableName);

        return result;
    }
}
