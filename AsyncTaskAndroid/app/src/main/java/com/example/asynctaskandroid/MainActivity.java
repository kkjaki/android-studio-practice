package com.example.asynctaskandroid;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private static final String TEXT_STATE = "currentText";

    private TextView mTextView;
    private EditText mTimeInput;
    private Button mRunButton;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        mTextView = findViewById(R.id.tv_result);
        mTimeInput = findViewById(R.id.in_time);
        mRunButton = findViewById(R.id.btn_run);
        mProgressBar = findViewById(R.id.progressBar);

        // Restore TextView if there is a savedInstanceState
        if (savedInstanceState != null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }

        // Set click listener for the button
        mRunButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTask();
            }
        });
    }

    private void startTask() {
        // Get the time from input
        String timeString = mTimeInput.getText().toString();
        int time = timeString.isEmpty() ? 1 : Integer.parseInt(timeString);

        // Show progress bar
        mProgressBar.setVisibility(View.VISIBLE);

        // Put a message in the text view
        mTextView.setText(R.string.napping);

        // Start the AsyncTask
        new SimpleAsyncTask(mTextView, mProgressBar).execute(time);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}