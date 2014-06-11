package com.kevinchapron.yourdvds;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDVDActivity extends Activity {
	
	Button buttonAdd;
	Intent data = new Intent();
	EditText titreFilm;
	EditText anneeFilm;
	EditText realisateurFilm;
	EditText acteur1;
	EditText acteur2;

	// Affichage de l'activité d'ajout du dvd
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity_dvd);
        
        // Récupération des champs text et du bouton ajouter
        buttonAdd = (Button)findViewById(R.id.validerAdd);
        titreFilm = (EditText) findViewById(R.id.editTitleFilm);
        anneeFilm = (EditText) findViewById(R.id.editAnneeFilm);
        realisateurFilm = (EditText) findViewById(R.id.editRealisateurFilm);
        acteur1 = (EditText) findViewById(R.id.editPremierActeurFilm);
        acteur2 = (EditText) findViewById(R.id.editDeuxiemeActeurFilm);
        
        // Ajout d'un évènement de clique sur le bouton ajouter
        addListenerOnButtonAdd(); 
    }
	
	// Méthode pour le clique sur le bouton ajouter
	public void addListenerOnButtonAdd() {	 
		buttonAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Si les champs n'ont pas été remplit
				if(titreFilm.getText().toString().equals("") && anneeFilm.getText().toString().equals("") && realisateurFilm.getText().toString().equals("") && acteur1.getText().toString().equals("") && acteur2.getText().toString().equals("")){
					// alors on affiche un message pour leur demander de les remplir
					Toast toast = Toast.makeText(getApplicationContext(), "Remplissez les champs", 1000);
					toast.show();
				}else{
					// sinon on ajoute les infos relative au nouveau dvd à l'intent
					data.putExtra("titre", (String)titreFilm.getText().toString());
					data.putExtra("annee", (String)anneeFilm.getText().toString());
					data.putExtra("realisateur", (String)realisateurFilm.getText().toString());
					data.putExtra("acteur1", (String)acteur1.getText().toString());
					data.putExtra("acteur2", (String)acteur2.getText().toString());
					
					// puis on lance la méthode pour terminer cette activité
					finish();
				}
			}
 
		});
		
 
	}
	
	// Méthode pour terminer l'activité
	@Override
	public void finish() {
		  // On termine l'activité et on retoune les nouvelles informations
		  setResult(RESULT_OK, data);
		  super.finish();
	} 
	

}
