package com.order.web.modules.redis.pojo;

public class RedisValue {
    public Object key;
    public Object type;
    public Object value;
    public Object expir;

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public Object getType() {
        return type;
    }

    public void setType(Object type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getExpir() {
        return expir;
    }

    public void setExpir(Object expir) {
        this.expir = expir;
    }
}
