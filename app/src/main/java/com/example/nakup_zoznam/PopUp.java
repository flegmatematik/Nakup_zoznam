package com.example.nakup_zoznam;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class PopUp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwin);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int sirka = dm.widthPixels;
        int vyska = dm.heightPixels;

        getWindow().setLayout((int)(sirka*0.8),(int)(vyska*0.6));

        Cursor cur = Database.getInstance(this).showPecivo();
        


    }
}
