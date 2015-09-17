package com.akiniyalocts.realmcommons;

import java.util.List;

/**
 *  Generic Otto result to return.
 */
public class OttoResult<T> {
    public List<T> items;

    public int type;

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
