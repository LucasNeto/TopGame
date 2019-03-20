package com.lucassantana.topgame.rest

/**
 * Created by capgemini on 18/03/2019.
 */
interface RestCallback<T> {
    fun result(result: T)
}