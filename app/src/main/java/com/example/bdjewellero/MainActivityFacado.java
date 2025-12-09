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
    
    // Using Singleton for image cycling
    private static class ImageCycler {
        private static ImageCycler instance;
        private Handler handler = new Handler();
        private int currentImageIndex = 0;
        private int[] images;
        private ImageView imageView;
        
        private ImageCycler() {}
        
        public static synchronized ImageCycler getInstance() {
            if (instance == null) {
                instance = new ImageCycler();
            }
            return instance;
        }
        
        public void startCycling(ImageView imageView, int[] images, long interval) {
            this.imageView = imageView;
            this.images = images;
            stopCycling();
            
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    imageView.setImageResource(images[currentImageIndex]);
                    currentImageIndex = (currentImageIndex + 1) % images.length;
                    handler.postDelayed(this, interval);
                }
            };
            
            handler.post(runnable);
        }
        
        public void stopCycling() {
            handler.removeCallbacksAndMessages(null);
        }
    }
    
    private ImageView imageView;
    private int[] images = {R.drawable.main_img1, R.drawable.main_img2, R.drawable.main_img3};
    private ImageCycler imageCycler;
    
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
        imageView = findViewById(R.id.imageView);
        main_textview1 = findViewById(R.id.main_textView1);
        main_textview2 = findViewById(R.id.main_textView2);
        main_textview3 = findViewById(R.id.main_textView3);
        main_textview4 = findViewById(R.id.main_textView4);
        
        // Use Singleton for image cycling
        imageCycler = ImageCycler.getInstance();
        imageCycler.startCycling(imageView, images, 2000);
        
        // Set click listeners - Using Command pattern approach
        setupNavigationCommands();
    }
    
    private void setupNavigationCommands() {
        // Factory method for creating navigation commands
        NavigationCommandFactory factory = new NavigationCommandFactory(this);
        
        main_textview1.setOnClickListener(v -> 
            factory.createCommand("Live_Price").execute());
        
        main_textview2.setOnClickListener(v -> 
            factory.createCommand("Design").execute());
        
        main_textview3.setOnClickListener(v -> 
            factory.createCommand("Calculator").execute());
        
        main_textview4.setOnClickListener(v -> 
            factory.createCommand("ManageJewelry").execute());
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        imageCycler.stopCycling();
    }
    
    // Command Pattern for Navigation
    interface NavigationCommand {
        void execute();
    }
    
    static class NavigationCommandFactory {
        private MainActivity activity;
        
        public NavigationCommandFactory(MainActivity activity) {
            this.activity = activity;
        }
        
        public NavigationCommand createCommand(String destination) {
            switch (destination) {
                case "Live_Price":
                    return () -> activity.startActivity(
                        new Intent(activity, Live_Price.class));
                case "Design":
                    return () -> activity.startActivity(
                        new Intent(activity, Design.class));
                case "Calculator":
                    return () -> activity.startActivity(
                        new Intent(activity, Calculator.class));
                case "ManageJewelry":
                    return () -> activity.startActivity(
                        new Intent(activity, ManageJewelryActivity.class));
                default:
                    return () -> {};
            }
        }
    }
}
