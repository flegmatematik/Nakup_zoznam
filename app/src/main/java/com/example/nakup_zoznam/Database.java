package com.example.nakup_zoznam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private  static Database instance;

    public static Database getInstance(Context context)
    {
        if(instance == null)
        {
            instance = new Database(context);
        }
        return instance;
    }

    public static final String MENO_DATABAZY = "Nakupne.zoznamy.db";


    private Database(Context context) {
        super(context, MENO_DATABAZY, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + Schema.Zoznam_Nakupov.ZOZNAM_NAKUPOV + " ("
                + Schema.Zoznam_Nakupov.ZOZNAM_NAKUPOV_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Schema.Zoznam_Nakupov.ZOZNAM_NAKUPOV_CENA +" REAL)");
        db.execSQL("CREATE TABLE " + Schema.Nakup.NAKUP + " ("
                + Schema.Nakup.NAKUP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Schema.Nakup.NAKUP_CENA + " REAL, "
                + Schema.Nakup.NAKUP_POCET + " INTEGER, "
                + Schema.Nakup.NAKUP_ID_ZOZNAMU + " INTEGER "
                + "REFERENCES " + Schema.Zoznam_Nakupov.ZOZNAM_NAKUPOV + ")" );
        db.execSQL("CREATE TABLE " + Schema.Objekt.OBJEKT + " ("
                + Schema.Objekt.OBJEKT_ID + " INTEGER PRIMARY KEY, "
                + Schema.Objekt.OBJEKT_NAZOV + " TEXT, "
                + Schema.Objekt.OBJEKT_TYP + " TEXT)");
        db.execSQL("CREATE TABLE " + Schema.Kupeny_Objekt.KUPENY_OBJEKT + " ("
                + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ID_OBJEKT + " INTEGER, "
                + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ID_NAKUP + " INTEGER, "
                + Schema.Kupeny_Objekt.KUPENY_OBJEKT_KVANTITA + " INTEGER, "
                + Schema.Kupeny_Objekt.KUPENY_OBJEKT_CENA + " REAL, "
                + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ZAPLATENE + "TEXT, "
                + " FOREIGN KEY (" + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ID_OBJEKT + ") REFERENCES " + Schema.Objekt.OBJEKT + "(" + Schema.Objekt.OBJEKT_ID + "),"
                + " FOREIGN KEY (" + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ID_NAKUP + ") REFERENCES " + Schema.Nakup.NAKUP + "(" + Schema.Nakup.NAKUP_ID + "))");

        //db = getWritableDatabase();
        ContentValues values_chleba = new ContentValues();
        values_chleba.put(Schema.Objekt.OBJEKT_ID, 0);
        values_chleba.put(Schema.Objekt.OBJEKT_NAZOV, "CHLEBA");
        values_chleba.put(Schema.Objekt.OBJEKT_TYP, "PECIVO");
        db.insert(Schema.Objekt.OBJEKT,null,values_chleba);
        ContentValues values_rozok = new ContentValues();
        values_rozok.put(Schema.Objekt.OBJEKT_ID, 1);
        values_rozok.put(Schema.Objekt.OBJEKT_NAZOV, "ROZOK");
        values_chleba.put(Schema.Objekt.OBJEKT_TYP, "PECIVO");
        db.insert(Schema.Objekt.OBJEKT,null,values_rozok);

        ContentValues values_zoznam = new ContentValues();
        values_zoznam.put(Schema.Zoznam_Nakupov.ZOZNAM_NAKUPOV_ID, 0);
        values_zoznam.put(Schema.Zoznam_Nakupov.ZOZNAM_NAKUPOV_CENA, 0.0);
        db.insert(Schema.Zoznam_Nakupov.ZOZNAM_NAKUPOV,null,values_zoznam);

        ContentValues values_nakup = new ContentValues();
        values_nakup.put(Schema.Nakup.NAKUP_ID, 0);
        values_nakup.put(Schema.Nakup.NAKUP_CENA, 0.0);
        values_nakup.put(Schema.Nakup.NAKUP_POCET, 0);
        values_nakup.put(Schema.Nakup.NAKUP_ID_ZOZNAMU, 0);
        db.insert(Schema.Nakup.NAKUP,null,values_nakup);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Schema.Kupeny_Objekt.KUPENY_OBJEKT);
        db.execSQL("DROP TABLE IF EXISTS " + Schema.Objekt.OBJEKT);
        db.execSQL("DROP TABLE IF EXISTS " + Schema.Nakup.NAKUP);
        db.execSQL("DROP TABLE IF EXISTS " + Schema.Zoznam_Nakupov.ZOZNAM_NAKUPOV);
        onCreate(db);
    }

    public Cursor showPecivo()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT NAZOV FROM " + Schema.Objekt.OBJEKT
                + " WHERE " + Schema.Objekt.OBJEKT_TYP + " LIKE 'PECIVO' " , null);
        return res;
    }

    public Cursor getObjektData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Schema.Objekt.OBJEKT , null);
        return res;
    }

    public Cursor getNakupData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Schema.Nakup.NAKUP , null);
        return res;
    }

    public Cursor getZoznam_NakupovData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Schema.Zoznam_Nakupov.ZOZNAM_NAKUPOV , null);
        return res;
    }

    public Cursor getKupeny_objektData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Schema.Kupeny_Objekt.KUPENY_OBJEKT , null);
        return res;
    }


}
