package com.example.bdjewellero;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Design extends AppCompatActivity {
    TextView textView1,textView2,textView3,textView4,textView5,textView6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_design);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);


        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Image URLs
                String[] chains = {
                        "https://ww2.meenajewelers.com/thumbFull/images/7_22K_Gold_Chain_23694.jpg",
                        "https://www.meenajewelers.com/thumbL/images/54_22Kt_Gold_Men_Chains_25658.jpg",
                        "https://www.meenajewelers.com/thumbL/images/58_22kt_Gold_Chains_25344.jpg",
                        "https://zaveribazaarjewelers.com/thumbL/images/99_22kt_Chains_66269.jpg",
                        "https://www.meenajewelers.com/thumbL/images/94_22kt_Gold_Chain_24826.jpg",
                        "https://www.meenajewelers.com/thumbL/images/81_22Kt_Gold_Men_Chains_25665.jpg",
                        "https://zaveribazaarjewelers.com/thumbL/images/66_22KT_Gold_Chains_66905.jpg",
                        "https://www.meenajewelers.com/thumbL/images/25_22kt_Chain_20464.jpg",
                        "https://i.pinimg.com/originals/a6/99/19/a69919b7c131fd44d11abc74017162e2.jpg",
                        "https://n2.sdlcdn.com/imgs/a/5/1/Ashree-22-Kt-Gold-Chain-SDL818304678-1-22ee1.jpg",
                        "https://i.pinimg.com/originals/02/5b/09/025b09fa28d65da48eee3a345d5f3963.jpg",
                        "https://i.etsystatic.com/27392624/r/il/68d147/4285353932/il_1080xN.4285353932_amhs.jpg",
                        "https://n2.sdlcdn.com/imgs/h/o/d/MAA-JEWELS-DESIGNER-GOLD-PLATED-SDL081640923-2-40657.jpg",
                        "https://th.bing.com/th/id/OIP.oR2sZRLAW9NzCQRunWOtjgHaHa?w=750&h=750&rs=1&pid=ImgDetMain",
                        "https://rukminim2.flixcart.com/image/612/612/xif0q/precious-chain/b/4/s/women-gold-chain-18kt-amzk000556-18k-yg-18l-candere-by-kalyan-original-imagqhem9qrtgnnd.jpeg?q=70",
                        "https://th.bing.com/th/id/OIP.IJc1-_EP0mAEsejR9NGdOAHaHa?rs=1&pid=ImgDetMain",
                        "https://a.1stdibscdn.com/archivesE/upload/1121189/j_149091221646409751278/14909122_datamatics.jpg?width=768",
                        "https://f.nooncdn.com/p/v1666112265/N40453079A_1.jpg?format=jpg&width=240",
                        "https://n1.sdlcdn.com/imgs/b/1/u/AVN-Jewellers-Brass-Studded-Gold-SDL118163003-1-8f86a.jpg",
                        "https://cdn11.bigcommerce.com/s-f813d/images/stencil/659x659/products/2712/5776/ROPE__15754.1299010570.jpg?c=2"
                };

                // Send images to Display_images activity
                Intent myIntent = new Intent(Design.this, Display_images.class);
                myIntent.putExtra("image_urls", chains);
                startActivity(myIntent);
            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Image URLs
                String[] necklase = {
                        "https://th.bing.com/th/id/R.88ed76c0bbbc7bdf201ec43fd9016aea?rik=c%2fAI1sCSQpgQDQ&riu=http%3a%2f%2fwww.pngmart.com%2ffiles%2f5%2fGold-Necklace-PNG-Transparent.png&ehk=6agIDgGzhnrmiqFg5vgNiKeH86%2b%2bDm4vfMkGIIwuUTk%3d&risl=&pid=ImgRaw&r=0",
                        "https://th.bing.com/th/id/R.775c5d88dec02f70803a97104fc2a462?rik=acSEf%2bNYmPahvw&riu=http%3a%2f%2fwww.pngmart.com%2ffiles%2f5%2fNecklace-Design-PNG-Clipart.png&ehk=2C32H1ZXHbyApWS4oYW%2fFF4oMVSaRepewqSpxyT6JMs%3d&risl=&pid=ImgRaw&r=0",
                        "https://th.bing.com/th/id/R.4ff9ffcbb4ca593038e5943b5fd079b2?rik=8t7HCvtw1T3CKA&riu=http%3a%2f%2fwww.pngmart.com%2ffiles%2f5%2fNecklace-Design-PNG-Photos.png&ehk=1KJxhHDO2QCdN25QLrRq%2bXv4VZQt1AiUZmK0XjxbCVs%3d&risl=&pid=ImgRaw&r=0",
                        "https://th.bing.com/th/id/R.407cf5fc9932bf0a2f6300ccf1b40380?rik=gsZfMaXyru7j%2bg&riu=http%3a%2f%2fwww.pngmart.com%2ffiles%2f5%2fNecklace-Design-PNG-File.png&ehk=ecvOT%2f5fIrCiTFdqMXqYR1gwMV%2bJAgHzl%2fCKV8%2ffYGE%3d&risl=&pid=ImgRaw&r=0",
                        "https://th.bing.com/th/id/OIP.hV2BRrh5XabFJFcfcwM8JQAAAA?rs=1&pid=ImgDetMain",
                        "https://www.pngall.com/wp-content/uploads/8/Gold-Jewellery-Necklace-PNG.png",
                        "https://static.malabargoldanddiamonds.com/media/catalog/product/cache/5/image/9df78eab33525d08d6e5fb8d27136e95/n/s/nsdvnkshstpr.jpg",
                        "https://3.imimg.com/data3/EG/IF/MY-8684350/designer-necklace-set-500x500.jpg",
                        "https://cdn1.jewelxy.com/live/img/business_product/360x360/3VltHunkXd_20220523150549.jpg",
                        "https://th.bing.com/th/id/R.18129751f9e9d82d6ad0a89a85bd7081?rik=cK%2btIktEoQMy4w&riu=http%3a%2f%2fwww.meenajewelers.com%2fimages%2f74_Indian_necklace_Set_9516.jpg&ehk=5H27TCRijuwpryhL%2fFkK%2bYIygKQeE%2b7cr9m1QA9tF0M%3d&risl=&pid=ImgRaw&r=0",
                        "https://th.bing.com/th/id/R.09a622df640c451791a913e2b0cdd43e?rik=3Z5Xvx2OWlwlnw&riu=http%3a%2f%2fmeenajewelers.com%2fthumbL%2fimages%2f51_Gold_Necklace_earrings_22Kt_8798.jpg&ehk=JaCrIvWdvcbXqqLRS1WW7OLFiNLRsH9SLHtw0Pf56U4%3d&risl=&pid=ImgRaw&r=0",
                        "https://cdn1.jewelxy.com/live/img/business_product/360x360/3VltHunkXd_20220523150549.jpg",
                        "https://th.bing.com/th/id/R.59cfb39ed2eb9bafac779c9b2e4ac8e8?rik=A%2fq9Z7XTzd1Edw&riu=http%3a%2f%2fwww.meenajewelers.com%2fimages%2f69_22KT_Necklace_earrings_set_4240.jpg&ehk=bghNz5gK5c4lQCpEtA9p9M20XJBZAyhCQiOQdtj7OiY%3d&risl=&pid=ImgRaw&r=0",

                };

                // Send images to Display_images activity
                Intent myIntent = new Intent(Design.this, Display_images.class);
                myIntent.putExtra("image_urls", necklase);
                startActivity(myIntent);
            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Image URLs
                String[] bangles = {
                        "https://th.bing.com/th/id/OIP.5PBBIpLHmz16LBrJ50h9fwHaHa?w=1080&h=1080&rs=1&pid=ImgDetMain",
                        "https://th.bing.com/th/id/OIP.y-nwOjLumXqXrZa65vXyjAHaHa?rs=1&pid=ImgDetMain",
                        "https://th.bing.com/th/id/OIP.TOHQDlQPOfa67207A3LkzwHaHa?rs=1&pid=ImgDetMain",
                        "https://www.ladiesfashiondesigns.com/wp-content/uploads/2018/06/bangles-2.jpg",
                        "https://th.bing.com/th/id/OIP.klHXA0QY5VKt_hvQvFcg_wHaGn?w=951&h=850&rs=1&pid=ImgDetMain",
                        "https://th.bing.com/th/id/OIP.HghAV9k4LYb1GqeIpiDv9wHaHS?w=762&h=750&rs=1&pid=ImgDetMain",
                        "https://th.bing.com/th/id/R.4a7bbf9aec5ac1c88ca03e2305c52c16?rik=4sKlcHhtIZVNJA&pid=ImgRaw&r=0",
                        "https://th.bing.com/th/id/OIP.NVYd3rFdrsgVTHxRGGtNogHaHf?w=494&h=500&rs=1&pid=ImgDetMain",
                        "https://th.bing.com/th/id/R.8d5389f04ee2bd3604f9d20b337b5d92?rik=AFFm645lNO7LpQ&riu=http%3a%2f%2fsheclick.com%2fwp-content%2fuploads%2f2010%2f05%2fLatest-Gold-Bangles-Set.jpg&ehk=2QFvUK0VfLBbDkqM53XspOiJl%2fXmWUFvPK6CqLdn7Yg%3d&risl=&pid=ImgRaw&r=0",
                        "https://th.bing.com/th/id/OIP.qO3UMX3ktq17irkB2pv5RgAAAA?w=332&h=400&rs=1&pid=ImgDetMain",
                        "https://s3.babusiya.com/EASYERP-BABUSIYA/PCAT_886814649/20220924102629/original.jpeg",
                        "https://th.bing.com/th/id/R.3f5ade47479d21670698a5a920996a56?rik=DGmOVBGt1cGgVw&riu=http%3a%2f%2fwww.meenajewelers.com%2fimages%2f84_Indian_gold_Bangles_10012.jpg&ehk=65YE7VpUc55vs5AdefL6v%2f96bct8MIxvWEwuI2YYJhA%3d&risl=&pid=ImgRaw&r=0",
                        "https://zaveribazaarjewelers.com/thumbL/images/80_Gold_Kadas_22Karat_60336.jpg",
                        "https://th.bing.com/th/id/OIP.ULmijrZQXJRDdy0NIaItlQHaIt?w=753&h=885&rs=1&pid=ImgDetMain",
                        "https://3.bp.blogspot.com/-0V9jYT6cCGY/UOf5H_Lpq7I/AAAAAAAAHuc/MYd5p33VKvM/s1600/Gold-Bangle-Collection.jpg",
                        "https://2.bp.blogspot.com/-BNRy3MMHa4I/UOf5GwpsSxI/AAAAAAAAHuE/_RY23WAS_GQ/s1600/2012-Gold-Bangles-Design.jpg"

                };

                // Send images to Display_images activity
                Intent myIntent = new Intent(Design.this, Display_images.class);
                myIntent.putExtra("image_urls", bangles);
                startActivity(myIntent);
            }
        });

        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Image URLs
                String[] earings = {
                        "https://i.pinimg.com/736x/7a/fa/9b/7afa9b8f040ca15f9e2110b6578dce8f--jhumka-earrings-gold-ladies-jewelry.jpg",
                        "https://5.imimg.com/data5/LC/XC/MY-35242755/gold-earrings-250x250.jpg",
                        "https://3.imimg.com/data3/NS/ST/MY-52856/gold-earrings-500x500.jpg",
                        "https://th.bing.com/th/id/R.af76210c0d83345c39711f6069e5ddf0?rik=gF1%2bodwAPXVQow&riu=http%3a%2f%2fhe.com.pk%2fwp-content%2fuploads%2f2016%2f11%2f3.jpg&ehk=spBo6HKSkECxsTF4UNDwFabtx7vW3JJEhbKHlwdWJ1M%3d&risl=&pid=ImgRaw&r=0",
                        "https://rukminim2.flixcart.com/image/612/612/kkfrjww0/earring/3/f/1/gold-jhumki-earrings-n113-nimz-original-imafzsf2wgygjyyf.jpeg?q=70",
                        "https://th.bing.com/th/id/OIP.JR3tPQCmk5GlckcsLeIDygHaHa?rs=1&pid=ImgDetMain",
                        "https://static.malabargoldanddiamonds.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/c/n/cniaaaafjxrj.jpg",
                        "https://th.bing.com/th/id/OIP.CTPdc7mAgo-lEasBTRAbfQHaFq?w=1247&h=954&rs=1&pid=ImgDetMain",
                        "https://static.malabargoldanddiamonds.com/media/catalog/product/cache/1/image/9df78eab33525d08d6e5fb8d27136e95/c/n/cniaaaafjxrj.jpg",
                        "https://th.bing.com/th/id/OIP.CTPdc7mAgo-lEasBTRAbfQHaFq?w=1247&h=954&rs=1&pid=ImgDetMain",
                        "https://th.bing.com/th/id/OIP.0jkOTyB8SYKRvekh5cY8bQHaHa?w=1200&h=1200&rs=1&pid=ImgDetMain," ,
                        "https://www.tanishq.co.in/dw/image/v2/BKCK_PRD/on/demandware.static/-/Sites-Tanishq-product-catalog/default/dwcfbf242b/images/hi-res/510671DDAABA00_1.jpg?sw=640&sh=640",
                        "https://th.bing.com/th/id/OIP.xraPtUw5AX_QWq3yfprb7gHaHa?w=1000&h=1000&rs=1&pid=ImgDetMain",
                        "https://th.bing.com/th/id/OIP.6bRI3XQuHT8UA3F_QI3TOgHaHa?w=640&h=640&rs=1&pid=ImgDetMain",
                        "https://www.tanishq.co.in/dw/image/v2/BKCK_PRD/on/demandware.static/-/Sites-Tanishq-product-catalog/default/dwcfbf242b/images/hi-res/510671DDAABA00_1.jpg?sw=640&sh=640",
                        "https://th.bing.com/th/id/OIP.E-K-DVElegz8GeVHvtdP9gHaG8?w=794&h=745&rs=1&pid=ImgDetMain",
                        "https://www.bisgold.com/wp-content/uploads/2023/01/Traditional-Wear-Jhumka-Earring-600x600.jpg",
                        "https://3.imimg.com/data3/NS/ST/MY-52856/gold-earrings-500x500.jpg",
                        "https://2.imimg.com/data2/MX/PI/MY-1351879/earring.jpg"
                };

                // Send images to Display_images activity
                Intent myIntent = new Intent(Design.this, Display_images.class);
                myIntent.putExtra("image_urls", earings);
                startActivity(myIntent);
            }
        });

        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Image URLs
                String[] finger_ring = {
                        "https://i.pinimg.com/originals/8c/c1/d6/8cc1d67a3d2e4968d8fb5c88e70f7113.jpg",
                        "https://img.pikbest.com/origin/09/21/08/92QpIkbEsTETp.png!bw700",
                        "https://i.pinimg.com/736x/db/ee/7b/dbee7b8fa80c4e71c4be13d7f20dd339.jpg",
                        "https://th.bing.com/th/id/OIP.jp5s6nnKVZBC6BcE9MA9cQHaHa?rs=1&pid=ImgDetMain",
                        "https://th.bing.com/th/id/OIP.wo_vPGrOi_Dni8zpu6BBlwHaHa?pid=ImgDet&w=201&h=201&c=7&dpr=1.6",
                        "https://www.tanishq.co.in/on/demandware.static/-/Sites-Tanishq-product-catalog/default/dwd81bab2f/images/hi-res/51E2A1FDNAA00_1.jpg",
                        "https://image.reliancejewels.com/Jewels/images/productImages/607/22-karat-gold-ring-large_1c3fd42c5226f3828af6d6e1272072b3.jpg",
                        "https://3.imimg.com/data3/MX/UC/MY-1757304/gold-ring-500x500.jpg",
                        "https://www.tanishq.co.in/on/demandware.static/-/Sites-Tanishq-product-catalog/default/dwd3c13920/images/hi-res/51D2A1FWEAA00_2.jpg",
                        "https://i.pinimg.com/originals/3e/ca/ce/3ecacedb61b47d645fe2e334a2684cfa.jpg",
                        "https://th.bing.com/th/id/OIP.KIXKlFeJw8cKnVC7nl06sAHaHa?pid=ImgDet&w=201&h=201&c=7&dpr=1.6",
                        "https://www.tanishq.co.in/on/demandware.static/-/Sites-Tanishq-product-catalog/default/dw04476526/images/hi-res/511087FGAAA00_1.jpg",
                        "https://zaveribazaarjewelers.com/thumbL/images/85_22KT_Gold_rings_63831.jpg",
                        "https://th.bing.com/th/id/OIP._G3DDg_ksiZjpXjy7pktYQHaHG?rs=1&pid=ImgDetMain",
                        "https://i.pinimg.com/originals/52/8e/59/528e598af88b872390cf3093d0c4dad7.jpg",
                        "https://sakthijewellers.in/wp-content/uploads/2021/07/8.jpg" ,
                        "https://www.tanishq.co.in/on/demandware.static/-/Sites-Tanishq-product-catalog/default/dw0900e785/images/hi-res/502015FHRAB02_1.jpg",
                        "https://th.bing.com/th/id/OIP.RzQFp8n3x-64-aeltECUVQHaHa?w=640&h=640&rs=1&pid=ImgDetMain",
                        "https://www.tanishq.co.in/on/demandware.static/-/Sites-Tanishq-product-catalog/default/dw38173db0/images/hi-res/503419FNMAA02_1.jpg",
                        "https://img.tatacliq.com/images/i2/437Wx649H/MP000000002483526_437Wx649H_20180301083605.jpeg",
                        "https://cf-cdn.pcjeweller.com/public/uploads/catalog/product/large/l/LR00185-6.jpg"
                };

                // Send images to Display_images activity
                Intent myIntent = new Intent(Design.this, Display_images.class);
                myIntent.putExtra("image_urls", finger_ring);
                startActivity(myIntent);
            }
        });

        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Image URLs
                String[] pendant = {
                        "https://th.bing.com/th/id/OIP.nLOfMiBuBVb3l_myJXpm_gHaHa?w=800&h=800&rs=1&pid=ImgDetMain",
                        "https://www.purejewels.com/wp-content/uploads/2020/03/32564.png",
                        "https://res.cloudinary.com/lbh-prod/image/fetch/w_1000,f_auto,q_auto/https://www.ross-simons.com/on/demandware.static/-/Sites-lbh-master/default/dwbe88c3dd/images/jewelry-diamond-necklaces/950900.jpg",
                        "https://content.thewosgroup.com/productimage/37520248/37520248_1.jpg?impolicy=hero&imwidth=700",
                        "https://images.bloomingdalesassets.com/is/image/BLM/products/5/optimized/12987885_fpx.tif?op_sharpen=1&wid=700&fit=fit,1&$filtersm$",
                        "https://th.bing.com/th/id/OIP.fEj4HqxvFDN2lCQOHx_wAQHaHa?w=500&h=500&rs=1&pid=ImgDetMain",
                        "https://th.bing.com/th/id/OIP.3h__3A81rdZHhC8vv1y9SQHaHa?w=1000&h=1000&rs=1&pid=ImgDetMain",
                        "https://www.boodles.com/cdn/shop/products/0735223331_B.jpg?v=1662998206",
                        "https://a.1stdibscdn.com/archivesE/upload/1121189/j_129625321629409315279/12962532_datamatics.jpg",
                        "https://purepng.com/public/uploads/large/purepng.com-gold-pendantjewelryjewellerydiamondpendantchain-1701528883490jbccb.png",
                        "https://th.bing.com/th/id/OIP.mtB4OD77NLg8bDtdyfYvvQHaHa?w=1200&h=1200&rs=1&pid=ImgDetMain",
                        "https://www.pngall.com/wp-content/uploads/15/Gold-Necklace-PNG-Photos.png",
                        "https://clipart-library.com/new_gallery/76-761939_light-weight-gold-chain-light-weight-gold-necklaces.png",
                        "https://th.bing.com/th/id/OIP.IvqGEd29z3wzp2pR98k0twHaHa?rs=1&pid=ImgDetMain"

                };

                // Send images to Display_images activity
                Intent myIntent = new Intent(Design.this, Display_images.class);
                myIntent.putExtra("image_urls", pendant);
                startActivity(myIntent);
            }
        });
    }
}
