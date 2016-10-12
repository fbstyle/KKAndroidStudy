package com.kkandroidstudy.activity;

import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kkandroidstudy.R;
import com.kkandroidstudy.iterface.RetrofitService;
import com.kkandroidstudy.network.RetrofitClient;
import com.kkandroidstudy.network.bean.PersonInfo;
import com.kkandroidstudy.network.bean.RequestInfo;
import com.orhanobut.logger.Logger;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Retrofit2Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_getone;
    private Button btn_gettwo;
    private Button btn_postone;
    private Button btn_posttwo;
    private Button btn_postthree;
    private Button btn_publiccode;
    private Button btn_cookie;
    private Button btn_sendCookie;
    private Button btn_sendCookie2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);

        btn_getone = (Button) findViewById(R.id.btn_getone);
        btn_getone.setOnClickListener(this);
        btn_gettwo = (Button) findViewById(R.id.btn_gettwo);
        btn_gettwo.setOnClickListener(this);

        btn_postone = (Button) findViewById(R.id.btn_postone);
        btn_postone.setOnClickListener(this);
        btn_posttwo = (Button) findViewById(R.id.btn_posttwo);
        btn_posttwo.setOnClickListener(this);
        btn_postthree = (Button) findViewById(R.id.btn_postthree);
        btn_postthree.setOnClickListener(this);
        btn_publiccode = (Button) findViewById(R.id.btn_publiccode);
        btn_publiccode.setOnClickListener(this);
        btn_cookie = (Button) findViewById(R.id.btn_cookie);
        btn_cookie.setOnClickListener(this);
        btn_sendCookie = (Button) findViewById(R.id.btn_sendCookie);
        btn_sendCookie.setOnClickListener(this);
        btn_sendCookie2 = (Button) findViewById(R.id.btn_sendCookie2);
        btn_sendCookie2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_getone:
                getOne();
                break;
            case R.id.btn_gettwo:
                getTwo();
                break;
            case R.id.btn_postone:
                postOne();
                break;
            case R.id.btn_posttwo:
                postTwo();
                break;
            case R.id.btn_postthree:
                postThree();
                break;
            case R.id.btn_publiccode:
                publicCode();
                break;
            case R.id.btn_cookie:
                testCookie();
                break;
            case R.id.btn_sendCookie:
                sendCookie();
                break;
            case R.id.btn_sendCookie2:
                sendCookie2();
                break;
        }
    }

    private void sendCookie2() {
        //修改baseUrl
        RetrofitClient.baseUrl = "http://192.168.0.247";
        RetrofitService service = RetrofitClient.getInstance(this);
        Call<String> cookieInfo = service.listClass();
        cookieInfo.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void sendCookie() {
        //修改baseUrl
        RetrofitClient.baseUrl = "http://192.168.0.247";
        RetrofitService service = RetrofitClient.getInstance(this);
        Map<String, String> map = new HashMap<>();
        map.put("pageNo", "1");
        map.put("pageSize", "5");
        map.put("textbookName", "小汤普森");
        map.put("tbType", "1");
        map.put("diffLevel", "1");
        Call<String> cookieInfo = service.list(map);
        cookieInfo.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    /**
     * 测试cookie
     */
    private void testCookie() {
        //修改baseUrl
        RetrofitClient.baseUrl = "http://192.168.0.247";
        RetrofitService service = RetrofitClient.getInstance(this);
        Map<String, String> map = new HashMap<>();
        map.put("userName", "我是老师");
        map.put("password", "123456");
        Call<String> cookieInfo = service.getCookieInfo(map);
        cookieInfo.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

    }

    private void publicCode() {
        RetrofitService service = RetrofitClient.getInstance(this);
        Map<String, String> map = new HashMap<>();
        map.put("app", "idcard.get");
        map.put("appkey", "10003");
        map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        map.put("format", "json");
        map.put("idcard", "110101199001011114");
        Call<PersonInfo> personInfo = service.getTwoPersonInfo(map);
        RetrofitClient.enqueue(personInfo, new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }

    private void postThree() {
        RetrofitService service = RetrofitClient.getInstance(this);
        RequestInfo requestInfo = new RequestInfo();
        requestInfo.setApp("idcard.get");
        requestInfo.setAppkey("10003");
        requestInfo.setSign("b59bc3ef6191eb9f747dd4e83c99f2a4");
        requestInfo.setFormat("json");
        requestInfo.setIdcard("110101199001011114");
        Call<PersonInfo> personInfo = service.postThreePersonInfo(requestInfo);
        personInfo.enqueue(new Callback<PersonInfo>() {
            @Override
            public void onResponse(Call<PersonInfo> call, Response<PersonInfo> response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<PersonInfo> call, Throwable t) {

            }
        });
    }

    private void postOne() {
        RetrofitService service = RetrofitClient.getInstance(this);
        Call<PersonInfo> personInfo = service.postOnePersonInfo("idcard.get", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json", "110101199001011114");
        personInfo.enqueue(new Callback<PersonInfo>() {
            @Override
            public void onResponse(Call<PersonInfo> call, Response<PersonInfo> response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<PersonInfo> call, Throwable t) {

            }
        });
    }

    private void postTwo() {
        RetrofitService service = RetrofitClient.getInstance(this);
        Map<String, String> map = new HashMap<>();
        map.put("app", "idcard.get");
        map.put("appkey", "10003");
        map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        map.put("format", "json");
        map.put("idcard", "110101199001011114");
        Call<PersonInfo> personInfo = service.postTwoPersonInfo(map);
        personInfo.enqueue(new Callback<PersonInfo>() {
            @Override
            public void onResponse(Call<PersonInfo> call, Response<PersonInfo> response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<PersonInfo> call, Throwable t) {

            }
        });
    }


    private void getTwo() {
        RetrofitService service = RetrofitClient.getInstance(this);
        Map<String, String> map = new HashMap<>();
        map.put("app", "idcard.get");
        map.put("appkey", "10003");
        map.put("sign", "b59bc3ef6191eb9f747dd4e83c99f2a4");
        map.put("format", "json");
        map.put("idcard", "110101199001011114");
        Call<PersonInfo> personInfo = service.getTwoPersonInfo(map);
        personInfo.enqueue(new Callback<PersonInfo>() {
            @Override
            public void onResponse(Call<PersonInfo> call, Response<PersonInfo> response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<PersonInfo> call, Throwable t) {

            }
        });
    }

    private void getOne() {
        RetrofitService service = RetrofitClient.getInstance(this);
        Call<PersonInfo> personInfo = service.getOnePersonInfo("idcard.get", "10003", "b59bc3ef6191eb9f747dd4e83c99f2a4", "json", "110101199001011114");
        personInfo.enqueue(new Callback<PersonInfo>() {
            @Override
            public void onResponse(Call<PersonInfo> call, Response<PersonInfo> response) {
                Logger.d(response.body().toString());
            }

            @Override
            public void onFailure(Call<PersonInfo> call, Throwable t) {

            }
        });
    }
}
