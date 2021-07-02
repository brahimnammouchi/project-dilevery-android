package com.example.dilevery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lst;
    ArrayList<String> allCommande = new ArrayList<>();
    ArrayList<String> Notes = new ArrayList<>();
    String[] allTopics = {"article1", "article2", "article3", "article4"};

    String selectedCommande;

    AutoCompleteTextView autoSaisie;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.lst= this.findViewById(R.id.articleLst);
        this.autoSaisie= this.findViewById(R.id.saisieAuto);
        databaseReference = FirebaseDatabase.getInstance().getReference("Commande");
        autoSaisie.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i , long l) {
                selectedCommande = ((TextView) view).getText().toString();
                MyAdapter adapter;
                getArticle(selectedCommande);
                adapter = new MyAdapter(MainActivity.this,allCommande);
                lst.setAdapter(adapter);
                lst.setOnItemClickListener(((parent, view1, position, id) ->{
                    String selectedTopic = allTopics[position];
                    TextView txt = (TextView) view.findViewById(R.id.article);
                    Double article = Double.parseDouble(txt.getText().toString());

                } ));
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getClientName();
        ArrayAdapter ar = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, this.allCommande);
        autoSaisie.setAdapter(ar);
    }
    public void goToAdd(View v) {
        Intent i = new Intent(this, Add.class);
        i.putExtra("child_name", "Commande");
        startActivityForResult(i,1);
    }
    private void getClientName(){
        this.allCommande.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    Commande c = dataSnapshot1.getValue(Commande.class);
                    allCommande.add(c.getName());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this,"FAil to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getArticle(final String commandeName) {
        allCommande.clear();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Commande c = new Commande();
                c = dataSnapshot.child(commandeName).getValue(Commande.class);
                if (c.getName().equals(commandeName)) {
                    allCommande.add(String.valueOf(c.getArticle1()));
                    allCommande.add(String.valueOf(c.getArticle2()));
                    allCommande.add(String.valueOf(c.getArticle3()));
                    allCommande.add(String.valueOf(c.getArticle4()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}