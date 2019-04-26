package com.example.project2.Database

import com.orm.SugarRecord

class CommentDb(var postId: Int = 0, var id: Int = 0, var name: String = "", var email: String = "", var body: String = ""): (SugarRecord<CommentDb>)() {
}