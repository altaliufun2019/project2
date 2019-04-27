package com.example.project2.Database

import android.provider.BaseColumns

object CommentReader {
    object Comment: BaseColumns{
        const val TABLE_NAME = "comment"
        const val COLUMN_POST_ID = "postId"
        const val COLUMN_ID = "ID"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_BODY = "body"
    }
}