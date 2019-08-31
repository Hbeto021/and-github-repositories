package com.hbeto021.module.main.gitRepositories.interactor;

import com.hbeto021.module.main.domain.RepositoryOwner;
import com.hbeto021.module.main.gitRepositories.domain.GitRepositoriesModel;
import com.hbeto021.module.main.gitRepositories.presenter.GitRepositoriesPresenter;

import java.util.List;

public interface GitRepositoriesInteractor {

    void getGitRepositories(String user);
    void onSuccess(List<GitRepositoriesModel> gitRepositoriesModelList);
    void onFailure();

    void getUserInfo(String user);
    void onSuccessUserInfo(RepositoryOwner repositoryOwner);
    void onFailureUserInfo();
}
