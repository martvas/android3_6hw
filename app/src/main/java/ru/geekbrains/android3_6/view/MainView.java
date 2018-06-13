package ru.geekbrains.android3_6.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface MainView extends MvpView {
    void init();

    void showAvatar(String avatarUrl);

    void showError(String message);

    void setUsername(String username);

    void showLoading();

    void hideLoading();

    void updateRepoList();

    void checkPermissions();
}
