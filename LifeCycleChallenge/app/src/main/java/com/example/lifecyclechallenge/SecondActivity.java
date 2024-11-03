package com.example.lifecyclechallenge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private EditText mLocationEditText;
    public static final String EXTRA_MESSAGE = "com.example.twoactivitieslifecycle.extra.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void addItem(View view) {
        Intent replyIntent = new Intent();
        String message = ((Button) view).getText().toString();  // Ambil teks dari tombol yang diklik
        replyIntent.putExtra(EXTRA_MESSAGE, message);
        setResult(RESULT_OK, replyIntent);  // Kirim hasil kembali ke MainActivity
        finish();  // Tutup aktivitas ini
    }

    public void openLocation(View view) {
    }
}