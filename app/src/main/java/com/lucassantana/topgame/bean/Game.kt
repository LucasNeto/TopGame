package com.lucassantana.topgame.bean

data class Game (

        val name: String,
        val popularity: Long,
        val _id: Int,
        val giantbomb_id: Long,
        val box: Box,
        val logo: Logo,
        val localized_name: String,
        val locale: String
)