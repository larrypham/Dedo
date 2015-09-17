package com.akiniyalocts.dedo.realm;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.akiniyalocts.dedo.BaseApplication;
import com.akiniyalocts.dedo.Dedo;
import com.akiniyalocts.dedo.OttoResult;
import com.squareup.otto.Bus;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;


public abstract class BaseRealmService<T> extends IntentService implements Callback<List<T>> {
    private static final String TAG = BaseRealmService.class.getSimpleName();

    public static final String ONLINE_KEY = "key::online";

    /**
     * Instantiate your realm manager with the appropriate class
     */
    public abstract void initRealmManager();

    /**
     * Your network update task.
     */
    public abstract void doNetworkedTask();

    /**
     * This gets around generics being lost over the bus. Give your type as an int.
     * @return OttoResult type
     */
    public abstract T getOttoType();

    /**
     * Set intent params if needed. Happens before Network Task and Offline select.
     */
    public abstract void setParams();

    public BaseRealmService() {
        super("BaseRealmService");
    }


    @Override
    public void failure(RetrofitError error) {
        Log.w(TAG, error.getMessage());
        postEvent();
    }

    /**
     * Starting intent
     */
    public Intent intent;

    /**
     * Your realm manager instance
     */
    protected RealmManager realmManager;

    /**
     * Eventbus
     * @return Bus
     */
    public Bus getBus(){
        return Dedo.getBus();
    }

    @Override protected void onHandleIntent(Intent intent) {
        this.intent = intent;

        initRealmManager();

        setParams();

        if(intent.getBooleanExtra(ONLINE_KEY, true))
            doNetworkedTask();
        else
            doOfflineSelect();

    }


    /**
     *
     * @return List<T> where T is the class from realm
     */
    public List<T> doOfflineSelect(){
        return realmManager.select();
    }

    /**
     * Send event on bus. Listen in according UI thread.
     */
    public void postEvent(){
        OttoResult<T> ottoResult = new OttoResult<>();
        ottoResult.setType(getOttoType());
        ottoResult.setItems(doOfflineSelect());
        getBus().post(ottoResult);
    }

}


