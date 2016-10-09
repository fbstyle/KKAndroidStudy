package com.kkandroidstudy.activity;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.kkandroidstudy.R;

public class FloatingActionButtonActivity extends AppCompatActivity {
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_floating_action_button);

        floatingActionButton = (FloatingActionButton) findViewById(R.id.floatingActionButton);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Hello FAB", Snackbar.LENGTH_SHORT).setActionTextColor(Color.parseColor("#ff00ff")).setAction("这是Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(FloatingActionButtonActivity.this, "你点击了Action", Toast.LENGTH_SHORT).show();
                    }
                }).show();
            }
        });
    }
}
