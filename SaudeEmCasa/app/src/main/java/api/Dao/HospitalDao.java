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
public class HospitalDao extends Dao{
    private static HospitalDao instance;

    private static String tableColumns[]={"latitude","longitude","city","address","state","rate","district","telephone","name","type","number","hospitalGid"};

    private static String tableName = "Hospital";

    private HospitalDao( Context context ) {
        HospitalDao.database = new DatabaseHelper( context );
    }

    public static HospitalDao getInstance( Context context ) {
        if( HospitalDao.instance != null ) {
			/* !Nothing To Do. */

        } else {
            HospitalDao.instance = new HospitalDao( context );
        }

        return HospitalDao.instance;
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

    public boolean insertHospital(Hospital hospital) {

        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(tableColumns[0], hospital.getLatitude());
        values.put(tableColumns[1], hospital.getLongitude());
        values.put(tableColumns[2], hospital.getCity());
        values.put(tableColumns[3], hospital.getAddress());
        values.put(tableColumns[4], hospital.getState());
        values.put(tableColumns[5], hospital.getRate());
        values.put(tableColumns[6], hospital.getDistrict());
        values.put(tableColumns[7], hospital.getTelephone());
        values.put(tableColumns[8], hospital.getName());
        values.put(tableColumns[9], hospital.getType());
        values.put(tableColumns[10], hospital.getNumber());
        values.put(tableColumns[11], hospital.getId());


        boolean result = insertAndClose(sqLiteDatabase,tableName, values)>0;
        return result;
    }
    public List<Hospital> getAllHospitals() {

        sqliteDatabase = database.getReadableDatabase();

        String query = "SELECT * FROM " + tableName;

        Cursor cursor = sqliteDatabase.rawQuery( query, null );

        List<Hospital> listHospitals = new ArrayList<Hospital>();

        while( cursor.moveToNext() ) {

            Hospital hospital = new Hospital();

            hospital.setLatitude(cursor.getString(cursor
                    .getColumnIndex(tableColumns[0])));

            hospital.setLongitude(cursor.getString(cursor
                    .getColumnIndex(tableColumns[1])));


            hospital.setCity(cursor.getString(cursor
                    .getColumnIndex(tableColumns[2])));


            hospital.setAddress(cursor.getString(cursor
                    .getColumnIndex(tableColumns[3])));


            hospital.setState(cursor.getString(cursor
                    .getColumnIndex(tableColumns[4])));


            hospital.setRate(cursor.getFloat(cursor
                    .getColumnIndex(tableColumns[5])));


            hospital.setDistrict(cursor.getString(cursor
                    .getColumnIndex(tableColumns[6])));


            hospital.setTelephone(cursor.getString(cursor
                    .getColumnIndex(tableColumns[7])));


            hospital.setName(cursor.getString(cursor
                    .getColumnIndex(tableColumns[8])));


            hospital.setType(cursor.getString(cursor
                    .getColumnIndex(tableColumns[9])));

            hospital.setNumber(cursor.getString(cursor
                    .getColumnIndex(tableColumns[10])));

            hospital.setId(cursor.getString(cursor
                    .getColumnIndex(tableColumns[11])));

            listHospitals.add(hospital);
        }

        //sqliteDatabase.close();

        return listHospitals;
    }
    public boolean insertAllHospitals( List<Hospital> hospitalList ) {
        Iterator<Hospital> index = hospitalList.iterator();

        boolean result = true;

        while( index.hasNext() ) {
            result = insertHospital( index.next() );
        }

        return result;
    }

    public long deleteAllHospitals() {
        long result;

        result = deleteAndClose(sqliteDatabase, tableName);

        return result;
    }
}
