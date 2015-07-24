package com.example.mingdao.mingdao;

import java.util.List;

/**
 * Created by mingdao on 15/7/24.
 */
public class Option {
    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private List<Member>members;
    private String name;
    private String value;
}
