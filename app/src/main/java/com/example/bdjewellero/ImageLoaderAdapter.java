package com.example.bdjewellero;

public interface ImageLoader {
    void loadImage(String url, ImageView imageView);
}

class GlideImageLoader implements ImageLoader {
    private Context context;
    
    public GlideImageLoader(Context context) {
        this.context = context;
    }
    
    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }
}

class PicassoImageLoader implements ImageLoader {
    private Context context;
    
    public PicassoImageLoader(Context context) {
        this.context = context;
    }
    
    @Override
    public void loadImage(String url, ImageView imageView) {
        Picasso.get().load(url).into(imageView);
    }
}

// Universal Image Loader Adapter
class UniversalImageLoaderAdapter implements ImageLoader {
    private ImageLoader imageLoader;
    
    public UniversalImageLoaderAdapter(Context context, String loaderType) {
        if (loaderType.equals("glide")) {
            imageLoader = new GlideImageLoader(context);
        } else if (loaderType.equals("picasso")) {
            imageLoader = new PicassoImageLoader(context);
        }
    }
    
    @Override
    public void loadImage(String url, ImageView imageView) {
        imageLoader.loadImage(url, imageView);
    }
}

// Usage in Display_images.java (modified)
public class Display_images extends AppCompatActivity {
    // ... existing code ...
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // ... existing code ...
        
        // Use adapter pattern
        ImageLoader imageLoader = new UniversalImageLoaderAdapter(this, "glide");
        
        if (images != null) {
            for (String url : images) {
                ImageView imageView = new ImageView(this);
                // ... setup imageView ...
                linearLayout.addView(imageView);
                
                // Load image using adapter
                imageLoader.loadImage(url, imageView);
            }
        }
    }
}
