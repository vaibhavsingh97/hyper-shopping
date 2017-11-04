package com.example.vaibhav.hypershopping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hypertrack.lib.HyperTrack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HyperTrack.initialize(this, "pk_test_96219ff5ed8a84e70b538a99499b748fe9a5c888");
    }

}
