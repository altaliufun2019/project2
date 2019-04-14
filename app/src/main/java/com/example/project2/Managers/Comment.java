package com.example.project2.Managers;

public class Comment {
    private final int postid;
    private final int id;
    private final String name;
    private final String email;
    private final String body;

    public Comment(int postId, int id, String name, String email, String body) {
        this.postid = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public int getPostid() {
        return postid;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }
}
