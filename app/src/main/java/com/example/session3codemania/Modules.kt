package com.example.session3codemania

data class TagsData(
    val id:Int,
    val name:String
)
data class CoursesData(
    val cover:String,
    val id: Int,
    val price:Int,
    val tags: List<Int>,
    val title:String
)