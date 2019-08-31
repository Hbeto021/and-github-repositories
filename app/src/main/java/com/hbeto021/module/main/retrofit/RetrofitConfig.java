package com.hbeto021.module.main.retrofit;

import com.hbeto021.module.main.gitRepositories.service.GitRepositoriesService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;
    private final String BASE_URL = "https://api.github.com/";

    public RetrofitConfig(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public GitRepositoriesService getGitRepositoriesService(){
        return this.retrofit.create(GitRepositoriesService.class);
    }

}
