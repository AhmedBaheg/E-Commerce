package com.example.e_commerce.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.e_commerce.Repo.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {

    private AuthRepository authRepository;
    private Application application;

    public AuthViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);

    }

    public FirebaseUser getCurrentUser() {
        return authRepository.getCurrentUser();
    }

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return authRepository.getUserMutableLiveData();
    }

    public void register(String email, String password, String fullName){authRepository.register(email, password, fullName);}

    public void login(String email, String password){authRepository.login(email, password);}

}
