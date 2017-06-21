package com.sargent.mark.githubreposearch.model;

/**
 * Created by mark on 6/17/17.
 */

public class Repository {

    private String name;
    private String owner;
    private String url;

    public Repository(String name, String owner, String url){
        this.name = name;
        this.owner = owner;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
