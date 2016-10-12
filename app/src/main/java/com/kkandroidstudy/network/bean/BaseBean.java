package com.kkandroidstudy.network.bean;

/**
 * Created by shiyan on 2016/10/11.
 */
public class BaseBean {
    private String code="305";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "BaseBean{" +
                "code='" + code + '\'' +
                '}';
    }
}
