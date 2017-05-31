package com.sanja.example.sunshineapp.home.di.modules;

import com.sanja.example.sunshineapp.home.core.api.APIService;
import com.sanja.example.sunshineapp.home.di.scopes.AppScope;
import com.sanja.example.sunshineapp.home.utils.constants.Constants;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class APIModule {

    @AppScope
    @Provides
    public APIService provideAPIService(Retrofit retrofit) {
        return retrofit.create(APIService.class);
    }

    @AppScope
    @Provides
    public Retrofit provideRetrofit(HttpUrl baseUrl, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @AppScope
    @Provides
    public HttpUrl provideBaseUrl(){
        return HttpUrl.parse(Constants.API_CURRENT_WEATHER_BASE_URL);
    }

    @AppScope
    @Provides
    public OkHttpClient provideOkHttpClient(){
        return new OkHttpClient.Builder()
                .build();
    }
}