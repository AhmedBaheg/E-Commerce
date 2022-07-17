package com.example.e_commerce.Repo;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.e_commerce.Model.User;
import com.example.e_commerce.Service.Constant;
import com.example.e_commerce.ViewModel.AuthViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AuthRepository {

    private Application application;
    private MutableLiveData<FirebaseUser> userMutableLiveData;
    private DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constant.USER);
    private FirebaseAuth auth = FirebaseAuth.getInstance();

    public AuthRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<FirebaseUser> getUserMutableLiveData() {
        if (userMutableLiveData == null) {
            userMutableLiveData = new MutableLiveData<>();
        }
        return userMutableLiveData;
    }

    public FirebaseUser getCurrentUser() {
        return auth.getCurrentUser();
    }

    public void register(String email, String password, String fullName) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    getUserMutableLiveData().postValue(getCurrentUser());
                    User user = new User(fullName, email);
                    ref.child(Constant.getUID()).setValue(user);
                } else {
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    getUserMutableLiveData().postValue(getCurrentUser());
                } else {
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}
