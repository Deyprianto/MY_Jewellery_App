package com.example.bdjewellero;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class ManageJewelryActivity extends AppCompatActivity {

    private EditText editName, editWeight, editPrice, editCategory;
    private Button btnSave;
    private RecyclerView recyclerView;
    private JewelryAdapter adapter;
    private List<JewelryItem> jewelryList;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_jewelry);

        editName = findViewById(R.id.editName);
        editWeight = findViewById(R.id.editWeight);
        editPrice = findViewById(R.id.editPrice);
        editCategory = findViewById(R.id.editCategory);
        btnSave = findViewById(R.id.btnSave);
        recyclerView = findViewById(R.id.recyclerViewJewelry);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        jewelryList = new ArrayList<>();
        adapter = new JewelryAdapter(jewelryList);
        recyclerView.setAdapter(adapter);

        databaseReference = FirebaseDatabase.getInstance().getReference("jewelry_items");

        btnSave.setOnClickListener(v -> saveJewelry());

        loadJewelry();
    }

    private void saveJewelry() {
        String name = editName.getText().toString();
        String weight = editWeight.getText().toString();
        String price = editPrice.getText().toString();
        String category = editCategory.getText().toString();

        if (!name.isEmpty() && !weight.isEmpty() && !price.isEmpty() && !category.isEmpty()) {
            // Generate a unique ID for the jewelry item
            String id = databaseReference.push().getKey();

            // Create a new JewelryItem object with the ID
            JewelryItem item = new JewelryItem(id, name, weight, price, category);

            // Save the item to Firebase
            databaseReference.child(id).setValue(item);

            // Clear input fields after saving
            editName.setText("");
            editWeight.setText("");
            editPrice.setText("");
            editCategory.setText("");
        }
    }

    private void loadJewelry() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                jewelryList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    // Create a new JewelryItem object from the dataSnapshot
                    JewelryItem item = dataSnapshot.getValue(JewelryItem.class);
                    // Retrieve the ID from the snapshot key and set it
                    if (item != null) {
                        item.setId(dataSnapshot.getKey()); // Set the ID here
                        jewelryList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });
    }
}
