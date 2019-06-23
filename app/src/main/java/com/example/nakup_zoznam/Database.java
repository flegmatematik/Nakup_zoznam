package com.example.nakup_zoznam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.transition.Scene;

import java.io.File;

public class Database extends SQLiteOpenHelper {
    private  static Database instance;
    private SQLiteDatabase db;
    private int cislo_nakupu = 0;

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
    public void onCreate(SQLiteDatabase database) {
        db = database;

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
                + Schema.Kupeny_Objekt.KUPENY_OBJEKT_KVANTITA + " REAL, "
                + Schema.Kupeny_Objekt.KUPENY_OBJEKT_JEDNOTKA + " TEXT, "
                + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ZAPLATENE + " TEXT, "
                + " FOREIGN KEY (" + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ID_OBJEKT + ") REFERENCES " + Schema.Objekt.OBJEKT + "(" + Schema.Objekt.OBJEKT_ID + "),"
                + " FOREIGN KEY (" + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ID_NAKUP + ") REFERENCES " + Schema.Nakup.NAKUP + "(" + Schema.Nakup.NAKUP_ID + "))");


        pridajObjekt("BUCHTA", "PECIVO");
        pridajObjekt("ROŽOK","PECIVO");
        pridajObjekt("CHLEBA","PECIVO");
        pridajObjekt("BAGETA","PECIVO");
        pridajObjekt("SLADKÝ ROŽOK","PECIVO");
        pridajObjekt("DONUT","PECIVO");
        pridajObjekt("MLIEKO","MLIECNE");
        pridajObjekt("MASLO","MLIECNE");
        pridajObjekt("SYR","MLIECNE");
        pridajObjekt("NÁTIERKA","MLIECNE");
        pridajObjekt("JOGURT","MLIECNE");
        pridajObjekt("SYROKRÉM","MLIECNE");
        pridajObjekt("TVAROH","MLIECNE");
        pridajObjekt("SMOTANA","MLIECNE");
        pridajObjekt("KOND. MLIEKO","MLIECNE");
        pridajObjekt("GRECKY JOGURT", "MLIECNE");
        pridajObjekt("ZMRZLINA","MLIECNE");
        pridajObjekt("ŽINČICA", "MLIECNE");
        pridajObjekt("ŽINČICA", "MLIECNE");
        pridajObjekt("ŽINČICA", "MLIECNE");
        pridajObjekt("ŽINČICA", "MLIECNE");
        pridajObjekt("JABLKÁ","OVOCIE");
        pridajObjekt("HRUŠKY","OVOCIE");
        pridajObjekt("JAHODY","OVOCIE");
        pridajObjekt("POMARANČE","OVOCIE");
        pridajObjekt("MANDARÍNKY","OVOCIE");
        pridajObjekt("MELÓN","OVOCIE");
        pridajObjekt("BANÁNY","OVOCIE");

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


    public void pridajObjekt(String nazov, String typ)
    {
        db.execSQL("INSERT INTO " + Schema.Objekt.OBJEKT + " ("+ Schema.Objekt.OBJEKT_NAZOV +", " + Schema.Objekt.OBJEKT_TYP + ") " +
                " VALUES ('" + nazov + "', '" + typ + "');");
    }

    public Cursor showTyp(String typObjektu)
    {
        db = getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT " + Schema.Objekt.OBJEKT_NAZOV + ", " + Schema.Objekt.OBJEKT_ID + " FROM " + Schema.Objekt.OBJEKT
                + " WHERE " + Schema.Objekt.OBJEKT_TYP + " LIKE '"+ typObjektu  +"' " , null);
        return cur;
    }

    public void kupObjekt(int id_objektu, double kvantita,  String jednotka ){

        db.execSQL("INSERT INTO " + Schema.Kupeny_Objekt.KUPENY_OBJEKT + " (" + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ID_OBJEKT + ", "
                + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ID_NAKUP + ", " + Schema.Kupeny_Objekt.KUPENY_OBJEKT_KVANTITA+ ", "
                + Schema.Kupeny_Objekt.KUPENY_OBJEKT_JEDNOTKA + ", " + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ZAPLATENE + ") "
                + " VALUES (" + id_objektu + ", " + cislo_nakupu + ", " + kvantita + ", '" + jednotka + "', 'NIE');");
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

    public Cursor getNakupnyKosikData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT " + Schema.Objekt.OBJEKT_NAZOV + ", " + Schema.Kupeny_Objekt.KUPENY_OBJEKT_KVANTITA + ", " + Schema.Kupeny_Objekt.KUPENY_OBJEKT_JEDNOTKA
                + "  FROM " + Schema.Kupeny_Objekt.KUPENY_OBJEKT + " INNER JOIN " + Schema.Objekt.OBJEKT
                + " ON " + Schema.Objekt.OBJEKT + "." + Schema.Objekt.OBJEKT_ID + " = " + Schema.Kupeny_Objekt.KUPENY_OBJEKT + "." + Schema.Kupeny_Objekt.KUPENY_OBJEKT_ID_OBJEKT + ";", null);
        return res;
    }

    public Cursor getKupeny_objektData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Schema.Kupeny_Objekt.KUPENY_OBJEKT , null);
        return res;
    }


}
