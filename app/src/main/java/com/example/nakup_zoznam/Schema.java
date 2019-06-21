package com.example.nakup_zoznam;

import android.provider.BaseColumns;

public final class Schema  {
    private Schema(){}

    public static class Zoznam_Nakupov implements BaseColumns{
        public static final String ZOZNAM_NAKUPOV = "Zoznam_nakupov";
        public static final String ZOZNAM_NAKUPOV_ID = "id_zoznamu";
        public static final String ZOZNAM_NAKUPOV_CENA = "cena";
    }

    public static class Nakup implements BaseColumns{
        public static final String NAKUP = "Nakup";
        public static final String NAKUP_ID = "id_nakupu";
        public static final String NAKUP_CENA = "cena";
        public static final String NAKUP_POCET = "pocet_obj";
        public static final String NAKUP_ID_ZOZNAMU = "id_zoznamu";
    }

    public static class Objekt implements BaseColumns{
        public static final String OBJEKT = "Objekt";
        public static final String OBJEKT_ID = "id_objektu";
        public static final String OBJEKT_NAZOV = "nazov";
        public static final String OBJEKT_TYP = "typ";
    }

    public static class Kupeny_Objekt implements BaseColumns{
        public static final String KUPENY_OBJEKT = "Kupeny_Objekt";
        public static final String KUPENY_OBJEKT_ID_NAKUP = "id_nakup";
        public static final String KUPENY_OBJEKT_ID_OBJEKT = "id_objekt";
        public static final String KUPENY_OBJEKT_KVANTITA = "kvantita";
        public static final String KUPENY_OBJEKT_CENA = "cena";
        public static final String KUPENY_OBJEKT_ZAPLATENE = "zaplatene";
    }
}
