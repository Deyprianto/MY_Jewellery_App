package com.example.bdjewellero;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private int[] images = {R.drawable.main_img1, R.drawable.main_img2, R.drawable.main_img3};
    private int currentImageIndex = 0;
    private Handler handler = new Handler();

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            imageView.setImageResource(images[currentImageIndex]);
            currentImageIndex = (currentImageIndex + 1) % images.length;
            handler.postDelayed(this, 2000);
        }
    };

    TextView main_textview1, main_textview2, main_textview3, main_textview4;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Views
        imageView = findViewById(R.id.imageView); // Make sure your XML has an ImageView with id="imageView"
        main_textview1 = findViewById(R.id.main_textView1);
        main_textview2 = findViewById(R.id.main_textView2);
        main_textview3 = findViewById(R.id.main_textView3);
        main_textview4 = findViewById(R.id.main_textView4);

        // Start image changing
        handler.post(runnable);

        // Set click listeners
        main_textview1.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, Live_Price.class);
            startActivity(myIntent);
        });

        main_textview2.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, Design.class);
            startActivity(myIntent);
        });

        main_textview3.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, Calculator.class);
            startActivity(myIntent);
        });

        main_textview4.setOnClickListener(v -> {
            Intent myIntent = new Intent(MainActivity.this, ManageJewelryActivity.class);
            startActivity(myIntent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Stop changing images when activity is destroyed
    }
}
