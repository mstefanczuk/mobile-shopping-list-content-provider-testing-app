package pl.edu.pjatk.stefanczuk.shoppinglistcontentprovidertestingapp;

import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static final String PRODUCT_NAME = "NAME";
    public static final String PRODUCTS_URL = "content://pl.edu.pjatk.stefanczuk.shoppinglist.provider.Products/products";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.mainTextView);
        showProductList();
    }

    private void showProductList() {
        Uri products = Uri.parse(PRODUCTS_URL);
        Cursor c = getContentResolver().query(products, null, null, null, PRODUCT_NAME);
        StringBuilder result = new StringBuilder("Lista zakupów:");

        if (!Objects.requireNonNull(c).moveToFirst()) {
            textView.setText(result.toString());
        }else{
            do{
                result.append("\n - ").append(c.getString(c.getColumnIndex(PRODUCT_NAME)));
            } while (c.moveToNext());
            textView.setText(result.toString());
            c.close();
        }
    }
}
