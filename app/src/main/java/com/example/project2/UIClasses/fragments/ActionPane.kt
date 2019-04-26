package com.example.test.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.project2.R
class ActionPane : Fragment() {


    val newFrg = ItemsFragment()
    val newFrg1 = ItemsFragment1()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val transaction = (activity as AppCompatActivity).supportFragmentManager
        Log.i("SSS", "SSS")
        transaction.beginTransaction().add(R.id.cont, newFrg).commit()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.action_pane, container, false)
        view.findViewById<Button>(R.id.btn1).setOnClickListener {

            val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
            Log.i("SSS", "SSS")
            fragmentManager.beginTransaction().replace(R.id.cont, newFrg).commit()


        }
        view.findViewById<Button>(R.id.btn2).setOnClickListener {

            val fragmentManager = (activity as AppCompatActivity).supportFragmentManager
            Log.i("SSssS", "SSssS")
            fragmentManager.beginTransaction().replace(R.id.cont, newFrg1).commit()


        }

        return view

    }
}