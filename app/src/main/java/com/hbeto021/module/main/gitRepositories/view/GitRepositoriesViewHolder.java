package com.hbeto021.module.main.gitRepositories.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hbeto021.module.main.R;

public class GitRepositoriesViewHolder extends RecyclerView.ViewHolder {

    TextView textViewGitRepositoryName, textViewGitRepositoryFullName, textViewGitRepositoryOwnerUser,
            textViewGitRepositoryLanguage, textViewGitRepositoryStars;

    public GitRepositoriesViewHolder(@NonNull View itemView) {
        super(itemView);

        textViewGitRepositoryName = itemView.findViewById(R.id.textview_git_repository_name);
        textViewGitRepositoryFullName = itemView.findViewById(R.id.textview_git_repository_full_name);
        textViewGitRepositoryOwnerUser = itemView.findViewById(R.id.textview_git_repository_owner_user);
        textViewGitRepositoryLanguage = itemView.findViewById(R.id.textview_git_repository_language);
        textViewGitRepositoryStars = itemView.findViewById(R.id.textview_git_repository_stars);

    }
}
