package com.example.finanzen;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionDao {
    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Income'")
    double getTotalIncome();

    @Query("SELECT SUM(amount) FROM transactions WHERE type = 'Expense'")
    double getTotalExpenses();

    @Query("SELECT (SELECT SUM(amount) FROM transactions WHERE type = 'Income') - (SELECT SUM(amount) FROM transactions WHERE type = 'Expense')")
    double getNetBalance();

    @Insert
    void insert(Transaction transaction);

    @Query("SELECT * FROM transactions")
    List<Transaction> getAllTransactions();
}
