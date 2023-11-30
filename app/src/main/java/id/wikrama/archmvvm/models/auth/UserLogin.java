package id.wikrama.archmvvm.models.auth;

import com.google.gson.annotations.SerializedName;

public class UserLogin {
    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String userPwd;

    public UserLogin(String uName, String pwd) {
        this.userName = uName;
        this.userPwd = pwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userEmail) {
        this.userName = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}
