package com.example.finanzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.List;

public class TransactionsFragment extends Fragment {
    private EditText inputAmount;
    private Spinner inputType;
    private Button submitButton;
    private TextView transactionList;
    private FinanceDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions, container, false);

        inputAmount = view.findViewById(R.id.input_amount);
        inputType = view.findViewById(R.id.input_type);
        submitButton = view.findViewById(R.id.submit_button);
        transactionList = view.findViewById(R.id.transaction_list);

        database = FinanceDatabase.getInstance(requireContext());

        submitButton.setOnClickListener(v -> addTransaction());

        loadTransactions();
        return view;
    }

    private void addTransaction() {
        String amountStr = inputAmount.getText().toString();
        String type = inputType.getSelectedItem().toString();

        if (amountStr.isEmpty()) {
            Toast.makeText(requireContext(), "Enter a valid amount", Toast.LENGTH_SHORT).show();
            return;
        }

        double amount = Double.parseDouble(amountStr);
        Transaction transaction = new Transaction(type.equals("Income") ? "Income" : "Expense", amount);

        database.transactionDao().insert(transaction);
        Toast.makeText(requireContext(), "Transaction Added", Toast.LENGTH_SHORT).show();

        inputAmount.setText("");
        loadTransactions();

        // Notify DashboardFragment to update stats
        if (getActivity() != null) {
            ((MainActivity) getActivity()).updateDashboard();
        }
    }

    private void loadTransactions() {
        List<Transaction> transactions = database.transactionDao().getAllTransactions();
        StringBuilder builder = new StringBuilder();
        for (Transaction t : transactions) {
            builder.append(t.getType()).append(": ₹").append(t.getAmount()).append("\n");
        }
        transactionList.setText(builder.toString());
    }
}
