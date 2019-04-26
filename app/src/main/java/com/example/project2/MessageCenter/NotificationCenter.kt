package com.example.project2.MessageCenter

import android.util.SparseArray
import com.example.project2.Constants.Constants
import java.util.ArrayList


object NotificationCenter{
    private val observers = SparseArray<MutableList<Any>>()
    private val allowedTasks = mutableListOf<Int>()
    val DATA_LOADING = Constants.Tasks.FETCH_DATA

    init {
        allowedTasks.add(Constants.Tasks.FETCH_DATA)
    }

    interface NotificationTarget {
        fun notified(taskID: Int)
    }

    fun addTask(taskID: Int): Boolean? {
        allowedTasks.add(taskID)
        println("success")
        return false
    }

    fun data_loaded() {
        val taskID = NotificationCenter.DATA_LOADING
        for (observer in observers.get(taskID)) {
            (observer as NotificationTarget).notified(taskID)
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
