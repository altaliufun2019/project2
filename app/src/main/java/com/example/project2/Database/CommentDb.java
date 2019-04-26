package com.example.project2.Database;

import com.orm.SugarRecord;

public class CommentDb extends SugarRecord<CommentDb> {
    private int postId;
    private int ID;
    private String name;
    private String email;
    private String body;

    public CommentDb(){

    }

    public CommentDb(int postId, int id, String name, String email, String body) {
        this.postId = postId;
        this.ID = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
