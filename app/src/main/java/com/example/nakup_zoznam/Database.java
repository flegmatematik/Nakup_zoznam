package com.example.nakup_zoznam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;

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


        pridajObjekt("BUCHTA", "PECIVO",db);
        pridajObjekt("ROŽOK","PECIVO",db);
        pridajObjekt("CHLEBA","PECIVO",db);
        pridajObjekt("BAGETA","PECIVO",db);
        pridajObjekt("SLADKÝ ROŽOK","PECIVO",db);
        pridajObjekt("DONUT","PECIVO",db);
        pridajObjekt("MLIEKO","MLIECNE",db);
        pridajObjekt("MASLO","MLIECNE",db);
        pridajObjekt("SYR","MLIECNE",db);
        pridajObjekt("NÁTIERKA","MLIECNE",db);
        pridajObjekt("JOGURT","MLIECNE",db);
        pridajObjekt("SYROKRÉM","MLIECNE",db);
        pridajObjekt("TVAROH","MLIECNE",db);
        pridajObjekt("SMOTANA","MLIECNE",db);
        pridajObjekt("KOND. MLIEKO","MLIECNE",db);
        pridajObjekt("JABLKÁ","OVOCIE",db);
        pridajObjekt("HRUŠKY","OVOCIE",db);
        pridajObjekt("JAHODY","OVOCIE",db);
        pridajObjekt("POMARANČE","OVOCIE",db);
        pridajObjekt("MANDARÍNKY","OVOCIE",db);
        pridajObjekt("MELÓN","OVOCIE",db);
        pridajObjekt("BANÁNY","OVOCIE",db);

        /*
        db.execSQL("INSERT INTO " + Schema.Objekt.OBJEKT + " ("+ Schema.Objekt.OBJEKT_NAZOV +", " + Schema.Objekt.OBJEKT_TYP + ") " +
                " VALUES ('BUCHTA', 'PECIVO');");
        db.execSQL("INSERT INTO " + Schema.Objekt.OBJEKT + " ("+ Schema.Objekt.OBJEKT_NAZOV +", " + Schema.Objekt.OBJEKT_TYP + ") " +
                " VALUES ('ROZOK', 'PECIVO');");
        db.execSQL("INSERT INTO " + Schema.Objekt.OBJEKT + " ("+ Schema.Objekt.OBJEKT_NAZOV +", " + Schema.Objekt.OBJEKT_TYP + ") " +
                " VALUES ('CHLEBA', 'PECIVO');");
        db.execSQL("INSERT INTO " + Schema.Objekt.OBJEKT + " ("+ Schema.Objekt.OBJEKT_NAZOV +", " + Schema.Objekt.OBJEKT_TYP + ") " +
                " VALUES ('BAGETA', 'PECIVO');");
        db.execSQL("INSERT INTO " + Schema.Objekt.OBJEKT + " ("+ Schema.Objekt.OBJEKT_NAZOV +", " + Schema.Objekt.OBJEKT_TYP + ") " +
                " VALUES ('SLADKY ROZOK', 'PECIVO');");
        db.execSQL("INSERT INTO " + Schema.Objekt.OBJEKT + " ("+ Schema.Objekt.OBJEKT_NAZOV +", " + Schema.Objekt.OBJEKT_TYP + ") " +
                " VALUES ('DONUT', 'PECIVO');");
        db.execSQL("INSERT INTO " + Schema.Objekt.OBJEKT + " ("+ Schema.Objekt.OBJEKT_NAZOV +", " + Schema.Objekt.OBJEKT_TYP + ") " +
                " VALUES ('MUFFIN', 'PECIVO');");
        */
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


    public void pridajObjekt(String nazov, String typ, SQLiteDatabase db)
    {
        db.execSQL("INSERT INTO " + Schema.Objekt.OBJEKT + " ("+ Schema.Objekt.OBJEKT_NAZOV +", " + Schema.Objekt.OBJEKT_TYP + ") " +
                " VALUES ('" + nazov + "', '" + typ + "');");
    }

    public Cursor showPecivo()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NAZOV FROM " + Schema.Objekt.OBJEKT
                + " WHERE " + Schema.Objekt.OBJEKT_TYP + " LIKE 'PECIVO' " , null);
        return res;
    }
    public Cursor showMliecne()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NAZOV FROM " + Schema.Objekt.OBJEKT
                + " WHERE " + Schema.Objekt.OBJEKT_TYP + " LIKE 'MLIECNE_VYROBKY' " , null);
        return res;
    }
    public Cursor showOvocie()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NAZOV FROM " + Schema.Objekt.OBJEKT
                + " WHERE " + Schema.Objekt.OBJEKT_TYP + " LIKE 'OVOCIE' " , null);
        return res;
    }
    public Cursor showZelenina()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NAZOV FROM " + Schema.Objekt.OBJEKT
                + " WHERE " + Schema.Objekt.OBJEKT_TYP + " LIKE 'ZELENINA' " , null);
        return res;
    }
    public Cursor showMaso()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NAZOV FROM " + Schema.Objekt.OBJEKT
                + " WHERE " + Schema.Objekt.OBJEKT_TYP + " LIKE 'MASO' " , null);
        return res;
    }
    public Cursor showSuroviny()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NAZOV FROM " + Schema.Objekt.OBJEKT
                + " WHERE " + Schema.Objekt.OBJEKT_TYP + " LIKE 'SUROVINY' " , null);
        return res;
    }
    public Cursor showSladkosti()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NAZOV FROM " + Schema.Objekt.OBJEKT
                + " WHERE " + Schema.Objekt.OBJEKT_TYP + " LIKE 'SLADKOSTI' " , null);
        return res;
    }
    public Cursor showOstatne()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT NAZOV FROM " + Schema.Objekt.OBJEKT
                + " WHERE " + Schema.Objekt.OBJEKT_TYP + " LIKE 'OSTATNE' " , null);
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
