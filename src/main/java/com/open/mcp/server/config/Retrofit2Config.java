package com.open.mcp.server.config;

import com.open.mcp.server.service.NewsApi;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassDescription:
 * @Author: song.chang
 * @Created: 2025/3/12 16:41
 */

@Configuration
public class Retrofit2Config {

    @Value("${news.api.key}")
    private String newApiKey;


    @Bean
    public NewsApi newsApi() {
        // 创建日志拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // 添加统一请求头拦截器
        Interceptor headerInterceptor = chain -> {
            Request originalRequest = chain.request();
            Request newRequest = originalRequest.newBuilder()
                    .header("Authorization", "Bearer " + newApiKey)
                    .build();
            return chain.proceed(newRequest);
        };

        // 创建OkHttpClient并添加日志拦截器
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://vj.uhc.cn/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(NewsApi.class);
    }

}
