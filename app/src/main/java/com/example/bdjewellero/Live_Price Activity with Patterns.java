package com.example.bdjewellero;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.Date;

public class Live_Price extends AppCompatActivity implements GoldPriceObserver {
    
    private TextView text4, text5, text6, text7, text8, text9;
    private TextView lastUpdatedView;
    private GoldPriceManager goldPriceManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_price);

   
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);
        text9 = findViewById(R.id.text9);
        lastUpdatedView = findViewById(R.id.lastUpdated);

    
        goldPriceManager = GoldPriceManager.getInstance();
        goldPriceManager.registerObserver(this);


        new FetchGoldPrice().execute();
        
      
        updateLastUpdatedTime();
    }
    
    @Override
    public void onGoldPriceUpdated(GoldPriceData data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                updatePriceDisplay(data);
                updateLastUpdatedTime();
            }
        });
    }
    
    private void updatePriceDisplay(GoldPriceData data) {

        text4.setText(String.format("%.2f BDT/g", data.getPrice22K()));
        text5.setText(String.format("%.2f BDT/g", data.getPrice21K()));
        text6.setText(String.format("%.2f BDT/g", data.getPrice18K()));

        double vori22K = data.getPrice22K() * 11.664;
        double vori21K = data.getPrice21K() * 11.664;
        double vori18K = data.getPrice18K() * 11.664;

        text7.setText(String.format("৳%.2f/vori", vori22K));
        text8.setText(String.format("৳%.2f/vori", vori21K));
        text9.setText(String.format("৳%.2f/vori", vori18K));
        
    
        updatePriceSuggestions(data);
    }
    
    private void updatePriceSuggestions(GoldPriceData data) {
        TextView suggestionView = findViewById(R.id.suggestionText);
        if (suggestionView != null) {
            StringBuilder suggestion = new StringBuilder();
            suggestion.append("Market Analysis:\n");
            
       
            if (data.getPrice22K() > 8000) {
                suggestion.append("• 22K Gold is relatively high\n");
                suggestion.append("• Consider waiting for dip\n");
            } else if (data.getPrice22K() < 7000) {
                suggestion.append("• 22K Gold is relatively low\n");
                suggestion.append("• Good time to buy\n");
            } else {
                suggestion.append("• 22K Gold at moderate level\n");
                suggestion.append("• Stable for investment\n");
            }
            

            double difference21_22 = ((data.getPrice21K() - data.getPrice22K()) / data.getPrice22K()) * 100;
            if (Math.abs(difference21_22) > 5) {
                suggestion.append(String.format("\n• 21K is %.1f%% %s than 22K\n", 
                    Math.abs(difference21_22), 
                    difference21_22 > 0 ? "higher" : "lower"));
            }
            
            suggestionView.setText(suggestion.toString());
        }
    }
    
    private void updateLastUpdatedTime() {
        if (lastUpdatedView != null) {
            lastUpdatedView.setText("Last updated: " + new Date().toString());
        }
    }

    private class FetchGoldPrice extends AsyncTask<Void, Void, GoldPriceData> {
        @Override
        protected GoldPriceData doInBackground(Void... voids) {
            try {
            
                GoldPriceData data = fetchFromBajus();
                if (data.getPrice22K() == 0) {
                    data = fetchFromAlternativeSource();
                }
                return data;
            } catch (Exception e) {
                e.printStackTrace();
                return new GoldPriceData(0, 0, 0);
            }
        }
        
        private GoldPriceData fetchFromBajus() {
            try {
                Document doc = Jsoup.connect("https://www.bajus.org/gold-price")
                    .timeout(10000)
                    .get();
                
                Elements priceElements = doc.select(".price, .gold-price, td");
                
                if (priceElements.size() >= 3) {
                    double price22K = parsePrice(priceElements.get(0).text());
                    double price21K = parsePrice(priceElements.get(1).text());
                    double price18K = parsePrice(priceElements.get(2).text());
                    
                    return new GoldPriceData(price22K, price21K, price18K);
                }
            } catch (Exception e) {
             
            }
            return new GoldPriceData(0, 0, 0);
        }
        
        private GoldPriceData fetchFromAlternativeSource() {
            try {
             
                Document doc = Jsoup.connect("https://www.goldpricebd.com/")
                    .timeout(10000)
                    .get();
                
                Elements priceElements = doc.select("[class*='price'], [class*='gold'], strong");
                
           
                String html = doc.html();
                double price22K = extractPriceFromText(html, "22");
                double price21K = extractPriceFromText(html, "21");
                double price18K = extractPriceFromText(html, "18");
                
                return new GoldPriceData(price22K, price21K, price18K);
            } catch (Exception e) {
                return new GoldPriceData(0, 0, 0);
            }
        }
        
        private double parsePrice(String priceText) {
            try {
              
                String cleanPrice = priceText.replaceAll("[^0-9.]", "");
                return Double.parseDouble(cleanPrice);
            } catch (Exception e) {
                return 0;
            }
        }
        
        private double extractPriceFromText(String text, String karat) {
           
            String pattern = karat + ".*?(\\d+[.,]\\d+)";
            java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);
            java.util.regex.Matcher m = r.matcher(text);
            
            if (m.find()) {
                try {
                    return Double.parseDouble(m.group(1).replace(",", ""));
                } catch (Exception e) {
                    return 0;
                }
            }
            return 0;
        }

        @Override
        protected void onPostExecute(GoldPriceData goldPriceData) {
            if (goldPriceData.getPrice22K() > 0) {
     
                goldPriceManager.updateGoldPrices(goldPriceData);
            } else {
               
                if (goldPriceManager.getCurrentPriceData() != null) {
                    updatePriceDisplay(goldPriceManager.getCurrentPriceData());
                }
                
        
                text4.setText("Update Failed");
                text5.setText("Using Last Data");
                text6.setText("Retrying...");
                
  
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new FetchGoldPrice().execute();
                    }
                }, 5000);
            }
        }
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        goldPriceManager.removeObserver(this);
    }
    
    public void refreshPrices(View view) {
        new FetchGoldPrice().execute();
        Toast.makeText(this, "Refreshing gold prices...", Toast.LENGTH_SHORT).show();
    }
}
