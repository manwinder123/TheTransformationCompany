package com.manwinder.thetransformationcompany

class Transformer (
    val name : String,
    val strength : Int,
    val intelligence : Int,
    val speed : Int,
    val endurance : Int,
    val rank : Int,
    val courage : Int,
    val firePower : Int,
    val skill : Int
) {
    lateinit var transformerType: TransformerType
    var overallRating : Int = 0

    fun setOverallRating() {
        overallRating = strength + intelligence + speed + endurance + rank + courage + firePower + skill
    }
}

enum class TransformerType{
    AUTOBOT, DECEPTICON
}