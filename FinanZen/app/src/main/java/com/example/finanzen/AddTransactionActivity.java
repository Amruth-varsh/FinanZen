package com.example.finanzen;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddTransactionActivity extends AppCompatActivity {
    private EditText amountEditText, noteEditText;
    private Spinner categorySpinner, typeSpinner;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        // Initialize UI components
        amountEditText = findViewById(R.id.amount_edit_text);
        noteEditText = findViewById(R.id.note_edit_text);
        categorySpinner = findViewById(R.id.category_spinner);
        typeSpinner = findViewById(R.id.type_spinner);
        saveButton = findViewById(R.id.save_button);

        // Save transaction logic
        saveButton.setOnClickListener(v -> saveTransaction());
    }

    private void saveTransaction() {
        // Implement transaction saving logic
    }
}
