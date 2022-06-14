package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class Mainadd extends AppCompatActivity {

    EditText title_input, des_input, price_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainadd);

        title_input = findViewById(R.id.title_input);
        des_input = findViewById(R.id.des_input);
        price_input = findViewById(R.id.price_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(Mainadd.this);
                myDB.add(title_input.getText().toString().trim(),
                        des_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()));
                Intent intent = new Intent(Mainadd.this, MainActivity2_CRUD.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
