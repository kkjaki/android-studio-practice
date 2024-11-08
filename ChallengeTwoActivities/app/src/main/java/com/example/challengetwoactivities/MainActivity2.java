package com.example.challengetwoactivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // Inisialisasi TextView
        TextView textView = findViewById(R.id.textView);

        // Mendapatkan Intent dan teks yang dikirim dari MainActivity
        Intent intent = getIntent();
        String text = intent.getStringExtra("text_key");

        // Menampilkan teks di TextView
        textView.setText(text);
    }
}
