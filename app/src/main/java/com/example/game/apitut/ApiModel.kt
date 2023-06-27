package com.example.game.apitut

data class ApiModel(
    val success: Boolean,
    val data: MemeData
)

data class MemeData(
    val memes: List<MemeItem>
)

data class MemeItem(
    val id: String,
    val name: String,
    val url: String,
    val width: Int,
    val height: Int,
    val box_count: Int
)