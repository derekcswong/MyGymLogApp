package com.example.mygymlogapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.recyclerview.widget.RecyclerView;

public class AddExerciseDialog extends AppCompatDialogFragment {
    private String[] exerciseTypes = {"Weights", "Cardio"};
    private String selectedType;
    private String exerciseName;
    private Spinner exerciseTypeSpinner;
    private AddExerciseListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.layout_addex_dialog, null);

        exerciseTypeSpinner = (Spinner) view.findViewById(R.id.exerciseTypeSpinner);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String> (getActivity(), android.R.layout.simple_spinner_item, exerciseTypes);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        exerciseTypeSpinner.setAdapter(arrayAdapter);

        exerciseTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedType = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // do nothing
            }
        });

        builder.setView(view)
                .setTitle("Add Exercise")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                    }})
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText exName = (EditText) view.findViewById(R.id.exerciseName);
                        exerciseName = exName.getText().toString();
                        listener.addExerciseInfo(exerciseName, selectedType);

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddExerciseListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "need to implement AddDialogListener");
        }
    }

    public interface AddExerciseListener {
        void addExerciseInfo(String name, String type);
    }
}
