package com.example.nakup_zoznam;

import android.app.ActionBar;
import android.app.Activity;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

public class PopUp extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridLayout lay = new GridLayout(this);
        lay.setColumnCount(2);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int sirka = dm.widthPixels;
        int vyska = dm.heightPixels;

        getWindow().setLayout((int) (sirka * 0.8), (int) (vyska * 0.6));


        Cursor cur = Database.getInstance(this).showPecivo();
        if(cur.moveToFirst()) {
            while(!cur.isAfterLast())
            {
                Button but = new Button(this);
                but.setText(cur.getString(0));
                int p = cur.getCount();
                ActionBar.LayoutParams params = new ActionBar.LayoutParams((int)(sirka*0.4), ActionBar.LayoutParams.WRAP_CONTENT);
                but.setLayoutParams(params);
                but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                lay.addView(but);
                cur.moveToNext();
            }
        }
        cur.close();
        lay.setBackgroundColor(Color.rgb(80,120,0));
        setContentView(lay);

    }
}
