package com.example.test.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import com.example.project2.R
import com.example.project2.UIClasses.PostAdapter

class ItemsFragment1 : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val gridView = inflater.inflate(R.layout.items_grid_view1, container, false) as GridView
        gridView.adapter = PostAdapter(this.context!!)
        return gridView

    }

}