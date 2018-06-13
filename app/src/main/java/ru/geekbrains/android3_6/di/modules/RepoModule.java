package ru.geekbrains.android3_6.di.modules;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import ru.geekbrains.android3_6.model.api.ApiService;
import ru.geekbrains.android3_6.model.cache.ICache;
import ru.geekbrains.android3_6.model.repo.UsersRepo;

@Module(includes = {ApiModule.class, CacheModule.class})
public class RepoModule {

    @Provides
    @Named("realmCache")
    public UsersRepo usersRepo(@Named("realm") ICache cache, ApiService api) {
        return new UsersRepo(cache, api);
    }

    @Provides
    @Named("paperCache")
    public UsersRepo usersRepo2(@Named("paper") ICache cache, ApiService api) {
        return new UsersRepo(cache, api);
    }

    @Provides
    @Named("aaCache")
    public UsersRepo usersRepo3(@Named("aa") ICache cache, ApiService api) {
        return new UsersRepo(cache, api);
    }
}
