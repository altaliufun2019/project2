package com.example.project2.Managers

import android.os.Handler
import android.os.Looper
import com.example.project2.Database.*
import com.example.project2.MessageCenter.DispatchQueue
import com.example.project2.MessageCenter.NotificationCenter
import com.orm.query.Condition
import com.orm.query.Select
import java.lang.Exception
import android.provider.ContactsContract.CommonDataKinds.Note
import com.orm.SugarRecord.findWithQuery



object StorageManager {
    private val mQueue: DispatchQueue = DispatchQueue("Storage")

    fun save(posts: List<Post>){
        PostDb.deleteAll(PostDb::class.java)
        mQueue.post(Runnable {
            for (post in posts){
                val postDb: PostDb = PostDb(post.userId, post.id,
                        post.title, post.body)
                try {
                    postDb.save()
                }catch (e: Exception) {
                    println("problem in saving posts in database")
                }
            }
            println("successfully added posts to database")
        })
    }

    fun save(comments: List<Comment>, postId: Int) {
        mQueue.post(Runnable {
            for (comment in comments) {
                val last_comments: MutableList<CommentDb>? = Select.from(CommentDb::class.java).where(Condition.prop("commentid").eq(comment.id)).list()
                for (c in last_comments!!)
                    c.delete()
                val commentDb: CommentDb = CommentDb(comment.postid, comment.id,
                        comment.name, comment.email, comment.body)
                try {
                    commentDb.save()
                }catch (e: Exception){
                    println("problem in saving comments in database")
                }
            }
            println("successfully added comments to database")
        })
    }

    fun load(postId: Int) {
        mQueue.post(Runnable {
            val cmnts = mutableListOf<Comment>()
            try {
                val comments = Select.from(CommentDb::class.java).where(Condition.prop("post_Id").eq(postId)).list()
                comments.forEach { it -> cmnts.add(Comment(it.postId, it.commentid, it.name, it.email, it.body)) }
            }catch (e: Exception){
                println("could not load comments from database")
            }
            (Handler(Looper.getMainLooper())).post {
                NotificationCenter.comments_loaded(cmnts)
            }
        })
    }

    fun load_posts() {
        mQueue.post(Runnable {
            val psts = mutableListOf<Post>()
            try {
                val posts = Select.from(PostDb::class.java).where().list()
                posts.forEach { it -> psts.add(Post(it.userid, it.postid, it.title, it.body)) }
            }catch(e: Exception){
                println("could not load posts from database")
            }
            (Handler(Looper.getMainLooper())).post {
                NotificationCenter.posts_loaded(psts)
            }
        })
    }
}