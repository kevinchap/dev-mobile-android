package com.kevinchapron.yourdvds;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

// Activité principale
public class YourDVDActivity extends Activity implements AdapterView.OnItemClickListener{
	
	private static Context context;
	ArrayList<DVD> yourDVDs = new ArrayList<DVD>();
	DVDAdapter adapter;
	ListView lvListe;
	String dispo;
	int REQUEST_CODE = 1;
	Button buttonAdd;

	// Affichage de l'acitivé principale
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_dvd);
        
        // On ajoute nos dvds
        RemplirYourDVDs();
	    
        // On récupère le context de notre application
        YourDVDActivity.context = getApplicationContext();
        
        // On affiche notre list view de dvd
        goListView();  
        
        // On rend cliquable chaque item de notre liste
        lvListe.setOnItemClickListener(this);
        
        // On rend cliquable notre bouton d'ajout de DVD
        buttonAdd = (Button) findViewById(R.id.buttonAddDVD);
        addListenerOnButtonAdd();
    }
    
    // Méthode au clic sur le bouton ajouté
    public void addListenerOnButtonAdd() {
		buttonAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// On va sur notre activité d'ajout de dvd en demandant un résultat en retour
				Intent intent = new Intent(YourDVDActivity.this, AddDVDActivity.class);
		        startActivityForResult(intent, REQUEST_CODE);
			}
		});
	}
    
    // Méthode pour afficher la list view de dvd
    public void goListView(){
    	// On affiche notre liste view en fonction de notre adapter (DVD Adapter)
    	lvListe = (ListView) findViewById(R.id.lvListe);
        adapter = new DVDAdapter(this, yourDVDs);
        lvListe.setAdapter(adapter);  
    }
    
    // Methode pour remplir au départ nos dvds
    private void RemplirYourDVDs() {
    	yourDVDs.clear();
    	yourDVDs.add(new DVD("Shrek", "2001", "Andrew Adamson", "shrek", "Disponible", "Vu", "Mike Myers", "Eddie Murphy"));
    	yourDVDs.add(new DVD("Hanckok", "2008", "Peter Berg", "hanckock", "Non disponible", "Vu", "Will Smith", "Charlize Theron"));
    	yourDVDs.add(new DVD("Fatal", "2009", "Mickaël Youn", "fatal", "Disponible", "Non vu", "Mickaël Youn", "Vincent Desagna"));	
    	yourDVDs.add(new DVD("Avatar", "2009", "James Cameron", "avatar", "Non disponible", "Non vu", "Sam Worthington", "Zoe Saldana"));	
    	yourDVDs.add(new DVD("Intouchables", "2011", "Eric Toledano", "intouchable", "Disponible", "Non vu", "François Cluzet", "Omar Sy"));	
    	yourDVDs.add(new DVD("Le soliste", "2009", "Joe Wright", "lesoliste", "Disponible", "Vu", "Jamie Foxx", "Robert Downey Jr."));
    	yourDVDs.add(new DVD("Safari", "2009", "Olivier Baroux", "safari", "Non disponible", "Vu", "Kad Merad", "Lionel Abelanski"));
    	yourDVDs.add(new DVD("The wrestler", "2008", "Darren Aronofsky", "thewrestler", "Non disponible", "Non vu", "Mickey Rourke", "Marisa Tomei"));
    	yourDVDs.add(new DVD("Thor", "2011", "Kenneth Branagh", "thor", "Disponible", "Vu", "Chris Hemsworth", "Natalie Portman"));
    	yourDVDs.add(new DVD("Time out", "2011", "Andrew Niccol", "timeout", "Disponible", "Vu", "Amanda Seyfried", "Justin Timberlake"));	
    	yourDVDs.add(new DVD("Wolfman", "2010", "Joe Johnston", "wolfman", "Non disponible", "Non vu", "Benicio Del Toro", "Anthony Hopkins"));
    }
    
    // Méthode pour récupérer le context de l'application
    public static Context getAppContext() {
        return YourDVDActivity.context;
    }
    
    // Méthode pour le click sur un item de la list view
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    	// On récupère le dvd de l'item cliqué
    	DVD item = yourDVDs.get(position);
    	// On ajout notre dvd et sa position à l'intent pour l'envoyer à l'acitivé single
        Intent intent = new Intent(YourDVDActivity.this, SingleDVDActivity.class);
        intent.putExtra("itemPosition",item);
        intent.putExtra("position", position);
        // On démarre l'activité single en demandant des informations en retour
        startActivityForResult(intent, REQUEST_CODE);
    }
    
    // Méthode pour récupérer les informations au retour sur l'activité principale
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
    	// récupération des données à propos de la disponibilité d'un dvd si il y en a en retour de l'activité single dvd
        if (data.hasExtra("dispo") && data.hasExtra("position")) {
        	yourDVDs.get(data.getExtras().getInt("position")).setDispo((String)data.getExtras().getString("dispo"));
        	goListView();
        }
        // récupération des données à propos de la visibilité d'un dvd si il y en a en retour de l'activité single dvd
        if (data.hasExtra("vu") && data.hasExtra("position")) {
        	// DVD dvd = yourDVDs.get(data.getExtras().getInt("position"));
        	// dvd.setDispo(data.getExtras().getString("dispo"));
        	yourDVDs.get(data.getExtras().getInt("position")).setVu((String)data.getExtras().getString("vu"));
        	goListView();
        }
        // récupération des données à propos d'un dvd en vu de son ajout au retour de l'acitivté d'ajout de dvd
        if (data.hasExtra("titre") && data.hasExtra("annee") && data.hasExtra("realisateur") && data.hasExtra("acteur1") && data.hasExtra("acteur2")) {
        	yourDVDs.add(new DVD((String)data.getExtras().getString("titre"), (String)data.getExtras().getString("annee"), (String)data.getExtras().getString("realisateur"), "imagedefaut", "Disponible", "Non vu", (String)data.getExtras().getString("acteur1"), (String)data.getExtras().getString("acteur2")));
        	goListView();
        }
      }
    } 

}
