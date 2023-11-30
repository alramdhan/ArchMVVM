package id.wikrama.archmvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.AnticipateInterpolator;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import id.wikrama.archmvvm.databinding.ActivityMainBinding;
import id.wikrama.archmvvm.models.auth.LoginResponse;
import id.wikrama.archmvvm.ui.HomeActivity;
import id.wikrama.archmvvm.viewmodel.LoginViewModel;

public class MainActivity extends AppCompatActivity {
    TextInputEditText etUName, etPassword;
    MaterialButton bLogin;
    LoginViewModel mViewModel;
    ProgressBar pb;
    SharedPreferences prefs;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startSplash();
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        pb = findViewById(R.id.loadingLogin);
        etUName = findViewById(R.id.userName);
        etPassword = findViewById(R.id.userPwd);
        bLogin = findViewById(R.id.btnLogin);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        prefs = getSharedPreferences("SharedPrefs", MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefs.edit();

        mViewModel.getProgressBar().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer visibility) {
                if(visibility == 0) {
                    binding.setTextLogin("");
                } else {
                    binding.setTextLogin("Login");
                }
                pb.setVisibility(visibility);
            }
        });

        mViewModel.getLoginResult().observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if(loginResponse == null) {
                    Toast.makeText(MainActivity.this, "Field is required", Toast.LENGTH_SHORT).show();
                } else if(loginResponse.getStatus() != 200) {
                    Toast.makeText(MainActivity.this, loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                } else if(loginResponse.getStatus() == 200) {
                    Intent i = new Intent(MainActivity.this, HomeActivity.class);
                    prefEditor.putString("token", loginResponse.getToken());
                    prefEditor.apply();
                    startActivity(i);
                }
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewModel.login(etUName.getText()+"", etPassword.getText()+"");
            }
        });
    }

    private void startSplash() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            getSplashScreen().setOnExitAnimationListener(splashScreenView -> {
                ObjectAnimator zoomX = ObjectAnimator.ofFloat(
                    splashScreenView.getIconView(),
                    View.SCALE_X,
                    1f,
                    0.0f
                );
                zoomX.setInterpolator(new AnticipateInterpolator());
                zoomX.setDuration(1000L);
                zoomX.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        splashScreenView.animate()
                            .alpha(0f)
                            .setDuration(500L)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                splashScreenView.setVisibility(View.GONE);
                            }
                        });
                    }
                });

                ObjectAnimator zoomY = ObjectAnimator.ofFloat(
                    splashScreenView.getIconView(),
                    View.SCALE_Y,
                    1f,
                    0.0f
                );
                zoomY.setInterpolator(new AnticipateInterpolator());
                zoomY.setDuration(1000L);
                zoomY.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        splashScreenView.animate()
                            .alpha(0f)
                            .setDuration(500L)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                splashScreenView.setVisibility(View.GONE);
                            }
                        });
                    }
                });

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        zoomX.start();
                        zoomY.start();
                    }
                }, 2000);
            });
        }
    }
}