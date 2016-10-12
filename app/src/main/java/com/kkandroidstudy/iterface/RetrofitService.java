package com.kkandroidstudy.iterface;

import com.kkandroidstudy.network.bean.PersonInfo;
import com.kkandroidstudy.network.bean.RequestInfo;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by shiyan on 2016/10/9.
 */
public interface RetrofitService {
    /**
     * http://api.k780.com:88/?app=idcard.get&&appkey=10003&&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&&format=json&&idcard=110101199001011114
     */

    @GET("/")
    Call<PersonInfo> getOnePersonInfo(@Query("app") String app, @Query("appkey") String appkey, @Query("sign") String sign, @Query("format") String format, @Query("idcard") String idcard);

    @GET("/")
    Call<PersonInfo> getTwoPersonInfo(@QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("/")
    Call<PersonInfo> postOnePersonInfo(@Field("app") String app, @Field("appkey") String appkey, @Field("sign") String sign, @Field("format") String format, @Field("idcard") String idcard);

    @FormUrlEncoded
    @POST("/")
    Call<PersonInfo> postTwoPersonInfo(@FieldMap Map<String, String> map);

    @POST("/")
    Call<PersonInfo> postThreePersonInfo(@Body RequestInfo requestInfo);

    /**
     * 示例: http://192.168.0.247/kuke/kukie/user/teacherLogin?userName=我是老师&password=123456
     */
    @GET("/kuke/kukie/user/teacherLogin")
    Call<String> getCookieInfo(@QueryMap Map<String, String> map);

    /**
     * 示例: "http://192.168.0.247/kuke/kukie/textbook/list?pageNo=1&pageSize=5&textbookName=小汤普森&tbType=1&diffLevel=1"
     */
    @GET("/kuke/kukie/textbook/list")
    Call<String> list(@QueryMap Map<String, String> map);

    /**
     * 示例: "http://192.168.0.247/kuke/kukie/clazz/list4manage"
     */
    @GET("/kuke/kukie/clazz/list4manage")
    Call<String> listClass();
}
