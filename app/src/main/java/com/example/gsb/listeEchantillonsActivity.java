package com.example.gsb;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class listeEchantillonsActivity extends Activity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Associe le menu défini dans res/menu/menu_main.xml à l'activité
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        // Vérifie quel élément du menu a été sélectionné
        if (itemId == R.id.quitter) {
            // Retour à l'écran principal
            Intent intent = new Intent(listeEchantillonsActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.ajout) {
            // Ouvre l'activité d'ajout d'échantillon
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Ajout !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(listeEchantillonsActivity.this, AjoutEchantillonActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.liste) {
            // Ouvre l'activité de liste (redondant, car déjà sur cette page)
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Liste !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(listeEchantillonsActivity.this, listeEchantillonsActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.maj) {
            // Ouvre l'activité de mise à jour des échantillons
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Maj !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(listeEchantillonsActivity.this, MajEchantillonActivity.class);
            startActivity(intent);
            return true;
        }

        return true;
    }

    private ListView listViewEchant;
    private BdAdapter echantBdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_echantillons);

        // Référence à la ListView qui affichera les échantillons
        listViewEchant = findViewById(R.id.listViewEch);

        // Initialisation de la connexion à la base de données
        echantBdd = new BdAdapter(this);
        echantBdd.open();

        // Récupération des données des échantillons sous forme de curseur
        Cursor leCurseur = echantBdd.getData();

        // Colonnes de la base de données à afficher dans la liste
        String[] colNoms = new String[] {
                BdAdapter.COL_CODE,   // Code de l'échantillon
                BdAdapter.COL_LIB,    // Libellé (nom) de l'échantillon
                BdAdapter.COL_STOCK   // Quantité en stock
        };

        // IDs des TextViews dans list_entree.xml où afficher les données
        int[] colNumeros = new int[] {
                R.id.listeTextViewCode,
                R.id.listeTextViewLib,
                R.id.listeTextViewStock
        };

        // Adaptateur pour afficher les données du curseur dans la ListView
        SimpleCursorAdapter dataAdapter = new SimpleCursorAdapter(
                this,                 // Contexte de l'activité
                R.layout.list_entree, // Layout de chaque ligne dans la liste
                leCurseur,            // Données provenant de la base de données
                colNoms,              // Colonnes à afficher
                colNumeros            // Emplacements où afficher les données
        );

        // Associe l'adaptateur à la ListView
        listViewEchant.setAdapter(dataAdapter);

        // Ferme la connexion à la base de données après avoir récupéré les données
        echantBdd.close();

        // Gestion du bouton "Quitter"
        Button buttonQuitter = findViewById(R.id.buttonQuitter);
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Ferme l'activité et retourne à l'écran précédent
            }
        });
    }
}
