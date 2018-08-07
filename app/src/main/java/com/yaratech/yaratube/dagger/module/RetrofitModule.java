package com.yaratech.yaratube.dagger.module;

import com.yaratech.yaratube.Services;
import com.yaratech.yaratube.Utils;
import com.yaratech.yaratube.dagger.scope.MainScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Vah on 6/30/2018.
 */

@Module
public class RetrofitModule {

    @Provides
    @MainScope
    public Services getService(Retrofit retrofit){
        return retrofit.create(Services.class);
    }

    @Provides
    @MainScope
    public Retrofit getRetrofit(){
        return new Retrofit.Builder()
                .baseUrl(Utils.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
