package com.akiniyalocts.dedo;

import java.util.List;

/**
 *  Generic Otto result to return.
 */
public class OttoResult<T> {
    public List<T> items;

    public T type;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public T getType() {
        return type;
    }

    public void setType(T type) {
        this.type = type;
    }
}
