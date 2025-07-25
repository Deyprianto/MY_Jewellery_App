package com.example.bdjewellero;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class JewelryAdapter extends RecyclerView.Adapter<JewelryAdapter.JewelryViewHolder> {

    private List<JewelryItem> jewelryList;
    private DatabaseReference mDatabase;

    public JewelryAdapter(List<JewelryItem> jewelryList) {
        this.jewelryList = jewelryList;
        mDatabase = FirebaseDatabase.getInstance().getReference("jewelry_items");
    }

    @Override
    public JewelryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_jewellery, parent, false);
        return new JewelryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(JewelryViewHolder holder, int position) {
        JewelryItem item = jewelryList.get(position);
        holder.textName.setText(item.getName());
        holder.textWeight.setText("Weight: " + item.getWeight() + " g");
        holder.textPrice.setText("Price: ৳" + item.getPrice());
        holder.textCategory.setText("Category: " + item.getCategory());

        // Handle the delete button click
        holder.buttonDelete.setOnClickListener(v -> {
            // Ensure that the item has an ID before trying to delete it
            if (item.getId() != null) {
                // Remove from the list (RecyclerView)
                removeItem(position);

                // Remove from Firebase
                mDatabase.child(item.getId()).removeValue()
                        .addOnSuccessListener(aVoid -> {
                            // Show a confirmation Toast
                            Toast.makeText(holder.itemView.getContext(), "Item Deleted", Toast.LENGTH_SHORT).show();
                        })
                        .addOnFailureListener(e -> {
                            // Handle failure in deleting from Firebase
                            Toast.makeText(holder.itemView.getContext(), "Failed to delete item", Toast.LENGTH_SHORT).show();
                        });
            } else {
                // Handle case where ID is null (just in case)
                Toast.makeText(holder.itemView.getContext(), "Item ID is null", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return jewelryList.size();
    }

    // Remove item from the list (RecyclerView)
    private void removeItem(int position) {
        jewelryList.remove(position);
        notifyItemRemoved(position);
    }

    class JewelryViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textWeight, textPrice, textCategory;
        Button buttonDelete;

        public JewelryViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textWeight = itemView.findViewById(R.id.textWeight);
            textPrice = itemView.findViewById(R.id.textPrice);
            textCategory = itemView.findViewById(R.id.textCategory);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }
    }
}
