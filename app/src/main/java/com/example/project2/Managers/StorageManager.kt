package com.example.project2.Managers

import android.content.ContentValues
import com.example.project2.Database.CommentReader
import com.example.project2.Database.CommentReaderHelper
import com.example.project2.Database.PostReader
import com.example.project2.Database.PostReaderHelper
import com.example.project2.MainActivity
import com.example.project2.MessageCenter.DispatchQueue

object StorageManager {
    private val mQueue: DispatchQueue = DispatchQueue("Storage")

    fun save(posts: List<Post>){
        mQueue.post(Runnable {
            val dbHelper = PostReaderHelper(MainActivity.getContext())
            val db = dbHelper.writableDatabase

            posts.forEach {
                val values = ContentValues().apply {
                    put(PostReader.Post.COLUMN_ID, it.id)
                    put(PostReader.Post.COLUMN_USER_ID, it.userId)
                    put(PostReader.Post.COLUMN_TITLE, it.title)
                    put(PostReader.Post.COLUMN_BODY, it.body)
                }
                db?.insert(PostReader.Post.TABLE_NAME, null, values)
            }
        })
    }

    fun save(comments: List<Comment>, postId: Int){
        mQueue.post(Runnable {
            val dbHelper = CommentReaderHelper(MainActivity.getContext())
            val db = dbHelper.writableDatabase

            comments.forEach {
                val values = ContentValues().apply {
                    put(CommentReader.Comment.COLUMN_ID, it.id)
                    put(CommentReader.Comment.COLUMN_POST_ID, it.postid)
                    put(CommentReader.Comment.COLUMN_NAME, it.name)
                    put(CommentReader.Comment.COLUMN_EMAIL, it.email)
                    put(CommentReader.Comment.COLUMN_BODY, it.body)
                }
                db?.insert(CommentReader.Comment.TABLE_NAME, null, values)
            }
        })
    }

    fun load(postId: Int) {
        mQueue.post(Runnable {
            val dbHelper = CommentReaderHelper(MainActivity.getContext())
            val db = dbHelper.readableDatabase

            val projection = arrayOf(CommentReader.Comment.COLUMN_ID,
                    CommentReader.Comment.COLUMN_POST_ID, CommentReader.Comment.COLUMN_NAME,
                    CommentReader.Comment.COLUMN_EMAIL, CommentReader.Comment.COLUMN_BODY)

            val selection = "${CommentReader.Comment.COLUMN_POST_ID} = ?"
            val selectionArgs = arrayOf("$postId")

            val sortOrder = "${CommentReader.Comment.COLUMN_ID} DESC"

            val cursor = db.query(
                    CommentReader.Comment.TABLE_NAME,
                    projection,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    sortOrder
            )

            val comments = mutableListOf<Comment>()
            with(cursor){
                while (moveToNext()){
//                    var id = get
//                    var comment = Comment()
                }
            }
        })

    }
}