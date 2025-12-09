package com.example.bdjewellero;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

public class DatabaseManager {
    private static DatabaseManager instance;
    private DatabaseReference databaseReference;
    
    private DatabaseManager() {
        databaseReference = FirebaseDatabase.getInstance().getReference("jewelry_items");
    }
    
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }
}
