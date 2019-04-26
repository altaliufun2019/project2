package com.example.project2.Database

import com.orm.SugarRecord

class PostDb(var userId: Int = 0, var id: Int = 0, var title: String = "", var body: String = ""): SugarRecord<PostDb>() {
}