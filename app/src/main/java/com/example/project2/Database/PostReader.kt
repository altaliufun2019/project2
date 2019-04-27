package com.example.project2.Database

import android.provider.BaseColumns

object PostReader {
    object Post: BaseColumns{
        const val TABLE_NAME = "post"
        const val COLUMN_USER_ID = "userId"
        const val COLUMN_ID = "ID"
        const val COLUMN_TITLE = "title"
        const val COLUMN_BODY = "body"
    }
}