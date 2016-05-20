/*****************************
 * Class name: HospitalDao (.java)
 *
 * Purpose: An activity in order to create a list of hospitals with the distance between the user
 *          and the respective hospitals.
 ****************************/

package api.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import api.Helper.DatabaseHelper;

import mds.gpp.saudeemcasa.model.Hospital;

public class HospitalDao extends Dao {
    private static HospitalDao instance;
    private static String tableName = "Hospital";
    private static final String TABLE_COLUMNS[] = {"latitude", "longitude", "city", "address",
                                                   "state", "district", "telephone", "name", "type",
                                                   "number"," hospitalGid"};

    private HospitalDao(Context context) {
        assert (context != null) : "Context must never be null.";

        HospitalDao.database = new DatabaseHelper(context);
    }

    /**
     * Method used to get instance of database. If the database where not
     * created, then the method create a instance.
     *
     * @param context
     *              Context application
     * @return
     *              HospitalDao.instance
     */
    public static HospitalDao getInstance(Context context) {
        assert (context != null) : "Context must never be null.";

        if(HospitalDao.instance != null) {
			/* !Nothing To Do. */
        } else {
            HospitalDao.instance = new HospitalDao(context);
        }

        return HospitalDao.instance;
    }

    /**
     * Method used to verify database is empty.
     *
     * @return
     *              isEmpty:boolean
     */
    public boolean isDbEmpty(){
        sqliteDatabase = database.getReadableDatabase();
        String query = "SELECT  1 FROM " + tableName;
        Cursor cursor = sqliteDatabase.rawQuery(query, null);
        boolean isEmpty = false;

        if(cursor != null) {

            if(cursor.getCount() <= 0) {
                cursor.moveToFirst();
                isEmpty = true;
            } else {
                // Nothing to do.
            }

        } else {
            isEmpty = true;
        }

        return isEmpty;
    }

    /**
     * Method used to insert an hospital object on database.
     *
     * @param hospital
     *              Hospital entity
     * @return result
     *              boolean
     */
    public boolean insertHospital(Hospital hospital) {
        assert (hospital != null) : "hospital can't be null.";

        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TABLE_COLUMNS[0], hospital.getLatitude());
        values.put(TABLE_COLUMNS[1], hospital.getLongitude());
        values.put(TABLE_COLUMNS[2], hospital.getCity());
        values.put(TABLE_COLUMNS[3], hospital.getAddress());
        values.put(TABLE_COLUMNS[4], hospital.getState());
        values.put(TABLE_COLUMNS[5], hospital.getRate());
        values.put(TABLE_COLUMNS[6], hospital.getDistrict());
        values.put(TABLE_COLUMNS[7], hospital.getTelephone());
        values.put(TABLE_COLUMNS[8], hospital.getName());
        values.put(TABLE_COLUMNS[9], hospital.getType());
        values.put(TABLE_COLUMNS[10], hospital.getNumber());
        values.put(TABLE_COLUMNS[11], hospital.getId());

        boolean resultFromDatabase = insertAndClose(sqLiteDatabase,tableName, values) > 0;
        return resultFromDatabase;
    }

    /**
     * Method to get the list of all hospitals on database organized on array.
     *
     * @return
     *              List of all hospitals
     */
    public List<Hospital> getAllHospitals() {

        sqliteDatabase = database.getReadableDatabase();
        String query = "SELECT * FROM " + tableName;
        Cursor cursor = sqliteDatabase.rawQuery( query, null );
        List<Hospital> listHospitals = new ArrayList<Hospital>();

        while(cursor.moveToNext()) {
            Hospital hospital = new Hospital();

            hospital.setLatitude(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[0])));
            hospital.setLongitude(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[1])));
            hospital.setCity(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[2])));
            hospital.setAddress(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[3])));
            hospital.setState(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[4])));
            hospital.setRate(cursor.getFloat(cursor.getColumnIndex(TABLE_COLUMNS[5])));
            hospital.setDistrict(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[6])));
            hospital.setTelephone(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[7])));
            hospital.setName(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[8])));
            hospital.setType(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[9])));
            hospital.setNumber(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[10])));
            hospital.setId(cursor.getString(cursor.getColumnIndex(TABLE_COLUMNS[11])));

            listHospitals.add(hospital);
        }

        return listHospitals;
    }

    /**
     * Method used to insert all hospitals objects on ArrayList.
     *
     * @param hospitalList
     *              List: list of hospitals
     * @return result
     *              Boolean: confirmation include hospitals of list
     */
    public boolean insertAllHospitals(List<Hospital> hospitalList) {
        assert (hospitalList != null) : "hospitalList can't be null";
        assert (hospitalList.isEmpty() == true) : "hospitalList must have at least one item";

        Iterator<Hospital> index = hospitalList.iterator();
        boolean resultFromInsert = true;

        while(index.hasNext()) {
            resultFromInsert = insertHospital(index.next());
        }

        return resultFromInsert;
    }

    /**
     * Method used to delete hospitals from database.
     *
     * @return
     *              result of the deletion.
     */
    public long deleteAllHospitals() {
        long resultFromDeletion;

        resultFromDeletion = deleteAndClose(sqliteDatabase, tableName);

        return resultFromDeletion;
    }
}
