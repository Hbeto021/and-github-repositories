package com.hbeto021.module.main.gitRepositories.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import com.hbeto021.module.main.domain.RepositoryOwner;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GitRepositoriesModel implements Serializable {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String repositoryName;
    @JsonProperty("full_name")
    private String fullRepositoryName;
    @JsonProperty("owner")
    private RepositoryOwner repositoryOwner;
    @JsonProperty("language")
    private String language;
    @JsonProperty("stargazers_count")
    private int stars;

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getFullRepositoryName() {
        return fullRepositoryName;
    }

    public void setFullRepositoryName(String fullRepositoryName) {
        this.fullRepositoryName = fullRepositoryName;
    }

    public RepositoryOwner getRepositoryOwner() {
        return repositoryOwner;
    }

    public void setRepositoryOwner(RepositoryOwner repositoryOwner) {
        this.repositoryOwner = repositoryOwner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
