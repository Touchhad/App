package com.example.app;

import com.example.app.Service.LoginService;
import com.example.app.Service.RegisterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

        public static Retrofit getRetrofit(){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("http://cinema.areas.su")
                    .build();

            return retrofit;
        }


    public static RegisterService getRegister(){
        RegisterService registerService = getRetrofit().create(RegisterService.class);
        return registerService;
    }

    public static LoginService getLogin(){
            LoginService loginService = getRetrofit().create(LoginService.class);
            return loginService;
    }

}
