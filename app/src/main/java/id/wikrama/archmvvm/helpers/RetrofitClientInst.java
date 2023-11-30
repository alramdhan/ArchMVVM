package id.wikrama.archmvvm.helpers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import id.wikrama.archmvvm.util.Constants;

public class RetrofitClientInst {
    private Retrofit retrofit;

    public Retrofit getInstance() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
