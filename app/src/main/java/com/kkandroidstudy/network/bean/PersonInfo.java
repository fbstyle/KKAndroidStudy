package com.kkandroidstudy.network.bean;

/**
 * Created by shiyan on 2016/10/9.
 */
public class PersonInfo extends BaseBean{
    private String success;
    private Result result;

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }


    @Override
    public String toString() {
        return "PersonInfo{" +
                "success='" + success + '\'' +
                ", result=" + result +
                '}';
    }
}
