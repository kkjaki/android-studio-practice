package com.example.asynctaskandroid;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.view.View;

public class SimpleAsyncTask extends AsyncTask<Integer, Void, String> {
    private TextView mTextView;
    private ProgressBar mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar pb) {
        mTextView = tv;
        mProgressBar = pb;
    }

    @Override
    protected String doInBackground(Integer... params) {
        // Get the time from params
        int sleepTime = params[0] * 1000;

        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + sleepTime + " milliseconds!";
    }

    @Override
    protected void onPostExecute(String result) {
        mTextView.setText(result);
        mProgressBar.setVisibility(View.GONE);
    }
}