package com.akiniyalocts.dedo;

import android.content.Context;
import com.squareup.otto.Bus;

/**
 * Created by anthony on 9/17/15.
 */
public class Dedo {

    private static Context context;

    private static Bus bus;

    private static Dedo instance;

    public static Bus getBus(){
        if(bus != null)
            return bus;
        else{
            bus = new Bus();

            return bus;
        }
    }

    private boolean hasLocalStorage;

    private boolean replaceOnMigrate;

    public static void init(Dedo dedo){
            instance = dedo;
    }

    public Dedo(boolean hasLocalStorage, boolean replaceOnMigrate) {
        this.hasLocalStorage = hasLocalStorage;
        this.replaceOnMigrate = replaceOnMigrate;
    }

    public boolean hasLocalStorage() {
        return hasLocalStorage;
    }

    public void setHasLocalStorage(boolean hasLocalStorage) {
        this.hasLocalStorage = hasLocalStorage;
    }

    public boolean setReplaceOnMigrate() {
        return replaceOnMigrate;
    }

    public void setReplaceOnMigrate(boolean replaceOnMigrate) {
        this.replaceOnMigrate = replaceOnMigrate;
    }


    public static class Builder {
        private boolean hasLocalStorage = true;
        private boolean replaceOnMigrate = false;

        public Builder setHasLocalStorage(boolean hasLocalStorage) {
            this.hasLocalStorage = hasLocalStorage;
            return this;
        }

        public Builder setReplaceOnMigrate(boolean replaceOnMigrate) {
            this.replaceOnMigrate = replaceOnMigrate;
            return this;
        }

        public Dedo build() {
            return new Dedo(hasLocalStorage, replaceOnMigrate);
        }
    }
}
