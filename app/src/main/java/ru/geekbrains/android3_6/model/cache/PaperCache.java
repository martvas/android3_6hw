package ru.geekbrains.android3_6.model.cache;

import java.util.List;

import io.paperdb.Paper;
import io.reactivex.Observable;
import ru.geekbrains.android3_6.model.entity.Repository;
import ru.geekbrains.android3_6.model.entity.User;

public class PaperCache implements ICache {

    @Override
    public void putUser(User user) {
        Paper.book("users").write(user.getLogin(), user);
    }

    @Override
    public Observable<User> getUser(String username) {
        if (!Paper.book("users").contains(username)) {
            return Observable.error(new RuntimeException("No such user in cache: " + username));
        }
        return Observable.fromCallable(() -> Paper.book("users").read(username));
    }

    @Override
    public void putUserRepos(User user, List<Repository> repositories) {
        Paper.book("repos").write(user.getLogin(), repositories);
    }

    @Override
    public Observable<List<Repository>> getUserRepos(User user) {
        if (!Paper.book("repos").contains(user.getLogin())) {
            return Observable.error(new RuntimeException("No repos for such url: " + user.getReposUrl()));
        }
        return Observable.fromCallable(() -> Paper.book("repos").read(user.getLogin()));
    }
}
