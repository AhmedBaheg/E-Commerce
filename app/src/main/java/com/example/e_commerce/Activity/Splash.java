package com.example.e_commerce.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.e_commerce.R;
import com.example.e_commerce.ViewModel.AuthViewModel;
import com.example.e_commerce.databinding.ActivitySplashBinding;

public class Splash extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private Animation animation;
    private AuthViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        model = new ViewModelProvider(this).get(AuthViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);

        animation = AnimationUtils.loadAnimation(this, R.anim.animation);
        binding.imgSplash.startAnimation(animation);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (model.getCurrentUser() != null){
                    startActivity(new Intent(Splash.this, MainActivity.class));
                }else {
                    startActivity(new Intent(Splash.this, Login.class));
                }
                finish();
            }
        },5000);

    }
}