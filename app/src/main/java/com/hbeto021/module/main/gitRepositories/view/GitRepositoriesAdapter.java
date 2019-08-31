package com.hbeto021.module.main.gitRepositories.view;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hbeto021.module.main.R;
import com.hbeto021.module.main.gitRepositories.domain.GitRepositoriesModel;
import java.util.ArrayList;
import java.util.List;

public class GitRepositoriesAdapter extends RecyclerView.Adapter<GitRepositoriesViewHolder> {

    private List<GitRepositoriesModel> gitRepositoriesModelList = new ArrayList<>();

    @NonNull
    @Override
    public GitRepositoriesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.git_repositories_item, viewGroup, false);
        return new GitRepositoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitRepositoriesViewHolder gitRepositorieViewHolder, int position) {
        gitRepositorieViewHolder.textViewGitRepositoryName.setText(gitRepositoriesModelList.get(position).getRepositoryName());
        gitRepositorieViewHolder.textViewGitRepositoryFullName.setText(gitRepositoriesModelList.get(position).getFullRepositoryName());
        gitRepositorieViewHolder.textViewGitRepositoryOwnerUser.setText(gitRepositoriesModelList.get(position).getRepositoryOwner().getLogin());
        gitRepositorieViewHolder.textViewGitRepositoryStars.setText(String.valueOf(gitRepositoriesModelList.get(position).getStars()));

        if (gitRepositoriesModelList.get(position).getLanguage() != null && !gitRepositoriesModelList.get(position).getLanguage().isEmpty()) {
            gitRepositorieViewHolder.textViewGitRepositoryLanguage.setVisibility(View.VISIBLE);
            gitRepositorieViewHolder.textViewGitRepositoryLanguage.setText(gitRepositoriesModelList.get(position).getLanguage());

        } else {
            gitRepositorieViewHolder.textViewGitRepositoryLanguage.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return gitRepositoriesModelList.size();
    }

    public void setGitRepositoriesModelList(List<GitRepositoriesModel> gitRepositoriesModelList) {
        this.gitRepositoriesModelList.clear();
        this.gitRepositoriesModelList.addAll(gitRepositoriesModelList);
        notifyDataSetChanged();
    }

    public void clearList() {
        this.gitRepositoriesModelList.clear();
        notifyDataSetChanged();
    }


}

