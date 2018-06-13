package ru.geekbrains.android3_6.model.cache;

import java.util.List;

import io.reactivex.Observable;
import ru.geekbrains.android3_6.model.entity.Repository;
import ru.geekbrains.android3_6.model.entity.User;

public interface ICache {
    void putUser(User user);

    Observable<User> getUser(String username);

    void putUserRepos(User user, List<Repository> repositories);

    Observable<List<Repository>> getUserRepos(User user);
}
