package ru.geekbrains.android3_6.presenter;

import android.annotation.SuppressLint;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import ru.geekbrains.android3_6.model.entity.User;
import ru.geekbrains.android3_6.model.repo.UsersRepo;
import ru.geekbrains.android3_6.view.MainView;
import ru.geekbrains.android3_6.view.RepoRowView;
import timber.log.Timber;

@InjectViewState
public class MainPresenter extends MvpPresenter<MainView> implements IRepoListPresenter {
    @Inject
    @Named("aaCache")
    public UsersRepo userRepo;
    private Scheduler scheduler;
    private User user;

    public MainPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().init();
    }

    @SuppressLint("CheckResult")
    public void loadInfo() {
        userRepo.getUser("AntonZarytski").subscribe(user -> {
            this.user = user;
            userRepo.getUserRepos(user)
                    .observeOn(scheduler)
                    .subscribe(userRepositories -> {
                        this.user.setRepos(userRepositories);
                        getViewState().hideLoading();
                        getViewState().setUsername(user.getLogin());
                        getViewState().showAvatar(user.getAvatarUrl());
                        getViewState().updateRepoList();
                    }, throwable -> {
                        Timber.e(throwable, "Failed to get user repos");
                        getViewState().showError(throwable.getMessage());
                        getViewState().hideLoading();
                    });
        }, throwable -> {
            Timber.e(throwable, "Failed to get user");
            scheduler.scheduleDirect(() -> {
                getViewState().showError(throwable.getMessage());
                getViewState().hideLoading();
            });
        });

    }

    public void onPermissionsGranted() {
        loadInfo();
    }

    @Override
    public void bindRepoListRow(int pos, RepoRowView rowView) {
        if (user != null) {
            rowView.setTitle(user.getRepos().get(pos).getName());
        }
    }

    @Override
    public int getRepoCount() {
        return user == null || user.getRepos() == null ? 0 : user.getRepos().size();
    }
}
