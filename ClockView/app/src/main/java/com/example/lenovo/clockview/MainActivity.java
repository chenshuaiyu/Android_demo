package com.example.lenovo.clockview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.clock_view:
                startActivity(new Intent(this,ClockViewActivity.class));
                break;
            case R.id.touch_view:
                startActivity(new Intent(this,TouchViewActivity.class));
                break;
        }
    }
}
