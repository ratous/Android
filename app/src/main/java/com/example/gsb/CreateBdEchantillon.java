package com.example.gsb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Classe pour la création et la gestion de la base de données SQLite
public class CreateBdEchantillon extends SQLiteOpenHelper {

    // Nom de la table
    private static final String TABLE_ECHANT = "echantillons";

    // Noms des colonnes
    static final String COL_ID = "_id"; // Identifiant unique auto-incrémenté
    private static final String COL_CODE = "CODE"; // Code de l'échantillon
    private static final String COL_LIB = "LIB"; // Libellé de l'échantillon
    private static final String COL_STOCK = "STOCK"; // Stock disponible

    // Requête SQL pour la création de la table
    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_ECHANT + "" +
            "("+COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COL_CODE + " TEXT NOT NULL, "
            + COL_LIB + " TEXT NOT NULL, "
            + COL_STOCK + " TEXT NOT NULL);";

    // Constructeur de la classe
    public CreateBdEchantillon(Context context, String name,
                               SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Méthode appelée lors de la création initiale de la base de données
        // Exécute la requête SQL pour créer la table
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Méthode appelée lorsqu'une mise à jour de la base de données est nécessaire
        // Suppression de l'ancienne table et recréation de la nouvelle
        db.execSQL("DROP TABLE " + TABLE_ECHANT + ";");
        onCreate(db);
    }
}
