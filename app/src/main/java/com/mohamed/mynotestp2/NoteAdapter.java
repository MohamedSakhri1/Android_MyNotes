package com.mohamed.mynotestp2;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Note> {

    public NoteAdapter(Context context, ArrayList<Note> notes) {
        super(context, 0, notes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Recyclage de la vue (pattern ViewHolder simplifié)
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_note, parent, false);
        }

        Note currentNote = getItem(position);

        TextView tvNom = convertView.findViewById(R.id.tvListNom);
        TextView tvDate = convertView.findViewById(R.id.tvListDate);

        if (currentNote != null) {
            tvNom.setText(currentNote.getNom());
            tvDate.setText(currentNote.getDate());

            // Gestion de la couleur selon la priorité
            if (currentNote.getPriorite() != null) {
                switch (currentNote.getPriorite()) {
                    case "Haute":
                        tvNom.setTextColor(Color.RED);
                        break;
                    case "Moyenne":
                        tvNom.setTextColor(Color.rgb(255, 165, 0)); // Orange
                        break;
                    case "Basse":
                    default:
                        tvNom.setTextColor(Color.BLACK);
                }
            }
        }

        return convertView;
    }
}
