package com.hbeto021.module.main.gitRepositories.view;

import com.hbeto021.module.main.domain.RepositoryOwner;
import com.hbeto021.module.main.gitRepositories.domain.GitRepositoriesModel;

import java.util.List;

public interface GitRepositoriesView {

    void showMessage(String message);
    void showGitRepositories(List<GitRepositoriesModel> gitRepositories);
    void showUserInfo(RepositoryOwner repositoryOwner);
    void showProgress();
    void hideProgress();
    void hideOwnerName();
    void clearData();
}
