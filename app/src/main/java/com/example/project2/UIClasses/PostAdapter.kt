package com.example.project2.UIClasses

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.project2.Managers.ConnectionManager
import com.example.project2.Managers.Post
import com.example.project2.R

class PostAdapter(private val context: Context) : BaseAdapter() {

    private val connectionManager: ConnectionManager = ConnectionManager.getInstance()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val postViewHolder: PostViewHolder
        var view: View? = convertView
        if (convertView == null) {
            view = View.inflate(context, R.layout.list_item, null)
            postViewHolder = PostViewHolder(
                    view.findViewById<TextView>(R.id.list_item_title),
                    view.findViewById<TextView>(R.id.list_item_body),
                    0
                    //TODO("get post user_id")

            )
            view.tag = postViewHolder
        } else {
            postViewHolder = convertView.tag as PostViewHolder

        }
        postViewHolder.title.text = "ewe"
        postViewHolder.body.text = "wewe"
        postViewHolder.userId = 12345

        return view!!

    }

    override fun getItem(position: Int): Any {

        return Post(0, 0, "aaa", "aaa")

    }

    override fun getItemId(position: Int): Long {

        return 1
    }

    override fun getCount(): Int {
        return 10
    }

}