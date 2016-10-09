package com.kkandroidstudy.network.bean;

/**
 * Created by shiyan on 2016/10/9.
 */
public class Result {
    private String postno;

    private String par;

    private String born;

    private String sex;

    private String style_simcall;

    private String status;

    private String att;

    private String idcard;

    private String style_citynm;

    private String areano;

    public String getPostno() {
        return postno;
    }

    public void setPostno(String postno) {
        this.postno = postno;
    }

    public String getPar() {
        return par;
    }

    public void setPar(String par) {
        this.par = par;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStyle_simcall() {
        return style_simcall;
    }

    public void setStyle_simcall(String style_simcall) {
        this.style_simcall = style_simcall;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAtt() {
        return att;
    }

    public void setAtt(String att) {
        this.att = att;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getStyle_citynm() {
        return style_citynm;
    }

    public void setStyle_citynm(String style_citynm) {
        this.style_citynm = style_citynm;
    }

    public String getAreano() {
        return areano;
    }

    public void setAreano(String areano) {
        this.areano = areano;
    }

    @Override
    public String toString() {
        return "Result{" +
                "postno='" + postno + '\'' +
                ", par='" + par + '\'' +
                ", born='" + born + '\'' +
                ", sex='" + sex + '\'' +
                ", style_simcall='" + style_simcall + '\'' +
                ", status='" + status + '\'' +
                ", att='" + att + '\'' +
                ", idcard='" + idcard + '\'' +
                ", style_citynm='" + style_citynm + '\'' +
                ", areano='" + areano + '\'' +
                '}';
    }
}
