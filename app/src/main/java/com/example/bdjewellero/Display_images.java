package com.example.bdjewellero;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;

public class Display_images extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Create a ScrollView with a LinearLayout
        ScrollView scrollView = new ScrollView(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);

        setContentView(scrollView);

        ViewCompat.setOnApplyWindowInsetsListener(linearLayout, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Retrieve image URLs from Intent
        String[] images = getIntent().getStringArrayExtra("image_urls");

        if (images != null) {
            for (String url : images) {
                ImageView imageView = new ImageView(this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,  // width
                        500  // height in pixels
                ));
                imageView.setPadding(10, 10, 10, 10);
                linearLayout.addView(imageView);

                // Load image using Glide
                Glide.with(this).load(url).into(imageView);

            }
        }
    }
}