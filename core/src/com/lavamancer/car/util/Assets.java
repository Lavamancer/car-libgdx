package com.lavamancer.car.util;

import com.badlogic.gdx.assets.AssetManager;

public class Assets {

    private static Assets instance;
    private Assets() { }
    public static Assets getInstance() {
        if (instance == null) instance = new Assets();
        return instance;
    }

    private final AssetManager assetManager = new AssetManager();


    public <T> T load(String assetPath, Class<T> clazz) {
        if (!assetManager.contains(assetPath)) {
            assetManager.load(assetPath, clazz);
            assetManager.finishLoading();
        }
        return assetManager.get(assetPath, clazz);
    }

}
