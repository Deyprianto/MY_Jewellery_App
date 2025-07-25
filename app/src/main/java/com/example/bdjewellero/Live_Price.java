package com.example.bdjewellero;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class Live_Price extends AppCompatActivity {
    private TextView text4, text5, text6, text7, text8, text9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.live_price);

        // Initialize TextViews
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);
        text7 = findViewById(R.id.text7);
        text8 = findViewById(R.id.text8);
        text9 = findViewById(R.id.text9);

        // Fetch Gold Prices
        new FetchGoldPrice().execute();
    }

    private class FetchGoldPrice extends AsyncTask<Void, Void, String[]> {
        @Override
        protected String[] doInBackground(Void... voids) {
            try {
                Document doc = Jsoup.connect("https://www.bajus.org/gold-price").get();
                Elements priceElements = doc.select(".price"); // Adjust selector if necessary

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
                    prices[i] = prices[i].split("/")[0].trim(); // Remove "BDT/GM" part
                }

                // Display raw text values
                text4.setText(prices[0]);
                text5.setText(prices[1]);
                text6.setText(prices[2]);

                // Convert to integer (handle possible conversion errors)
                int price22K = Integer.parseInt(prices[0].replaceAll("[^0-9]", ""));
                int price21K = Integer.parseInt(prices[1].replaceAll("[^0-9]", ""));
                int price18K = Integer.parseInt(prices[2].replaceAll("[^0-9]", ""));


                //converting to vori
                int x = (int) (price22K*11.664);
                int y = (int) (price21K*11.664);
                int z = (int) (price18K*11.664);

                // Display only numeric values
                text7.setText(x +" BDT");
                text8.setText(y +" BDT");
                text9.setText(z +" BDT");

            } catch (NumberFormatException e) {
                e.printStackTrace();
                text7.setText("Error");
                text8.setText("Error");
                text9.setText("Error");
            }
        }
    }
}
