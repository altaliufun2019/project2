package com.example.project2.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class PostReaderHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME,
        null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(POST_CREATE_ENTRIES)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(POST_DELETE_ENTRIES)
        onCreate(db)
    }

    companion object {
        const val POST_CREATE_ENTRIES = "CREATE TABLE ${PostReader.Post.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${PostReader.Post.COLUMN_ID} INTEGER," +
                "${PostReader.Post.COLUMN_USER_ID} INTEGER," +
                "${PostReader.Post.COLUMN_TITLE} TEXT," +
                "${PostReader.Post.COLUMN_BODY} TEXT)"
        const val POST_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${PostReader.Post.TABLE_NAME}"
        const val DATABASE_NAME = "database"
        const val DATABASE_VERSION = 1
    }
}