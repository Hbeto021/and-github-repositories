package com.hbeto021.module.main.gitRepositories.presenter;

import com.hbeto021.module.main.domain.RepositoryOwner;
import com.hbeto021.module.main.gitRepositories.domain.GitRepositoriesModel;
import com.hbeto021.module.main.gitRepositories.view.GitRepositoriesView;


import java.util.List;

public class GitRepositoriesPresenterImpl implements GitRepositoriesPresenter {

    private GitRepositoriesView view;
    private final String ERROR_MESSAGE = "Não foi possível realizar a operação no momento, tente mais tarde.";

    public GitRepositoriesPresenterImpl(GitRepositoriesView view){
        this.view = view;
    }

    @Override
    public void showProgress() {
        view.showProgress();
    }

    @Override
    public void presentMessageToUser() {
        view.hideProgress();
        view.showMessageToUser(ERROR_MESSAGE);
    }

    @Override
    public void presentGitRepositoriesToUser(List<GitRepositoriesModel> gitRepositoriesModelList) {
        view.hideProgress();
        view.showGitRepositoriesToUser(gitRepositoriesModelList);
    }

    @Override
    public void presentUserInfo(RepositoryOwner repositoryOwner) {
        view.showUserInfo(repositoryOwner);
        if(repositoryOwner.getName() == null || repositoryOwner.getName().isEmpty()){
            view.hideOwnerName();
        }
    }
}
