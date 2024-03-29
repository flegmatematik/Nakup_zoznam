package com.example.nakup_zoznam;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity {
    public Database mojaDB;
    Button btnViewAll;
    Button btnPridaj;
    public static final String Vyber = "com.example.nakup_zoznam.vyber_objekt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
        if(getSupportActionBar() != null)
            this.getSupportActionBar().hide();
            */
        mojaDB = Database.getInstance(this);
        btnViewAll = (Button)findViewById(R.id.button_viewAll);
        btnPridaj = (Button)findViewById(R.id.button_Pridaj);
        viewAll();
        pridajObjekt();
    }

    public void pridajObjekt()
    {
        btnPridaj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,vyber_objekt.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }
    public void viewAll()
    {
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res =  mojaDB.getNakupnyKosikData();
                if(res.getCount() == 0)
                {
                    //message
                    showMessage("Upozornenie","Vas kosik je prazdny");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext())
                {
                    buffer.append(res.getString(1) + " ");
                    buffer.append(res.getString(2) + " ");
                    buffer.append(res.getString(0) + "\n\n");
                }
                res.close();
                //all data
                showMessage("Váš Nákupný Košík ",buffer.toString());

            }
        });
    }

    public void showMessage(String nazov, String hlaska){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(nazov);
        builder.setMessage(hlaska);
        builder.show();
    }
}
