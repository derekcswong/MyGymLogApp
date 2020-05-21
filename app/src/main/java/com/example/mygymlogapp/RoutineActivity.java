package com.example.mygymlogapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.util.ArrayList;

public class RoutineActivity extends AppCompatActivity implements AddExerciseDialog.AddExerciseListener {

    DatabaseHelper myDb;
    private String routineName;
    private Button addExerciseBTN;
    private RecyclerView recyclerView;
    ArrayList<String> exerciseNames = new ArrayList<>();
    ArrayList<String> typeDescriptions = new ArrayList<>();
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine);

        myDb = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.ExerciseList);

        adapter = new RecyclerViewAdapter(this, exerciseNames, typeDescriptions);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        addExerciseBTN = (Button) findViewById(R.id.addExerciseBTN);
        addExerciseBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddExerciseDialog();
            }
        });

        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addRoutineToDatabase();
                finish();
            }
        });
    }

    // create exerciseList of Strings and serialize using gson
    private void addRoutineToDatabase() {
        EditText routine = (EditText) findViewById(R.id.routineName);
        routineName = routine.getText().toString();
        Gson gson = new Gson();
        String exerciseList = gson.toJson(exerciseNames);
        String typeList = gson.toJson(typeDescriptions);

        boolean isInserted = myDb.insertData(routineName, exerciseList, typeList);
        if (isInserted) {
            Toast.makeText(RoutineActivity.this, "Routine Recorded", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(RoutineActivity.this, "Routine Not Recorded", Toast.LENGTH_LONG).show();
        }
    }

    public void openAddExerciseDialog() {
        AddExerciseDialog addExerciseDialog = new AddExerciseDialog();
        addExerciseDialog.show(getSupportFragmentManager(), "Add Exercise Dialog");
    }

    // exercise name and type from dialog, update recycler view
    @Override
    public void addExerciseInfo(String name, String type) {
        exerciseNames.add(name);
        typeDescriptions.add(type);
        adapter.notifyDataSetChanged();
    }
}
