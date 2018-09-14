package com.rumodigi.fanduelmobilechallenge.data.connect;

import com.rumodigi.fanduelmobilechallenge.data.entity.PlayerEntity;

import java.util.List;

import io.reactivex.Observable;

public interface RestApi {
    String API_BASE_URL = "https://gist.githubusercontent.com/liamjdouglas/bb40ee8721f1a9313c22c6ea0851a105/raw/6b6fc89d55ebe4d9b05c1469349af33651d7e7f1/Player.json";

    /**
     * Retrieves an {@link Observable} which will emit a List of {@link PlayerEntity}.
     */
    Observable<List<PlayerEntity>> playerEntityList();
}
