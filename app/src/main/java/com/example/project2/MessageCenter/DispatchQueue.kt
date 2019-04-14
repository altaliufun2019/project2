package com.example.project2.MessageCenter

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import java.lang.Exception
import java.util.concurrent.CountDownLatch

class DispatchQueue(private val threadName: String): Thread() {
    private lateinit var handlerThread: HandlerThread
    private lateinit var mHandler: Handler
    private val latch = CountDownLatch(1)

    init {
        start()
    }

    fun post(msg: Runnable) {
        try{
            latch.await()
        } catch (e: Exception) {
            println("problem in latch")
        }
        mHandler.post(msg)
    }

    override fun run() {
        handlerThread = HandlerThread(threadName)
        handlerThread.start()
        mHandler = Handler(handlerThread.looper)
        latch.countDown()
    }
}