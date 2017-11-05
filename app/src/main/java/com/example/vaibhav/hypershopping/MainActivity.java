package com.example.vaibhav.hypershopping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hypertrack.lib.HyperTrack;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HyperTrack.initialize(this, "pk_3f3c8ec252a417a10707f6457bb7018601f89036");
        HyperTrack.requestPermissions(this);
        HyperTrack.requestLocationServices(this);

        Button b = (Button) findViewById(R.id.button3);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

}
