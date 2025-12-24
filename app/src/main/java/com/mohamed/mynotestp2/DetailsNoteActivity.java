package com.mohamed.mynotestp2;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailsNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_note);

        TextView tvNom = findViewById(R.id.tvDetailNom);
        TextView tvDesc = findViewById(R.id.tvDetailDesc);
        TextView tvDate = findViewById(R.id.tvDetailDate);
        TextView tvPrio = findViewById(R.id.tvDetailPrio);
        ImageView ivDetailImage = findViewById(R.id.ivDetailImage);
        Button btnBack = findViewById(R.id.btnBack);


        Note note = (Note) getIntent().getSerializableExtra("note_key");

        if (note != null) {
            tvNom.setText(note.getNom());
            tvDesc.setText(note.getDescription());
            tvDate.setText("Date: " + note.getDate());
            tvPrio.setText("Priorité: " + note.getPriorite());
            
            // Affichage de l'image si présente
            if (note.getImageUri() != null && !note.getImageUri().isEmpty()) {
                ivDetailImage.setVisibility(View.VISIBLE);
                ivDetailImage.setImageURI(Uri.parse(note.getImageUri()));
            } else {
                ivDetailImage.setVisibility(View.GONE);
            }
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
