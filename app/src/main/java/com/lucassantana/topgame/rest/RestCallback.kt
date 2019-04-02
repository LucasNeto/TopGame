package com.lucassantana.topgame.rest

/**
 * Created by Lucas Santana on 18/03/2019.
 */
interface RestCallback<T> {
    fun result(result: T)
}