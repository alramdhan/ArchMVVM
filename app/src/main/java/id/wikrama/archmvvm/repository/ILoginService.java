package id.wikrama.archmvvm.repository;

import id.wikrama.archmvvm.models.auth.LoginResponse;
import id.wikrama.archmvvm.models.auth.UserLogin;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ILoginService {
    @POST("/auth/login")
    Call<LoginResponse> login(@Body UserLogin body);
}
