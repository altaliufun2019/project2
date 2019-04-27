package com.example.project_1.UIComponents.DataAdapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.project2.Managers.Comment
import com.example.project2.Managers.MessageController
import com.example.project2.Managers.Post
import com.example.project2.R

class CommentAdapter(var mData: List<Comment>) : RecyclerView.Adapter<CommentAdapter.ViewHolder>() {
    class ViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {
        var comment_name: TextView
        var comment_body: TextView
        var post_id: Int = 0
        var image: ImageView

        init {
            comment_name = view.findViewById(R.id.comment_name)
            comment_body = view.findViewById(R.id.comment_body)
            image = view.findViewById(R.id.comment_image_holder)
            view.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            var pos = layoutPosition
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater: LayoutInflater = LayoutInflater.from(context)

        val dataView: View = inflater.inflate(R.layout.comment_layout, parent, false)
        return ViewHolder(dataView)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data: Comment = mData[position]
        viewHolder.comment_name.text = data.name
        viewHolder.comment_body.text = data.body
        viewHolder.post_id = data.postid
    }

    override fun getItemCount(): Int {
        return mData.size
    }
}