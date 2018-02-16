package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    private ImageView ingredientsIv;

    private TextView name;
    private TextView aka;
    private TextView origin;
    private TextView ingredients;
    private TextView description;

    private Sandwich sandwich;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ingredientsIv = findViewById(R.id.image_iv);

        name           = findViewById(R.id.name_tv);
        aka            = findViewById(R.id.AKA_tv);
        origin         = findViewById(R.id.origin_tv);
        ingredients    = findViewById(R.id.ingredients_tv);
        description    = findViewById(R.id.description_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI();

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {

        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());

        name.setText(sandwich.getMainName());

        for(int i = 0;i<sandwich.getAlsoKnownAs().size();i++){

            aka.append("- " + sandwich.getAlsoKnownAs().get(i));
            aka.append("\n");
        }

        origin.setText(sandwich.getPlaceOfOrigin());

        for(int i = 0;i<sandwich.getIngredients().size();i++){

            ingredients.append("- " + sandwich.getIngredients().get(i));
            ingredients.append("\n");
        }

        description.setText(sandwich.getDescription());

    }
}
