package com.example.gsb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

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
            // Attention : cela recharge simplement MainActivity au lieu de quitter l'application.
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.ajout) {
            // Affiche un message et ouvre l'activité d'ajout d'échantillon
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Ajout !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, AjoutEchantillonActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.liste) {
            // Affiche un message et ouvre l'activité de liste des échantillons
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Liste !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, listeEchantillonsActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.maj) {
            // Affiche un message et ouvre l'activité de mise à jour des échantillons
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Maj !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this, MajEchantillonActivity.class);
            startActivity(intent);
            return true;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupère les références des boutons dans l'interface utilisateur
        Button btnAjouter = findViewById(R.id.buttonAccueil1);
        Button btnListe = findViewById(R.id.buttonAccueil2);
        Button btnMaj = findViewById(R.id.buttonAccueil3);

        // Associe chaque bouton à l'ouverture d'une activité spécifique
        btnAjouter.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AjoutEchantillonActivity.class)));
        btnListe.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, listeEchantillonsActivity.class)));
        btnMaj.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MajEchantillonActivity.class)));

        // Méthode pour insérer des échantillons de test dans la base de données (désactivée)
        // jeuEssaiBd();
    }

    /*
    // Méthode pour insérer des données de test dans la base de données (actuellement commentée)
    public void jeuEssaiBd() {
        // Création d'une instance de la classe BdAdapter
        BdAdapter echantBdd = new BdAdapter(this);
        // On ouvre la base de données pour écrire dedans
        echantBdd.open();
        // Insertion d'échantillons dans la base de données
        echantBdd.insererEchantillon(new Echantillon("code1", "lib1", "3"));
        echantBdd.insererEchantillon(new Echantillon("code2", "lib2", "5"));
        echantBdd.insererEchantillon(new Echantillon("code3", "lib3", "7"));
        echantBdd.insererEchantillon(new Echantillon("code4", "lib4", "6"));

        // Récupération des données et affichage du nombre d'échantillons
        Cursor unCurseur = echantBdd.getData();
        System.out.println("Il y a " + unCurseur.getCount() + " échantillons dans la BD");

        // Fermeture de la connexion à la base de données
        echantBdd.close();
    }
    */
}
