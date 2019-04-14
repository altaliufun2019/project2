package com.example.project2.Managers;

public class MessageController {
    private static final MessageController ourInstance = new MessageController();

    public static MessageController getInstance() {
        return ourInstance;
    }

    private MessageController() {
    }
}
