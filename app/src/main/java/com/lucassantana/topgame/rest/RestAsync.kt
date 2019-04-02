package com.lucassantana.topgame.rest

import android.os.AsyncTask
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Lucas Santana on 18/03/2019.
 */

open class RestAsync : AsyncTask<RestAsyncParams, Int, String>() {
    var callback : RestCallback<String?>? = null

    override fun doInBackground(vararg params: RestAsyncParams?): String {
        if(params == null || params.size == 0) { return "" }
        this.callback = params[0]!!.callBack
        var txt = params[0]!!.url!!.readText()
        return txt
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
        callback?.result(result)
    }

}