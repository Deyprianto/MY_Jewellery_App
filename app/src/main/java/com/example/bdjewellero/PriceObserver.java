package com.example.bdjewellero;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


public class Live_Price extends AppCompatActivity implements PriceObserver {
    private TextView text4, text5, text6, text7, text8, text9;
    private PriceSubject priceSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_price);

     
        priceSubject = new PriceSubject();
        priceSubject.attach(this);

      
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);
        text9 = findViewById(R.id.text9);

   
        new FetchGoldPrice().execute();
    }

    @Override
    public void onPriceUpdate(String goldType, int pricePerGram, int pricePerVori) {
        runOnUiThread(() -> {
            switch (goldType) {
                case "22K":
                    text4.setText(String.valueOf(pricePerGram));
                    text7.setText(pricePerVori + " BDT");
                    break;
                case "21K":
                    text5.setText(String.valueOf(pricePerGram));
                    text8.setText(pricePerVori + " BDT");
                    break;
                case "18K":
                    text6.setText(String.valueOf(pricePerGram));
                    text9.setText(pricePerVori + " BDT");
                    break;
            }
        });
    }

    private class FetchGoldPrice extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://www.bajus.org/gold-price").get();
                Elements priceElements = doc.select(".price");
                
                if (priceElements.size() >= 3) {
                    return new String[]{
                            priceElements.get(0).text(),
                            priceElements.get(1).text(),
                            priceElements.get(2).text()
                    };
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new String[]{"Error", "Error", "Error"};
        }

        @Override
        protected void onPostExecute(String[] prices) {
            try {
              
                for (int i = 0; i < prices.length; i++) {
                    prices[i] = prices[i].split("/")[0].trim();
                }

               
                int price22K = Integer.parseInt(prices[0].replaceAll("[^0-9]", ""));
                int price21K = Integer.parseInt(prices[1].replaceAll("[^0-9]", ""));
                int price18K = Integer.parseInt(prices[2].replaceAll("[^0-9]", ""));

             
                int vori22K = (int) (price22K * 11.664);
                int vori21K = (int) (price21K * 11.664);
                int vori18K = (int) (price18K * 11.664);

                
                priceSubject.notifyObservers("22K", price22K, vori22K);
                priceSubject.notifyObservers("21K", price21K, vori21K);
                priceSubject.notifyObservers("18K", price18K, vori18K);

            } catch (NumberFormatException e) {
                e.printStackTrace();
                text7.setText("Error");
                text8.setText("Error");
                text9.setText("Error");
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (priceSubject != null) {
            priceSubject.detach(this);
        }
    }
}
