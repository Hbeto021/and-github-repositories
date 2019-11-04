package com.hbeto021.module.main.gitRepositories.view;

import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.hbeto021.module.main.R;
import com.hbeto021.module.main.domain.RepositoryOwner;
import com.hbeto021.module.main.gitRepositories.domain.GitRepositoriesModel;
import com.hbeto021.module.main.gitRepositories.interactor.GitRepositoriesInteractor;
import com.hbeto021.module.main.gitRepositories.interactor.GitRepositoriesInteractorImpl;
import com.hbeto021.module.main.gitRepositories.presenter.GitRepositoriesPresenterImpl;


import java.util.ArrayList;
import java.util.List;

public class GitRepositoriesViewActivity extends AppCompatActivity implements GitRepositoriesView {


    private ProgressBar progressBar;
    private Context context = this;
    private LinearLayout layoutGitRepositoriesSearch;
    private RecyclerView recyclerViewGitRepositoriesActivity;
    private MenuItem menuItemSearch;
    private ImageView imageViewOwnerAvatar;
    private TextView textViewOwnerUser, textViewOwnerName, textViewOwnerFollowers, textViewOwnerFollowing,
    textViewOwnerBio, textViewOwnerReposNumber;
    private ConstraintLayout userInfoLayout;

    private List<GitRepositoriesModel> gitRepositories = new ArrayList<>();
    private GitRepositoriesAdapter adapter;
    private GitRepositoriesInteractor gitRepositoriesInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_git_repositories);

        initComponents();
        gitRepositoriesInteractor =
                new GitRepositoriesInteractorImpl(new GitRepositoriesPresenterImpl(this));

    }

    private void initComponents() {
        setSupportActionBar((Toolbar) findViewById(R.id.main_toolbar));

        ImageView imageViewGitRepositoriesSearch = findViewById(R.id.imageview_git_repositories_search);
        imageViewGitRepositoriesSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSearchDialog();
            }
        });

        progressBar = findViewById(R.id.progressbar_git_repositories_activity);
        layoutGitRepositoriesSearch = findViewById(R.id.layout_git_repositories_search);

        //user info
        userInfoLayout = findViewById(R.id.user_info_layout);
        imageViewOwnerAvatar = findViewById(R.id.imageview_git_repositories_owner_avatar);
        textViewOwnerUser = findViewById(R.id.texviewview_git_repositories_owner_login);
        textViewOwnerName = findViewById(R.id.textview_git_repositories_owner_name);
        textViewOwnerFollowers = findViewById(R.id.textview_git_repositories_owner_followers);
        textViewOwnerFollowing = findViewById(R.id.textview_git_repositories_owner_following);
        textViewOwnerBio = findViewById(R.id.textview_git_repositories_owner_bio);
        textViewOwnerReposNumber = findViewById(R.id.textview_git_repositories_owner_respos_number);

        initRecyclerView();

    }

    private void initRecyclerView(){
        recyclerViewGitRepositoriesActivity = findViewById(R.id.recyclerview_git_repositories_activity);
        recyclerViewGitRepositoriesActivity.setLayoutManager(new LinearLayoutManager(this));
        adapter = new GitRepositoriesAdapter();
        recyclerViewGitRepositoriesActivity.setAdapter(adapter);
    }

    private void showSearchDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = View.inflate(context, R.layout.dialog_search_view, null);
        builder.setView(view);

        final AlertDialog dialog;
        dialog = builder.create();

        final EditText editTextDialogSearchView = view.findViewById(R.id.dialog_search_view_edittext_github_user);

        TextView tvCancel = view.findViewById(R.id.dialog_search_view_textview_cancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        TextView tvSearch = view.findViewById(R.id.dialog_search_view_texview_search);
        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchListener(editTextDialogSearchView.getText().toString());
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void searchListener(String user) {
        gitRepositoriesInteractor.start(user);
        gitRepositories.clear();
        adapter.clearList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        menuItemSearch = menu.findItem(R.id.item_search);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_search :
                showSearchDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        layoutGitRepositoriesSearch.setVisibility(View.GONE);
        userInfoLayout.setVisibility(View.GONE);
        recyclerViewGitRepositoriesActivity.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage(String message) {
        layoutGitRepositoriesSearch.setVisibility(View.VISIBLE);
        menuItemSearch.setVisible(false);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGitRepositories(List<GitRepositoriesModel> gitRepositories) {
        this.gitRepositories.clear();
        this.gitRepositories.addAll(gitRepositories);
        menuItemSearch.setVisible(true);
        recyclerViewGitRepositoriesActivity.setVisibility(View.VISIBLE);
        adapter.setGitRepositoriesModelList(this.gitRepositories);
    }

    @Override
    public void showUserInfo(RepositoryOwner repositoryOwner) {
        //Glide
        Glide.with(context).load(repositoryOwner.getOwnerAvatar()).into(imageViewOwnerAvatar);
        textViewOwnerReposNumber.setText(String.valueOf(repositoryOwner.getReposNumber()));
        textViewOwnerUser.setText(repositoryOwner.getLogin());
        textViewOwnerName.setVisibility(View.VISIBLE);
        textViewOwnerName.setText(repositoryOwner.getName());
        textViewOwnerFollowers.setText(String.valueOf(repositoryOwner.getFollowers()));
        textViewOwnerFollowing.setText(String.valueOf(repositoryOwner.getFollowing()));
        textViewOwnerBio.setText(repositoryOwner.getBio());
        userInfoLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideOwnerName() {
        textViewOwnerName.setVisibility(View.GONE);
    }

    @Override
    public void clearData() {
        userInfoLayout.setVisibility(View.GONE);
        recyclerViewGitRepositoriesActivity.setVisibility(View.GONE);
        textViewOwnerReposNumber.setText("");
        textViewOwnerUser.setText("");
        textViewOwnerName.setText("");
        textViewOwnerFollowers.setText("");
        textViewOwnerFollowing.setText("");
        textViewOwnerBio.setText("");
        adapter.clearList();
    }
}
