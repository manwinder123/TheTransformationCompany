package com.manwinder.thetransformationcompany

class Transformer (
    val transformerType: TransformerType,
    val name : String,
    val strength : Int,
    val intelligence : Int,
    val speed : Int,
    val endurance : Int,
    val rank : Int,
    val courage : Int,
    val firePower : Int,
    val skill : Int
)

enum class TransformerType{
    AUTOBOT, DECEPTICON
}