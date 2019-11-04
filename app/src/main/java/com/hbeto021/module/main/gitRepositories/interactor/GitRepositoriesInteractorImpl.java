package com.hbeto021.module.main.gitRepositories.interactor;

import com.hbeto021.module.main.domain.RepositoryOwner;
import com.hbeto021.module.main.gitRepositories.domain.GitRepositoriesModel;
import com.hbeto021.module.main.gitRepositories.presenter.GitRepositoriesPresenter;
import com.hbeto021.module.main.gitRepositories.presenter.GitRepositoriesPresenterImpl;
import com.hbeto021.module.main.gitRepositories.view.GitRepositoriesView;
import com.hbeto021.module.main.gitRepositories.worker.GitRepositoriesWorker;
import com.hbeto021.module.main.gitRepositories.worker.GitRepositoriesWorkerImpl;

import java.util.List;

public class GitRepositoriesInteractorImpl implements GitRepositoriesInteractor {

    private GitRepositoriesWorker gitRepositoriesWorker;
    private GitRepositoriesPresenter gitRepositoriesPresenter;

    public GitRepositoriesInteractorImpl(GitRepositoriesPresenter gitRepositoriesPresenter){
        this.gitRepositoriesPresenter = gitRepositoriesPresenter;
        gitRepositoriesWorker = new GitRepositoriesWorkerImpl();
    }

    @Override
    public void start(String githubUser) {
        if(!githubUser.trim().isEmpty()){
            gitRepositoriesPresenter.showProgress();
            getGitRepositories(githubUser);
            getUserInfo(githubUser);
        } else {
            gitRepositoriesPresenter.clearData();
            gitRepositoriesPresenter.presentMessageToUser();
        }
    }

    @Override
    public void getGitRepositories(String user) {
        gitRepositoriesWorker.getGitRepositories(user, this);
    }

    @Override
    public void getUserInfo(String user) {
        gitRepositoriesWorker.getGitUserInfo(user, this);
    }

    @Override
    public void onSuccess(List<GitRepositoriesModel> response) {
        gitRepositoriesPresenter.presentGitRepositoriesToUser(response);
    }

    @Override
    public void onFailure() {
        gitRepositoriesPresenter.clearData();
        gitRepositoriesPresenter.presentMessageToUser();
    }

    @Override
    public void onSuccessUserInfo(RepositoryOwner repositoryOwner) {
        gitRepositoriesPresenter.presentUserInfo(repositoryOwner);
    }

    @Override
    public void onFailureUserInfo() {
        gitRepositoriesPresenter.clearData();
        gitRepositoriesPresenter.presentMessageToUser();
    }
}
