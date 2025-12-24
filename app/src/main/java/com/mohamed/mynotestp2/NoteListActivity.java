package com.mohamed.mynotestp2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class NoteListActivity extends AppCompatActivity {

    public static ArrayList<Note> noteList = new ArrayList<>();
    ListView listView;
    NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_list);

        listView = findViewById(R.id.listViewNotes);
        Button btnAdd = findViewById(R.id.btnGoToAdd);
        Button btnCamera = findViewById(R.id.btnGoToCamera);


        adapter = new NoteAdapter(this, noteList);
        listView.setAdapter(adapter);

        // TO FORM
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NoteListActivity.this, AddNoteActivity.class);
                startActivity(intent);
            }
        });
        
        // TO CAMERA
        btnCamera.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(NoteListActivity.this, CameraActivity.class);
                 startActivity(intent);
             }
        });

        // Click note -> Detail
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note selectedNote = noteList.get(position);
                Intent intent = new Intent(NoteListActivity.this, DetailsNoteActivity.class);
                intent.putExtra("note_key", selectedNote);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }
}
