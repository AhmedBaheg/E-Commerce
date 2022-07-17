package com.example.e_commerce.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.e_commerce.R;
import com.example.e_commerce.Service.Validation;
import com.example.e_commerce.ViewModel.AuthViewModel;
import com.example.e_commerce.databinding.ActivityRegisterBinding;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private ActivityRegisterBinding binding;
    private AuthViewModel model;
    private Validation validation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        model = new ViewModelProvider(this).get(AuthViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.tvLogin.setOnClickListener(this);
        binding.btnRegister.setOnClickListener(this);

        validation = new Validation();

    }

    private void register() {
        String email = binding.edEmail.getEditText().getText().toString();
        String password = binding.edPassword.getEditText().getText().toString();
        String fullName = binding.edFullName.getEditText().getText().toString();

        if (!validation.validationFullName(fullName, binding.edFullName)) {
            binding.edFullName.requestFocus();
        } else if (!validation.validationEmail(email, binding.edEmail)) {
            binding.edEmail.requestFocus();
        } else if (!validation.validationPassword(password, binding.edPassword)) {
            binding.edPassword.requestFocus();
        } else {
            
            model.register(email, password, fullName);
            model.getFirebaseUserMutableLiveData().observe(this, new Observer<FirebaseUser>() {
                @Override
                public void onChanged(FirebaseUser firebaseUser) {
                    if (firebaseUser != null){
                        startActivity(new Intent(Register.this, MainActivity.class));
                    }else {
                        startActivity(new Intent(Register.this, Login.class));
                    }
                    finish();
                }
            });
        }

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Register:
                startActivity(new Intent(Register.this, Login.class));
                finish();
                break;
            case R.id.btn_Register:
                register();
                break;
        }
    }
}