package id.wikrama.archmvvm.viewmodel;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import id.wikrama.archmvvm.MainActivity;
import id.wikrama.archmvvm.models.auth.LoginResponse;
import id.wikrama.archmvvm.models.auth.UserLogin;
import id.wikrama.archmvvm.repository.LoginRepository;

public class LoginViewModel extends ViewModel {
    MutableLiveData<Integer> mProgressBar = new MutableLiveData<>();
    MutableLiveData<LoginResponse> mLoginResultMD = new MutableLiveData<>();
    LoginRepository repo;

    public LoginViewModel() {
        mProgressBar.postValue(View.GONE);
        mLoginResultMD.postValue(new LoginResponse(0, ""));
        repo = new LoginRepository();
    }

    public void login(String username, String password) {
        mProgressBar.postValue(View.VISIBLE);
        Log.i("info", "username" + username);
        Log.i("info", "password " + password);
        if(username.isEmpty() || password.isEmpty()) {
            mLoginResultMD.postValue(null);
            mProgressBar.postValue(View.GONE);
        } else {
            repo.login(new UserLogin(username, password), new LoginRepository.ILoginResponse() {
                @Override
                public void onResponse(LoginResponse response) {
                    mLoginResultMD.postValue(response);
                    mProgressBar.postValue(View.GONE);
                }

                @Override
                public void onFailure(Throwable t) {
                    mLoginResultMD.postValue(new LoginResponse(500, t.getLocalizedMessage()));
                    mProgressBar.postValue(View.GONE);
                }
            });
        }
    }

    public LiveData<LoginResponse> getLoginResult() {
        return mLoginResultMD;
    }

    public LiveData<Integer> getProgressBar() {
        return mProgressBar;
    }
}
