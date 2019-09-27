package com.example.mymvpframe.bean;

/**
 * Created by luoling on 2019/9/16.
 * description:
 */
public class GirlBean {

    private int icon;
    private String like;
    private String style;

    public GirlBean(int icon, String like, String style) {
        this.icon = icon;
        this.like = like;
        this.style = style;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
