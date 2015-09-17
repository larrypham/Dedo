package com.akiniyalocts.realmcommons.realm;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by anthony on 8/21/15.
 */
public class RealmManager<T extends RealmObject> {

    protected Context mContext;

    protected Realm realm;

    protected Class clazz;


    private Realm getRealmFromConfig(Context context){
            RealmConfiguration.Builder realmBuilder = new RealmConfiguration.Builder(context)
                    .deleteRealmIfMigrationNeeded();
        return Realm.getInstance(realmBuilder.build());
    }

    /**
     * RealmManager wraps your query and insert/update statements based on class type
     * @param context application context
     * @param clazz class for table in realm
     */
    public RealmManager(Context context, Class clazz){
        mContext = context;
        this.clazz = clazz;

        realm = getRealmFromConfig(mContext);

    }

    public RealmResults<T> select(){
        Realm realm = getRealmFromConfig(mContext);

        return realm.where(clazz).findAll();
    }

    @SuppressWarnings("Unchecked")
    public RealmResults<T> queryByFieldname(@NonNull String fieldName, @NonNull String query){
        if(!fieldName.trim().isEmpty() && !query.trim().isEmpty()){
            return realm.where(clazz).contains(fieldName, query, false).findAll();
        }
            return null;
    }

    public RealmResults<T> queryByFieldname(@NonNull String fieldName, int query){
        if(!fieldName.trim().isEmpty()){
            return realm.where(clazz).equalTo(fieldName, query).findAll();
        }
        return null;
    }

    public void updateMultiple(final List<T> realmObjects){
        Realm realm = getRealmFromConfig(mContext);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(realmObjects);

            }
        });
    }

}
