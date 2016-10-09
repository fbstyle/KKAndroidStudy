package com.kkandroidstudy.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.kkandroidstudy.R;
import com.orhanobut.logger.Logger;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LoggerActivity extends AppCompatActivity {
    private String json = "{'name':'123','age':64}";
    private String invalidJson = "{'name':'123'";
    String xml = "<xml>123456</xml>";
    String invalidxml = "<xml>Test</xml>12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logger);
        Logger.d("Hello");
        Logger.d("Hello %s %d", "world", 5);
        Logger.i("Hello");
        Logger.w("Hello");
        Logger.e("Hello");
        Logger.wtf("Hello");

        //json
        Logger.json(json);
        //invalid json
        Logger.json(invalidJson);
        Logger.json(null);

        //xml
        Logger.xml(xml);
        //invalid xml
        Logger.xml(invalidxml);
        //null
        Logger.xml(null);

        logSet();

        logMap();

        logList();

        logArray();


    }


    public void logSet() {
        Set<String> set = new HashSet<>();
        set.add("key");
        set.add("key1");
        Logger.d(set);
    }

    public void logMap() {
        Map<String, String> map = new HashMap<>();
        map.put("key", "value");
        map.put("key2", "value2");
        Logger.d(map);
    }

    public void logList() {
        List<String> list = Arrays.asList("foo", "bar");
        Logger.d(list);
    }


    public void logArray() {
        double[][] doubles = {{1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33},
                {1.2, 1.6, 1.7, 30, 33}};

        Logger.d(doubles);
    }
}
