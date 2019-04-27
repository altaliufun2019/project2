package com.example.project2.UIClasses.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project2.Managers.MessageController
import com.example.project2.Managers.Post
import com.example.project2.R
import com.example.project_1.UIComponents.DataAdapter.DataNumberAdapter
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.RecyclerView
import com.example.project2.MainActivity


class ListFragment() : Fragment() {
    private lateinit var mAdapter: DataNumberAdapter

    fun changeData(posts: MutableList<Post>){
        mAdapter.mData = posts
        mAdapter.notifyDataSetChanged()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//
//        val rvData = inflater.inflate(R.layout.items_grid_view, container, false) as RecyclerView
//
////        val rvData = view!!.findViewById(R.id.numList) as RecyclerView
//        MessageController.getInstance().getPosts()
//        val dataNumbers = mutableListOf<Post>(Post(1,1,"sdf", "asdfkj"))
//        mAdapter = DataNumberAdapter(dataNumbers)
//        rvData.setAdapter(mAdapter)
//        rvData.layoutManager = LinearLayoutManager(MainActivity.getContext())
////        rvData.setItemAnimator(DefaultItemAnimator())
//
////        gridView.adapter = PostAdapter(this.context!!)
//        return rvData
    return null
    }

}