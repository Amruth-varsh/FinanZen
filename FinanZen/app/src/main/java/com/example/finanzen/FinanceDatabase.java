package com.example.finanzen;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {com.example.finanzen.Transaction.class}, version = 2)
public abstract class FinanceDatabase extends RoomDatabase {
    private static FinanceDatabase instance;

    public abstract TransactionDao transactionDao();

    public static synchronized FinanceDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            FinanceDatabase.class,
                            "finance_database"
                    ).fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}