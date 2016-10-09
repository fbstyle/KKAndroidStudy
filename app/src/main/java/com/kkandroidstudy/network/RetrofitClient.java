package com.kkandroidstudy.network;

import com.kkandroidstudy.BuildConfig;
import com.kkandroidstudy.iterface.RetrofitService;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shiyan on 2016/10/9.
 */
public class RetrofitClient {
    /**
     * 接口测试地址
     * http://api.k780.com:88/?app=idcard.get&&appkey=10003&&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&&format=json&&idcard=110101199001011114
     * <p/>
     * {"success":"1","result":{"status":"ALREADY_ATT","par":"110101","idcard":"110101199001011114","born":"1990年01月01日","sex":"男","att":"北京市 东城区 ","postno":"100000","areano":"010","style_simcall":"中国,北京","style_citynm":"中华人民共和国,北京市"}}
     */
    private static String baseUrl = " http://api.k780.com:88";

    public static RetrofitService getInstance() {
        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        //设置超时
        builder.connectTimeout(15, TimeUnit.SECONDS);
        builder.readTimeout(20, TimeUnit.SECONDS);
        builder.writeTimeout(20, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);

        //OkHttp添加拦截器
        if (BuildConfig.DEBUG) {
            // Log信息拦截器
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            //设置 Debug Log 模式
            builder.addInterceptor(loggingInterceptor);
        }

        /**
         *  添加公共参数拦截器
         */
        //builder.addInterceptor(addQueryParameterInterceptor);


        /**
         * 添加请求头拦截器
         */
        //builder.addInterceptor(headerInterceptor);


        /**
         * 配置Cookie
         */
        //buildCookie(builder);


        OkHttpClient okHttpClient = builder.build();


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service;
    }

    /**
     * 公共参数
     */
    static Interceptor addQueryParameterInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originRequest = chain.request();
            HttpUrl modifiedUrl = originRequest.url().newBuilder().addQueryParameter("platform", "android")
                    .addQueryParameter("version", "1.0").build();
            originRequest = originRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(originRequest);
        }
    };

    /**
     * 请求头
     */
    static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header("AppType", "TPOS")
                    .method(originalRequest.method(), originalRequest.body());
            originalRequest = requestBuilder.build();
            return chain.proceed(originalRequest);
        }
    };

    static public void buildCookie(OkHttpClient.Builder builder) {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));
    }
}
