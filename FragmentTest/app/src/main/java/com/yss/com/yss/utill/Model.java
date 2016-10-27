package com.yss.com.yss.utill;

/**
 * Created by Administrator on 2016/10/27.
 */
public class Model {
    int weixinImg;//微信头像
    String weixinTltile;//用户名
    String time;//时间

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getWeixinImg() {
        return weixinImg;
    }

    public void setWeixinImg(int weixinImg) {
        this.weixinImg = weixinImg;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getWeixinTltile() {
        return weixinTltile;
    }

    public void setWeixinTltile(String weixinTltile) {
        this.weixinTltile = weixinTltile;
    }

    String content;//内容简述
}
