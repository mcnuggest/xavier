package com.example.mingdao.mingdao;

/**
 * Created by mingdao on 15/7/24.
 */
public class Member {
    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String avatar;
    private String id;
    private String name;
}
