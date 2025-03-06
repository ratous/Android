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

public class AjoutEchantillonActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
//dans le cas où on clique sur l’item quittter
        if (itemId == R.id.quitter){
            Intent intent = new Intent(AjoutEchantillonActivity.this, MainActivity.class);
            startActivity(intent);
            return true;
        }else if(itemId == R.id.ajout){
//dans le cas où on clique sur l’item ajouter
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Ajout !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AjoutEchantillonActivity.this, AjoutEchantillonActivity.class);
            startActivity(intent);
            return true;
        }else if(itemId == R.id.liste){
//dans le cas où on clique sur l’item liste
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Liste !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AjoutEchantillonActivity.this, listeEchantillonsActivity.class);
            startActivity(intent);
            return true;
        }else if(itemId == R.id.maj){
//dans le cas où on clique sur l’item maj
            Toast.makeText(getApplicationContext(), "ouverture fenêtre Maj !", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(AjoutEchantillonActivity.this, MajEchantillonActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajout_echantillon);

        EditText edtCode = findViewById(R.id.editTextCode);
        EditText edtLibelle = findViewById(R.id.editTextLib);
        EditText edtQuantite = findViewById(R.id.editTextStock);
        Button btnValider = findViewById(R.id.buttonAjouter);
        Button btnQuitter = findViewById(R.id.buttonQuitter);

        btnValider.setOnClickListener(v -> {
            String code = edtCode.getText().toString();
            String libelle = edtLibelle.getText().toString();
            String quantite = edtQuantite.getText().toString();

            if (code.isEmpty() || libelle.isEmpty() || quantite.isEmpty()) {
                Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
                return;
            }

            // Créer un nouvel échantillon
            Echantillon nouvelEchantillon = new Echantillon(code, libelle, quantite);

            // Ouvrir la base de données
            BdAdapter db = new BdAdapter(this);
            db.open();

            // Insérer l'échantillon
            db.insererEchantillon(nouvelEchantillon);

            // Fermer la base de données
            db.close();

            Toast.makeText(this, "Échantillon ajouté", Toast.LENGTH_SHORT).show();
            finish();
        });
        Button buttonQuitter = (Button)findViewById(R.id.buttonQuitter);
        buttonQuitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); //fermeture de la fenêtre
            }
        });
    }
}