package com.example.bdjewellero;

//Singletone By Anik
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseManager {
    private static DatabaseManager instance;
    private final DatabaseReference databaseReference;
    
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
    
    public void saveJewelryItem(JewelryItem item) {
        if (item.getId() == null) {
            String id = databaseReference.push().getKey();
            item.setId(id);
        }
        databaseReference.child(item.getId()).setValue(item);
    }
    
    public void deleteJewelryItem(String itemId) {
        databaseReference.child(itemId).removeValue();
    }
}
