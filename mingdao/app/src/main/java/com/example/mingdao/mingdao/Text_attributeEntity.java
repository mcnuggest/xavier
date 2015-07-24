package com.example.mingdao.mingdao;

import java.util.List;

/**
 * Created by mingdao on 15/7/24.
 */
public class Text_attributeEntity {
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getField() {
        return field;
    }

    public void setField(List<String> field) {
        this.field = field;
    }

    public String type;
    public List<String> field;
}
