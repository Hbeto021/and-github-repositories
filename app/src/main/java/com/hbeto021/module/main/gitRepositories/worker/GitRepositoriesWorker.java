package com.hbeto021.module.main.gitRepositories.worker;

import com.hbeto021.module.main.gitRepositories.interactor.GitRepositoriesInteractor;

public interface GitRepositoriesWorker {

    void getGitRepositories(String url, GitRepositoriesInteractor gitRepositoriesInteractor);

    void getGitUserInfo(String url, GitRepositoriesInteractor gitRepositoriesInteractor);
}
