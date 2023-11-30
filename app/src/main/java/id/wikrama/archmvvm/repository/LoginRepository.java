package id.wikrama.archmvvm.repository;

import android.util.Log;

import id.wikrama.archmvvm.helpers.RetrofitClientInst;
import id.wikrama.archmvvm.models.auth.LoginResponse;
import id.wikrama.archmvvm.models.auth.UserLogin;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRepository {
    private final RetrofitClientInst client = new RetrofitClientInst();

    public void login(UserLogin body, ILoginResponse loginResponse) {
        ILoginService loginService = client.getInstance().create(ILoginService.class);
        Call<LoginResponse> initiateLogin = loginService.login(body);

        initiateLogin.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.i("info", "response " + response);
                assert response.body() != null;
                response.body().setStatus(response.code());
                response.body().setMessage(response.message());

                if(response.isSuccessful()) {
                    loginResponse.onResponse(response.body());
                } else {
                    loginResponse.onFailure(new Throwable(response.message()));
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                loginResponse.onFailure(t);
            }
        });
    }

    public interface ILoginResponse {
        void onResponse(LoginResponse response);
        void onFailure(Throwable t);
    }
}
