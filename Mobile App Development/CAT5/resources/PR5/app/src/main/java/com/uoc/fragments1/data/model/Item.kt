package com.uoc.fragments1.data.model


enum class ItemType(val v1:Int) {
    BASIC(1),
    REGULAR(2),
    ADVANCED(3),
}


data class Item(
    val type:ItemType,
    val id: Int,
    val question: String,
    val link: String?,
    val correct_answer: Long?,
    val answer1: String?,
    val answer2: String?,
    val answer3: String?,
    val answer4: String?,

)