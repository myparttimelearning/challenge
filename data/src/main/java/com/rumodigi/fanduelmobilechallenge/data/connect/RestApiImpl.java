package com.rumodigi.fanduelmobilechallenge.data.connect;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;
import com.rumodigi.fanduelmobilechallenge.data.entity.PlayerEntity;
import com.rumodigi.fanduelmobilechallenge.data.entity.mapping.PlayerEntityJsonMap;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;

/**
 * {@link RestApi} implementation for retrieving data from the network.
 */

public class RestApiImpl implements RestApi {

    private final Context context;
    private final PlayerEntityJsonMap playerEntityJsonMap;

    /**
     * Constructor
     *
     * @param context {@link android.content.Context}
     * @param playerEntityJsonMap {@link PlayerEntityJsonMap}.
     */

    public RestApiImpl(Context context, PlayerEntityJsonMap playerEntityJsonMap) {
        if (context == null || playerEntityJsonMap == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.playerEntityJsonMap = playerEntityJsonMap;
    }

    @Override
    public Observable<List<PlayerEntity>> playerEntityList(){

        return Observable.create(emitter -> {
            if(isThereInternetConnection()){
                try{
                    String playerJson = getPlayerEntitiesFromApi();
                    if(playerJson != null){
                        emitter.onNext(playerEntityJsonMap.transformPlayerEntityCollection(
                                new Gson().fromJson(playerJson, JSONObject.class).get("players").toString()));
                        emitter.onComplete();
                    } else {
                        emitter.onError(new Exception());
                    }
                }catch (Exception e){
                    emitter.onError(e.getCause());
                }
            } else {
                emitter.onError(new Exception());
            }
        });
    }

    private String getPlayerEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(API_BASE_URL).requestSyncCall();
    }

    /**
     * Checks if the device has any active internet connection.
     *
     * @return true device with internet connection, otherwise false.
     */
    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
