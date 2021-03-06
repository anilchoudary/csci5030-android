package edu.slu.yumdev;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class RecipeCreateActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;

    public RecipeCreateActivity() {
        this(FirebaseDatabase.getInstance().getReference());
    }

    // Pass in a specific database.  (The test instance will use this)
    public RecipeCreateActivity(DatabaseReference mDatabase) {
        this.mDatabase = mDatabase;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button savebtn = findViewById(R.id.savebtn);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String recipeId = UUID.randomUUID().toString();
                String title = ((TextView)findViewById(R.id.title)).getText().toString();
                String ingredients = ((TextView)findViewById(R.id.ingredients)).getText().toString();
                String steps = ((TextView)findViewById(R.id.steps)).getText().toString();
                Recipe recipe = new Recipe();
                recipe.writeRecipe(recipeId, title, ingredients, steps,mDatabase);

                Intent intent = new Intent(RecipeCreateActivity.this, SingleRecipe.class);
                intent.putExtra("recipeID", recipeId);
                startActivity(intent);
            }
        });
    }
}

