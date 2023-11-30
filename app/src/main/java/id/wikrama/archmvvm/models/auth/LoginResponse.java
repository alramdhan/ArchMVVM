package id.wikrama.archmvvm.models.auth;

import com.google.gson.annotations.SerializedName;

import id.wikrama.archmvvm.models.BaseResponse;

public class LoginResponse extends BaseResponse {
    @SerializedName("id")
    String id;
    @SerializedName("username")
    String username;
    @SerializedName("token")
    String token;

    public LoginResponse(int status, String message) {
        super(status, message);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
