package com.hbeto021.module.main.gitRepositories.worker;

import android.util.Log;
import com.hbeto021.module.main.retrofit.RetrofitConfig;
import com.hbeto021.module.main.domain.RepositoryOwner;
import com.hbeto021.module.main.gitRepositories.domain.GitRepositoriesModel;
import com.hbeto021.module.main.gitRepositories.interactor.GitRepositoriesInteractor;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GitRepositoriesWorkerImpl implements GitRepositoriesWorker {


    @Override
    public void getGitRepositories(String user, final GitRepositoriesInteractor gitRepositoriesInteractor) {

        Call<List<GitRepositoriesModel>> call = new RetrofitConfig().getGitRepositoriesService()
                .getGitRepositoriesModel(user);

        call.enqueue(new Callback<List<GitRepositoriesModel>>() {
            @Override
            public void onResponse(Call<List<GitRepositoriesModel>> call, Response<List<GitRepositoriesModel>> response) {

                if(response.isSuccessful()){
                    List<GitRepositoriesModel> gitRepositoriesModelList = response.body();
                    gitRepositoriesInteractor.onSuccess(gitRepositoriesModelList);
                } else {
                    gitRepositoriesInteractor.onFailure();
                }

            }

            @Override
            public void onFailure(Call<List<GitRepositoriesModel>> call, Throwable t) {
                Log.e(GitRepositoriesWorkerImpl.class.getName(), "retrofitError: " + t.getMessage() );
                gitRepositoriesInteractor.onFailure();
            }
        });
    }

    @Override
    public void getGitUserInfo(String user, final GitRepositoriesInteractor gitRepositoriesInteractor) {

        Call<RepositoryOwner> call = new RetrofitConfig().getGitRepositoriesService()
                .getGitUserInfo(user);

        call.enqueue(new Callback<RepositoryOwner>() {
            @Override
            public void onResponse(Call<RepositoryOwner> call, Response<RepositoryOwner> response) {

                if(response.isSuccessful()){
                    RepositoryOwner repositoryOwner = response.body();
                    gitRepositoriesInteractor.onSuccessUserInfo(repositoryOwner);
                } else {
                    gitRepositoriesInteractor.onFailureUserInfo();
                }

            }

            @Override
            public void onFailure(Call<RepositoryOwner> call, Throwable t) {
                Log.e(GitRepositoriesWorkerImpl.class.getName(), "retrofitError: " + t.getMessage() );
                gitRepositoriesInteractor.onFailureUserInfo();
            }
        });
    }
}
