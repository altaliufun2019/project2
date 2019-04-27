package com.example.project2.Database;

import com.orm.SugarRecord;

public class CommentDb extends SugarRecord<CommentDb> {
    public int postId;
    public int ID;
    public String name;
    public String email;
    public String body;

    public CommentDb(){

    }

    public CommentDb(int postId, int id, String name, String email, String body) {
        this.postId = postId;
        this.ID = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
