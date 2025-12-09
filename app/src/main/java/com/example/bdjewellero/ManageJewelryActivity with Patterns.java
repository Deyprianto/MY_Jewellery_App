package com.example.bdjewellero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ManageJewelryActivity extends AppCompatActivity {
    
    private EditText editName, editWeight, editPrice, editCategory, editType;
    private Button btnSave;
    private RecyclerView recyclerView;
    private JewelryAdapter adapter;
    private List<JewelryItem> jewelryList;
    
  
    private DatabaseManager databaseManager;
    private JewelryCollection jewelryCollection;
    private JewelryIterator jewelryIterator;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_jewelry);

        editName = findViewById(R.id.editName);
        editWeight = findViewById(R.id.editWeight);
        editPrice = findViewById(R.id.editPrice);
        editCategory = findViewById(R.id.editCategory);
        editType = findViewById(R.id.editType);
        btnSave = findViewById(R.id.btnSave);
        recyclerView = findViewById(R.id.recyclerViewJewelry);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jewelryList = new ArrayList<>();
        adapter = new JewelryAdapter(jewelryList);
        recyclerView.setAdapter(adapter);

       
        databaseManager = DatabaseManager.getInstance();
        
       
        jewelryCollection = new JewelryCollectionImpl(jewelryList);
        jewelryIterator = jewelryCollection.createIterator();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveJewelry();
            }
        });

        loadJewelry();
        
        
        setupFilterButtons();
    }
    
    private void setupFilterButtons() {
        Button btnFilterChain = findViewById(R.id.btnFilterChain);
        Button btnFilterRing = findViewById(R.id.btnFilterRing);
        Button btnFilterAll = findViewById(R.id.btnFilterAll);
        
        if (btnFilterChain != null) {
            btnFilterChain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    filterByCategory("Chain");
                }
            });
        }
        
        if (btnFilterRing != null) {
            btnFilterRing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    filterByCategory("Ring");
                }
            });
        }
        
        if (btnFilterAll != null) {
            btnFilterAll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadJewelry(); 
            });
        }
    }
    
    private void filterByCategory(String category) {
        if (jewelryIterator instanceof JewelryIteratorImpl) {
            List<JewelryItem> filteredItems = 
                ((JewelryIteratorImpl) jewelryIterator).filterByCategory(category);
            
            adapter.updateList(filteredItems);
            Toast.makeText(this, 
                "Showing " + filteredItems.size() + " " + category + " items", 
                Toast.LENGTH_SHORT).show();
        }
    }

    private void saveJewelry() {
        String name = editName.getText().toString();
        String weight = editWeight.getText().toString();
        String price = editPrice.getText().toString();
        String category = editCategory.getText().toString();
        String type = editType.getText().toString();

        if (!name.isEmpty() && !weight.isEmpty() && !price.isEmpty() 
            && !category.isEmpty() && !type.isEmpty()) {
            
            try {
              
                Jewelry jewelry;
                
                switch (type.toLowerCase()) {
                    case "chain":
                        jewelry = JewelryFactory.createJewelry(
                            "chain", null, name, weight, price, 20.0 
                        );
                        break;
                    case "ring":
                        jewelry = JewelryFactory.createJewelry(
                            "ring", null, name, weight, price, "US-7", false
                        );
                        break;
                    case "necklace":
                        jewelry = JewelryFactory.createJewelry(
                            "necklace", null, name, weight, price
                        );
                        break;
                    case "bangle":
                        jewelry = JewelryFactory.createJewelry(
                            "bangle", null, name, weight, price
                        );
                        break;
                    case "earring":
                        jewelry = JewelryFactory.createJewelry(
                            "earring", null, name, weight, price
                        );
                        break;
                    case "pendant":
                        jewelry = JewelryFactory.createJewelry(
                            "pendant", null, name, weight, price
                        );
                        break;
                    default:
                        jewelry = JewelryFactory.createJewelry(
                            "simple", null, name, weight, price, category
                        );
                }
                
        
                double makingCharge = jewelry.calculateMakingCharge();
                double basePrice = Double.parseDouble(price);
                double totalPrice = basePrice + makingCharge;
                
                
                JewelryItem item = new JewelryItem(
                    jewelry.getId(),
                    jewelry.getName(),
                    jewelry.getWeight(),
                    String.valueOf(totalPrice),
                    jewelry.getCategory()
                );
                
                item.setDescription(jewelry.getDescription());
                item.setMakingCharge(String.valueOf(makingCharge));

           
                databaseManager.saveJewelryItem(item);

               
                editName.setText("");
                editWeight.setText("");
                editPrice.setText("");
                editCategory.setText("");
                editType.setText("");
                
                Toast.makeText(this, 
                    "Jewelry saved successfully!\nMaking Charge: ৳" + makingCharge, 
                    Toast.LENGTH_LONG).show();
                    
            } catch (Exception e) {
                Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void loadJewelry() {
        databaseManager.getDatabaseReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jewelryList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    JewelryItem item = dataSnapshot.getValue(JewelryItem.class);
                    if (item != null) {
                        item.setId(dataSnapshot.getKey());
                        jewelryList.add(item);
                    }
                }
                
         
                jewelryCollection = new JewelryCollectionImpl(jewelryList);
                jewelryIterator = jewelryCollection.createIterator();
                
   
                demonstrateIteratorUsage();
                
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(ManageJewelryActivity.this, 
                    "Failed to load jewelry: " + error.getMessage(), 
                    Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    private void demonstrateIteratorUsage() {

        StringBuilder stats = new StringBuilder();
        stats.append("Collection Statistics:\n");
        
        jewelryIterator.first();
        int count = 0;
        double totalValue = 0;
        
        while (!jewelryIterator.isDone()) {
            JewelryItem item = jewelryIterator.current();
            if (item != null) {
                count++;
                try {
                    totalValue += Double.parseDouble(item.getPrice());
                } catch (NumberFormatException e) {
              
                }
            }
            jewelryIterator.next();
        }
        
        stats.append("Total Items: ").append(count).append("\n");
        stats.append("Total Value: ৳").append(String.format("%.2f", totalValue));
        
    
        TextView statsView = findViewById(R.id.statsTextView);
        if (statsView != null) {
            statsView.setText(stats.toString());
        }
    }
    
    public void updateJewelryList(List<JewelryItem> newList) {
        jewelryList.clear();
        jewelryList.addAll(newList);
        adapter.notifyDataSetChanged();
    }
}
