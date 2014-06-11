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

// Activit� single dvd
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
        
        // On r�cup�re le dvd et sa position envoy� par l'activit� principale
        position = getIntent().getExtras().getInt("position");
        dvd = (DVD)getIntent().getExtras().get("itemPosition");
        
        // On ajoute les donn�es du dvd � la single activity
        ImageView imgView = (ImageView)findViewById(R.id.dvdAfficheSingle);
        String nameAffiche = dvd.getImage();
	    imgView.setImageResource(YourDVDActivity.getAppContext().getResources().getIdentifier(nameAffiche, "drawable", "com.kevinchapron.yourdvds"));
        TextView dvdTitre = (TextView)findViewById(R.id.dvdTitreSingle);
        dvdTitre.setText(dvd.getTitre());
        TextView dvdAnnee = (TextView)findViewById(R.id.dvdAnneeSingle);
        dvdAnnee.setText(dvd.getAnnee());
        TextView dvdRealisateur = (TextView)findViewById(R.id.dvdRealisateurSingle);
        dvdRealisateur.setText("R�alis� par : "+dvd.getRealisateur());
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

        // On ajoute des �v�nements de click au bouton de disponibilit� et de visibilit�
        addListenerOnButtonDispo();
        addListenerOnButtonVu();
    }
	
	// M�thode click sur le bouton disponibilit�
	public void addListenerOnButtonDispo() {
		buttonDispo.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Si le bouton est �gal � disponible
				if(buttonDispo.getText().equals("Disponible")){
					// alors on met le text du bouton sur non-disponible et on change son background en rouge
					buttonDispo.setText("Non disponible");
					buttonDispo.setBackgroundColor(getResources().getColor(R.color.rouge));
					// on change l'attribut dispo du dvd
					dvd.setDispo("Non disponible");
					// on ajoute la nouvelle disponiblit� du dvd � l'intent pour le retour � l'activit� principale
					data.putExtra("dispo", "Non disponible");
				}else{
					// sinon on met le text du bouton sur disponible et on change son background en vert
					buttonDispo.setText("Disponible");
					buttonDispo.setBackgroundColor(getResources().getColor(R.color.vert));
					// on change l'attribut vu du dvd
					dvd.setDispo("Disponible");
					// on ajoute la novuelle disponibilit� � l'intent pour le retour � l'activit� principale
					data.putExtra("dispo", "Disponible");
				}
			}
		});
	}
	
	// M�thode click sur le bouton visibilit�
	public void addListenerOnButtonVu() {
		buttonVu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// Si le bouton est �gal � Vu
				if(buttonVu.getText().equals("Vu")){
					// alors on met le text du bouton sur non-vu et on change son background en rouge
					buttonVu.setText("Non vu");
					buttonVu.setBackgroundColor(getResources().getColor(R.color.rouge));
					// on change l'attribut vu du dvd
					dvd.setVu("Non vu");
					// on ajoute la nouvelle visibilit� du dvd � l'intent pour le retour � l'activit� principale
					data.putExtra("vu", "Non vu");
				}else{
					// sinon on met le text du bouton sur vu et on change son background en vert
					buttonVu.setText("Vu");
					buttonVu.setBackgroundColor(getResources().getColor(R.color.vert));
					// on change l'attribut vu du dvd
					dvd.setDispo("Vu");
					// on ajoute la nouvelle visibilit� du dvd � l'intent pour le retour � l'activit� principale
					data.putExtra("vu", "Vu");
				}
			}
		});
	}
	
	// M�thode lorsque l'activit� se termine et que l'on retourne sur l'activit� principale
	@Override
	public void finish() {
		  // On ajoute aussi � l'intent la position du dvd 
		  data.putExtra("position", position);
		  // On termine l'activit� et on revoie l'intent avec les infos
		  setResult(RESULT_OK, data);
		  super.finish();
	} 
}
