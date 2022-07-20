package io.osemwota.unit_test_sample.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import io.osemwota.unit_test_sample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding viewBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());
    }
}