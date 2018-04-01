package com.fuyin.model;

import java.util.List;

/**
 * Description
 * Created by Administrator
 * Time 2018/1/3  21:49
 */

public class Girl {
    private String name;
    private String address;
    private List<String> imageList;
    private int type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }
}
