package com.example.e_commerce.Activity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.e_commerce.R;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class MainActivity extends AppCompatActivity {

    private ChipNavigationBar chipNavigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Sprite doubleBounce = new DoubleBounce();
//        progressBar.setIndeterminateDrawable(doubleBounce);
//        progressBar.setVisibility(View.VISIBLE);

        initializeBottomNavigation();

    }

    private void initializeBottomNavigation(){
        chipNavigationBar = findViewById(R.id.nav_bottom);
        chipNavigationBar.setItemSelected(R.id.menu_home, true);
        chipNavigationBar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                switch (i){
                    case R.id.menu_home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_favorites:
                        Toast.makeText(MainActivity.this, "Favourite", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_profile:
                        Toast.makeText(MainActivity.this, "Profile", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_settings:
                        Toast.makeText(MainActivity.this, "Setting", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

}