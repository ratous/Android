package com.example.gsb;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MajEchantillonActivity extends AppCompatActivity {

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
            // Redirige vers l'activité principale
            Intent intent = new Intent(MajEchantillonActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.ajout) {
            // Affiche un message et ouvre l'activité d'ajout d'échantillon
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Ajout !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MajEchantillonActivity.this, AjoutEchantillonActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.liste) {
            // Affiche un message et ouvre l'activité de liste des échantillons
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Liste !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MajEchantillonActivity.this, listeEchantillonsActivity.class);
            startActivity(intent);
            return true;
        } else if (itemId == R.id.maj) {
            // Affiche un message et recharge l'activité actuelle
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Maj !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MajEchantillonActivity.this, MajEchantillonActivity.class);
            startActivity(intent);
            return true;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maj_echantillon);

        // Récupère les références des éléments de l'interface utilisateur
        EditText edtCode = findViewById(R.id.editTextCode);
        EditText edtLibelle = findViewById(R.id.editTextLibelle);
        EditText edtQuantite = findViewById(R.id.editTextQuant);
        Button btnSupprimer = findViewById(R.id.buttonSupprimer);
        Button btnAjouter = findViewById(R.id.buttonAjout);
        Button btnQuitter = findViewById(R.id.buttonQuitter);

        // Gestion du clic sur le bouton "Supprimer"
        btnSupprimer.setOnClickListener(v -> {
            String code = edtCode.getText().toString();

            if (code.isEmpty()) {
                // Affiche un message si aucun code n'a été saisi
                Toast.makeText(this, "Veuillez saisir un code", Toast.LENGTH_SHORT).show();
                return;
            }

            // Ouvre la connexion à la base de données
            BdAdapter db = new BdAdapter(this);
            db.open();

            // Supprime l'échantillon correspondant au code saisi
            db.removeEchantillonWithCode(code);

            // Ferme la connexion à la base de données
            db.close();

            // Affiche un message de confirmation
            Toast.makeText(this, "Échantillon supprimé", Toast.LENGTH_SHORT).show();
        });

        // Gestion du clic sur le bouton "Ajouter"
        btnAjouter.setOnClickListener(v -> {
            String code = edtCode.getText().toString();
            String libelle = edtLibelle.getText().toString();
            String quantite = edtQuantite.getText().toString();

            if (code.isEmpty() || libelle.isEmpty() || quantite.isEmpty()) {
                // Vérifie que tous les champs sont remplis
                Toast.makeText(this, "Veuillez saisir un code et une quantité", Toast.LENGTH_SHORT).show();
                return;
            }

            // Crée un nouvel objet Echantillon avec les valeurs saisies
            Echantillon echantillon = new Echantillon(code, libelle, quantite);

            // Ouvre la connexion à la base de données
            BdAdapter db = new BdAdapter(this);
            db.open();

            // Met à jour l'échantillon existant dans la base de données
            db.updateEchantillon(code, libelle, echantillon);

            // Ferme la connexion à la base de données
            db.close();

            // Affiche un message de confirmation
            Toast.makeText(this, "Stock mis à jour", Toast.LENGTH_SHORT).show();
        });

        // Gestion du clic sur le bouton "Quitter"
        Button buttonQuitter = findViewById(R.id.buttonQuitter);
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Ferme l'activité en cours
            }
        });
    }
}
