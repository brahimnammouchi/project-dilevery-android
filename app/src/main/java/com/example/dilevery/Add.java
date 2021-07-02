package com.example.dilevery;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.core.view.View;

import java.util.jar.Attributes;

public class Add extends AppCompatActivity {
    EditText EditName;
    EditText Article1;
    EditText Article2;
    EditText Article3;
    EditText Article4;
    DatabaseReference reff;
    Commande commande;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commadne_add);

        this.EditName = findViewById(R.id.name);
        this.Article1 = findViewById(R.id.article1);
        this.Article2 = findViewById(R.id.article2);
        this.Article3 = findViewById(R.id.article3);
        this.Article4 = findViewById(R.id.article4);

        Intent i = getIntent();
        reff = FirebaseDatabase.getInstance().getReference().child(i.getStringExtra("cleint_name"));
        commande = new Commande();
    }
    public void CommitData(View v) {
        String name = EditName.getText().toString();
        String article1 = Article1.getText().toString();
        String article2 = Article2.getText().toString();
        String article3 = Article3.getText().toString();
        String article4 = Article4.getText().toString();

        commande.setName(name);
        commande.setArticle1(article1);
        commande.setArticle2(article2);
        commande.setArticle3(article3);
        commande.setArticle4(article4);

        reff.push().setValue(commande);

        Toast.makeText(getApplicationContext(),"Data inserted!" , Toast.LENGTH_LONG).show();
        finish();
    }
}
