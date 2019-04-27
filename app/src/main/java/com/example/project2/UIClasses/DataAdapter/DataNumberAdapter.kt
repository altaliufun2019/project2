package com.example.project_1.UIComponents.DataAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.project2.Managers.MessageController
import com.example.project2.Managers.Post
import com.example.project2.R

class DataNumberAdapter(var mData: List<Post>) : RecyclerView.Adapter<DataNumberAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        var post_title: TextView
        var post_body: TextView
        var post_id: Int = 0
        var image: ImageView

        init {
            post_title = view.findViewById(R.id.post_title)
            post_body = view.findViewById(R.id.post_body)
            image = view.findViewById(R.id.data_image_holder)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var pos = layoutPosition

            MessageController.getInstance().current_postID = post_id
            MessageController.getInstance().getComments(post_id)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater: LayoutInflater = LayoutInflater.from(context)

        val dataView: View = inflater.inflate(R.layout.post_layout, parent, false)
        return ViewHolder(dataView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data: Post = mData[position]
        viewHolder.post_title.text = data.title
        viewHolder.post_body.text = data.body
        viewHolder.post_id = data.id
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}