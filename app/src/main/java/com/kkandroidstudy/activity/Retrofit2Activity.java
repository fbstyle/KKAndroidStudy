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
        }
    }

    private void postThree() {
        RetrofitService service = RetrofitClient.getInstance();
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
        RetrofitService service = RetrofitClient.getInstance();
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
        RetrofitService service = RetrofitClient.getInstance();
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
        RetrofitService service = RetrofitClient.getInstance();
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
        RetrofitService service = RetrofitClient.getInstance();
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
