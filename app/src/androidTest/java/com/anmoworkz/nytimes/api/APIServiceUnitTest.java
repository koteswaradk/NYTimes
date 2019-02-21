package com.anmoworkz.nytimes.api;

import com.anmoworkz.nytimes.network.APIInterface;
import com.anmoworkz.nytimes.utils.Constants;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@RunWith(JUnit4.class)
public class APIServiceUnitTest {
    public static Retrofit retrofit;
    @Before
    public Retrofit createService(){
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

}

