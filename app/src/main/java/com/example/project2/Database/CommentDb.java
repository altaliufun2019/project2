package com.example.project2.Database;

import android.content.Context;

import com.orm.SugarRecord;

public class CommentDb extends SugarRecord<CommentDb> {
    public int postId;
    public int commentid;
    public String name;
    public String email;
    public String body;

    public CommentDb(){
        super();
    }

    public CommentDb( int postId, int commentid, String name, String email, String body) {
        super();
        this.postId = postId;
        this.commentid = commentid;
        this.name = name;
        this.email = email;
        this.body = body;
    }
}
