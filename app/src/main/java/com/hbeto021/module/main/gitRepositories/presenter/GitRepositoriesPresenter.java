package com.hbeto021.module.main.gitRepositories.presenter;

import com.hbeto021.module.main.domain.RepositoryOwner;
import com.hbeto021.module.main.gitRepositories.domain.GitRepositoriesModel;

import java.util.List;

public interface GitRepositoriesPresenter {

    void showProgress();
    void presentMessageToUser();
    void presentGitRepositoriesToUser(List<GitRepositoriesModel> gitRepositoriesModelList);
    void presentUserInfo(RepositoryOwner repositoryOwner);
}
