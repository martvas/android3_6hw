package ru.geekbrains.android3_6.presenter;

import ru.geekbrains.android3_6.view.RepoRowView;

/**
 * Created by stanislav on 3/15/2018.
 */

public interface IRepoListPresenter {
    void bindRepoListRow(int pos, RepoRowView rowView);

    int getRepoCount();
}
