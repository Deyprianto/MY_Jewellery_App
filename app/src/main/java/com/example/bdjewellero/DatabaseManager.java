package com.example.bdjewellero;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseManager {
    private static DatabaseManager instance;
    private FirebaseDatabase database;
    private DatabaseReference jewelryReference;
    
    private DatabaseManager() {
        database = FirebaseDatabase.getInstance();
        jewelryReference = database.getReference("jewelry_items");
    }
    
    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }
    
    public DatabaseReference getJewelryReference() {
        return jewelryReference;
    }
    
    public DatabaseReference getReference(String path) {
        return database.getReference(path);
    }
}
