package com.example.project2.Database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

class CommentReaderHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, 
        null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
    
    companion object{
        const val COMMENT_CREATE_ENTRIES = "CREATE TABLE ${CommentReader.Comment.TABLE_NAME} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY," +
                "${CommentReader.Comment.COLUMN_ID} INTEGER," +
                "${CommentReader.Comment.COLUMN_POST_ID} INTEGER," +
                "${CommentReader.Comment.COLUMN_NAME} TEXT," +
                "${CommentReader.Comment.COLUMN_EMAIL} EMAIL," +
                "${CommentReader.Comment.COLUMN_BODY} TEXT)"
        const val COMMENT_DELETE_ENTRIES = "DROP TABLE IF EXISTS ${CommentReader.Comment.TABLE_NAME}"

        const val DATABASE_NAME = "database"
        const val DATABASE_VERSION = 1
    }
}