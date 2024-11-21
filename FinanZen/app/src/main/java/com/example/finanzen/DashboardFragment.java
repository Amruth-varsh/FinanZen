package com.example.finanzen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class DashboardFragment extends Fragment {
    private TextView incomeText, expenseText, balanceText;
    private com.example.finanzen.FinanceDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        incomeText = view.findViewById(R.id.income_text);
        expenseText = view.findViewById(R.id.expense_text);
        balanceText = view.findViewById(R.id.balance_text);

        database = com.example.finanzen.FinanceDatabase.getInstance(requireContext());
        updateDashboardStats();

        return view;
    }

    void updateDashboardStats() {
        double income = database.transactionDao().getTotalIncome();
        double expenses = database.transactionDao().getTotalExpenses();
        double balance = database.transactionDao().getNetBalance();

        incomeText.setText(String.format("Income: ₹%.2f", income));
        expenseText.setText(String.format("Expenses: ₹%.2f", expenses));
        balanceText.setText(String.format("Net Balance: ₹%.2f", balance));

    }
}