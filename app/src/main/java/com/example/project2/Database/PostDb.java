package com.example.project2.Database;

import com.orm.SugarRecord;

public class PostDb extends SugarRecord<PostDb> {
    public int userid;
    public int id;
    public String title;
    public String body;

    public PostDb() {
    }

    public PostDb(int userid, int id, String title, String body) {
        this.userid = userid;
        this.id = id;
        this.title = title;
        this.body = body;
    }
}
