package ru.geekbrains.android3_6.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.cache.AACache;
import ru.geekbrains.android3_6.model.cache.ICache;
import ru.geekbrains.android3_6.model.cache.PaperCache;
import ru.geekbrains.android3_6.model.cache.RealmCache;

@Module
public class CacheModule {

    @Provides
    @Named("realm")
    public ICache realmCache() {
        return new RealmCache();
    }

    @Provides
    @Named("paper")
    public ICache paperCache() {
        return new PaperCache();
    }

    @Provides
    @Named("aa")
    public ICache aACache() {
        return new AACache();
    }


}
