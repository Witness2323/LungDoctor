package com.sourcey.materiallogindemo;


public class News {
    private int imageId;          //使用id锁定水果图片
    private String title;     //对应的水果名字
    private String neirong;
    private String time;
    public News(int imageId, String title,String neirong,String time) {
        super();
        this.imageId = imageId;
        this.title =title;
        this.neirong = neirong;
        this.time =  time;
    }
    public int getImageId() {
        return imageId;
    }
    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getNeirong() {
        return neirong;
    }
    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time =time;
    }
}