package com.mohamed.mynotestp2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri selectedImageUri = null;
    private ImageView ivPreview;
    private Button btnSelectImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        EditText etNom = findViewById(R.id.etNom);
        EditText etDesc = findViewById(R.id.etDesc);
        
        Spinner spinner = findViewById(R.id.spinnerPriority);
        Button btnSave = findViewById(R.id.btnSave);
        
        btnSelectImage = findViewById(R.id.btnSelectImage);
        ivPreview = findViewById(R.id.ivPreview);

        // Configuration du Spinner
        String[] priorities = {"Basse", "Moyenne", "Haute"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorities);
        spinner.setAdapter(adapter);

        // Bouton Image : Ajout ou Détachement
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedImageUri == null) {
                    // Si pas d'image, on ouvre la galerie
                    openGallery();
                } else {
                    // Si image présente, on la détache
                    selectedImageUri = null;
                    ivPreview.setImageURI(null);
                    ivPreview.setVisibility(View.GONE);
                    btnSelectImage.setText("Ajouter une image");
                }
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nom = etNom.getText().toString();
                String desc = etDesc.getText().toString();
                
                // Date automatique avec heure
                String date = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm", java.util.Locale.getDefault()).format(new java.util.Date());
                
                String prio = "Basse";
                if(spinner.getSelectedItem() != null) {
                    prio = spinner.getSelectedItem().toString();
                }

                if (nom.isEmpty()) {
                    Toast.makeText(AddNoteActivity.this, "Le nom est obligatoire !", Toast.LENGTH_SHORT).show();
                } else {
                    // On passe l'URI sous forme de String (ou null)
                    String uriString = (selectedImageUri != null) ? selectedImageUri.toString() : null;
                    Note newNote = new Note(nom, desc, date, prio, uriString);
                    
                    // Ajout à la liste statique de l'écran d'accueil
                    NoteListActivity.noteList.add(newNote);
                    Toast.makeText(AddNoteActivity.this, "Note ajoutée", Toast.LENGTH_SHORT).show();
                    
                    // Fermer l'activité pour revenir à la liste
                    finish();
                }
            }
        });
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            selectedImageUri = data.getData();
            ivPreview.setImageURI(selectedImageUri);
            ivPreview.setVisibility(View.VISIBLE);
            btnSelectImage.setText("Détacher l'image");
        }
    }
}
