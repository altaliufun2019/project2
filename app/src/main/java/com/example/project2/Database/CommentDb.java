package com.example.project2.Database;

import com.orm.SugarRecord;

public class CommentDb extends SugarRecord<CommentDb> {
    int postId;
    int id;
    String name;
    String email;
    String body;

    public CommentDb(){

    }

    public CommentDb(int postId, int id, String name, String email, String body) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
