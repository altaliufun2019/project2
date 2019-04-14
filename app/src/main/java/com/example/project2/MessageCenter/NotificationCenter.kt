package com.example.project2.MessageCenter

object NotificationCenter {
    interface NotificationTarget{
        fun notified(taskId: Int)
    }
}