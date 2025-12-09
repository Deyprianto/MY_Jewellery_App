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
    

    private DatabaseManager databaseManager;
    
    public JewelryAdapter(List<JewelryItem> jewelryList) {
        this.jewelryList = jewelryList;
        this.databaseManager = DatabaseManager.getInstance();
    }
    
    @Override
    public JewelryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_jewellery, parent, false);
        return new JewelryViewHolder(view);
    }
    
    @Override
    public void onBindViewHolder(JewelryViewHolder holder, int position) {
        JewelryItem item = jewelryList.get(position);
        
  
        JewelryDataAdapter adapter = new JewelryItemAdapter(item);
        
        holder.textName.setText(adapter.getFormattedName());
        holder.textWeight.setText(adapter.getFormattedWeight());
        holder.textPrice.setText(adapter.getFormattedPrice());
        holder.textCategory.setText(adapter.getFormattedCategory());
        
     
        if (item.getDescription() != null && !item.getDescription().isEmpty()) {
            holder.textDescription.setVisibility(View.VISIBLE);
            holder.textDescription.setText("Description: " + item.getDescription());
        } else {
            holder.textDescription.setVisibility(View.GONE);
        }
        
        // Add making charge if available
        if (item.getMakingCharge() != null && !item.getMakingCharge().isEmpty()) {
            holder.textMakingCharge.setVisibility(View.VISIBLE);
            holder.textMakingCharge.setText("Making Charge: ৳" + item.getMakingCharge());
        } else {
            holder.textMakingCharge.setVisibility(View.GONE);
        }
        
        
        setCategoryStyling(holder, item.getCategory());
        
        holder.buttonDelete.setOnClickListener(v -> {
            DeleteJewelryCommand deleteCommand = new DeleteJewelryCommand(
                item.getId(),
                position,
                this,
                databaseManager,
                holder.itemView.getContext()
            );
            deleteCommand.execute();
        });
        
        
        holder.buttonEdit.setOnClickListener(v -> {
          
            Toast.makeText(holder.itemView.getContext(),
                "Editing: " + item.getName(),
                Toast.LENGTH_SHORT).show();
        });
   
        holder.itemView.setOnClickListener(v -> {
            showItemDetails(holder.itemView.getContext(), item);
        });
    }
    
    private void setCategoryStyling(JewelryViewHolder holder, String category) {
     
        switch (category.toLowerCase()) {
            case "chain":
                holder.itemView.setBackgroundResource(R.drawable.bg_chain);
                holder.textCategory.setTextColor(
                    holder.itemView.getContext().getResources().getColor(R.color.chain_color));
                break;
            case "ring":
                holder.itemView.setBackgroundResource(R.drawable.bg_ring);
                holder.textCategory.setTextColor(
                    holder.itemView.getContext().getResources().getColor(R.color.ring_color));
                break;
            case "necklace":
                holder.itemView.setBackgroundResource(R.drawable.bg_necklace);
                holder.textCategory.setTextColor(
                    holder.itemView.getContext().getResources().getColor(R.color.necklace_color));
                break;
            case "bangle":
                holder.itemView.setBackgroundResource(R.drawable.bg_bangle);
                holder.textCategory.setTextColor(
                    holder.itemView.getContext().getResources().getColor(R.color.bangle_color));
                break;
            default:
                holder.itemView.setBackgroundResource(R.drawable.bg_default);
                break;
        }
    }
    
    private void showItemDetails(android.content.Context context, JewelryItem item) {
     
        JewelryDataAdapter adapter = new JewelryItemAdapter(item);
        
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        builder.setTitle("Jewelry Details")
            .setMessage(
                adapter.getFormattedName() + "\n" +
                adapter.getFormattedWeight() + "\n" +
                adapter.getFormattedPrice() + "\n" +
                adapter.getFormattedCategory() + "\n" +
                (item.getDescription() != null ? "Description: " + item.getDescription() + "\n" : "") +
                (item.getMakingCharge() != null ? "Making Charge: ৳" + item.getMakingCharge() : "")
            )
            .setPositiveButton("OK", null)
            .setNegativeButton("Delete", (dialog, which) -> {
          
                DeleteJewelryCommand deleteCommand = new DeleteJewelryCommand(
                    item.getId(),
                    getPositionById(item.getId()),
                    this,
                    databaseManager,
                    context
                );
                deleteCommand.execute();
            })
            .show();
    }
    
    private int getPositionById(String id) {
        for (int i = 0; i < jewelryList.size(); i++) {
            if (jewelryList.get(i).getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }
    
    @Override
    public int getItemCount() {
        return jewelryList.size();
    }
    

    public void removeItem(int position) {
        if (position >= 0 && position < jewelryList.size()) {
            jewelryList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, jewelryList.size() - position);
        }
    }
    

    public void addItem(JewelryItem item) {
        jewelryList.add(item);
        notifyItemInserted(jewelryList.size() - 1);
    }
    

    public void updateList(List<JewelryItem> newList) {
        jewelryList.clear();
        jewelryList.addAll(newList);
        notifyDataSetChanged();
    }
    

    public JewelryItem getItem(int position) {
        return jewelryList.get(position);
    }
    
 
    private static class DeleteJewelryCommand {
        private final String itemId;
        private final int position;
        private final JewelryAdapter adapter;
        private final DatabaseManager databaseManager;
        private final android.content.Context context;
        
        public DeleteJewelryCommand(String itemId, int position, JewelryAdapter adapter,
                                   DatabaseManager databaseManager, android.content.Context context) {
            this.itemId = itemId;
            this.position = position;
            this.adapter = adapter;
            this.databaseManager = databaseManager;
            this.context = context;
        }
        
        public void execute() {
            if (itemId != null) {
              
                new android.app.AlertDialog.Builder(context)
                    .setTitle("Confirm Delete")
                    .setMessage("Are you sure you want to delete this item?")
                    .setPositiveButton("Delete", (dialog, which) -> performDelete())
                    .setNegativeButton("Cancel", null)
                    .show();
            } else {
                Toast.makeText(context, "Item ID is null", Toast.LENGTH_SHORT).show();
            }
        }
        
        private void performDelete() {
       
            databaseManager.deleteJewelryItem(itemId)
                .addOnSuccessListener(aVoid -> {
                 
                    if (position >= 0) {
                        adapter.removeItem(position);
                    }
                    
              
                    Toast.makeText(context, "Item Deleted Successfully", Toast.LENGTH_SHORT).show();
                    
                  
                    android.util.Log.d("JewelryAdapter", "Deleted item with ID: " + itemId);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Failed to delete item: " + e.getMessage(), 
                        Toast.LENGTH_SHORT).show();
                });
        }
    }
    
    class JewelryViewHolder extends RecyclerView.ViewHolder {
        TextView textName, textWeight, textPrice, textCategory;
        TextView textDescription, textMakingCharge;
        Button buttonDelete, buttonEdit;
        
        public JewelryViewHolder(View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textWeight = itemView.findViewById(R.id.textWeight);
            textPrice = itemView.findViewById(R.id.textPrice);
            textCategory = itemView.findViewById(R.id.textCategory);
            textDescription = itemView.findViewById(R.id.textDescription);
            textMakingCharge = itemView.findViewById(R.id.textMakingCharge);
            buttonDelete = itemView.findViewById(R.id.buttonDelete);
            buttonEdit = itemView.findViewById(R.id.buttonEdit);
        }
    }
}
