package ru.geekbrains.android3_6.model.repo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import ru.geekbrains.android3_6.NetworkStatus;
import ru.geekbrains.android3_6.model.api.ApiService;
import ru.geekbrains.android3_6.model.cache.ICache;
import ru.geekbrains.android3_6.model.entity.Repository;
import ru.geekbrains.android3_6.model.entity.User;

public class UsersRepo {
    ICache cache;
    ApiService api;

    public UsersRepo(ICache cache, ApiService api) {
        this.cache = cache;
        this.api = api;
    }

    public Observable<User> getUser(String username) {
        if (NetworkStatus.isOnline()) {
            return api.getUser(username).subscribeOn(Schedulers.io()).map(user -> {
                cache.putUser(user);
                return user;
            });
        } else {
            return cache.getUser(username);
        }
    }

    public Observable<List<Repository>> getUserRepos(User user) {
        if (NetworkStatus.isOnline()) {
            return api.getUserRepos(user.getReposUrl()).subscribeOn(Schedulers.io()).map(userRepositories -> {
                cache.putUserRepos(user, userRepositories);
                return userRepositories;
            });
        } else {
            return cache.getUserRepos(user);
        }
    }
}
