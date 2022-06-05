package com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen;

import android.app.Application;

//import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.di.AppComponent;
//import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.di.DaggerAppComponent;
import com.example.nhom13_huynhanhkiet_nguyengiahao_haquocthanhnguyen.di.DatabaseModule;

public class BaseApplication extends Application {
    private static BaseApplication INSTANCE;

    public static BaseApplication getApplication() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        INSTANCE = this;
        PrefHelper.initHelper(this);

    }
//
//    private void initializeDependencies() {
//        appComponent = DaggerAppComponent.builder()
//                .databaseModule(new DatabaseModule(this))
//                .build();
//    }
//
//    public AppComponent getAppComponent() {
//        return appComponent;
//    }

}
