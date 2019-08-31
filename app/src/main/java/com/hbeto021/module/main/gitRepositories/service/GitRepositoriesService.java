package com.hbeto021.module.main.gitRepositories.service;

import com.hbeto021.module.main.domain.RepositoryOwner;
import com.hbeto021.module.main.gitRepositories.domain.GitRepositoriesModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitRepositoriesService {

    @GET("users/{user}/repos")
    Call<List<GitRepositoriesModel>> getGitRepositoriesModel(@Path("user") String user);

    @GET("users/{user}")
    Call<RepositoryOwner> getGitUserInfo(@Path("user") String user);
}
