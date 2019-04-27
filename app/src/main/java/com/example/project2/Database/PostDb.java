package com.example.project2.Database;

import com.orm.SugarRecord;

public class PostDb extends SugarRecord<PostDb> {
    public int userid;
    public int postid;
    public String title;
    public String body;

    public PostDb() {
        super();
    }

    public PostDb(int userid, int postid, String title, String body) {
        super();
        this.userid = userid;
        this.postid = postid;
        this.title = title;
        this.body = body;
    }
}
