package com.example.lifecyclechallenge;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int TEXT_REQUEST = 1;
    private ArrayList<String> items = new ArrayList<>();  // Gantikan HashMap dengan ArrayList
    private EditText editTextStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextStore = findViewById(R.id.editTextStore);
        // Memulihkan data jika ada (misalnya saat rotasi layar)
        if (savedInstanceState != null && savedInstanceState.getStringArrayList("list") != null) {
            items = savedInstanceState.getStringArrayList("list");
            drawView();  // Perbarui tampilan setelah memuat ulang
        }
    }

    public void launchSecondActivity(View view) {
        Intent intent = new Intent(this, com.example.lifecyclechallenge.SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Simpan daftar item saat aktivitas disimpan (misalnya saat rotasi layar)
        savedInstanceState.putStringArrayList("list", items);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TEXT_REQUEST && resultCode == RESULT_OK) {
            String item = data.getStringExtra(com.example.lifecyclechallenge.SecondActivity.EXTRA_MESSAGE);
            items.add(item);  // Tambahkan item ke dalam daftar
            drawView();  // Perbarui tampilan
        }
    }

    public void drawView() {
        TextView tv = findViewById(R.id.textView);
        tv.setText("");  // Reset teks tampilan

        // Tampilkan semua item dalam daftar
        for (String item : items) {
            tv.setText(tv.getText() + item + "\n");
        }
    }

    public void locateStore(View view) {
        String location = editTextStore.getText().toString().trim();
        if (!location.isEmpty()) {
            Uri addressUri = Uri.parse("geo:0,0?q=" + location);
            Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
            try {
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.d("ImplicitIntents", "Can't handle this loc: " + location);
                Toast.makeText(this, "Can't handle this request.", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Please enter a store name or address", Toast.LENGTH_SHORT).show();
        }
    }
}