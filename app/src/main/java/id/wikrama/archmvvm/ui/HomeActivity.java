package id.wikrama.archmvvm.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import id.wikrama.archmvvm.R;
import id.wikrama.archmvvm.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityHomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        prefs = getSharedPreferences("SharedPrefs", MODE_PRIVATE);

        String token = prefs.getString("token", "");
        binding.setToken(token);
        Log.i("info", "asu teh " + token);
    }
}