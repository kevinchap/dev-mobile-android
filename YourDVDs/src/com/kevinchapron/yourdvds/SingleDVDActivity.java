package com.kevinchapron.yourdvds;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

// Activité single dvd
public class SingleDVDActivity extends Activity {
	
	DVD dvd;
	ListView lvListe;
	ArrayList<DVD> dvds;
	Button buttonDispo;
	Button buttonVu;
	Intent data = new Intent();
	int position;

	// Affichage de la single activity
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_dvd);
        
        lvListe = (ListView) findViewById(R.id.lvListe);
        
        // On récupère le dvd et sa position envoyé par l'activité principale
        position = getIntent().getExtras().getInt("position");
        dvd = (DVD)getIntent().getExtras().get("itemPosition");
        
        // On ajoute les données du dvd à la single activity
        ImageView imgView = (ImageView)findViewById(R.id.dvdAfficheSingle);
        String nameAffiche = dvd.getImage();
	    imgView.setImageResource(YourDVDActivity.getAppContext().getResources().getIdentifier(nameAffiche, "drawable", "com.kevinchapron.yourdvds"));
        TextView dvdTitre = (TextView)findViewById(R.id.dvdTitreSingle);
        dvdTitre.setText(dvd.getTitre());
        TextView dvdAnnee = (TextView)findViewById(R.id.dvdAnneeSingle);
        dvdAnnee.setText(dvd.getAnnee());
        TextView dvdRealisateur = (TextView)findViewById(R.id.dvdRealisateurSingle);
        dvdRealisateur.setText("Réalisé par : "+dvd.getRealisateur());
        TextView dvdActeurs = (TextView)findViewById(R.id.dvdActeursSingle);
        dvdActeurs.setText("Avec "+dvd.getActeurs());
        buttonDispo = (Button)findViewById(R.id.btnDisponibilite);
        buttonDispo.setText(dvd.getDispo());
        if(dvd.getDispo().equals("Disponible")){
        	buttonDispo.setBackgroundColor(getResources().getColor(R.color.vert));
        }else{
        	buttonDispo.setBackgroundColor(getResources().getColor(R.color.rouge));
        }
        buttonVu = (Button)findViewById(R.id.btnVu);
        buttonVu.setText(dvd.getVu());
        if(dvd.getVu().equals("Vu")){
        	buttonVu.setBackgroundColor(getResources().getColor(R.color.vert));
        }else{
        	buttonVu.setBackgroundColor(getResources().getColor(R.color.rouge));
        }
        LinearLayout layout = (LinearLayout)findViewById(R.id.infoFilmSingle);
        layout.getBackground().setAlpha(200);

        // On ajoute des évènements de click au bouton de disponibilité et de visibilité
        addListenerOnButtonDispo();
        addListenerOnButtonVu();
    }
	
	// Méthode click sur le bouton disponibilité
	public void addListenerOnButtonDispo() {
		buttonDispo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Si le bouton est égal à disponible
				if(buttonDispo.getText().equals("Disponible")){
					// alors on met le text du bouton sur non-disponible et on change son background en rouge
					buttonDispo.setText("Non disponible");
					buttonDispo.setBackgroundColor(getResources().getColor(R.color.rouge));
					// on change l'attribut dispo du dvd
					dvd.setDispo("Non disponible");
					// on ajoute la nouvelle disponiblité du dvd à l'intent pour le retour à l'activité principale
					data.putExtra("dispo", "Non disponible");
				}else{
					// sinon on met le text du bouton sur disponible et on change son background en vert
					buttonDispo.setText("Disponible");
					buttonDispo.setBackgroundColor(getResources().getColor(R.color.vert));
					// on change l'attribut vu du dvd
					dvd.setDispo("Disponible");
					// on ajoute la novuelle disponibilité à l'intent pour le retour à l'activité principale
					data.putExtra("dispo", "Disponible");
				}
			}
		});
	}
	
	// Méthode click sur le bouton visibilité
	public void addListenerOnButtonVu() {
		buttonVu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Si le bouton est égal à Vu
				if(buttonVu.getText().equals("Vu")){
					// alors on met le text du bouton sur non-vu et on change son background en rouge
					buttonVu.setText("Non vu");
					buttonVu.setBackgroundColor(getResources().getColor(R.color.rouge));
					// on change l'attribut vu du dvd
					dvd.setVu("Non vu");
					// on ajoute la nouvelle visibilité du dvd à l'intent pour le retour à l'activité principale
					data.putExtra("vu", "Non vu");
				}else{
					// sinon on met le text du bouton sur vu et on change son background en vert
					buttonVu.setText("Vu");
					buttonVu.setBackgroundColor(getResources().getColor(R.color.vert));
					// on change l'attribut vu du dvd
					dvd.setDispo("Vu");
					// on ajoute la nouvelle visibilité du dvd à l'intent pour le retour à l'activité principale
					data.putExtra("vu", "Vu");
				}
			}
		});
	}
	
	// Méthode lorsque l'activité se termine et que l'on retourne sur l'activité principale
	@Override
	public void finish() {
		  // On ajoute aussi à l'intent la position du dvd 
		  data.putExtra("position", position);
		  // On termine l'activité et on revoie l'intent avec les infos
		  setResult(RESULT_OK, data);
		  super.finish();
	} 
}
