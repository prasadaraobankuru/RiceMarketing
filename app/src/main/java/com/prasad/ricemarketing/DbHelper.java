package com.prasad.ricemarketing;

/**
 * Created by Prasad on 11/27/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "RiceMarket.db";
    private static final int DATABASE_VERSION = 1;

    public static final String ShopsList_TABLE = "Shops";
    public static final String Id = "id";
    public static final String shop_name = "shop_name";
    public static final String Gst_No = "Gst_No";
    public static final String PhoneNumber = "PhoneNumber";
    public static final String Shop_Aria = "Shop_Aria";


    public static final String MillsList_TABLE = "Mills";
    public static final String Mill_Id = "Mill_Id";
    public static final String Mill_name = "Mill_name";
    public static final String Mill_Brand = "Mill_Brand";
    public static final String Mill_Gst_No = "Mill_Gst_No";
    public static final String Mill_PhoneNumber = "Mill_PhoneNumber";
    public static final String Mill_BankAccountNo = "Mill_BankAccountNo";
    public static final String Mill_Ifsc = "Mill_Ifsc";
    public static final String Mill_bank = "Mill_bank";
    public static final String Mill_bank_address = "Mill_bank_address";
    public static final String Mill_address = "Mill_address";



    public DbHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE " + ShopsList_TABLE +
                        "(" + Id + " INTEGER PRIMARY KEY, " +
                        shop_name + " TEXT, " +
                        Gst_No + " TEXT, " +
                        PhoneNumber + " TEXT, " +
                        Shop_Aria + " TEXT)"
        );

        db.execSQL(
                "CREATE TABLE " + MillsList_TABLE +
                        "(" + Mill_Id + " INTEGER PRIMARY KEY, " +
                        Mill_name + " TEXT, " +
                        Mill_Brand + " TEXT, " +
                        Mill_Gst_No + " TEXT, " +
                        Mill_PhoneNumber + " TEXT, " +
                        Mill_BankAccountNo + " TEXT, " +
                        Mill_Ifsc + " TEXT, " +
                        Mill_bank + " TEXT, " +
                        Mill_bank_address + " TEXT, " +
                        Mill_address + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ShopsList_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + MillsList_TABLE);
        onCreate(db);
    }

    public boolean insertVideos(String shopname, String gstno, String phnumber,String shop_aria) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(shop_name, shopname);
        contentValues.put(Gst_No, gstno);
        contentValues.put(PhoneNumber, phnumber);
        contentValues.put(Shop_Aria, shop_aria);



        db.insert(ShopsList_TABLE, null, contentValues);
        return true;
    }

    public boolean Milldata_insert(String millname, String MillBrand,String Mill_GstNo,String Mill_PhNumber,String Mill_BankAcNo,String Ifsc,
                                   String Millbank,String Mill_bankaddress,String Milladdress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(Mill_name, millname);
        contentValues.put(Mill_Brand, MillBrand);
        contentValues.put(Mill_Gst_No, Mill_GstNo);
        contentValues.put(Mill_PhoneNumber, Mill_PhNumber);
        contentValues.put(Mill_BankAccountNo, Mill_BankAcNo);
        contentValues.put(Mill_Ifsc, Ifsc);
        contentValues.put(Mill_bank, Millbank);
        contentValues.put(Mill_bank_address, Mill_bankaddress);
        contentValues.put(Mill_address, Milladdress);


        db.insert(MillsList_TABLE, null, contentValues);
        return true;
    }

    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, ShopsList_TABLE);
        return numRows;
    }

    public boolean updatePerson(Integer id, String shopname, String gstno, String phnumber,String shop_aria) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(shop_name, shopname);
        contentValues.put(Gst_No, gstno);
        contentValues.put(PhoneNumber, phnumber);
        contentValues.put(Shop_Aria, shop_aria);


        db.update(ShopsList_TABLE, contentValues, Id + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }



    public boolean Mill_dataupdate(Integer id, String millname, String MillBrand,String Mill_GstNo,String Mill_PhNumber,String Mill_BankAcNo,String Ifsc,
                                String Millbank,String Mill_bankaddress,String Milladdress) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Mill_name, millname);
        contentValues.put(Mill_Brand, MillBrand);
        contentValues.put(Mill_Gst_No, Mill_GstNo);
        contentValues.put(Mill_PhoneNumber, Mill_PhNumber);
        contentValues.put(Mill_BankAccountNo, Mill_BankAcNo);
        contentValues.put(Mill_Ifsc, Ifsc);
        contentValues.put(Mill_bank, Millbank);
        contentValues.put(Mill_bank_address, Mill_bankaddress);
        contentValues.put(Mill_address, Milladdress);


        db.update(MillsList_TABLE, contentValues, Mill_Id + " = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteVideo(Integer id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(ShopsList_TABLE,
                Id + " = ? ",
                new String[] { Integer.toString(id) });
    }

    public Integer deletemill(Integer id) {

        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(MillsList_TABLE,
                Mill_Id + " = ? ",
                new String[] { Integer.toString(id) });
    }
    /*public Cursor getPerson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery("SELECT * FROM " + VideoList_TABLE + " WHERE " +
                Video_ID + "=?", new String[]{Integer.toString(id)});
        return res;
    }*/

    public Cursor getAllPersons() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + ShopsList_TABLE, null );
        return res;
    }

    public Cursor getAllMills() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "SELECT * FROM " + MillsList_TABLE, null );
        return res;
    }
}