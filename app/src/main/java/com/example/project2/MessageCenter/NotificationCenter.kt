package com.example.project2.MessageCenter

import android.util.SparseArray
import com.example.project2.Constants.Constants
import com.example.project2.Managers.Comment
import com.example.project2.Managers.Post


object NotificationCenter{
    private val observers = SparseArray<MutableList<Any>>()
    private val allowedTasks = mutableListOf<Int>()
    val POST_LOADING = Constants.Tasks.LOAD_POST
    val COMMENT_LOADING = Constants.Tasks.LOAD_COMMENT

    init {
        allowedTasks.add(Constants.Tasks.LOAD_POST)
        allowedTasks.add(Constants.Tasks.LOAD_COMMENT)
    }

    interface NotificationTarget {
        fun notifiedPosts(taskID: Int, posts: MutableList<Post>)
        fun notifiedComments(taskID: Int, comments: MutableList<Comment>)
    }

    fun addTask(taskID: Int): Boolean? {
        allowedTasks.add(taskID)
        println("success")
        return false
    }

    fun posts_loaded(posts: MutableList<Post>) {
        val taskID = NotificationCenter.POST_LOADING
        for (observer in observers.get(taskID)) {
            (observer as NotificationTarget).notifiedPosts(taskID, posts)
        }
    }

    fun comments_loaded(comments: MutableList<Comment>) {
        val taskID = NotificationCenter.COMMENT_LOADING
        for (observer in observers.get(taskID)) {
            (observer as NotificationTarget).notifiedComments(taskID, comments)
        }
    }

    fun register(observer: Any, taskID: Int): Boolean? {
        if (!allowedTasks.contains(taskID))
            return false
        var taskObservers: MutableList<Any>? = observers.get(taskID)
        if (taskObservers == null) {
            taskObservers = mutableListOf<Any>()
            observers.put(taskID, taskObservers)
        }
        if (taskObservers.contains(observer))
            return false
        taskObservers.add(observer)
        return true
    }

    fun unregister(observer: Any, taskID: Int): Boolean? {
        if (!allowedTasks.contains(taskID))
            return false
        val taskObservers = observers.get(taskID) ?: return false
        if (!taskObservers.contains(observer))
            return false
        taskObservers.remove(observer)
        return true
    }

}
