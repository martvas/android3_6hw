package ru.geekbrains.android3_6;

import com.activeandroid.ActiveAndroid;

import io.paperdb.Paper;
import io.realm.Realm;
import ru.geekbrains.android3_6.di.modules.AppModule;
import ru.geekbrains.android3_6.model.di.AppComponent;
import ru.geekbrains.android3_6.model.di.DaggerAppComponent;

public class App extends com.activeandroid.app.Application {
    private static App instance;

    private AppComponent appComponent;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Paper.init(this);
        Realm.init(this);
        ActiveAndroid.initialize(this);

        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
