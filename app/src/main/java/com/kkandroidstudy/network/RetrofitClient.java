package com.kkandroidstudy.network;

import android.content.Context;
import android.os.Environment;

import com.kkandroidstudy.BuildConfig;
import com.kkandroidstudy.iterface.RetrofitService;
import com.kkandroidstudy.network.bean.BaseBean;
import com.kkandroidstudy.util.NetWorkUtil;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
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
    public static String baseUrl = " http://api.k780.com:88";
    private static Context context;
    private static OkHttpClient okHttpClient;

    public static RetrofitService getInstance(Context context) {
        if (okHttpClient == null) {
            RetrofitClient.context = context;
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

            buildCache(builder, true);

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
//            buildCookie(builder);


            okHttpClient = builder.build();
        }


        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service;
    }

    private static void buildCache(OkHttpClient.Builder builder, boolean isCache) {
        if (!isCache)
            return;
        //设置缓存路径
        File cacheFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "kkcache");
        //设置缓存大小 20M
        Cache cache = new Cache(cacheFile, 20 * 1024 * 1024);
        builder.cache(cache).addInterceptor(cacheInterceptor);
    }

    /**
     * 缓存
     */
    static Interceptor cacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            //没网
            if (!NetWorkUtil.isConnectedByState(context)) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response response = chain.proceed(request);
            if (NetWorkUtil.isConnectedByState(context)) {
                int maxAge = 0;
                // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效
                        .build();
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    };


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

    /**
     * 配置Cookie
     *
     * @param builder
     */
    static public void buildCookie(OkHttpClient.Builder builder) {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        builder.cookieJar(new JavaNetCookieJar(cookieManager));
    }

    /**
     * 统一处理返回对象公共参数
     */
    static public void enqueue(Call call, final Callback callback) {
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, retrofit2.Response response) {
                BaseBean bean = (BaseBean) response.body();
                /**
                 * 公共参数处理
                 * eg
                 * if(bean.getCode().equals("xxx")){
                 *     ........
                 * }
                 */
                if (bean.getCode().equals("305")) {
                    Logger.d("公共返回code处理");
                }
                callback.onResponse(call, response);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }

}
