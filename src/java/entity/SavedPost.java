package entity;

import java.util.Date;

public class SavedPost {
    private int id;
    private int accountId;
    private int postId;
    private Date savedAt;

    public SavedPost() {}

    public SavedPost(int id, int accountId, int postId, Date savedAt) {
        this.id = id;
        this.accountId = accountId;
        this.postId = postId;
        this.savedAt = savedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public Date getSavedAt() {
        return savedAt;
    }

    public void setSavedAt(Date savedAt) {
        this.savedAt = savedAt;
    }
} 