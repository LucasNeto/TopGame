package com.lucassantana.topgame.rest

import java.net.URL

/**
 * Created by Lucas Santana on 18/03/2019.
 */

interface RestService {
    fun <T> get(path: URL, callback: RestCallback<T> ) {}
}

