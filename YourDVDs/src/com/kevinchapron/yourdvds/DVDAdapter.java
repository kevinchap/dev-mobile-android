package com.kevinchapron.yourdvds;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

// Création de l'adapter pour la list view des dvds
public class DVDAdapter extends BaseAdapter {
	
	List<DVD> biblio;
	LayoutInflater inflater;

	// Constructeur de l'adapter
	public DVDAdapter(Context context,List<DVD> biblio) {
	    inflater = LayoutInflater.from(context);
	    this.biblio = new ArrayList<DVD>();
        this.biblio.addAll(biblio);
	}

	// getter du nombre d'élément de la liste de dvd
	@Override
	public int getCount() {
		return biblio.size();
	}

	// getter de l'item en fonction de la position du dvd dans la liste
	@Override
	public Object getItem(int position) {
		return biblio.get(position);
	}

	// getter pour la position de l'item
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	// préparation de la vue
	private class ViewHolder {
	    TextView dvdTitre;
	    TextView dvdAnnee;
	    TextView dvdRealisateur;
	    ImageView dvdAffiche;
	    ImageView iconDispo;
	    ImageView iconVu;
	}

	// getter pour la vu de l'item
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		String nameAffiche;
		String dispo;
		String vu;
		
		// On accède aux éléments de données et met une vue pour chaque élément dans l'ensemble des données.
		 
	    if(convertView == null) {
	        holder = new ViewHolder();
	        convertView = inflater.inflate(R.layout.itemdvd, null);
	        holder.dvdTitre = (TextView)convertView.findViewById(R.id.dvdTitre);
	        holder.dvdAnnee = (TextView)convertView.findViewById(R.id.dvdAnnee);
	        holder.dvdRealisateur = (TextView)convertView.findViewById(R.id.dvdRealisateur);
	        holder.dvdAffiche = (ImageView)convertView.findViewById(R.id.dvdAffiche);
	        holder.iconDispo = (ImageView)convertView.findViewById(R.id.iconDispo);
	        holder.iconVu = (ImageView)convertView.findViewById(R.id.iconVu);
	        convertView.setTag(holder);
	    } else {
	        holder = (ViewHolder) convertView.getTag();
	    }
	    nameAffiche = biblio.get(position).getImage();
	    holder.dvdAffiche.setImageResource(YourDVDActivity.getAppContext().getResources().getIdentifier(nameAffiche, "drawable", "com.kevinchapron.yourdvds"));
		dispo = String.valueOf(biblio.get(position).getDispo());
		Log.v("test adapter get dispo", dispo);
		Log.v("test adapter get dispo", "Disponible");
		Log.v("test condition adapter", Boolean.toString(dispo.equals("Disponible")));
		if(dispo.equals("Disponible")){
		    holder.iconDispo.setImageResource(YourDVDActivity.getAppContext().getResources().getIdentifier("dispo", "drawable", "com.kevinchapron.yourdvds"));
		}else if(dispo.equals("Non disponible")){
			holder.iconDispo.setImageResource(YourDVDActivity.getAppContext().getResources().getIdentifier("disponon", "drawable", "com.kevinchapron.yourdvds"));
		}else{
			holder.iconDispo.setImageResource(YourDVDActivity.getAppContext().getResources().getIdentifier("shrek", "drawable", "com.kevinchapron.yourdvds"));
		}
		vu = biblio.get(position).getVu();
		if(vu=="Vu"){
		    holder.iconVu.setImageResource(YourDVDActivity.getAppContext().getResources().getIdentifier("vu", "drawable", "com.kevinchapron.yourdvds"));
		}else{
			holder.iconVu.setImageResource(YourDVDActivity.getAppContext().getResources().getIdentifier("vunon", "drawable", "com.kevinchapron.yourdvds"));
		}
	    holder.dvdTitre.setText(biblio.get(position).getTitre());
	    holder.dvdAnnee.setText(biblio.get(position).getAnnee());
	    holder.dvdRealisateur.setText(biblio.get(position).getRealisateur()); 
	    return convertView;
	}

}
