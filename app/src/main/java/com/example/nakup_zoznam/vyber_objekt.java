package com.example.nakup_zoznam;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class vyber_objekt extends AppCompatActivity {

    Button pecivo;
    Button mliecne;
    Button maso;
    Button ovocie;
    Button zelenina;
    Button suroviny;
    Button sladkosti;
    Button ostatne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vyber_objekt);
        pecivo = (Button)findViewById(R.id.pecivo_button);
        mliecne = (Button)findViewById(R.id.mliecne_button);
        maso = (Button)findViewById(R.id.maso_button);
        ovocie = (Button)findViewById(R.id.ovocie_button);
        zelenina = (Button)findViewById(R.id.zelenina_button);
        suroviny = (Button)findViewById(R.id.suroviny_button);
        sladkosti = (Button)findViewById(R.id.sladkosti_button);
        ostatne = (Button)findViewById(R.id.ostatne_button);
        vyberKategoriu();

    }

    public void vyberKategoriu()
    {
        pecivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vyber_objekt.this, PopUp.class);
                intent.putExtra("kurzor", "PECIVO");
                startActivity(intent);
            }
        });

        mliecne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vyber_objekt.this, PopUp.class);
                intent.putExtra("kurzor", "MLIECNE");
                startActivity(intent);
            }
        });
        maso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vyber_objekt.this, PopUp.class);
                intent.putExtra("kurzor", "MASO");
                startActivity(intent);
            }
        });
        ovocie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vyber_objekt.this, PopUp.class);
                intent.putExtra("kurzor", "OVOCIE");
                startActivity(intent);
            }
        });
        zelenina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vyber_objekt.this, PopUp.class);
                intent.putExtra("kurzor", "ZELENINA");
                startActivity(intent);
            }
        });
        suroviny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vyber_objekt.this, PopUp.class);
                intent.putExtra("kurzor", "SUROVINA");
                startActivity(intent);
            }
        });
        sladkosti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vyber_objekt.this, PopUp.class);
                intent.putExtra("kurzor", "SLADKOST");
                startActivity(intent);
            }
        });
        ostatne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(vyber_objekt.this, PopUp.class);
                intent.putExtra("kurzor", "OSTATNE");
                startActivity(intent);
            }
        });



    }



}
