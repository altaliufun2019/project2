package com.example.project2.Managers

import android.os.Handler
import com.example.project2.Database.*
import com.example.project2.MessageCenter.DispatchQueue
import com.orm.query.Condition
import com.orm.query.Select

object StorageManager {
    private val mQueue: DispatchQueue = DispatchQueue("Storage")

    fun save(posts: List<Post>){
        mQueue.post(Runnable {
            for (post in posts){
                val postDb: PostDb = PostDb(post.userId, post.id,
                        post.title, post.body)
                postDb.save()
            }
            val posts =  Select.from(PostDb::class.java).list()
            posts;
        })
    }

    fun save(comments: List<Comment>, postId: Int) {
        mQueue.post(Runnable {
            for (comment in comments) {
                val last_comments: MutableList<CommentDb>? = Select.from(CommentDb::class.java).where(Condition.prop("id").eq(comment.id)).list()
                for (c in last_comments!!)
                    c.delete()
                val commentDb: CommentDb = CommentDb(comment.postid, comment.id,
                        comment.name, comment.email, comment.body)
                commentDb.save()
            }
        })
    }

    fun load(postId: Int) {
        mQueue.post(Runnable {
            val cmnts = mutableListOf<Comment>()
            val comments = Select.from(CommentDb::class.java).where(Condition.prop("postId").eq(postId)).list()
            comments.forEach { it -> cmnts.add(Comment(it.postId, it.getID(), it.name, it.email, it.body)) }
            (Handler()).post({

            })
        })
    }

    fun load_posts() {
        mQueue.post(Runnable {
            val psts = mutableListOf<Post>()
            val posts = PostDb.listAll(PostDb::class.java)
            posts.forEach { it -> psts.add(Post(it.userid, it.id, it.title, it.body)) }
            (Handler()).post({

            })
        })
    }
}