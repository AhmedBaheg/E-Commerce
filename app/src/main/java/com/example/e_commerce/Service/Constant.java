package com.example.e_commerce.Service;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Constant {

    public static String USER = "User";

    public static String getUID(){
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }

}
