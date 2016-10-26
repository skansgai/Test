package com.yss.news.utill;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/10/26.
 */
public class NewsModel implements Serializable{
    private static final long seriaVersionUID=1;
    int showstyle;//这个是显示样式，-1，0，1分别代表三种无图片，有一张在右边，有三张在下边不同新闻标题显示
    String newstitle;//这个是新闻标题
    public boolean isTuijie() {
        return tuijie;
    }
    public void setTuijie(boolean tuijie) {
        this.tuijie = tuijie;
    }

    boolean style;//这个是判断该新闻是否是热点的
    boolean tuijie;//设置是否为推介
    String medianame;//新闻媒体单位
    String discussnumber;//评论数量
    String time;//发表到此时的时间

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;

    }

    int titleImage;//标题图片
    int image1;

    public int getImage3() {
        return image3;
    }

    public void setImage3(int image3) {
        this.image3 = image3;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public int getImage1() {
        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    int image2;
    int image3;

    public int getTitleImage() {
        return titleImage;
    }

    public void setTitleImage(int titleImage) {
        this.titleImage = titleImage;
    }

    public int getShowstyle() {
        return showstyle;
    }

    public void setShowstyle(int showstyle) {
        this.showstyle = showstyle;
    }

    public String getNewstitle() {
        return newstitle;
    }

    public void setNewstitle(String newstitle) {
        this.newstitle = newstitle;
    }

    public boolean isStyle() {
        return style;
    }

    public void setStyle(boolean style) {
        this.style = style;
    }

    public String getMedianame() {
        return medianame;
    }

    public void setMedianame(String medianame) {
        this.medianame = medianame;
    }

    public String getDiscussnumber() {
        return discussnumber;
    }

    public void setDiscussnumber(String discussnumber) {
        this.discussnumber = discussnumber;
    }

}
